<div class="individualPost col-md-12">
    <div class="col-md-4"><h2 id="title">${blogPost.title}</h2></div>


    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <span class="col-md-12" id="body">${blogPost.body}</span>
    <g:render template="/comments/commentEntry" bean="${blogPost}" var="blogPost"/>
</div>
