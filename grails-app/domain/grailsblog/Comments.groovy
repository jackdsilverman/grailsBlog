package grailsblog

class Comments {

    String name
    String body
    static mapping = {
        table 'Comments'
        id column: 'comment_id'
        version: false

    }
    static belongsTo = [blogPost : BlogPost]
    static constraints = {
        name(blank: false)
        body(blank: false)
    }
}
