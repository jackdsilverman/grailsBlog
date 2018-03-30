<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<a href="#create-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                             default="Skip to content&hellip;"/></a>

<div id="create-user" class="content scaffold-create" role="main">
    <div class="row">
        <div class="blogPostListing col-md-10 col-md-offset-1">
            <div class="row"><h1 class="col-md-2"><g:message code="default.create.label" args="[entityName]"/></h1></div>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.user}">
                <ul class="errors" role="alert">
                    <g:eachError bean="${this.user}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                                error="${error}"/></li>
                    </g:eachError>
                </ul>
            </g:hasErrors>
            <div class="row">
                <div class="individualPost col-md-12">
                    <g:form resource="${this.user}" method="POST">
                        <g:hiddenField name="id" value="${user?.id}"/>
                        <div class="row" id="titleInput">
                            <label class="col-sm-2">Username:</label>
                            <span class="col-sm-10"><g:textField class="UsernameTextField" id="usernameBox"
                                                                 name="username"
                                                                 value="${user?.username}"/></span>
                        </div>

                        <div class="row" id="bodyInput">
                            <label class="col-sm-2">Password:</label>
                            <span class="col-sm-10"><g:textField class="passwordTextField" name="password"
                                                                 value="${user?.password}"/></span>
                        </div>

                        <div class="">
                            <g:submitButton name="create" class="save btn btn-default btn-success"
                                                  value="${message(code: 'default.button.create.label', default: 'Create')}"/>
                        </div>
                    </g:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
