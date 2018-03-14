package grailsblog

import grails.gorm.services.Service

@Service(Comments)
interface CommentsService {

    Comments get(Serializable id)

    List<Comments> list(Map args)

    Long count()

    void delete(Serializable id)

    Comments save(Comments comments)

}