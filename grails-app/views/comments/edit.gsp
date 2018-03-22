<%@ page import="grailsblog.BlogPost" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <title>grailsBog | Edit Post</title>
</head>
<body>
<div class="content">
    <div id="edit-entry" class="content scaffold-edit" role="main">
        <h1 style="text-align:center;">Edit Post "${postInstance?.title}"</h1>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <g:hasErrors bean="${postInstance}">
            <ul class="errors" role="alert">
                <g:eachError bean="${postInstance}" var="error">
                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
        </g:hasErrors>
        <g:form method="post" >
            <g:hiddenField name="id" value="${postInstance?.id}" />
            <g:hiddenField name="version" value="${postInstance?.version}" />
            <fieldset class="form">
                <g:render template="commentForm"/>
            </fieldset>
            <fieldset class="buttons">
                <g:actionSubmit action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" class="btn primary large"/>
                %{--<g:actionSubmit action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" class="btn secondary large"/>--}%
            </fieldset>
        </g:form>
    </div>
</div>
</body>
</html>
