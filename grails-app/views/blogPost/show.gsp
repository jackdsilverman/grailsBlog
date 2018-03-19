<%@ page import="grailsblog.BlogPost"%>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <title>grailsBlog | ${postInstance.title}</title>
</head>
<body>
<div class="content">

        <div class="buttonWrapper">
            <g:link controller="blogPost" action="index">Home</g:link>
            <g:link class="btn primary pull-left" controller="blogPost" action="create">Add a new post</g:link>
            %{--<g:form controller="entry" action="delete" id="${postInstance.id}" class="pull-left">--}%
                %{--<g:submitButton class="btn primary pull-left" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" value="Delete this post" name="delete"></g:submitButton>--}%
            %{--</g:form>--}%
            <g:link class="btn primary" controller="blogPost" action="edit" id="${postInstance.id}">Update this post</g:link>
        </div>
        <hr style="width:98%;margin:0 auto;padding-top:15px;"/>

    <g:render template="viewPost" bean="${postInstance}" var="blogPost" />
</div>
</body>
</html>
