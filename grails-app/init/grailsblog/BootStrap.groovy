package grailsblog

class BootStrap {

    def init = { servletContext ->
        new BlogPost(title: "First Post", body: "Hello I am Jack").save()
        new BlogPost(title: "Second Post", body: "Hello I am Jack").save()
        new BlogPost(title: "Third Post", body: "Hello I am Jack").save()
        new BlogPost(title: "Fourth Post", body: "Hello I am Jack").save()
        new BlogPost(title: "Fifth Post", body: "Hello I am Jack").save()
        new BlogPost(title: "Sixth Post", body: "Hello I am Jack").save()
        new BlogPost(title: "Seventh Post", body: "Hello I am Jack").save()
        new BlogPost(title: "Eighth Post", body: "Hello I am Jack").save()
        new BlogPost(title: "Ninth Post", body: "Hello I am Jack").save()
        new BlogPost(title: "Tenth Post", body: "Hello I am Jack").save()
    }
    def destroy = {
    }
}
