<%@ page import="grailsblog.BlogPost" %>
<div class="clearfix ${hasErrors(bean: postInstance, field: 'title', 'error')}">
  <label for="title"><g:message code="blogPost.title.label" default="Title" /></label>
  <div class="input">
    <input class="xlarge" name="title" value="${postInstance?.title}" size="254" type="text">
  </div>
</div>
<div class="clearfix ${hasErrors(bean: postInstance, field: 'body', 'error')}">
  <label for="entry"><g:message code="blogPost.body.label" default="Body" /></label>
  <div class="input">
    <g:textArea class="xxlarge" rows="12" name="body" value="${postInstance?.body}"/>
  </div>
</div>