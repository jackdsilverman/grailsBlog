package grailsblog

class UrlMappings {

    static mappings = {
        "/"(controller: 'BlogPost',view: "index", action: "index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
