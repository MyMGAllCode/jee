/**
 * 
 */
function validate(frm)
{
	var name=frm.name.value;
	var age=frm.age.value;
	document.getElementById("errmsg").innerHTML="";
	document.getElementById("errmsg1").innerHTML="";
	document.getElementById("errmsg").style.color="red";
	document.getElementById("errmsg1").style.color="red";
	if(name=="")
		{
		document.getElementById("errmsg").innerHTML="Please Enter Name";
		 frm.age.focus();
		return false;
		}
	if(age=="")
	{
		document.getElementById("errmsg1").innerHTML="Please Enter Age";
		 frm.age.focus();
		return false;
		}
	else if(isNaN(age)){
        document.getElementById("errmsg1").innerHTML="Person age must be numeric value";	
    	  frm.age.value="";
		  frm.age.focus();
		  return false;
		  
 	   }
	else if(parseInt(frm.age.value)<0)
		{document.getElementById("errmsg1").innerHTML="Person age must be numeric & Positive value";	
  	  frm.age.value="";
	  frm.age.focus();
	  return false;
		
		}
	return true;
	}