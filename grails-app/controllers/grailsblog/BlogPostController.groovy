package grailsblog

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

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
        [blogPostList: blogPostList, blogPostCount: blogPostService.count()]
    }

    def show() {
        def postInstance = BlogPost.get(params.id)
        if (!postInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'blogpost.label', default: 'BlogPost'), params.id])
            redirect(action: "index")
            return
        }

        [postInstance: postInstance]
    }

    def create() {
        respond new BlogPost(params)
    }

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
                redirect blogPost
            }
            '*' { respond blogPost, [status: CREATED] }
        }
    }

    def edit() {
        def postInstance = BlogPost.get(params.id)
        if (!postInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'blogpost.label', default: 'BlogPosts'), params.id])
            redirect(action: "index")
            return
        }
        [postInstance: postInstance]
    }

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
        redirect(action: "show", id: postInstance.id)
    }

    def delete() {
        def blogPost = BlogPost.get(params.id)
        if (!blogPost) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'entry.label', default: 'Entry'), params.id])
            redirect(action: "index")
            return
        }
        blogPost.delete(flush: true)
        redirect(action: "index")
//        try {
//
//            flash.message = message(code: 'default.deleted.message', args: [message(code: 'entry.label', default: 'Entry'), params.id])
//            redirect(action: "index")
//        }
//        catch (DataIntegrityViolationException e) {
//            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'entry.label', default: 'Entry'), params.id])
//            redirect(action: "show", id: params.id)
//        }

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
