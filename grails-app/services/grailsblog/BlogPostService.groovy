package grailsblog

import grails.gorm.services.Service

@Service(BlogPost)
interface BlogPostService {

    BlogPost get(Serializable id)

    List<BlogPost> list(Map args)

    Long count()

    void delete(Serializable id)

    BlogPost save(BlogPost blogPost)

}