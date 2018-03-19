<div class="blogPost well">
	<h3>${blogPost.title}</h3>
	<p>
		%{--<g:formatDate date="1994-12-14" format="yyyy-MM-dd HH:mm:ss"/>--}%
	</p>
	<p>
		${blogPost.body}
	</p>
	<g:link controller="blogPost" action="show" id="${blogPost.id}" class="pull-right">Permalink</g:link>
	%{--<g:render template="/comment/commentEntry" bean="${blogPost}" var="blogPost"/>--}%
</div>