package grailsblog

import javax.xml.bind.ValidationException
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND

class BlogPostController {
    BlogPostService blogPostService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    @Secured(['ROLE_ADMIN'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def blogPostList = BlogPost.createCriteria().list(params) {
            if (params.query) {
                ilike("title", "%${params.query}%")
            }
        }
        [blogPostList: blogPostList, blogPostCount: blogPostService.count()]
    }
    @Secured(['ROLE_ADMIN'])
    def show() {
        def postInstance = BlogPost.get(params.id)
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
    def save(BlogPost blogPost) {
        if (blogPost == null) {
            notFound()
            return
        }

        try {
            blogPostService.save(blogPost)
        } catch (ValidationException e) {
            respond blogPost.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'blogPost.label', default: 'BlogPost'), blogPost.id])
                redirect(action: "show", id: blogPost.id, params: [year: blogPost.dateCreated[Calendar.YEAR], month: blogPost.dateCreated[Calendar.MONTH] +1, title: blogPost.title])
            }
            '*' { respond blogPost, [status: CREATED] }
        }

    }
    @Secured(['ROLE_ADMIN'])
    def edit() {
        def postInstance = BlogPost.get(params.id)
        if (!postInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'blogpost.label', default: 'BlogPosts'), params.id])
            redirect(action: "index")
            return
        }
        [postInstance: postInstance]
    }
    @Secured(['ROLE_ADMIN'])
    def update() {
        def postInstance = BlogPost.get(params.id)
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
        def blogPost = BlogPost.get(id)
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
