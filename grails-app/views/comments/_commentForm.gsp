<%@ page import="grailsblog.Comments" %>
<g:form controller="comments" action="save" name="commentForm">
    <g:hiddenField name="blogPostId" value="${blogPost.id}"/>
    <div class="row" id="commentInput">
        <label class="col-sm-2">Comment:</label>
        <span class="col-sm-10"><g:textArea class="commentTextArea" name="comment"/></span>
    </div>

    <div class="row" id="submitButton">
        <span class="col-md-1  "><g:submitButton class=" btn btn-primary" name="create" id="save"
                        value="${message(code: 'default.button.create.label', default: 'Create')}"/></span>
    </div>
</g:form>