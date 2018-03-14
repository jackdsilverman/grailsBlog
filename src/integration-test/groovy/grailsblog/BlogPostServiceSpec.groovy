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

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new BlogPost(...).save(flush: true, failOnError: true)
        //new BlogPost(...).save(flush: true, failOnError: true)
        //BlogPost blogPost = new BlogPost(...).save(flush: true, failOnError: true)
        //new BlogPost(...).save(flush: true, failOnError: true)
        //new BlogPost(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //blogPost.id
    }

    void "test get"() {
        setupData()

        expect:
        blogPostService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<BlogPost> blogPostList = blogPostService.list(max: 2, offset: 2)

        then:
        blogPostList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        blogPostService.count() == 5
    }

    void "test delete"() {
        Long blogPostId = setupData()

        expect:
        blogPostService.count() == 5

        when:
        blogPostService.delete(blogPostId)
        sessionFactory.currentSession.flush()

        then:
        blogPostService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        BlogPost blogPost = new BlogPost()
        blogPostService.save(blogPost)

        then:
        blogPost.id != null
    }
}
