package grailsblog

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class BlogPostServiceSpec extends Specification {

    BlogPostService blogPostService
    SessionFactory sessionFactory
    def blogPost
    def blogPost1
    private Long setupData() {
        blogPost = new BlogPost(title: "First post", body: "first post ever").save(flush:true, failOnError: true)
        blogPost1 = new BlogPost(title: "Second post", body: "Hello World").save(flush:true, failOnError: true)
        blogPost.id

    }

    void "test get"() {
        def blogPostId= setupData()
        expect:
        blogPostService.get(blogPostId) != null
    }

    void "test list"() {
        setupData()
        when:
        List<BlogPost> blogPostList = blogPostService.list(max: 2, offset: 2)

        then:
        blogPostList.size() == 2
    }

    void "test count"() {
setupData()
        expect:
        blogPostService.count() == 2
    }

    void "test delete"() {
        setupData()
        expect:
        blogPostService.count() == 2

        when:
        blogPostService.delete(blogPost.id)
        sessionFactory.currentSession.flush()

        then:
        blogPostService.count() == 1
    }

    void "test save"() {
        when:
        BlogPost blogPost = new BlogPost(title: "Third post", body: "first post ever")
        blogPostService.save(blogPost)

        then:
        blogPost.id != null
    }
}
