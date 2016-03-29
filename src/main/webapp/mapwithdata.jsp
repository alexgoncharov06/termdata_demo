<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>


    <meta charset="UTF-8">
    <title>Мониторинг показаний датчиков</title>


    <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
	<link type="text/css" rel="stylesheet" href="./css/main.css">
	

	
	
	
	
	<link href='https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300' rel='stylesheet' type='text/css'>
 
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> 

	<script type="text/javascript" src="./js/mapWithdata.js"></script>
    <!--<link rel="import" href="./menu.html">-->





   



  
</head>
<body onload="onLoad()">

<%@include file="./header.jsp" %>


<div id="type_of_map_text" class="select_table">
    
                <select id="typeMap" onchange="showAllChange()">
                    <option disabled>Выберите тип карты</option>
                    <option value="all">Все записи</option>
                    <option value="errors">Только ошибки</option>
                </select>
           


</div>
<div class="main">
</div>


<div id="map" class="map"></div>

<div class="footer" style="background-color: whitesmoke;  padding: 3px;">&copy; AlexWolfGoncharov, 2015</div>

</body>
</html>
