<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse"
				data-target=".nav-collapse"> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
			</a> <a class="brand" href="<c:url value="/"/>">Average calculator</a>
			<div class="nav-collapse collapse">
				<ul class="nav">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> Szakirány <b class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<c:forEach var="specialization" items="${specializations}">
								<li onclick="showSubjects('${specialization.id}')"><a
									href='<c:url value="#"/>'>${specialization.name}</a></li>
							</c:forEach>
						</ul></li>
					<li><a href="<c:url value='/contacts'/>">Kapcsolat</a></li>
					<li><a id="dependencyLink-forward4" class="color-info-text">Előrefüggőség</a></li>
					<li><a id="dependencyLink-forward-4" class="color-info-text">Függőség</a></li>
					<li><a id="dependencyLink-forward0" class="color-info-text">Kiválaszott</a></li>
					<li><a id="dependencyLink-forward-99" class="color-info-text">Csak
							felvétel szükséges</a></li>
					<li><a id="dependencyLink-forward99" class="color-info-text">A
							tárgyhoz a kijelölt tárgy felvétele szükséges</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>