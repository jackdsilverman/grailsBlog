<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'blogPost.label', default: 'BlogPost')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<a href="#create-blogPost" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                 default="Skip to content&hellip;"/></a>
<div id="create-blogPost" class="content" role="main">

    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.blogPost}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.blogPost}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>

    <div class="row">
        <div class="blogPostListing col-md-10 col-md-offset-1">
            <div class="row">
                <h1 class="postHeader">Create Blog Post</h1>
            </div>
            <g:render template="form"/>
        </div>
    </div>
</div>
</body>
</html>
