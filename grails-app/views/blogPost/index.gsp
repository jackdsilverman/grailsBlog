<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'blogPost.label', default: 'BlogPost')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-blogPost" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                               default="Skip to content&hellip;"/></a>

<div class="nav navigation" role="navigation">
    <ul>
        <li><span class="blogName">Green Thumb</span></li>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div><br>

<div id="list-blogPost" class="row content" role="main">
    <div class="blogPostList col-md-10 col-md-offset-1">
        <div class="col-md-12 titleBox">
            <div class="row">
                <h1 class="titleHeader">Green Thumb</h1>
                <span class="blogDescription">A place to talk about all things gardening. Whether that be tips, questions, or just thoughts in general this is the place to come</span>
            </div>
        </div>
        <g:each var="blogPost" in="${blogPostList}">
            <div class="row">
                <div class="individualPost col-md-12">
                    <h2 class="col-md-12"><g:link class="blogTitle" controller="BlogPost" action="show"
                                                  id="${blogPost.id}"
                                                  params="[title: blogPost.title]">${blogPost.title}</g:link></h2>
                    <g:if test="${flash.message}">
                        <div class="message" role="status">${flash.message}</div>
                    </g:if>


                    <span class="col-md-12">${blogPost.body}</span>
                </div>
            </div>
        </g:each>
        <div class="pagination">
            <g:paginate prev="Previous" next="Forward" total="${blogPostList}" controller="blogPost" action="index"/>
        </div>
    </div>
</div>

</body>
</html>