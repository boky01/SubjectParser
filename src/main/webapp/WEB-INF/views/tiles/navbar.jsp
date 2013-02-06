<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<c:url var="resources" value="/resources" />
<div id="navbar" class="navbar">
	<div class="navbar-inner">
		<ul class="nav">
			<li><a class="brand" href="/">FÜGGŐSÉGEK</a></li>
			<li><a class="dropdown-toggle" data-toggle="dropdown" href="#">Szakirányok
					<span class="caret"></span>
			</a>
				<ul class="dropdown-menu">
					<c:forEach var="specialization" items="${specializations}">
						<c:choose>
							<c:when test="${specialization.id == actualSpecialization.id}">
							</c:when>
							<c:otherwise>
								<li onclick="showSubjects('${specialization.id}')"><a
									href="#">${specialization.name}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul></li>
			<li><a href="#">Átlag számító</a></li>
			<li><button class="btn dependencyBtn dependencyBtn-forward4">Előrefüggőség</button></li>
			<li><button class="btn dependencyBtn dependencyBtn-forward-4">Függőség</button></li>
			<li><button class="btn dependencyBtn dependencyBtn-forward0">Kiválaszott
					tárgy</button></li>
			<li><button class="btn dependencyBtn dependencyBtn-forward-99">Csak
					felvétel szükséges</button></li>
			<li><button class="btn dependencyBtn dependencyBtn-forward99">A
					tárgyhoz a kijelölt tárgy felvétele szükséges</button></li>
		</ul>
		<ul class="nav pull-right">
			<li><a href="/contacts">Kapcsolat</a></li>
			<li><fb:like send="false" width="450" show_faces="true"
					action="recommend" href="/"></fb:like></li>
		</ul>
	</div>
</div>
