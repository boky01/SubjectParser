<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


<!-- <div id="navbar" class="navbar"> -->
<!-- 	<ul> -->
<!-- 		<li class="navbarElement"><select id="specializationSelector" -->
<!-- 			onchange="showSubjects()"> -->
<%-- 				<c:forEach var="specialization" items="${specializations}"> --%>
<%-- 					<c:choose> --%>
<%-- 						<c:when test="${specialization.id == actualSpecialization.id}"> --%>
<%-- 							<option value="${specialization.id}" selected="selected">${specialization.name}</option> --%>
<%-- 						</c:when> --%>
<%-- 						<c:otherwise> --%>
<%-- 							<option value="${specialization.id}">${specialization.name}</option> --%>
<%-- 						</c:otherwise> --%>
<%-- 					</c:choose> --%>
<%-- 				</c:forEach> --%>
<!-- 		</select></li> -->
<!-- 		<li class="navbarElement"><a href="#">Average calculator</a></li> -->
<!-- 		<li class="navbarElement"><button -->
<!-- 				class="dependencyBtn dependencyBtn-forward4">Előrefüggőség</button></li> -->
<!-- 		<li class="navbarElement"><button -->
<!-- 				class="dependencyBtn dependencyBtn-forward-4">Függőség</button></li> -->
<!-- 		<li class="navbarElement"><button -->
<!-- 				class="dependencyBtn dependencyBtn-forward0">Kiválaszott -->
<!-- 				tárgy</button></li> -->
<!-- 		<li class="navbarElement"><button -->
<!-- 				class="dependencyBtn dependencyBtn-forward-99">Csak -->
<!-- 				felvétel szükséges</button></li> -->
<!-- 		<li class="navbarElement"><button -->
<!-- 				class="dependencyBtn dependencyBtn-forward99">A tárgyhoz -->
<!-- 				a kijelölt tárgy felvétele szükséges</button></li> -->
<!-- 		<li class="navbarElement right"><a href="/contacts">Contacts</a></li> -->
<!-- 	</ul> -->
<!-- </div> -->

<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse"
				data-target=".nav-collapse"> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
			</a> <a class="brand" href="<c:url value="/"/>">Average calculator</a>
			<div class="nav-collapse collapse">
				<ul class="nav">
					<li class="dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"> Szakirány <b
							class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<c:forEach var="specialization" items="${specializations}">
								<li onclick="showSubjects('${specialization.id}')"><a href='<c:url value="#"/>'>${specialization.name}</a></li>
							</c:forEach>
						</ul></li>
				</ul>
			</div>
		</div>
	</div>
</div>