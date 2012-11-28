<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="content">
	<div class="page-header">
	  <h1 class="pretty">${actualSpecialization.name} <small>szakir�ny</small></h1>
	</div>
	<c:forEach var="i" begin="1" end="${numberOfSemesters}" step="1">
		<div id="semester${i}" class="semester">
			<label>${semesterWithRomanNums[i-1]}. f�l�v</label>
			<c:forEach var="subject" items="${subjects }">
				<c:if test="${subject.offeredSemester eq i}">
					<button id="${subject.id}" class="dependencyBtn dependencyBtn-standby"
						onclick="showDependencies(this.id,'${actualSpecialization.id}')">${subject.name}</button>
				</c:if>
			</c:forEach>
		</div>
	</c:forEach>
</div>