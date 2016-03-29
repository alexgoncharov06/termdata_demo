<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%
	String base = (String) request.getParameter("base_Id");
%>
	<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Просмотр текущих значений</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

    <script src="./js/mainViewJS.js"></script>

    <link type="text/css" rel="stylesheet" href="./css/main.css">
    <link href='https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300' rel='stylesheet' type='text/css'>
	 <link rel="stylesheet" href="https://storage.googleapis.com/code.getmdl.io/1.0.4/material.light_blue-red.min.css" />
    <!--<link rel="stylesheet" href="https://storage.googleapis.com/code.getmdl.io/1.0.4/material.indigo-pink.min.css">-->
    <script src="https://storage.googleapis.com/code.getmdl.io/1.0.4/material.min.js"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">



	<script type="text/javascript" src="./js/mainViewJS.js"></script>

<script defer>
   baseId = '<%=base%>';
   

</script>
</head>
<body onload="onLoad()">
<%@include file="./header.jsp" %>

<div class="otstup"></div>


<div id="table_sensors_data">


    </div>
<div class="footer">&copy; AlexWolfGoncharov, 2015</div>

</body>
</html>
