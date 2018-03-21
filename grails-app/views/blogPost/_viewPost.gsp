<div class="blogPost well">
	<div class="row">
		<div class="individualPost col-md-12">
			<h2 class="col-md-10"><g:link class="blogTitle" controller="BlogPost" action="show"
										  id="${blogPost.id}"
										  params="[year: blogPost.dateCreated[Calendar.YEAR], month: blogPost.dateCreated[Calendar.MONTH] +1, title: blogPost.title]">${blogPost.title}</g:link></h2>
			<span><g:formatDate date="${blogPost.dateCreated}" format="yyyy-MM-dd HH:mm:ss"/></span>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>


			<span class="col-md-12">${blogPost.body}</span>
		</div>
		<g:render template="/comments/commentEntry" bean="${blogPost}" var="blogPost"/>
	</div>
	<g:link controller="blogPost" action="show" id="${blogPost.id}" class="pull-right">Permalink</g:link>

</div>