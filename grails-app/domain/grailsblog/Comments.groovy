package grailsblog

class Comments {

    String name
    String comment
    Date dateCreated = new Date()
    BlogPost blogPost

    static mapping = {
        table 'Comments'
        sort dateCreated: "desc"
    }
    static belongsTo = BlogPost
    static constraints = {
        name(blank: false)
        comment(blank: false)
    }
}
