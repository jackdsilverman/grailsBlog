package grailsblog

import com.blog.security.Role
import com.blog.security.User
import com.blog.security.UserRole

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

        def adminRole = new Role(authority: 'ROLE_ADMIN').save()

        def testUser = new User(username: 'me', password: 'password').save()

        UserRole.create testUser, adminRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }

        assert User.count() == 1
        assert Role.count() == 1
        assert UserRole.count() == 1
    }
    def destroy = {
    }
}
