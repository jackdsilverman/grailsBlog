package grailsblog

import grails.testing.gorm.DomainUnitTest
import grails.testing.web.controllers.ControllerUnitTest
import grails.validation.ValidationException
import org.jboss.logging.BasicLogger
import spock.lang.Specification

class BlogPostControllerSpec extends Specification implements ControllerUnitTest<BlogPostController>, DomainUnitTest<BlogPost> {

    def populateValidParams(params) {
        assert params != null

        params["title"] = "First post"
        params["body"] = "This is my first blog post"
        params["dateCreated"]=new Date(2018, 3, 27)
    }
    void setup(){
        controller.blogPostService = Mock(BlogPostService)
    }

    def "Test the index action returns the correct model"() {
        given:
        List<BlogPost> samplePosts=[new BlogPost(title: "First Post", body: "Hello I am Jack").save()]
        controller.blogPostService = Stub(BlogPostService) {
            list(_) >> samplePosts
            count() >> samplePosts.size()
        }

        when:"The index action is executed"
        def model = controller.index()

        then:"The model is correct"
        model.blogPostList.size() == 1
        model.blogPostCount == 1
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
        controller.create()

        then:"The model is correctly created"
        model.blogPost!= null
    }

    void "Test the save action correctly persists"() {

        when:"The save action is executed with a valid instance"
        response.reset()
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        populateValidParams(params)
        def blogPost = new BlogPost(params)
        blogPost.id = 1

        controller.save()

        then:"A redirect is issued to the show action"
        response.redirectedUrl == '/blogPost/show/1/2018/3/First%20post'
        controller.flash.message != null
    }

    void "Test the save action with an invalid instance"() {
        when:"The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        populateValidParams(params)
        BlogPost blogPost = new BlogPost(null)
        controller.save()

        then:"The create view is rendered again with the correct model"
        view == '/blogPost/save.gsp'
    }

    void "Test the show action with a null id"() {
        when:"The show action is executed with a null domain"
        controller.show()

        then:"A 302 error is returned"
        response.status == 302
    }

    void "Test the show action with a valid id"() {
        when:"A domain instance is passed to the show action"
        populateValidParams(params)
        BlogPost blogPost = new BlogPost(params).save(flush:true)
        def model = controller.show(blogPost.id)

        then:"A model is populated containing the domain instance"
        model.postInstance != null
    }

    void "Test the edit action with a null id"() {

        when:"The show action is executed with a null domain"
        controller.edit(null)

        then:"A 302 is returned"
        response.status == 302
}

    void "Test the edit action with a valid id"() {
        when:"A domain instance is passed to the show action"
        populateValidParams(params)
        BlogPost blogPost = new BlogPost(params).save(flush:true)
        def model = controller.show(blogPost.id)

        then:"A model is populated containing the domain instance"
        model.postInstance instanceof BlogPost
    }


    void "Test the update action with a null instance"() {
        when:"Save is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'post'
        controller.update(null)

        then:"A 404 error is returned"
        response.redirectedUrl == '/blogPost'
        flash.message != null
    }

    void "Test the update action correctly persists"() {

        when:"The save action is executed with a valid instance"
        response.reset()
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'post'
        populateValidParams(params)
        BlogPost blogPost = new BlogPost(params)
        controller.update(blogPost)

        then:"A redirect is issued to the show action"
        response.redirectedUrl == '/blogPost/show/1/2018/3/First%20post'
        controller.flash.message != null
    }

    void "Test the update action with an invalid instance"() {

        when:"The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'post'
        BlogPost blogPost = new BlogPost()
        blogPost.title = null
        blogPost.body = "jaja"
        controller.update(blogPost)

        then:"The edit view is rendered again with the correct model"
        view == "/blogPost/edit"
    }

    void "Test the delete action with a null instance"() {
        when:"The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'post'
        controller.delete(null)

        then:"A 404 is returned"
        response.redirectedUrl == '/blogPost'
        flash.message != null
    }

    void "Test the delete action with an instance"() {

        when:"The domain instance is passed to the delete action"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'post'
        controller.delete(2)

        then:"The user is redirected to index"
        response.redirectedUrl == '/blogPost'
        flash.message != null
    }
}