/**
 * 
 */
	const calForm = $("form[name=calForm]").on("change", ":input[name]", function(event){
// 		event.target===this
		$(this.form).submit();
	}).on("submit", function(event){
		event.preventDefault();
		let url = this.action;
		let method = this.method;
		let queryString = $(this).serialize();
		$.ajax({
			url:url
			, type:method
			, data:queryString
			, dateType:"html"
			, success:function(resp){
				calForm.next("table").remove();
				calForm.after(resp);
			}
		});
		return false;
	}).submit();
	$(".controlA").on("click", function(event){
// 		event.target === this
		event.preventDefault();
		console.log(this);
		console.log($(this));
		let year = this.dataset.year;
		let month = $(this).data("month");
		calForm.find("[name=year]").val(year);
		calForm.find("[name=month]").val(month);
		calForm.submit();
		return false;
	});