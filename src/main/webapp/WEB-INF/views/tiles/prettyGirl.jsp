<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="content">
	<h1 class="pretty">Nap l�nya</h1>
	<h5 class="pretty">N�zz vissza holnap is!</h5>
	<div class="semester">
		<img class="img-polaroid" src="${imageName}"
			alt="Hoops! Valami�rt nem jelenik meg a k�p!"><br />
		<fb:like send="false" width="450" show_faces="true"	href="${pageContext.request.requestURL}/${imageName}"/>
	</div>
</div>