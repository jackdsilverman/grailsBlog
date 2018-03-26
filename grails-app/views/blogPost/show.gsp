<%@ page import="grailsblog.BlogPost" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <title>grailsBlog | ${postInstance.title}</title>
</head>

<body>
<div class="content">
    <div class="row">

        <div class="blogPostListing col-md-10 col-md-offset-1">
            <div class="col-md-12"><g:formatDate date="${postInstance.dateCreated}" format="yyyy-MM-dd HH:mm:ss"/></div>
            <g:render template="viewPost" bean="${postInstance}" var="blogPost"/>
            <span class="col-md-2"><g:link class="updateLink btn btn-default btn-success"
                                           controller="blogPost" action="edit"
                                           id="${postInstance.id}">Update this post</g:link></span>

        </div>

    </div>
</div>
</body>
</html>
