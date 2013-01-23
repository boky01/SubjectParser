<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="navbar" class="navbar">
	<div class="navbar-inner">
		<ul class="nav nav-pills">
			<li>
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">Szakirányok
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
					</ul>
			</li>
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
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"> Social <b class="caret"></b>
			</a>
				<ul class="dropdown-menu">
					<li class="socials"><g:plusone annotation="inline" width="150"></g:plusone></li>
					<li class="socials">
						<div class="fb-like" data-send="true" data-width="150"
							data-show-faces="true"></div>
					</li>
					<li class="socials"><a href="https://twitter.com/share"
						class="twitter-share-button">Tweet</a> <script>
							!function(d, s, id) {
								var js, fjs = d.getElementsByTagName(s)[0];
								if (!d.getElementById(id)) {
									js = d.createElement(s);
									js.id = id;
									js.src = "//platform.twitter.com/widgets.js";
									fjs.parentNode.insertBefore(js, fjs);
								}
							}(document, "script", "twitter-wjs");
						</script></li>
				</ul></li>
		</ul>
		<ul class="nav pull-right">
			<li><a href="/contacts">Kapcsolat</a></li>
		</ul>
	</div>
</div>
