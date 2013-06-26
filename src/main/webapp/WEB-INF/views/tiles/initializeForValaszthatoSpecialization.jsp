<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="content">
	<h1 class="pretty">${actualSpecialization.name} szakirány</h1>

	<div class="semester">
		<c:forEach var="subject" items="${subjects}" varStatus="loopVariable">

			<button id="${subject.id}" class="btn dependencyBtn dependencyBtn-standby"
				onclick="showDependencies(this.id,'${actualSpecialization.id}')">${subject.name}</button>
			<c:if test="${loopVariable.count % 9 == 0}">
	</div>
	<div class="semester">
		</c:if>
		</c:forEach>
	</div>
</div>