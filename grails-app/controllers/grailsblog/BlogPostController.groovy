package grailsblog

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.NOT_FOUND

class BlogPostController {
    BlogPostService blogPostService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def blogPostList = BlogPost.createCriteria().list(params) {
            if (params.query) {
                ilike("title", "%${params.query}%")
            }
        }
        model: [blogPostList: blogPostList, blogPostCount: blogPostService.count()]
    }
    def show(Long id) {
        def postInstance = BlogPost.get(id)
        if (!postInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'blogPost.label', default: 'BlogPost'), params.id])
            redirect(action: "index")
            return
        }

        [postInstance: postInstance]
    }
    @Secured(['ROLE_ADMIN'])
    def create() {
       respond new BlogPost(params)

    }
    @Secured(['ROLE_ADMIN'])
    def save() {
        def blogPost = new BlogPost(params)
        if (blogPost == null) {
            notFound()
            return
        }
        if(!blogPost.save(flush:true)){
            respond(blogPost.errors, view: "create")
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'blogPost.label', default: 'BlogPost'), blogPost.id])
        redirect(action: "show", id: blogPost.id, params: [year: blogPost.dateCreated[Calendar.YEAR], month: blogPost.dateCreated[Calendar.MONTH] +1, title: blogPost.title])



    }
    @Secured(['ROLE_ADMIN'])
    def edit(Long id) {
        def postInstance = BlogPost.get(id)
        if (!postInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'blogpost.label', default: 'BlogPosts'), params.id])
            redirect(action: "index")
            return
        }
        [postInstance: postInstance]
    }
    @Secured(['ROLE_ADMIN'])
    def update(BlogPost postInstance) {

        if (!postInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'body.label', default: 'BlogPost'), params.id])
            redirect(action: "index")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (postInstance.version > version) {
                postInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'body.label', default: 'BlogPost')] as Object[],
                        "Another user has updated this Post while you were editing")
                render(view: "edit", model: [postInstance: postInstance])
                return
            }
        }

        postInstance.properties = params

        if (!postInstance.save(flush: true)) {
            render(view: "edit", model: [postInstance: postInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'body.label', default: 'BlogPost'), postInstance.id])
        redirect(action: "show", params: [id: postInstance.id ,year: postInstance.dateCreated[Calendar.YEAR], month: postInstance.dateCreated[Calendar.MONTH]+ 1, title: postInstance.title])
    }
    @Secured(['ROLE_ADMIN'])
    def delete(Long id) {
        BlogPost blogPost = BlogPost.get(id)
        if (!blogPost) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'entry.label', default: 'Entry'), params.id])
            redirect(action: "index")
            return
        }
        blogPost.delete(flush: true)
        redirect(action: "index")
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'blogPost.label', default: 'BlogPost'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
