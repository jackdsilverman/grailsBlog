<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'blogPost.label', default: 'BlogPost')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>

        <style>
            .portalLink{

            }
        </style>
    </head>
    <body>
        <a href="#list-blogPost" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-blogPost" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:each var="blogPost" in="${blogPostList}">

                <div class="portalLink">
                    <div>
                        <a href="/blogPost/show/${blogPost.blogID}">${blogPost.title}</a>
                    </div>
                        ${blogPost.body}
                    <div>

                    </div>
                </div>
            </g:each>

            <div class="pagination">
                <g:paginate total="${blogPostCount ?: 0}" />
            </div>
        </div>
    </body>
</html>