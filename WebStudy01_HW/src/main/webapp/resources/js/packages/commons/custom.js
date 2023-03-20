/**
 * 
 */
if($){
	$.fn.getObject=function(){
		let data = {};
		this.find(":input[name]").each(function(idx, input){
			let propName = this.name;
			let propValue = $(this).val();
			data[propName] = propValue;
		});	
		return data;
	}
}