<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	input{
		background-color: red;
	}
</style>
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
</head>
<body>

<form action="${cPath }/image.do">
<select name="name">
	
</select>
<input type="submit" value="전송"/>
</form>
<script type="text/javascript">
	$.ajax({
		url:"${cPath}/image/getImageFileNamesJson.do"
		, dataType: "json"
		, success:function(resp) {
			let options = "";
			$.each(resp, function(index, filename){
				options += `<option>\${filename}</option>`;
			});
			
			$('select[name="name"]').html(options)
		}
		
	});

</script>

</body>
</html>