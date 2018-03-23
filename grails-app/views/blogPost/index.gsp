
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



<div id="list-blogPost" class="row content" role="main">
    <div class="blogPostListing col-md-10 col-md-offset-1">
        <div class="col-md-12 titleBox">
            <div class="row">
                <h1 class="titleHeader">Green Thumb</h1>
                <span class="blogDescription">A place to talk about all things gardening. Whether that be tips, questions, or just thoughts in general this is the place to come</span>
            </div>
        </div>
        <g:form action="index" method="GET">
            <div class="row">
                <g:textField class="col-2 pull-right" name="query" value="${params.query}" placeHolder="Search for post"/>
            </div>
        </g:form>
        <g:each var="blogPost" in="${blogPostList}">
            <div class="blogPost">
                <div class="row">
                    <span class="col-md-3 postDate"><g:formatDate date="${blogPost.dateCreated}" format="yyyy-MM-dd HH:mm:ss"/></span>
                    <div class="individualPost col-md-12">
                        <h2 class="col-md-10"><g:link class="blogTitle" controller="BlogPost" action="show"
                                                      id="${blogPost.id}"
                                                      params="[year: blogPost.dateCreated[Calendar.YEAR], month: blogPost.dateCreated[Calendar.MONTH] +1, title: blogPost.title]">${blogPost.title}</g:link></h2>

                        <g:if test="${flash.message}">
                            <div class="message" role="status">${flash.message}</div>
                        </g:if>


                        <span class="col-md-12">${blogPost.body}</span>
                    </div>
                </div>


            </div>
        </g:each>
        <div class="pagination">
            <g:paginate total="${blogPostCount}" prev="Previous" next="Next" maxsteps="5" max="5"/>
        </div>
    </div>
</div>

</body>
</html>
