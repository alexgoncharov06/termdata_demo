<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Текущие данные</title>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://www.google.com/jsapi"></script>
 
<script>
    google.load('visualization', '1', {
      packages: ['corechart','gauge']
    });
	 
var Thermdata;
setInterval("loadTermData()",10000);
 function loadTermData() {
 

      var data = { json: true};
      $.ajax( "http://52.11.54.112:5555/term/get", {
        cache: true,
        data: data,
        dataType: "json",
       // error: errorHandler,
        success: success
      } );

 
    function success( forecastData ) {
      var forecast =  forecastData.ThermData;
		Thermdata = forecastData;
		 // alert( forecast );
		 var term1, term2, term3, term7 ;
		 for (var i = 0; i < forecast.length ; i ++)
			 if (forecast[i].BaseID == 'home') {
			 
		  term1 = forecast[i].term2;
		  term2 = forecast[i].term1;
		 
		  var time = document.getElementById('forecast');
		 time.innerHTML = forecast[i].time;
	}
      var gauge = new google.visualization.Gauge(document.getElementById('gauge'));
      var gaugeData = google.visualization.arrayToDataTable([
        ['Label', 'Value'],
        ['На улице', (term1-1)],
		['Дома', (term2-1)]
      ]);
	           var options = {
	             width: 500,
	             redFrom: 40, redTo: 50,
	             yellowFrom:25, yellowTo: 40,
	             greenFrom:18, greenTo: 25,
				blueFrom:0, blueTo:18,
				minorTicks: 10,
				max : 40,
				min: -20,
				animation:{
					duration: 1000,
					easing: 'in'
				},
					 
	           };
	           // For animation purpose only
	           gauge.draw(gaugeData,options);
	           // Show real data
	           gaugeData.setValue(0,1,parseFloat(term1));
	           gaugeData.setValue(1,1,parseFloat(term2));
	          
	           gauge.draw(gaugeData,options);
		
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
<div id="gauge"></div>
<button id="getForecast" onclick="loadTermData()">Обновить данные</button>
 </center>
</html>