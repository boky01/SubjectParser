<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="content">
    <div class="page-header">
      <h1 class="pretty">${actualSpecialization.name} <small>szakirány</small></h1>
    </div>
	<div class="semester">
		<c:forEach var="subject" items="${subjects}" varStatus="loopVariable">

			<button id="${subject.id}" class="dependencyBtn dependencyBtn-standby"
				onclick="showDependencies(this.id,'${actualSpecialization.id}')">${subject.name}</button>
			<c:if test="${loopVariable.count % 9 == 0}">
	</div>
	<div class="semester">
		</c:if>
		</c:forEach>
	</div>
</div>