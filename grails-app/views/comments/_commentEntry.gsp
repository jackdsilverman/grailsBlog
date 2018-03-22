<asset:javascript src="jquery-2.2.0.min.js"/>
<%@ page import="grailsblog.Comments" %>
<div class="col-md-1">
    <div class="btn btn-default btn-success" id="displayCommentTextBox">Comment</div>
</div>

<div class="col-md-12" id="commentSection">
    %{--<g:render template="form" bean="${blogPost}" var="blogPost"/>
    <g:submitButton name="create" class="save" id="save"/>--}%
     <g:form controller="comments" action="save" name="commentForm">
         <g:hiddenField name="blogPostId" value="${blogPost.id}"/>
         <div class="input">
             <g:textField name="name" />
             <g:textArea class="xxlarge" rows="12" name="comment"/>
         </div>
         <g:submitButton name="create" id="save" value="${message(code: 'default.button.create.label', default: 'Create')}" class="save btn primary large" style="margin-left:160px;"/>
     </g:form>
    <ul id="commentList"></ul>
</div>

<script type="text/javascript">
    $(function () {
        $("#displayCommentTextBox").click(function (evt) {
            $("#commentSection").toggle()
        });
        var updateCommentList = function () {
            $.ajax({
                data: "blogPostId=${blogPost.id}",
                url: '<g:createLink action="list" controller="comments"/>',
                success: function (data) {
                    $("#commentSection").slideUp();
                    $("#commentList").html("");
                    $.each(data, function () {
                        $("#commentList").append("<li class='commentItem'><div><span class='dataComment'>" + this.dateCreated + "</span></div><pre>" + this.comment + "</pre></li>").slideDown()
                    });
                }

            })
        };
        updateCommentList();

        $("#commentForm").submit(function (evt) {
            var $form = $(this);

            $.ajax({
                data: $form.serialize(),
                url: $form.attr("action"),
                type: $form.attr("method"),
                success: function (data) {
                    if (data.success) {
                        updateCommentList();
                    }
                },
            })
            return false;
        });
    });
</script>
