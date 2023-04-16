/**
 * 
 */
//DOM(Document Object Model)
let settingsFactory = function(){
	return {
		url : $.CPATH+"/props"
			, dataType:"json"
			, success : function(resp, textStatus, jqXHR){
				if(jqXHR.responseJSON){
					if(resp.error){
						Swal.fire({
							  icon: 'error',
							  title: 'Oops...',
							  text: resp.message,
							  footer: '<a href="">Why do I have this issue?</a>'
							})
					}else if(resp.status && resp.location){
						loadBtn.trigger("click");
					}else{
						let trTags = [];
						list = resp.list
						list.forEach(prop => {
							let tr = $("<tr>").append(
											$("<td data-bs-toggle='modal' data-bs-target='#exampleModal'>").html(prop.propertyName)
											, $("<td>").html(prop.propertyValue)
											, $("<td>").html(
												$("<button>")
													.addClass("deleteBtn")
													.text("삭제")	
											)
										).data("source", prop);
							trTags.push(tr);
						});
						listBody.html(trTags);
						insertForm[0].reset();
						exampleModal.modal("hide");
					}
				}
			}
		}	
}



let updateForm = $("#updateForm").on("submit", function(event){
	event.preventDefault();
	let url = this.action;
	let method = "put";
	let data = JSON.stringify( $(this).serializeObject() );
	let settings = settingsFactory();
	settings.method = method;
	settings.contentType = "application/json;charset=UTF-8";
	settings.data = data;
	$.ajax(settings);
	return false;
});
let exampleModal = $("#exampleModal").on("show.bs.modal", function(event){
	let propTd = event.relatedTarget;
	let modifyProp = $(propTd).parents("tr:first").data("source");
	updateForm.find(":input[name]").each(function(idx, input){
		let propName = this.name;
		$(this).val( modifyProp[propName] );
	});
}).on("hidden.bs.modal", function(){
	updateForm[0].reset();
});



let insertForm = $("#insertForm").on("submit", function(event){
	event.preventDefault();
	let url = this.action;
	let method = this.method;
	let data = JSON.stringify( $(this).serializeObject() );
	let settings = settingsFactory();
	settings.method = method;
	settings.contentType = "application/json;charset=UTF-8";
	settings.data = data;
	$.ajax(settings);
	return false;
});

let listBody = $('#listBody').on("click", ".deleteBtn", function(){
	Swal.fire({
		  title: '삭제 할텨?',
		  showDenyButton: false,
		  showCancelButton: true,
		  confirmButtonText: '삭제',
		}).then((result) => {
		  if (result.isConfirmed) {
			let prop = $(this).parents("tr:first").data("source");
			let settings = settingsFactory();
			settings.method = "delete";
			settings.contentType = "application/json;charset=UTF-8";
			settings.data = JSON.stringify(prop);
			$.ajax(settings);
		  }
		})
});

let loadBtn = $('#loadBtn').on('click',function(){
	$.ajax(settingsFactory());
}).trigger("click");
