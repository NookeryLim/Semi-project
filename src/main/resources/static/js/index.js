$(document).ready(function(){
	
});

function myPage(){
	if(document.cookie.length != 0){
		const id=document.cookie.slice(3);
		$.post("../getById",
			{
				id
			},
			function(data){
				data=JSON.parse(data);
				//console.log(data);
				alert( "User info\nName : "+data.name+"\nID : "+data.id+"\nAddress :"+data.address);
				window.open("html/member_info.html", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=100,left=500,width=500,height=300");
			}
		);
	} else {
		window.open("html/login_resist_form.html", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=100,left=500,width=500,height=600");
	}
}

function myBoard(){
	if(document.cookie.length != 0){
		/*window.open("html/board_index.html", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=100,left=500,width=800,height=800");*/
		window.open("html/boardList.html", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=100,left=500,width=800,height=800");
	} else {
		alert("Login first");
		window.open("html/login_resist_form.html", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=100,left=500,width=500,height=600");
	}
}

function myInfo(){
	if(document.cookie.length != 0){
		const id=document.cookie.slice(3);
		$.post("../getById",
			{
				id
			},
			function(data){
				data=JSON.parse(data);
				console.log(data);
			}
		);
	} 
}

function myProducts(){
	window.open("html/products.html", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=100,left=500,width=800,height=800");
}