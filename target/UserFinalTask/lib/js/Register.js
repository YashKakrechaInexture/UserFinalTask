$(document).ready(function(){
	
	$('#profilepic').change(function(){
		
		const file = this.files[0];
		
		if (file){
			let reader = new FileReader();
			reader.onload = function(event){
				
				$('#imgPreview').attr('src', event.target.result);
				
			}
			reader.readAsDataURL(file);
		}else{
			$("#imgPreview").attr('src', "");
		}
	});
	$('#profilepic2').change(function(){
		
		const file = this.files[0];
		
		if (file){
			let reader = new FileReader();
			reader.onload = function(event){
				
				$('#imgPreview2').attr('src', event.target.result);
				
			}
			reader.readAsDataURL(file);
		}else{
			$("#imgPreview2").attr('src', "");
		}
	});
});