<%@ page import="grailsblog.Comments" %>
<g:form controller="comments" action="save" name="commentForm">
    <g:hiddenField name="blogPostId" value="${blogPost.id}"/>
    <div class="input">
        <g:textField name="name" />
        <g:textArea class="xxlarge" rows="12" name="comment"/>
    </div>

</g:form>