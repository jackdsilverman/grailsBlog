package grailsblog

class UrlMappings {

    static mappings = {

        "/$controller/$action?/$id?/$year?/$month?/$title?"{
            controller: "blogPost"
            action:"show"
        }

        "/blogPost"(controller: "blogPost", action: "index" )
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
