/**
 * Created by Alex on 16.11.15.
 */
/**
 *
 */



var countPoints = 4*24;
var archive = {};
var predel;
var graphCount = 1;
var color = 0;
var base;
var dataFill = true;

var fillColors = ["rgba(142,111,182,0.2)",
    "rgba(246,180,64,0.2)",
    "rgba(2,236,206,0.2)",
    "rgba(150,32,58,0.2)",
    "rgba(23,168,22,0.2)",
    "rgba(254,255,9,0.2)",
    "rgba(77,193,253,0.2)",
    "rgba(241,98,53,0.2)",
    "rgba(222,140,144,0.2)",
    "rgba(214,222,41,0.2)",
    "rgba(87,18,108,0.2)",
    "rgba(164,89,42,0.2)",
    "rgba(251,207,224,0.2)",
    "rgba(157,244,239,0.2)",
    "rgba(222,32,221,0.2)",
];

var strokeColors = ["rgba(142,111,182,1)",
    "rgba(246,180,64,1)",
    "rgba(2,236,206,1)",
    "rgba(150,32,58,1)",
    "rgba(23,168,22,1)",
    "rgba(254,255,9,1)",
    "rgba(77,193,253,1)",
    "rgba(241,98,53,1)",
    "rgba(222,140,144,1)",
    "rgba(214,222,41,1)",
    "rgba(87,18,108,1)",
    "rgba(164,89,42,1)",
    "rgba(251,207,224,1)",
    "rgba(157,244,239,1)",
    "rgba(222,32,221,1)",
]
// var fillColors = ["rgba(142,111,182,0.2)",
// "rgba(246,180,64,0.2)",
// "rgba(2,236,206,0.2)",
// "rgba(150,32,58,0.2)",
// "rgba(23,168,22,0.2)",
// "rgba(254,255,9,0.2)",
// "rgba(77,193,253,0.2)",
// "rgba(241,98,53,0.2)",
// "rgba(222,140,144,0.2)",
// "rgba(214,222,41,0.2)",
// 	 "rgba(87,18,108,0.2)",
// 	  "rgba(164,89,42,0.2)",
// 	 "rgba(251,207,224,0.2)",
// 	 "rgba(157,244,239,0.2)",
// 	 "rgba(222,32,221,0.2)",
//  ];
//
// var strokeColors = ["rgba(142,111,182,1)",
// "rgba(246,180,64,1)",
// "rgba(2,236,206,1)",
// "rgba(150,32,58,1)",
// "rgba(23,168,22,1)",
// "rgba(254,255,9,1)",
// "rgba(77,193,253,1)",
// "rgba(241,98,53,1)",
// "rgba(222,140,144,1)",
// "rgba(214,222,41,1)",
// "rgba(87,18,108,1)",
// 	  "rgba(164,89,42,1)",
// 	 "rgba(251,207,224,1)",
// 	 "rgba(157,244,239,1)",
// 	 "rgba(222,32,221,1)",
// ];

// var strokeColors= ["rgba(217, 11, 11, 1)","rgba(132, 20, 245, 1)","rgba(201, 46, 240, 1)","rgba(147, 95, 199, 1)","rgba(27, 14, 201, 1)","rgba(12, 222, 245, 1)","rgba(6, 60, 66, 1)","rgba(10, 194, 120, 1)","rgba(6, 99, 62, 1)","rgba(66, 235, 23, 1)","rgba(189, 237, 17, 1)","rgba(94, 117, 8, 1)","rgba(224, 188, 7, 1)","rgba(240, 143, 17, 1)","rgba(255, 0, 179, 1)","rgba(140, 45, 10, 1)","rgba(116, 216, 227, 1)"];
// var fillColors = ["rgba(217, 11, 11,0.2)","rgba(132, 20, 245,0.2)","rgba(201, 46, 240,0.2)","rgba(147, 95, 199,0.2)","rgba(27, 14, 201,0.2)","rgba(12, 222, 245,0.2)","rgba(6, 60, 66,0.2)","rgba(10, 194, 120,0.2)","rgba(6, 99, 62,0.2)","rgba(66, 235, 23,0.2)","rgba(189, 237, 17,0.2)","rgba(94, 117, 8,0.2)","rgba(224, 188, 7,0.2)","rgba(240, 143, 17,0.2)","rgba(255, 0, 179,0.2)","rgba(140, 45, 10,0.2)","rgba(116, 216, 227,0.2)"];

$(document).ready(function() {
    $("#datepicker1").datepicker({showAnim:"slide",showButtonPanel:true});



});

jQuery(function($){
    $.datepicker.regional['ru'] = {
        closeText: 'Закрыть',
        prevText: '&#x3c;Пред',
        nextText: 'След&#x3e;',
        currentText: 'Сегодня',
        monthNames: ['Январь','Февраль','Март','Апрель','Май','Июнь',
            'Июль','Август','Сентябрь','Октябрь','Ноябрь','Декабрь'],
        monthNamesShort: ['Янв','Фев','Мар','Апр','Май','Июн',
            'Июл','Авг','Сен','Окт','Ноя','Дек'],
        dayNames: ['воскресенье','понедельник','вторник','среда','четверг','пятница','суббота'],
        dayNamesShort: ['вск','пнд','втр','срд','чтв','птн','сбт'],
        dayNamesMin: ['Вс','Пн','Вт','Ср','Чт','Пт','Сб'],
        weekHeader: 'Не',
        dateFormat: 'dd.mm.yy',
        firstDay: 1,
        isRTL: false,
        showMonthAfterYear: false,
        yearSuffix: ''};
    $.datepicker.setDefaults($.datepicker.regional['ru']);
});



function poluchitDan(){
    var selectDate = document.getElementById("datepicker1").value;
    // console.log(selectDate);
    var tttt;
    tttt = selectDate.split(".");
    var datStart = new Date(tttt[2],(tttt[1]-1),tttt[0]);



    var datEnd  = new Date(datStart.getTime());
    datEnd.setDate(datEnd.getDate() + 1);
//        var ndate = ""+ "."+datEnd.getMonth()+"."+datEnd.getFullYear();

    // console.log(datStart + "  " + datEnd);

    var endDDD = ""+datEnd.getDate() +"."+(datEnd.getMonth()+1) + "." + datEnd.getFullYear();
    var  startDDD = ""+datStart.getDate() +"."+(datStart.getMonth()+1) + "." + datStart.getFullYear();
    // console.log(startDDD);
    var baseID;
    if (base !==  "null") {
        baseID = base;
    }
    else {
        baseID = "860719023988417";
    }
    getFromServer(baseID,startDDD,endDDD);


}

function getFromServer(base,from,to){
    var divForTable1 = document.getElementById("sensors");
    divForTable1.innerHTML = '';
    var graphDiv = document.getElementById("graph");
    graphDiv.innerHTML = '';
//        $.ajax( "http://localhost:5555/term/getset", {
    $.ajax( "http://52.11.54.112:5555/term/getset", {

        beforeSend: function (xhr) {
            xhr.setRequestHeader('json', 'true');
            xhr.setRequestHeader('type', 'archive');
            //запуск спиннера загрузки
            runLoader();
        },

        cache: true,
        type: 'POST',
        data: { datefrom : from, dateto : to, baseid : base },
        contentType: 'application/x-www-form-urlencoded',
        dataType: "json",
        success: success,
        error: error

    } );
}

function success( forecastData ) {
    console.log("success");
    stopLoader();

    archive = forecastData.Archive;

    createTableSettings(archive.BaseId);

//        console.log(forecastData.Archive);

}

function error() {

    console.log("error");
    stopLoader();

}

function getRandomInt(min, max) {
    return Math.floor(Math.random() * (max - min)) + min;
}

function runLoader(){
    var circle = new Sonic({

        width: 50,
        height: 50,
        padding: 50,

        strokeColor: '#000',

        pointDistance: .01,
        stepsPerFrame: 3,
        trailLength: .7,

        step: 'fader',

        setup: function() {
            this._.lineWidth = 5;
        },

        path: [
            ['arc', 25, 25, 25, 0, 360]
        ]

    });

    circle.play();
    var div = document.createElement('div');
    div.id = 'runLoader';
    div.className = 'center';
    div.appendChild(circle.canvas);
    document.body.appendChild(div);
    document.getElementById("getButton").disabled = true;
}

function stopLoader(){
    var div = document.getElementById('runLoader');
    div.remove();
    document.getElementById("getButton").disabled = false;

}




function createTableSettings(BaseId){

    var divForTable = document.getElementById("sensors");
	var nameBaseId = document.createElement("h3");
	nameBaseId.textContent =  BaseId.name;
    var form = document.createElement("form");
    form.name = "data";
	// divForTable.appendChild(nameBaseId);
    divForTable.appendChild(form);

        var typeSensor = document.createElement('caption');
   typeSensor.appendChild(nameBaseId);
    var tableSensor  = document.createElement('TABLE');
    tableSensor.id = 'sensors_table';
    tableSensor.className = 'sensors_table1';
//            tableSensor.setAttribute('style','border: 1px solid grey; border-collapse: collapse;');



    tableSensor.appendChild(typeSensor);


    function drawSensTable(typeS) {
        var num = 1;
        var sensType, sensid;
        if (typeS === "tm") {

            sensType = "Температурные датчики:";
            sensid = "tbodyTemp";

        } else if(typeS === "dt"){

            sensType = "Дискретные датчики:";
            sensid = "tbodySignal";
        }

        var tbodyTemp = tableSensor.createTBody();
        tbodyTemp.id = sensid;

        var rowstheaderTemp = tbodyTemp.insertRow(-1);
        // rowstheaderTemp.className = 'sensors_head_table';
        var cellSensor1 =  rowstheaderTemp.insertCell(-1);
        cellSensor1.setAttribute('colspan', '5');

        cellSensor1.style.textAlign = "center";

        var head = document.createElement("h4");
        head.textContent = sensType;
        cellSensor1.appendChild(head);
        var rowsSensor= tbodyTemp.insertRow(-1);
        rowsSensor.className = 'sensors_head_table';

        var cellSensor1 =  rowsSensor.insertCell(-1);
        cellSensor1.id = 'numSensor';
        cellSensor1.textContent = '#';
        cellSensor1.class = 'sensors_head_table';
        cellSensor1.setAttribute('style','text-align:right;');
        var cellSensor2 =  rowsSensor.insertCell(-1);
        cellSensor2.textContent = 'Название датчика';

        var cellSensor3 =  rowsSensor.insertCell(-1);
        cellSensor3.textContent = 'График 1';
        cellSensor3.setAttribute('style','text-align:center;');

        var cellSensor4 =  rowsSensor.insertCell(-1);
        cellSensor4.textContent = 'График 2';
        cellSensor4.setAttribute('style','text-align:center;');

        var cellSensor5 =  rowsSensor.insertCell(-1);
        cellSensor5.textContent = 'График 3';
        cellSensor5.setAttribute('style','text-align:center;');


        for (var j = 0; j < BaseId.sensors.length; j++) {
            var type = BaseId.sensors[j].type;
            if (BaseId.sensors[j].used && type == typeS) {


                var sensorNum = document.createElement('div');
                sensorNum.textContent = '' + num;

                var sensorName = document.createElement('div');
                sensorName.textContent = BaseId.sensors[j].name;


                var id = BaseId.sensors[j].sensorId;
                var nameOfSensor;
                if (type == 'tm') {
                    nameOfSensor = 'term';

                } else if (type == 'dt') {
                    nameOfSensor = 'signal';

                }
                nameOfSensor += id;

                sensorName.id =nameOfSensor+"Name";



                var check1 = document.createElement('input');
                check1.type = 'checkbox';
                check1.id = 'sensor_gr1_' + type+ num;
                check1.name = nameOfSensor;
                var check2 = document.createElement('input');
                check2.type = 'checkbox';
                check2.id = 'sensor_gr2_' + type+ num;
                check2.name = nameOfSensor;

                var check3 = document.createElement('input');
                check3.type = 'checkbox';
                check3.id = 'sensor_gr3_' + type + num;
                check3.name = nameOfSensor;
                rowsSensor = tbodyTemp.insertRow(-1);
//                    rowsSensor.setAttribute('style', 'background-color : ' + color + '; opacity : 0,7');
                cellSensor1 = rowsSensor.insertCell(-1);
                cellSensor1.appendChild(sensorNum);
                cellSensor1.setAttribute('style', 'text-align:right;');
                cellSensor2 = rowsSensor.insertCell(-1);
                cellSensor2.appendChild(sensorName);
                cellSensor3 = rowsSensor.insertCell(-1);
                cellSensor3.appendChild(check1);
                cellSensor3.setAttribute('style', 'text-align:center;');
                cellSensor4 = rowsSensor.insertCell(-1);
                cellSensor4.appendChild(check2);
                cellSensor4.setAttribute('style', 'text-align:center;');
                //cellSensor4.setAttribute('style', 'background-color : ' + color + '; opacity : 0,7');
                cellSensor5 = rowsSensor.insertCell(-1);
                cellSensor5.appendChild(check3);
                cellSensor5.setAttribute('style', 'text-align:center;');
                predel = num;
                num++;
            }
        }




    }



    drawSensTable('tm');
    drawSensTable('dt');


    var buttonDraw = document.createElement("button");
    buttonDraw.textContent = "Построить";
    buttonDraw.onclick = drawGraph;
    buttonDraw.type = "button";
    buttonDraw.className ="update";
    buttonDraw.className ="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored"

    var tbodyButton = tableSensor.createTBody();
    tbodyButton.id = "tbodyButton";
    var rowButton = tbodyButton.insertRow(-1);


    var cellSensor1 =  rowButton.insertCell(-1);
    cellSensor1.setAttribute('colspan', '5');
    cellSensor1.appendChild(buttonDraw);
    cellSensor1.style.textAlign = "center";
    form.appendChild(tableSensor);


}


var graph1 = [];
var graph2 = [];
var graph3 = [];
var graph4 = [];
var graph5 = [];
var graph6 = [];
var stepsArray = 3*24;


function drawGraph(){
    deleteAll(graph1);
    deleteAll(graph2);
    deleteAll(graph3);
    deleteAll(graph4);
    deleteAll(graph5);
    deleteAll(graph6);
    color = 0;
    var needTemp = false;
    var needDt = false;
    var formSens = document.forms.data;
    var predelTm = document.getElementById("sensors_table").tBodies.namedItem("tbodyTemp").rows.length -2;
    var predelDt = document.getElementById("sensors_table").tBodies.namedItem("tbodySignal").rows.length -2;

    for (var i = 0; i < predelTm; i++) {
        var count = i +1;
        if(formSens.elements.namedItem('sensor_gr1_tm' + count).checked) {
            // graph1.push("term" + count);

            graph1.push(formSens.elements.namedItem('sensor_gr1_tm' + count).name);
            needTemp = true;
        }
        if(formSens.elements.namedItem('sensor_gr2_tm' + count).checked) {
            // graph2.push("term" + count);
            graph2.push(formSens.elements.namedItem('sensor_gr2_tm' + count).name);
            needTemp = true;
        }
        if(formSens.elements.namedItem('sensor_gr3_tm' + count).checked) {
            // graph3.push("term" + count);
            graph3.push(formSens.elements.namedItem('sensor_gr3_tm' + count).name);
            needTemp = true;
        }

    }

    for (var i = 0; i < predelDt; i++) {
        var count = i +1;

        if(formSens.elements.namedItem('sensor_gr1_dt' + count).checked) {
            // graph3.push("term" + count);
            graph4.push(formSens.elements.namedItem('sensor_gr1_dt' + count).name);
            needDt = true;
        }
        if(formSens.elements.namedItem('sensor_gr2_dt' + count).checked) {
            // graph3.push("term" + count);
            graph5.push(formSens.elements.namedItem('sensor_gr2_dt' + count).name);
            needDt = true;
        }
        if(formSens.elements.namedItem('sensor_gr3_dt' + count).checked) {
            // graph3.push("term" + count);
            graph6.push(formSens.elements.namedItem('sensor_gr3_dt' + count).name);
            needDt = true;
        }
    }

    var gr = document.getElementById("graph");
    gr.innerHTML = '';
    graphCount = 1;
    if( needTemp){
        var gr = document.getElementById("graph");
        var head = document.createElement("h3");
        head.textContent = "Температурные датчики:";
        gr.appendChild(head);
        createGraph(graph1);
        createGraph(graph2);
        createGraph(graph3);
    }
    var hr = document.createElement("hr");
    if(needDt){
        if(needTemp) gr.appendChild(hr);
        var head = document.createElement("h3");
        head.textContent = "Дискретные датчики:";

        gr.appendChild(head);
        createGraphD(graph4);
        createGraphD(graph5);
        createGraphD(graph6);
    }


}

function createShortArray(array, steps){
    var data2=[];
    var testData = 0;
    var count = 0;
    var cc = (array.length/steps).toFixed(0);
    if (cc < 2) {
        return array;
    }
    for(var i = 0; i < array.length; i++){
        if (i% cc == 0 & i !=0){
            data2.push((testData/count).toFixed(2));
            testData = 0;
            count = 0;

        }else {
            testData+=parseFloat(array[i]);
            count++;
        }
    }
    return data2;
}

function createShortArrayD(array, steps){
    var data=[];
    var testData = 0;
    var count = 0;
    var cc = (array.length/steps).toFixed(0);
    if (cc < 2) {
        return array;
    }
    for(var i = 0; i < array.length; i++){
        if (i% cc == 0 & i !=0){
            data.push(Math.ceil(testData/count));
            testData = 0;
            count = 0;

        } else {
            testData+=parseFloat(array[i]);
            count++;
        }
    }
    return data;
}

function createShortLabel(label, steps) {
    var cc = (label.length/steps).toFixed(0);
    if (cc < 2) {
        return label;
    }
    var labelAr = [];
    for (var i = 0; i < label.length; i++) {
        if (i % cc == 0 & i != 0) labelAr.push(label[i]);
    }
    return labelAr;
}

function deleteAll(array){

    while(array.length > 0) {
        array.pop();
    }
}
function createGraph(graphCanvas){
    if(graphCanvas.length != 0) {
        var datas = {};
        var gr = document.getElementById("graph");
        var head = document.createElement("h4");
        head.textContent = "График " + graphCount;
        var canvas = document.createElement("canvas");
        canvas.style.height = '600px';
        canvas.style.width = '3000px';
        gr.appendChild(head);
        gr.appendChild(canvas);
        var ctx = canvas.getContext("2d");


//            var randoms = [];
        for (var i = 0; i < graphCanvas.length; i++) {
//                randoms.push(getRandomInt(0, (fillColors.length)));
            datas[graphCanvas[i]] = [];
        }

        var label1 = [];
        deleteAll(label1);


        var datasets = [];
        deleteAll(datasets);


        for (var j = 0; j < archive.data.length; j++) {
            for (var i = 0; i < graphCanvas.length; i++) {

                datas[graphCanvas[i]].push(archive.data[j][graphCanvas[i]]);
            }
            label1.push(archive.data[j].time)

        }

        if (graphCanvas.length > 5){
            dataFill = false;
        } else {
            dataFill = true;
        }

        for (var a = 0; a < graphCanvas.length; a++) {

            datasets[a] = {
                label: document.getElementById(graphCanvas[a] + "Name").textContent,
                fillColor: fillColors[color],
                strokeColor: strokeColors[color],
                pointColor: strokeColors[color],
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(151,187,205,1)",
                data: createShortArray(datas[graphCanvas[a]], stepsArray)
            };
            if (color < fillColors.length) {
                color++;
            }
            else color = 0;
        }


        var dataSet = {
            labels: createShortLabel(label1, stepsArray),
            datasets: datasets
        };


        var optionsGraph = {

            animation: true,
            XScaleStep: 6,

            // Number - Number of animation steps
            animationSteps: 60,
// String - Animation easing effect
            // Possible effects are:
            // [easeInOutQuart, linear, easeOutBounce, easeInBack, easeInOutQuad,
            //  easeOutQuart, easeOutQuad, easeInOutBounce, easeOutSine, easeInOutCubic,
            //  easeInExpo, easeInOutBack, easeInCirc, easeInOutElastic, easeOutBack,
            //  easeInQuad, easeInOutExpo, easeInQuart, easeOutQuint, easeInOutCirc,
            //  easeInSine, easeOutExpo, easeOutCirc, easeOutCubic, easeInQuint,
            //  easeInElastic, easeInOutSine, easeInOutQuint, easeInBounce,
            //  easeOutElastic, easeInCubic]

            animationEasing: "easeOutQuart",
            // Boolean - If we should show the scale at all
            showScale: true,

            // Boolean - If we want to override with a hard coded scale
            scaleOverride: false,

            // ** Required if scaleOverride is true **
            // Number - The number of steps in a hard coded scale
            scaleSteps: 5,
            // Number - The value jump in the hard coded scale
            scaleStepWidth: 10,
            // Number - The scale starting value
            scaleStartValue: 40,
            ///Boolean - Whether grid lines are shown across the chart
            scaleShowGridLines: true,

            //String - Colour of the grid lines
            scaleGridLineColor: "rgba(0,0,0,.05)",

            //Number - Width of the grid lines
            scaleGridLineWidth: 1,
            // scaleSteps: 2,

            //Boolean - Whether to show horizontal lines (except X axis)
            scaleShowHorizontalLines: true,

            //Boolean - Whether to show vertical lines (except Y axis)
            scaleShowVerticalLines: true,

// Boolean - Whether to show labels on the scale
            scaleShowLabels: true,

            // Interpolated JS string - can access value
            scaleLabel: "<%=value%>",


            //Boolean - Whether the line is curved between points
            bezierCurve: true,

            //Number - Tension of the bezier curve between points
            bezierCurveTension: 0.4,

            //Boolean - Whether to show a dot for each point
            pointDot: true,

            //Number - Radius of each point dot in pixels
            pointDotRadius: 4,

            //Number - Pixel width of point dot stroke
            pointDotStrokeWidth: 1,

            //Number - amount extra to add to the radius to cater for hit detection outside the drawn point
            pointHitDetectionRadius: 20,

            //Boolean - Whether to show a stroke for datasets
            datasetStroke: true,

            //Number - Pixel width of dataset stroke
            datasetStrokeWidth: 3,

            //Boolean - Whether to fill the dataset with a colour
            datasetFill: dataFill,

            // Number - Pixel offset from point x to tooltip edge
            tooltipXOffset: 1,

            // String - Template string for single tooltips
            tooltipTemplate: "<%if (label){%><%=label%>: <%}%><%= value %>",

            // String - Template string for single tooltips
            multiTooltipTemplate: "<%= value %>",

            //String - A legend template
           legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\" style=\"list-style-type: none;\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"-moz-border-radius:7px 7px 7px 7px; border-radius:7px 7px 7px 7px; list-style-type: none; margin-right:10px;width:15px;height:15px;display:inline-block;background-color:<%=datasets[i].strokeColor%>\"></span>- <%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>"
        };


        var myLineChart = new Chart(ctx).Line(dataSet, optionsGraph);
        var legend = document.createElement("div");
        legend.innerHTML = myLineChart.generateLegend();
        gr.appendChild(legend);

        graphCount++;
    }

}

function createGraphD(graphCanvas){
    if(graphCanvas.length != 0) {
        var datas = {};
        var gr = document.getElementById("graph");
        var head = document.createElement("h4");
        head.textContent = "График " + graphCount;
        var canvas = document.createElement("canvas");
        canvas.style.height = '600px';
        canvas.style.width = '3000px';
        gr.appendChild(head);
        gr.appendChild(canvas);
        var ctx = canvas.getContext("2d");


//            var randoms = [];
        for (var i = 0; i < graphCanvas.length; i++) {
//                randoms.push(getRandomInt(0, (fillColors.length)));
            datas[graphCanvas[i]] = [];
        }

        var label1 = [];
        deleteAll(label1);


        var datasets = [];
        deleteAll(datasets);


        for (var j = 0; j < archive.data.length; j++) {
            for (var i = 0; i < graphCanvas.length; i++) {

                datas[graphCanvas[i]].push(archive.data[j][graphCanvas[i]]);
            }
            label1.push(archive.data[j].time)

        }

        if (graphCanvas.length > 5){
            dataFill = false;
        } else {
            dataFill = true;
        }

        for (var a = 0; a < graphCanvas.length; a++) {

            datasets[a] = {
                label: document.getElementById(graphCanvas[a] + "Name").textContent,
                fillColor: fillColors[color],
                strokeColor: strokeColors[color],
                pointColor: strokeColors[color],
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(151,187,205,1)",
                data: createShortArrayD(datas[graphCanvas[a]], stepsArray)
            };
            if (color < fillColors.length) {
                color++;
            }
            else color = 0;
        }


        var dataSet = {
            labels: createShortLabel(label1, stepsArray),
            datasets: datasets
        };


        var optionsGraph = {

            animation: true,
            XScaleStep: 6,

            // Number - Number of animation steps
            animationSteps: 60,
// String - Animation easing effect
            // Possible effects are:
            // [easeInOutQuart, linear, easeOutBounce, easeInBack, easeInOutQuad,
            //  easeOutQuart, easeOutQuad, easeInOutBounce, easeOutSine, easeInOutCubic,
            //  easeInExpo, easeInOutBack, easeInCirc, easeInOutElastic, easeOutBack,
            //  easeInQuad, easeInOutExpo, easeInQuart, easeOutQuint, easeInOutCirc,
            //  easeInSine, easeOutExpo, easeOutCirc, easeOutCubic, easeInQuint,
            //  easeInElastic, easeInOutSine, easeInOutQuint, easeInBounce,
            //  easeOutElastic, easeInCubic]

            animationEasing: "easeOutQuart",
            // Boolean - If we should show the scale at all
            showScale: true,

            // Boolean - If we want to override with a hard coded scale
            scaleOverride: false,

            // ** Required if scaleOverride is true **
            // Number - The number of steps in a hard coded scale
            scaleSteps: 5,
            // Number - The value jump in the hard coded scale
            scaleStepWidth: 10,
            // Number - The scale starting value
            scaleStartValue: 40,
            ///Boolean - Whether grid lines are shown across the chart
            scaleShowGridLines: true,

            //String - Colour of the grid lines
            scaleGridLineColor: "rgba(0,0,0,.05)",

            //Number - Width of the grid lines
            scaleGridLineWidth: 1,
            // scaleSteps: 2,

            //Boolean - Whether to show horizontal lines (except X axis)
            scaleShowHorizontalLines: true,

            //Boolean - Whether to show vertical lines (except Y axis)
            scaleShowVerticalLines: true,

// Boolean - Whether to show labels on the scale
            scaleShowLabels: true,

            // Interpolated JS string - can access value
            scaleLabel: "<%=value%>",


            //Boolean - Whether the line is curved between points
            bezierCurve: false,

            //Number - Tension of the bezier curve between points
            bezierCurveTension: 0,

            //Boolean - Whether to show a dot for each point
            pointDot: true,

            //Number - Radius of each point dot in pixels
            pointDotRadius: 4,

            //Number - Pixel width of point dot stroke
            pointDotStrokeWidth: 1,

            //Number - amount extra to add to the radius to cater for hit detection outside the drawn point
            pointHitDetectionRadius: 20,

            //Boolean - Whether to show a stroke for datasets
            datasetStroke: true,

            //Number - Pixel width of dataset stroke
            datasetStrokeWidth: 3,

            //Boolean - Whether to fill the dataset with a colour
            datasetFill: dataFill,

            // Number - Pixel offset from point x to tooltip edge
            tooltipXOffset: 1,

            // String - Template string for single tooltips
            tooltipTemplate: "<%if (label){%><%=label%>: <%}%><%= value %>",

            // String - Template string for single tooltips
            multiTooltipTemplate: "<%= value %>",

            //String - A legend template
            legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\" style=\"list-style-type: none;\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"-moz-border-radius:7px 7px 7px 7px; border-radius:7px 7px 7px 7px; list-style-type: none; margin-right:10px;width:15px;height:15px;display:inline-block;background-color:<%=datasets[i].strokeColor%>\"></span>- <%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>"
        };


        var myLineChart = new Chart(ctx).Line(dataSet, optionsGraph);
        var legend = document.createElement("div");
        legend.innerHTML = myLineChart.generateLegend();
        gr.appendChild(legend);

        graphCount++;
    }

}
