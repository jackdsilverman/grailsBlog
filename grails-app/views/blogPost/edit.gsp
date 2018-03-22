<%@ page import="grailsblog.BlogPost" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <title>Nuez | Edit Post</title>
</head>
<body>
<div class="content">
    <div id="edit-entry" class="content" role="main">

        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <g:hasErrors bean="${postInstance}">
            <ul class="errors" role="alert">
                <g:eachError bean="${postInstance}" var="error">
                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
        </g:hasErrors>*


        <div class="row">
            <div class="blogPostListing col-md-10 col-md-offset-1">
                <div class="row">
                    <h1 class="postHeader">Edit Post</h1>
                </div>
                <g:render template="form"/>

            </div>
        </div>



    </div>
</div>
</body>
</html>
