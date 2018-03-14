package grailsblog

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CommentsServiceSpec extends Specification {

    CommentsService commentsService
    SessionFactory sessionFactory

    private Long setupData() {
        Comments comment = new Comments(name: "Jack", comment: "Hello Bob").save(flush: true, failOnError: true)
        Comments comment1 = new Comments(name: "Jim", comment: "Hello Jack").save(flush: true, failOnError: true)
        comments.id
    }

    void "test get"() {
        setupData()

        expect:
        commentsService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Comments> commentsList = commentsService.list(max: 2, offset: 2)

        then:
        commentsList.size() == 2
    }

    void "test count"() {
        setupData()

        expect:
        commentsService.count() == 2
    }

    void "test delete"() {
        Long commentsId = setupData()

        expect:
        commentsService.count() == 2

        when:
        commentsService.delete(commentsId)
        sessionFactory.currentSession.flush()

        then:
        commentsService.count() == 1
    }

    void "test save"() {
        when:
        Comments comments = new Comments(name: "Saife", comment: "Hello Jim")
        commentsService.save(comments)

        then:
        comments.id != null
    }
}
