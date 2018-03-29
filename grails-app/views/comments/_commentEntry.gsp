<asset:javascript src="jquery-2.2.0.min.js"/>
<%@ page import="grailsblog.Comments" %>
<sec:ifAnyGranted roles="ROLE_COMMENTER, ROLE_ADMIN">
    <div class="col-md-3 displayComments">
        <div class="btn btn-default btn-success" id="displayCommentTextBox">Post A Comment</div>
    </div>

    <div class="col-md-12" id="commentSection" style="display: none">
        <g:render template="/comments/commentForm" bean="${blogPost}" var="blogPost"/>
    </div>
    <ul id="commentList"></ul>
</sec:ifAnyGranted>
<script type="text/javascript">
    $(function () {
        $("#displayCommentTextBox").click(function (evt) {
            $("#commentSection").toggle()
            $("#commentForm")[0].reset();
        });
        var updateCommentList = function () {
            $.ajax({
                data: "blogPostId=${blogPost.id}",
                url: '<g:createLink action="list" controller="comments"/>',
                success: function (data) {
                    $("#commentSection").slideUp();
                    $("#commentList").html("");
                    $.each(data, function () {
                        var username = "<div><span class='username col-md-2'>" + this.username + "</span>"
                        var dateCreated = "<span class='dataComment col-md-4'>" + this.dateCreated + "</span></div>"
                        var comment = "<span class='col-md-12'>" + this.comment + "</span>"
                        $("#commentList").append("<li class='commentItem col-md-12'>"+ username+ dateCreated+ comment+"</li>").slideDown()
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
