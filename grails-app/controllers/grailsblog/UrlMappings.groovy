package grailsblog

class UrlMappings {

    static mappings = {

        "/$controller/$action?/$id?/$title?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: BlogPost, action: "index" )
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
