<%@ page import="grailsblog.BlogPost" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <title>Green Thumb | Edit Post</title>
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
                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                            error="${error}"/></li>
                </g:eachError>
            </ul>
        </g:hasErrors>*


        <div class="row">
            <div class="blogPostListing col-md-10 col-md-offset-1">
                <div class="row">
                    <h1 class="postHeader">Edit Post</h1>
                </div>
                <g:form method="post">
                    <g:render template="form"/>
                    <div class="row">
                        <g:actionSubmit action="save" class="btn btn-primary" name="create" id="save"
                                        value="${message(code: 'default.button.create.label', default: 'Create')}"/>
                        <g:actionSubmit action="delete" class="btn btn-danger"
                                        value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                                        formnovalidate=""
                                        onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
                    </div>
                </g:form>
            </div>
        </div>

    </div>
</div>
</body>
</html>
