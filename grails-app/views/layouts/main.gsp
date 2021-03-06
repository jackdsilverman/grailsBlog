<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>

<body>
<div class="forceHeight">
    <div class="nav navigation" role="navigation">
        <ul>
            <li><a href="http://localhost:8080/blogPost" class="blogName">Green Thumb</a></li>
            <li><a class="home" href="${createLink(uri: '/blogPost')}"><g:message code="default.home.label"/></a></li>
            <li><g:link class="create" action="create">Create A New Post</g:link></li>
        </ul>
    </div><br>

    <g:layoutBody/>



    <div id="spinner" class="spinner" style="display:none;">
        <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>
    <asset:javascript src="application.js"/>

</div>
<div class="footer" role="contentinfo">
    <span class="col-md-4 col-md-offset-2">This blog was created by: Saife Akhter and Jack Silverman</span>
    <span class="trademarked col-md-2">&copy; 2018 <a href="http://localhost:8080/blogPost"
                                                      class="footerLinks">Green Thumb</a></span>
    <span>Feel free to check out the code on <a href="https://github.com/jackdsilverman/grailsBlog"
                                                class="footerLinks">GitHub</a></span>
</div>
</body>
</html>
