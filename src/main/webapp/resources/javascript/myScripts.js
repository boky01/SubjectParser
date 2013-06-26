function showSubjects(specialization) {
	var page = specialization
			.toLowerCase();
	window.location.replace(page);
}

function showDependencies(subjectId, specializationId) {
	$
			.ajax({
				type : "GET",
				url : "showDependencies",
				data : {
					subjectId : subjectId,
					specializationId : specializationId
				},
				dataType : "json",
				success : function(response) {
					// we have the response
					fillWithData(response);
				},
				error : function(e) {
					$('#500errorAlert').modal();
				}
			});
}

function fillWithData(response) {
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

function breakLines(){
	$('.semester > .dependencyBtn').each(function() {
	    if($(this).html().indexOf("(") !== -1){
	        var newText=$(this).html().replace("(","<br/>(");
	        $(this).html(newText);
	    }
	    
	});
}

