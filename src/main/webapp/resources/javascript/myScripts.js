function showSubjects(selectElement) {
	var page = document.getElementById("specializationSelector").value
			.toLowerCase();
	window.location.replace(page);
}

function showDependencies(subjectId, specializationId) {
	$
			.ajax({
				type : "GET",
				url : "/showDependencies",
				data : {
					subjectId : subjectId,
					specializationId : specializationId
				},
				dataType : "json",
				success : function(response) {
					// we have the response
					fillWithDatas(response);
				},
				error : function(e) {
					alert('An error happend. Please contact with the administrator (contact page).');
				}
			});
}

function fillWithDatas(response) {
	console.log(response);
	$(".semester > button").each(function() {
		var newClass = this.className.replace(/forward-?\d+/, 'standby');
		this.setAttribute("class", newClass);
	});

	$.each(response.dependencyDepths, function(key, value) {
		var button = $("#" + key);
		button.removeClass("dependencyBtn-standby");
		button.addClass("dependencyBtn-forward" + value);
	});

	$("#description > p").html(response.description.replace(/\n/g, "<br/>"));

}