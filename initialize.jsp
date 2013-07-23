<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/basic.css">
<title>Ybl-Miklos Foiskola targyfüggőségei -
	${initDatasFromServer.actualSpecialization.name} szakirány</title>
</head>
<body>
	<div id="navbar" class="navbar">
		<ul>
			<li class="navbarElement"><select id="specializationSelector">
					<c:forEach var="specialization"
						items="${initDatasFromServer.specializations}">
						<c:choose>
							<c:when
								test="${specialization.id == initDatasFromServer.actualSpecialization.id}">
								<option value="${specialization.id}" selected="selected">${specialization.name}</option>
							</c:when>
							<c:otherwise>
								<option value="${specialization.id}">${specialization.name}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
			</select></li>
			<li class="navbarElement"><a href="#">Average calculator</a></li>
			<li class="navbarElement"><button
					class="dependencyBtn dependencyBtn-forward4">Előrefüggőség</button></li>
			<li class="navbarElement"><button
					class="dependencyBtn dependencyBtn-forward-4">Függőség</button></li>
			<li class="navbarElement"><button
					class="dependencyBtn dependencyBtn-actual">Aktuális tárgy</button></li>
			<li class="navbarElement"><button
					class="dependencyBtn dependencyBtn-onlyRegistration">Csak
					felvétel szükséges</button></li>
			<li class="navbarElement right"><a href="#">Contact</a></li>
		</ul>
	</div>
	<div id="content">
		<h1 class="pretty">${initDatasFromServer.actualSpecialization.name}
			szakirány</h1>
		<c:forEach var="i" begin="1"
			end="${initDatasFromServer.numberOfSemesters}" step="1">
			<div id="${i}.semester" class="semester">
				<span>${semesterWithRomanNums[i-1]}. felev</span>
				<c:forEach var="subject" items="${initDatasFromServer.subjects }">
					<c:if test="${subject.offeredSemester eq i}">
						<button class="dependencyBtn dependencyBtn-standby">${subject.name}</button>
					</c:if>
				</c:forEach>
			</div>
		</c:forEach>
	</div>
	<div id="thirdPart">
		<div id="description">
			<p>Click to a Subject to see the its dependencies, and
				description.</p>
		</div>
	</div>
</body>
</html>