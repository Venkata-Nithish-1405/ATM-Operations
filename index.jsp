<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#error{
color:red;
text-align:center;
}
#heading{
color:green;
}
#sButton{
color:white;
background-color:blue;
padding:4px;
border-radius:10px;
font-size:14px;
}
#CButton{
color:white;
background-color:blue;
padding:4px;
border-radius:10px;
font-size:14px;
}
#sbutton1{
color:white;
background-color:blue;
padding:4px;
border-radius:10px;
font-size:14px;
}
#cbutton1{
color:white;
background-color:blue;
padding:4px;
border-radius:10px;
font-size:14px;
}
#hea1{
color:red;
}
#sbutton2{
color:white;
background-color:blue;
padding:4px;
border-radius:10px;
font-size:14px;
}
#cbutton2{
color:white;
background-color:blue;
padding:4px;
border-radius:10px;
font-size:14px;
}
#hea2{
color:orange;
}
#sbutton3{
color:white;
background-color:blue;
padding:4px;
border-radius:10px;
font-size:14px;
}
#cbutton3{
color:white;
background-color:blue;
padding:4px;
border-radius:10px;
font-size:14px;
}
#hea3{
color:violet;
}
#main{
text-align:center;
color:brown;
}
</style>
</head>
<body>

<h1 id="main">ATM Operations</h1>
<form action="TransferAccount" name="f1" align="center" onsubmit="return validateForm()" >
<h3 align="center" id="heading">Transfer Amount Application</h3>
<table align="center">
<tr>
<td>Enter Source Account Number:</td>
<td><input type="number" name="t1" max-length="10" min="101" max="200" required></td>
</tr>
<br>
<tr>
<td>Enter Target Account Number:</td>
<td><input type="number" max-length="10" name="t2" min="101" max="200" size="10" required></td>
</tr>
<br>
<tr>
<td>Enter Amount To Be Transferred:</td>
<td><input type="number" required max-length="10"  name="t3"></td>
</tr>
<br>
<tr>
<th><input  id="sButton" type="submit" value="Transfer"></th>
<th><input id="CButton" type="reset" value="Cancel"></th>
</tr>
</table>
<br>
<h3 id="error"></h3>

<script>

function validateForm(){
	
	var saccno=f1.t1.value;
	var taccno=f1.t2.value;
	if(saccno==taccno){
		document.getElementById("error").innerHTML="Both Account Numbers Should Not Be Same";
		return false;
	}
	else{
		return true;
	}
}
</script>


</form>

<form action="DepositAmount" align="center">
<h3 id="hea1">Withdraw Operation</h3>
<table align="center">
<tr>
<td>Enter Account Number: </td>
<td><input type="number" min="101" max="200" name="a1" required></td>
</tr>

<tr>
<td>Enter The Withdraw Amount: </td>
<td><input type="number" required  name="a2"></td>
</tr>

<tr>
<th><input id="sbutton1"type="submit" value="Withdraw"></th>
<th><input id="cbutton1" type="reset" value="Cancel"></th>
</tr>
</table>

</form>
<br>


<form action="DepositAmount" align="center">
<h3 id="hea2">Deposit Operation</h3>
<table align="center">
<tr>
<td>Enter Account Number: </td>
<td><input type="number" min="101" max="200" name="a3" required></td>
</tr>

<tr>
<td>Enter The Deposit Amount: </td>
<td><input type="number" required  name="a4"></td>
</tr>

<tr>
<th><input id="sbutton2"type="submit" value="Deposit"></th>
<th><input id="cbutton2" type="reset" value="Cancel"></th>
</tr>
</table>

</form>
<br>
<form action="CheckBalance" align="center">
<h3 id="hea3">Check Balance Operation</h3>
<table align="center">
<tr>
<td>Enter Account Number: </td>
<td><input type="number" min="101" max="200" name="a5" required></td>
</tr>

<tr>
<th><input id="sbutton3"type="submit" value="Check Balance"></th>
<th><input id="cbutton3" type="reset" value="Cancel"></th>
</tr>
</table>

</form>

</body>
</html>