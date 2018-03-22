<%@ page import="grailsblog.BlogPost" %>
<div class="row">
    <div class="individualPost col-md-12">
        <g:form controller="blogPost" action="save" name="blogPostForm">
            <g:hiddenField name="id" value="${postInstance?.id}" />
            <g:hiddenField name="version" value="${postInstance?.version}" />
            <div class="row" id="titleInput">
                <label class="col-sm-2">Title:</label>
                <span class="col-sm-10"><g:textField class="titleTextField" id="titleBox" name="title" value="${postInstance?.title}"/></span>
            </div>

            <div class="row" id="bodyInput">
                <label class="col-sm-2">Body:</label>
                <span class="col-sm-10"><g:textArea class="postTextArea" name="body" value="${postInstance?.body}"/></span>
            </div>

            <div class="row" id="submitButton">
                <span class="col-sm-1  "><g:submitButton class=" btn btn-primary" name="create" id="save"
                                                         value="${message(code: 'default.button.create.label', default: 'Create')}"/></span>
            </div>
        </g:form>
    </div>
</div>