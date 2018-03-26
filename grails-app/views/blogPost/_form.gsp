<%@ page import="grailsblog.BlogPost" %>
<div class="row">
    <div class="individualPost col-md-12">
        <g:hiddenField name="id" value="${postInstance?.id}"/>
        <g:hiddenField name="version" value="${postInstance?.version}"/>
        <div class="row" id="titleInput">
            <label class="col-sm-2">Title:</label>
            <span class="col-sm-10"><g:textField class="titleTextField" id="titleBox" name="title"
                                                 value="${postInstance?.title}"/></span>
        </div>

        <div class="row" id="bodyInput">
            <label class="col-sm-2">Body:</label>
            <span class="col-sm-10"><g:textArea class="postTextArea" name="body" value="${postInstance?.body}"/></span>
        </div>


    </div>
</div>