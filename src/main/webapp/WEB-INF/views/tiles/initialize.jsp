<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootContext" value="${pageContext.request.contextPath}"/>
<div id="content">
	<h1 class="pretty">${actualSpecialization.name} szakirány</h1>
	<c:forEach var="i" begin="1" end="${numberOfSemesters}" step="1" varStatus="counter">
		<c:choose>
			<c:when test="${counter.count % 2 == 0}">
				<div id="semester${i}" class="semester even">
			</c:when>
			<c:otherwise>
				<div id="semester${i}" class="semester">
			</c:otherwise>
		</c:choose>
		<label>${semesterWithRomanNums[i-1]}. félév</label>
		<c:forEach var="subject" items="${subjects }">
			<c:if test="${subject.offeredSemester eq i}">
				<button id="${subject.id}"
					class="btn dependencyBtn dependencyBtn-standby"
					onclick="showDependencies(this.id,'${actualSpecialization.id}','${rootContext}')">${subject.name}</button>
			</c:if>
		</c:forEach>
</div>
</c:forEach>
</div>
