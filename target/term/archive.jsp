<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String base = (String) request.getParameter("base_Id");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Архив значений по <%=base%>
</title>

<script
	src="./js/jquery.js"></script>
<script
	src="./js/jquery-ui.min.js"></script>




<link type="text/css" rel="stylesheet" href="./css/jquery-ui.1.css">
<link type="text/css" rel="stylesheet" href="./css/main.css">
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="https://storage.googleapis.com/code.getmdl.io/1.0.6/material.indigo-red.min.css" />
<!--<link rel="stylesheet" href="https://storage.googleapis.com/code.getmdl.io/1.0.4/material.indigo-pink.min.css">-->
<script
	src="https://storage.googleapis.com/code.getmdl.io/1.0.6/material.min.js"></script>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">


<script src="./js/archive.js"></script>

<script src="./js/Sonic.js"></script>
<script src="./js/Chart.js"></script>



</head>
<body>
	<%@include file="./header.jsp"%>

	<div class="otstup"></div>

	<script defer>
   base = '<%=base%>';
</script>
	<div class="getDate">
		
		
		<form name="datePick" id="datePick">
			
		   <div style="width:100px; text-align:center;" class="mdl-textfield mdl-js-textfield mdl-textfield-label" >
				<label class="mdl-button-icon material-icons" style="font-size:60px">today
		      <input class="mdl-textfield__input" size="10" type="text" id="datepicker1" >
		     </label>
		    </div>
			 
			<!-- <label > Выберите дату: </label>
			<div class="mdl-textfield mdl-js-textfield">
				<input class="mdl-textfield__input" type="text" id="datepicker1" size="10"/>

			</div> -->
				<button type="button" onclick="poluchitDan()" class="mdl-button mdl-js-button mdl-button--primary" id="getButton">Получить</button>
		
		</form>
	</div>



	<div id="sensors" class="sensors"></div>
	<div >
	<div id="graph" class="container" style ="overflow-x:scroll;overflow-y:hidden;width=100%;"></div>


	</div>
	<div class="footer">&copy; AlexWolfGoncharov, 2015</div>

</body>
</html>
