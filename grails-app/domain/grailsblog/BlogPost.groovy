package grailsblog


class BlogPost {
    String title
    String body
    Date dateCreated = new Date()
    Date lastUpdated = new Date()
    static mapping = {
        table 'blogPost'
        id column: 'blog_id'
        sort dateCreated: "desc"
    }

    static hasMany = [comments: Comments]
    static constraints = {
        title(blank:false)
        body(blank:false, maxSize:5000)
    }
}
