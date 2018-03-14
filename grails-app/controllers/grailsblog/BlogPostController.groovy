package grailsblog

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class BlogPostController {

    BlogPostService blogPostService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond blogPostService.list(params), model:[blogPostCount: blogPostService.count()]
    }

    def show(Long id) {
        respond blogPostService.get(id)
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
            respond blogPost.errors, view:'create'
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

    def edit(Long id) {
        respond blogPostService.get(id)
    }

    def update(BlogPost blogPost) {
        if (blogPost == null) {
            notFound()
            return
        }

        try {
            blogPostService.save(blogPost)
        } catch (ValidationException e) {
            respond blogPost.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'blogPost.label', default: 'BlogPost'), blogPost.id])
                redirect blogPost
            }
            '*'{ respond blogPost, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        blogPostService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'blogPost.label', default: 'BlogPost'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'blogPost.label', default: 'BlogPost'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
