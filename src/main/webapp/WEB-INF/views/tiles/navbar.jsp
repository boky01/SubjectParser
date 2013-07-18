<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<c:url var="resources" value="/resources" />
<div id="navigationbar" class="navbar" style="margin-bottom: 0px;">
	<div class="navbar-inner">
		<ul class="nav">
			<li class="active"><a class="brand" href="<c:url value="/"/>">FÜGGŐSÉGEK</a></li>
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#">Szakirányok <span class="caret"></span>
			</a>
				<ul class="dropdown-menu">
					<c:forEach var="specialization" items="${specializations}">
						<c:choose>
							<c:when test="${specialization.id == actualSpecialization.id}">
							</c:when>
							<c:otherwise>
								<c:url var="redirectUrl"
									value="/fuggosegek/${specialization.id}" />
								<li onclick="showSubjects('${redirectUrl}')"><a href="#">${specialization.name}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul></li>
			<li><a href="#">Átlag számító</a></li>
			<li class="divider-vertical"></li>
			<li><a
				href="<c:url value="/resources/SubjectEditClient/SubjectEditor.jnlp"/>">Tantárgy
					módosító</a></li>
		</ul>
		<ul class="nav pull-right">
			<li><a href="<c:url value="/"/>contacts">Kapcsolat</a></li>
			<li style="margin:5px;"><fb:like send="false" width="450" show_faces="false"
					action="recommend" href="http://subjectparser.boky01.cloudbees.net/fuggosegek/kotelezo"></fb:like></li>
		</ul>
	</div>
</div>
<div id="navigationbar" class="navbar">
	<div class="navbar-inner">
		<ul class="nav">
			<li><button class="btn dependencyBtn dependencyBtn-forward4">Előrefüggőség</button></li>
			<li><button class="btn dependencyBtn dependencyBtn-forward-4">Függőség</button></li>
			<li><button class="btn dependencyBtn dependencyBtn-forward0">Kiválaszott
					tárgy</button></li>
			<li><button class="btn dependencyBtn dependencyBtn-forward-99">Csak
					felvétel szükséges</button></li>
			<li><button class="btn dependencyBtn dependencyBtn-forward99">A
					tárgyhoz a kijelölt tárgy felvétele szükséges</button></li>
		</ul>
	</div>
</div>
