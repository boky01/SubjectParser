<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="navbar" class="navbar">
	<ul>
		<li class="navbarElement"><select id="specializationSelector"
			onchange="showSubjects()">
				<c:forEach var="specialization" items="${specializations}">
					<c:choose>
						<c:when test="${specialization.id == actualSpecialization.id}">
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
				class="dependencyBtn dependencyBtn-forward0">Kiválaszott
				tárgy</button></li>
		<li class="navbarElement"><button
				class="dependencyBtn dependencyBtn-forward-99">Csak
				felvétel szükséges</button></li>
		<li class="navbarElement"><button
				class="dependencyBtn dependencyBtn-forward99">A tárgyhoz
				a kijelölt tárgy felvétele szükséges</button></li>
		<li class="navbarElement right"><a href="/contacts">Contacts</a></li>
	</ul>
</div>