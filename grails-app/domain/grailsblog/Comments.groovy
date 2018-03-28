package grailsblog

import com.blog.security.User

class Comments {

    String username
    String comment
    Date dateCreated = new Date()
    BlogPost blogPost

    static mapping = {
        table 'Comments'
        sort dateCreated: "desc"
    }
    static belongsTo = BlogPost
    static constraints = {
        comment(blank: false)
    }
}
