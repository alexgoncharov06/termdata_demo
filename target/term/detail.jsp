<%@page import="com.github.alexwolfgoncharov.termdata.dao.TermDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.github.alexwolfgoncharov.termdata.dao.Factory"%>
<%@ page import="com.github.alexwolfgoncharov.termdata.interfaces.ThermData"%>
<%@ page import="java.util.*"%>
<%
	String base = (String) request.getParameter("base_id");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Энергополис. Получение информации об <%=base%>
</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://www.google.com/jsapi"></script>
 
<script>
 google.load("visualization", "1.1", {packages:["table"]});
	 
var Thermdata;
//setInterval("loadTermData()",30000);
 function loadTermData() {
 

      var data = { json: 'all', id: '<%=base%>'};
      $.ajax( "http://52.11.54.112:5555/term/get", {
        cache: true,
        data: data,
        dataType: "json",
        error: errorHandler,
        success: success
      } );

 
    function success( forecastData ) {
		 document.getElementById('table_div').innerHTML = "";
		 document.getElementById('forecast').innerHTML = "";
      var forecast =  forecastData.ThermData;
		Thermdata = forecastData;
		var counter = 0;
    	var goods = document.createElement('div');
  	 	var desc = document.createElement('div');  // div для картинки и описания товара
     	desc.innerHTML =  '<h3> Данные по точке: ' + forecast[0].BaseID + '</h3>';
		var time = document.createElement('div');  // div для картинки и описания товара
		time.innerHTML =  '<p><i>' + forecast[0].time + '</i></p>'+'';
		
		
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'Найменование');
		data.addColumn('string', 'Время');
		data.addColumn('number', 'Т1 (°С)');
		data.addColumn('number', 'Т2 (°С)');
		data.addColumn('number', 'Т3 (°С)');
		data.addColumn('number', 'Т4 (°С)');
		data.addColumn('number', 'Т5 (°С)');
		data.addColumn('number', 'Т6 (°С)');
		data.addColumn('number', 'Т7 (°С)');
		data.addColumn('number', 'Т8 (°С)');
		data.addColumn('number', 'Т9 (°С)');
		data.addColumn('number', 'Т10 (°С)');
		data.addColumn('number', 'Т11 (°С)');
		data.addColumn('number', 'Т12 (°С)');
		data.addColumn('number', 'Т13 (°С)');
		data.addColumn('number', 'Т14 (°С)');
		data.addColumn('number', 'Т15 (°С)');
		data.addColumn('number', 'Т16 (°С)');
		data.addColumn('number', 'Т17 (°С)');
		data.addColumn('number', 'Т18 (°С)');
		data.addColumn('number', 'Т19 (°С)');
		data.addColumn('number', 'Т20 (°С)');
		data.addColumn('number', 'Т21 (°С)');
		data.addColumn('number', 'Т22 (°С)');
		data.addColumn('number', 'Т23 (°С)');
		data.addColumn('number', 'Т24 (°С)');
		data.addColumn('boolean', 'Signal 1');
		data.addColumn('boolean', 'Signal 2');
		data.addColumn('boolean', 'Signal 3');
		data.addColumn('boolean', 'Signal 4');
		data.addColumn('boolean', 'Signal 5');
		data.addColumn('boolean', 'Signal 6');
		data.addColumn('boolean', 'Signal 7');
		data.addColumn('boolean', 'Signal 8');
		data.addColumn('boolean', 'Signal 9');
		data.addColumn('boolean', 'Signal 10');
		data.addColumn('boolean', 'Signal 11');
		data.addColumn('boolean', 'Signal 12');
		data.addColumn('boolean', 'Signal 13');
		data.addColumn('boolean', 'Signal 14');
		data.addColumn('boolean', 'Signal 15');
		data.addColumn('boolean', 'Signal 16');
		data.addColumn('number', 'Баланс, грн.');
		
		
			for (var id in forecast)
		        {   
					  
					  data.addRows([[forecast[id].BaseID,forecast[id].time,
						  forecast[id].term1, 
						  forecast[id].term2, 
						  forecast[id].term3,
						  forecast[id].term4, 
						  forecast[id].term5, 
						  forecast[id].term6,
						  forecast[id].term7, 
						  forecast[id].term8, 
						  forecast[id].term9, 
						  forecast[id].term10, 
						  forecast[id].term11, 
						  forecast[id].term12, 
						  forecast[id].term13,
						  forecast[id].term14,
						  forecast[id].term15,
						  forecast[id].term16,
						  forecast[id].term17,
						  forecast[id].term18,
						  forecast[id].term19,
						  forecast[id].term20,
						  forecast[id].term21,
						  forecast[id].term22,
						  forecast[id].term23,
						  forecast[id].term24,
						  // {v: forecast[id].term1, f: forecast[id].term1 +'°' },
 // 						  {v: forecast[id].term2, f: forecast[id].term2 +'°' },
 // 						  {v: forecast[id].term3, f: forecast[id].term3 +'°' },
 // 						  {v: forecast[id].term4, f: forecast[id].term4 +'°' },
 // 						  {v: forecast[id].term5, f: forecast[id].term5 +'°' },
 // 						  {v: forecast[id].term6, f: forecast[id].term6 +'°' },
 // 						  {v: forecast[id].term7, f: forecast[id].term7 +'°' },
 // 						  {v: forecast[id].term8, f: forecast[id].term8 +'°' },
 // 						  {v: forecast[id].term9, f: forecast[id].term9 +'°' },
 // 						  {v: forecast[id].term10, f: forecast[id].term10 +'°' },
 // 						  {v: forecast[id].term11, f: forecast[id].term11 +'°' },
 // 						  {v: forecast[id].term12, f: forecast[id].term12 +'°' },
 // 						  {v: forecast[id].term13, f: forecast[id].term13 +'°' },
						  (forecast[id].signal1 == 0 ? false : true),
						  (forecast[id].signal2 == 0 ? false : true),
						  (forecast[id].signal3 == 0 ? false : true),
						  (forecast[id].signal4 == 0 ? false : true),
						  (forecast[id].signal5 == 0 ? false : true),
						  (forecast[id].signal6 == 0 ? false : true),
						  (forecast[id].signal7 == 0 ? false : true),
						  (forecast[id].signal8 == 0 ? false : true),
						  (forecast[id].signal9 == 0 ? false : true),
						  (forecast[id].signal10 == 0 ? false : true),
						  (forecast[id].signal11 == 0 ? false : true),
						  (forecast[id].signal12 == 0 ? false : true),
						  (forecast[id].signal13 == 0 ? false : true),
						  (forecast[id].signal14 == 0 ? false : true),
						  (forecast[id].signal15 == 0 ? false : true),
						  (forecast[id].signal16 == 0 ? false : true),
						  forecast[id].balance]]);
		        }
				  
				  var table = new google.visualization.Table(document.getElementById('table_div'));
				  // var tableDiv  = document.createElement('div');
				  table.draw(data, {showRowNumber: true, width: '100%', height: '100%', page: 'enable', pageSize : 15});
				  
				  goods.appendChild(desc);
				  goods.appendChild(time);
				 
		
		document.getElementById('forecast').appendChild(goods);
      // forecast += ": " + forecastData.forecast + ". Максимальная температура: " + forecastData.maxTemp + "C";
     
    }
 
    function errorHandler() {
      // alert( "Есть проблемы с получением данных." );
		loadTermData();
    }
 
  } ;
</script>
 
</head>
 
<body onload = "loadTermData()">
  
	<center>
 

<div id="forecast"></div>
  <button id="getForecast" onclick="loadTermData()">Обновить данные</button>
  </p>
<div id="table_div"></div>
  
  <div id="test" > </div>

 </center>
</body>
</html>