package grailsblog

import grails.testing.gorm.DomainUnitTest
import grails.testing.web.controllers.ControllerUnitTest
import grails.validation.ValidationException
import spock.lang.*

class BlogPostControllerSpec extends Specification implements ControllerUnitTest<BlogPostController>, DomainUnitTest<BlogPost> {

    def populateValidParams(params) {
        assert params != null

        params["title"] = "First post"
        params["body"] = "This is my first blog post"
        params["dateCreated"]=new Date(2018, 3, 27)
    }
    void setup(){
        controller.blogPostService = Mock(BlogPostService)
        populateValidParams(params)
        new BlogPost(params).save(flush: true)
    }

    void "Test the index action returns the correct model"() {
        when:"The index action is executed"
        controller.index(1)

        then:"The model is correct"
        !model.blogPostList
        model.blogPostCount == 1
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
        controller.create()

        then:"The model is correctly created"
        model.blogPost!= null
    }

    void "Test the save action with a null instance"() {
        when:"Save is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        controller.save(null)

        then:"A 404 error is returned"
        response.redirectedUrl == '/blogPost'
        flash.message != null
    }

    void "Test the save action correctly persists"() {
        given:
        controller.blogPostService = Mock(BlogPostService) {
            1 * save(_ as BlogPost)
        }

        when:"The save action is executed with a valid instance"
        response.reset()
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        populateValidParams(params)
        def blogPost = new BlogPost(params)
        blogPost.id = 1

        controller.save(blogPost)

        then:"A redirect is issued to the show action"
        response.redirectedUrl == '/blogPost/show/1/2018/3/First%20post'
        controller.flash.message != null
    }

    void "Test the save action with an invalid instance"() {
        given:
        controller.blogPostService = Mock(BlogPostService) {
            1 * save(_ as BlogPost) >> { BlogPost blogPost ->
                throw new ValidationException("Invalid", blogPost.errors)
            }
        }

        when:"The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        def blogPost = new BlogPost()
        controller.save(blogPost)

        then:"The create view is rendered again with the correct model"
        model.blogPost != null
        view == 'create'
    }

    void "Test the show action with a null id"() {
        given:
        controller.blogPostService = Mock(BlogPostService) {
            1 * get(null) >> null
        }

        when:"The show action is executed with a null domain"
        controller.show(null)

        then:"A 404 error is returned"
        response.status == 404
    }

    void "Test the show action with a valid id"() {
        given:
        controller.blogPostService = Mock(BlogPostService) {
            1 * get(2) >> new BlogPost()
        }

        when:"A domain instance is passed to the show action"
        controller.show(2)

        then:"A model is populated containing the domain instance"
        model.blogPost instanceof BlogPost
    }

    void "Test the edit action with a null id"() {
        given:
        controller.blogPostService = Mock(BlogPostService) {
            1 * get(null) >> null
        }

        when:"The show action is executed with a null domain"
        controller.edit(null)

        then:"A 404 error is returned"
        response.status == 404
    }

    void "Test the edit action with a valid id"() {
        given:
        controller.blogPostService = Mock(BlogPostService) {
            1 * get(2) >> new BlogPost()
        }

        when:"A domain instance is passed to the show action"
        controller.edit(2)

        then:"A model is populated containing the domain instance"
        model.blogPost instanceof BlogPost
    }


    void "Test the update action with a null instance"() {
        when:"Save is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(null)

        then:"A 404 error is returned"
        response.redirectedUrl == '/blogPost/index'
        flash.message != null
    }

    void "Test the update action correctly persists"() {
        given:
        controller.blogPostService = Mock(BlogPostService) {
            1 * save(_ as BlogPost)
        }

        when:"The save action is executed with a valid instance"
        response.reset()
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        populateValidParams(params)
        def blogPost = new BlogPost(params)
        blogPost.id = 1

        controller.update(blogPost)

        then:"A redirect is issued to the show action"
        response.redirectedUrl == '/blogPost/show/1'
        controller.flash.message != null
    }

    void "Test the update action with an invalid instance"() {
        given:
        controller.blogPostService = Mock(BlogPostService) {
            1 * save(_ as BlogPost) >> { BlogPost blogPost ->
                throw new ValidationException("Invalid instance", blogPost.errors)
            }
        }

        when:"The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(new BlogPost())

        then:"The edit view is rendered again with the correct model"
        model.blogPost != null
        view == 'edit'
    }

    void "Test the delete action with a null instance"() {
        when:"The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(null)

        then:"A 404 is returned"
        response.redirectedUrl == '/blogPost/index'
        flash.message != null
    }

    void "Test the delete action with an instance"() {
        given:
        controller.blogPostService = Mock(BlogPostService) {
            1 * delete(2)
        }

        when:"The domain instance is passed to the delete action"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(2)

        then:"The user is redirected to index"
        response.redirectedUrl == '/blogPost/index'
        flash.message != null
    }
}






