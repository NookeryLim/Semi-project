$(document).ready(function(){
	var setCookie = function(name, value, exp) {
		var date = new Date();
		date.setTime(date.getTime() + exp*24*60*60*1000);
		document.cookie = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
	};
	
	$('#registerBtn').click(async function(){
		const name=$('#reg_name').val();
		const id=$('#reg_id').val();
		const pw=$('#reg_pw').val();
		//console.log(id);
		alert("Processing membership...\n\nName : "+name+"\nID : "+id+"\nPassword : "+pw);
		$.post("../memberInsert",
				  {
				    name,
				    id,
				    pw
				  },
				  function(data, status){
					  alert("Register "+status);
					  window.location.reload()
				  }
		);
	});
	
	$('#loginBtn').click(async function(){
		const id=$('#log_id').val();
		const pw=$('#log_pw').val();
		//console.log(id);
		alert("login...\nID : "+id+"\nPassword : "+pw);
		$.post("../loginById",
				  {
				    id,
				    pw
				  },
				  function(data, status){
					data=JSON.parse(data);
					//console.log(data);
					if(data.login){
						alert("login failed");
					} else {
						setCookie("id", data.id, 1);
						alert("Login "+status+"\nWELCOME, "+data.name);
						opener.location.reload();
						window.close();
					}
				  }
		);
	});
	
	$('#logoutBtn').click(async function(){
		result = confirm("Logout ?");
		if(result){
			if(document.cookie.length == 0){
				alert("Already logged out");
				window.close();
			}
			const id=document.cookie.slice(3);
			$.post("../getById",
				  {
				    id
				  },
				  function(data){
					data=JSON.parse(data);
					//console.log(data);
					//console.log(document.cookie);
		
					setCookie("id", " ", 0);
					
				    alert("Logout success");
				    opener.location.reload();
				    window.close();
					}				  
		);
		}else{
		    alert("Logout cancel");
		}
	});
	
	$('#modBtn').click(async function(){
		result = confirm("Modify your member info ?");
		if(result){
			if(document.cookie.length == 0){
				alert("Login first");
				window.close();
			}
			const id=document.cookie.slice(3);
			const pw=prompt("New password","");
			const name=prompt("Name","");
			const address=prompt("Address","");
			if(address==null || pw==null || name==null){
				alert("Modify cancel");
				window.reload();
			}
			$.post("../updateMember",
				  {
				    id,
				    pw,
				    name,
				    address
				  },
				  function(data){
					//data=JSON.parse(data);
					//console.log(data);
		
					//setCookie("id", data.id, 1);
				    alert("Modify success");
				    opener.location.reload();
				    window.close();
					}				  
		);
		}else{
		    alert("Modify cancel");
		}
		
	});
	
	$('#delBtn').click(async function(){
		result = confirm("Delete your member info ?");
		if(result){
			if(document.cookie.length == 0){
				alert("Login first");
				window.close();
			}
			const id=document.cookie.slice(3);
			const pw=prompt("password"+"");
			$.post("../loginById",
				  {
				    id,
				    pw
				  },
				  
				  function(data, status){
					data=JSON.parse(data);
					//console.log(data);
					if(data.login){
						alert("Delete failed");
						window.close();
					} else if(pw!=data.pw) {
						alert("Check your password");
					} else {
						result = confirm(data.name+"\nAre you sure you want to leave? ");
						if(result){
							//alert(id);
							
							$.post("../deleteMember",
							{
								id
							},
							function(data, status){
								setCookie("id", " ", 0);
								alert("Good Bye...\nDelete success ");
								opener.location.reload();
								window.close();
							});
				  
				  
						}else{
						    alert("Delete cancel");
						}
					}
				  }
				  
		);
		}else{
		    alert("Delete cancel");
		}
	});
});