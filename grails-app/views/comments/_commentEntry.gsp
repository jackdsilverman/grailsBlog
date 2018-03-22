<asset:javascript src="jquery-2.2.0.min.js"/>
<%@ page import="grailsblog.Comments" %>
<div class="col-md-3 displayComments">
    <div class="btn btn-default btn-success" id="displayCommentTextBox">Post A Comment</div>
</div>

<div class="col-md-12" id="commentSection" style="display: none">
    <g:render template="/comments/commentForm" bean="${blogPost}" var="blogPost"/>
</div>
<ul id="commentList"></ul>

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
                        $("#commentList").append("<li class='commentItem col-md-12 commentItem'>" +
                            "<div><span class='dataComment'>" + this.dateCreated + "</span></div>" +
                            "<span>" + this.comment + "</span></li>").slideDown()
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
