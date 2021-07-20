$(document).ready(function(){
	
});

function viewArticle(){
	const articleNO=0;
	$.get("../viewArticle",
		{
			articleNO
		},
		function(data){
			data=JSON.parse(data);
			//console.log(data);
			alert( "User info\nName : "+data.name+"\nID : "+data.id+"\n");
			window.open("html/member_info.html", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=100,left=500,width=500,height=300");
		}
	)
}
		
