package grailsblog

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT


class CommentsController {

    CommentsService commentsService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    @Secured(['ROLE_ADMIN', 'ROLE_COMMENTER'])
    def list(){
        def comments = []
        BlogPost blogPost = BlogPost.get(params.blogPostId)
        if(blogPost){
            comments = Comments.findAllByBlogPost(blogPost)
        }
        render comments as JSON
    }
    @Secured(['ROLE_ADMIN', 'ROLE_COMMENTER'])
    def save() {
        def model =[:]

        BlogPost blogPost = BlogPost.get(params.blogPostId)
        def comment = new Comments(params)
        comment.blogPost = blogPost
        if (!comment.save(flush: true)) {
            model.success = false
            model.errors = comment.errors
        }else{
            model.success = true;
            model.message = message(code: 'default.created.message', args: [message(code: 'comment.label', default: 'Comment'), comment.id])
        }

        render model as JSON
    }
    @Secured(['ROLE_ADMIN', 'ROLE_COMMENTER'])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        commentsService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'comments.label', default: 'Comments'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'comments.label', default: 'Comments'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
