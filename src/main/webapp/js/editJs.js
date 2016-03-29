/**
 * Created by Alex on 22.09.15.
 */


var baseId;
var id;
var  map;
var markers = [];
var pos;
var BaseIdArray;
var errCount = 0;
var sensor_baseId;



function loadTermData(){

	
    var main = document.getElementById('table_sensors_data');
    main.innerHTML = '';
main.className = 'data_table';

     sensor_baseId = {};

var buttonBack =  document.getElementById('buttonBack');
	buttonBack.innerHTML  = '<button id="update" onclick="createTableSelectBaseId()" class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab"><i class="material-icons">keyboard_backspace</i></button>';


    var form = document.createElement('div');
    var desc = document.createElement('div');
    desc.innerHTML =  '<h3> Заполните данные по объекту:</h3>';

    var table = document.createElement('TABLE');
    table.id = 'myTable';

    var rows = table.insertRow(-1);

    var cell1 =  rows.insertCell(-1);

    var cell2 =  rows.insertCell(-1);

    var name = document.createElement('div');
    name.id = 'name';

    var bas_id = document.createElement('div');
    bas_id.id = 'id';
    bas_id.hidden = true;



    var name1 = document.createElement('div');
    name1.innerHTML = 'Название точки: ';
    name1.id = 'name1';
    name1.appendChild(bas_id);
    var name2 = document.createElement('input');
    name2.type = 'text';
    name2.name = 'name';
    name2.id = 'name2';
    cell1.appendChild(name1);
    cell2.appendChild(name2);


//            Address of point

    var address = document.createElement('div');
    var address1 = document.createElement('div');
    address1.innerHTML = 'Адрес точки: ';
    var address2 = document.createElement('input');
    address2.type = 'text';
    address2.name = 'address';
    address2.size = 50;
    address2.id = 'address';



    rows = table.insertRow(-1);
    cell1 =  rows.insertCell(-1);
    cell2 =  rows.insertCell(-1);
    cell1.appendChild(address1);
    cell2.appendChild(address2);



    var buttonGetLoc = document.createElement('button');
    buttonGetLoc.id = 'search';
    buttonGetLoc.type = 'button';

    buttonGetLoc.setAttribute('onclick','getAdress()');
    buttonGetLoc.textContent = 'Найти на карте';


    rows = table.insertRow(-1);
    cell1 =  rows.insertCell(-1);
    cell1.setAttribute('colspan', '2');
    cell1.appendChild(buttonGetLoc);



    var mapDiv = document.createElement('div');
    mapDiv.id = 'map';




    rows = table.insertRow(-1);
    cell1 =  rows.insertCell(-1);
    cell1.setAttribute('colspan', '2');
    cell1.appendChild(mapDiv);






    var location = document.createElement('div');
    var location1 = document.createElement('div');
    location1 .innerHTML = 'Геопозиция точки:';
    location1.id = 'loc1';
    var location2 = document.createElement('input');
    location2.type = 'text';
    location2.name = 'location';
    location2.size = 19;
    location2.id = 'loc2';
    location.id = 'loc';

    var locMap = document.createElement('div');
//            locMap.id = 'map';


    rows = table.insertRow(-1);
    cell1 =  rows.insertCell(-1);
    cell2 =  rows.insertCell(-1);
    cell1.appendChild(location1);
    cell2.appendChild(location2);
    cell2.appendChild(locMap);


    var district = document.createElement('div');
    var district1 = document.createElement('div');
    district1.innerHTML = 'Район:';


    var district2  = document.createElement('SELECT');
    district2.name = 'district';
    district2.id = 'district';
    district2.setAttribute("id", "district");
    var districtOp  = document.createElement('OPTION');
    districtOp.setAttribute("value", "0");
    var d0 = document.createTextNode(0);

    var districtOp2  = document.createElement('OPTION');
    districtOp2.setAttribute("value", "1");
    var d1 = document.createTextNode(1);
    var districtOp3  = document.createElement('OPTION');
    districtOp3.setAttribute("value", "2");
    var d2 = document.createTextNode(2);
    var districtOp4  = document.createElement('OPTION');
    districtOp4.setAttribute("value", "3");
    var d3 = document.createTextNode(3);
    districtOp.appendChild(d0);
    districtOp2.appendChild(d1);
    districtOp3.appendChild(d2);
    districtOp4.appendChild(d3);

    district2.appendChild(districtOp);
    district2.appendChild(districtOp2);
    district2.appendChild(districtOp3);
    district2.appendChild(districtOp4);





    rows = table.insertRow(-1);
    cell1 =  rows.insertCell(-1);
    cell2 =  rows.insertCell(-1);
    cell1.appendChild(district1);
    cell2.appendChild(district2);


    var status1 = document.createElement('div');
    status1.innerHTML = 'Работает ';
    var status2 = document.createElement('input');
    status2.type = 'checkbox';
    status2.name = 'status';
    status2.checked = true;
    status2.id = 'status';



    rows = table.insertRow(-1);
    cell1 =  rows.insertCell(-1);
    cell2 =  rows.insertCell(-1);
    cell1.appendChild(status1);
    cell2.appendChild(status2);


    var sensors = document.createElement('div');
    var sensorsName = document.createElement('div');
    sensorsName.innerHTML = '<b>Натройки датчиков:</b>';
    sensors.appendChild(sensorsName);



    rows = table.insertRow(-1);
    cell1 =  rows.insertCell(-1);
    cell1.appendChild(sensors);
    cell2 =  rows.insertCell(-1);
    rows = table.insertRow(-1);
    cell1 =  rows.insertCell(-1);
    cell1.innerHTML= '</br>';
    cell2 =  rows.insertCell(-1);


//            Добавление таблицы для температурных датчиков

    var typeSensor = document.createElement('caption');
    typeSensor.innerHTML = '<b>Температурные датчики</b>';
    var tableSensor  = document.createElement('TABLE');
    tableSensor.id = 'sensors_table';
//            tableSensor.setAttribute('style','border: 1px solid grey; border-collapse: collapse;');

    rows = table.insertRow(-1);
    cell1 =  rows.insertCell(-1);
    cell2 =  rows.insertCell(-1);
//            cell1.appendChild(typeSensor);
    cell1.setAttribute('align','char;');
    cell1.setAttribute('colspan', '2');
    cell1.appendChild(tableSensor);
    tableSensor.appendChild(typeSensor);


    var rowsSensor= tableSensor.insertRow(-1);

    var cellSensor1 =  rowsSensor.insertCell(-1);
    cellSensor1.id = 'numSensor'
    cellSensor1.textContent = '#';
    cellSensor1.setAttribute('style','text-align:right;');
    var cellSensor2 =  rowsSensor.insertCell(-1);
    cellSensor2.textContent = 'Название датчика';

    var cellSensor3 =  rowsSensor.insertCell(-1);
    cellSensor3.textContent = 'Min';
    cellSensor3.setAttribute('style','text-align:center;');
    var cellSensor4 =  rowsSensor.insertCell(-1);
    cellSensor4.textContent = 'Max';
    cellSensor4.setAttribute('style','text-align:center;');
    var cellSensor5 =  rowsSensor.insertCell(-1);
    cellSensor5.textContent = 'Задествован';
    cellSensor5.setAttribute('style','text-align:center;');




//          Создание таблицы для температурных датчиков

    for (var i = 0; i < 24; i++) {
        var num = i +1;



        var sensorNum = document.createElement('div');

        sensorNum.innerHTML = ''+ num;
        var sensorId = document.createElement('div');
        sensorId.id = 'sensor_id' + num;
        sensorId.hidden = true;

        sensorNum.appendChild(sensorId);


        var sensorName = document.createElement('input');
        sensorName.type = 'text';
        sensorName.name = 'sensor_name_' + num;
        sensorName.id = 'sensor_name_' + num;
        sensorName.size = 50;



        var sensorMin = document.createElement('input');
        sensorMin.type = 'number';
        sensorMin.max = 200;
        sensorMin.min = -50;
        sensorMin.name = 'sensor_min_' + num;
        sensorMin.id = 'sensor_min_' + num;
        sensorMin.size = 5;



        var sensorMax = document.createElement('input');
        sensorMax.type = 'number';
        sensorMax.max = 200;
        sensorMax.min = -50;
        sensorMax.name = 'sensor_max_' + num;
        sensorMax.id = 'sensor_max_' + num;
        sensorMax.size = 5;



        var sensorOn = document.createElement('input');
        sensorOn.type = 'checkbox';
        sensorOn.name = 'sensor_on_' + num;
        sensorOn.id = 'sensor_on_' + num;
        sensorName.length;




        var rowsSensor= tableSensor.insertRow(-1);
        rowsSensor.id = 'sensor_' + num;

        var cellSensor1 =  rowsSensor.insertCell(-1);
        cellSensor1.appendChild(sensorNum);
        cellSensor1.setAttribute('style','text-align:right;');
        var cellSensor2 =  rowsSensor.insertCell(-1);
        cellSensor2.appendChild(sensorName);
        var cellSensor3 =  rowsSensor.insertCell(-1);
        cellSensor3.appendChild(sensorMin);
        var cellSensor4 =  rowsSensor.insertCell(-1);
        cellSensor4.appendChild(sensorMax);
        var cellSensor5 =  rowsSensor.insertCell(-1);
        cellSensor5.appendChild(sensorOn);
        cellSensor5.setAttribute('style','text-align:center;');


    }


//            Пропуск 2х ячеек для отсупа между типами датчиков
    rows = table.insertRow(-1);
    cell1 =  rows.insertCell(-1);
    cell1.innerHTML= '</br>'
    cell2 =  rows.insertCell(-1);
    rows = table.insertRow(-1);
    cell1 =  rows.insertCell(-1);
    cell1.innerHTML= '</br>';
    cell2 =  rows.insertCell(-1);


//            Добавление таблицы для Дискретных датчиков

    var typeSensor1 = document.createElement('caption');
    typeSensor1.innerHTML = '<b>Дискретные датчики</b>';
    var tableSensor1  = document.createElement('TABLE');
    tableSensor1.id = 'sensors_table';

    typeSensor1.align = 'center';

    rows = table.insertRow(-1);
    cell1 =  rows.insertCell(-1);
    cell2 =  rows.insertCell(-1);


    cell1.setAttribute('align','char;');
    cell1.setAttribute('colspan', '2');
    cell1.appendChild(tableSensor1);
    tableSensor1.appendChild(typeSensor1);


    var rowsSensor1 = tableSensor1.insertRow(-1);

    var cellSensorDT1 =  rowsSensor1.insertCell(-1);
    cellSensorDT1.id = 'numSensor'
    cellSensorDT1.textContent = '#';
    var cellSensorDT2 =  rowsSensor1.insertCell(-1);
    cellSensorDT2.textContent = 'Название датчика';
    var cellSensorDT3 =  rowsSensor1.insertCell(-1);
    cellSensorDT3.textContent = 'Задествован';
    cellSensorDT3.setAttribute('style','text-align:center;');
    var cellSensorDT4 =  rowsSensor1.insertCell(-1);
    cellSensorDT4.textContent = 'Инверт.';
    cellSensorDT4.setAttribute('style','text-align:center;');





//            Создание таблицы для дискретных датчиков
    for (var i = 0; i < 16; i++) {
        var num = i +1;



        var sensorNum = document.createElement('div');

        sensorNum.textContent = ''+ num;
        var sensorId = document.createElement('div');
        sensorId.id = 'sensor_dt_id' + num;
        sensorId.hidden = true;

        sensorNum.appendChild(sensorId);


        var sensorName = document.createElement('input');
        sensorName.type = 'text';
        sensorName.name = 'sensor_dt_name_' + num;
        sensorName.id = 'sensor_dt_name_' + num;
        sensorName.size = 50;




        var sensorOn = document.createElement('input');
        sensorOn.type = 'checkbox';
        sensorOn.name = 'sensor_dt_on_' + num;
        sensorOn.id = 'sensor_dt_on_' + num;



        var sensorInverse = document.createElement('input');
        sensorInverse.type = 'checkbox';

        sensorInverse.name = 'sensor_inverse_' + num;
        sensorInverse.id = 'sensor_inverse_' + num;





        var rowsSensor1= tableSensor1.insertRow(-1);
        rowsSensor.id = 'sensor_' + num;

        var cellSensorDT1 =  rowsSensor1.insertCell(-1);
        cellSensorDT1.appendChild(sensorNum);
        var cellSensorDT2 =  rowsSensor1.insertCell(-1);
        cellSensorDT2.appendChild(sensorName);
        var cellSensorDT3 =  rowsSensor1.insertCell(-1);
        cellSensorDT3.appendChild(sensorOn);
        cellSensorDT3.setAttribute('style','text-align:center;');
        var cellSensorDT4 =  rowsSensor1.insertCell(-1);
        cellSensorDT4.appendChild(sensorInverse);
        cellSensorDT4.setAttribute('style','text-align:center;');



    }





var button_panel = document.getElementById('button_panel');
    // button_panel.innerHTML ='<button id="update" class="update"  onclick="loadTermData()">Обновить</button><button id="saveData" class="saveData" onclick="sentData()">Сохранить</button>';
	 // button_panel.className = 'button_panel';
	 button_panel.innerHTML ='<button class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab" onclick="loadTermData()" style="padding: 28px;margin: 15px" ><i class="material-icons">autorenew</i></button><button class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mdl-button--colored" onclick="sentData()" style="padding: 28px;margin: 15px" ><i class="material-icons">save</i></button>';


    form.appendChild(desc);


    form.appendChild(table);

    main.appendChild(form);


    loadFromServer();
}



function loadFromServer(){

    getFromServer();
    fillSensorTable(BaseIdArray);

}


function getFromServer(){



     $.ajax( "http://52.11.54.112:5555/term/getset", {
		  // $.ajax( "http://localhost:5555/term/getset", {

        beforeSend: function (xhr) {
            xhr.setRequestHeader('json', 'true');
            xhr.setRequestHeader('type', 'get');
        },

        cache: true,
        type: 'POST',
        data: {},
        contentType: 'application/x-www-form-urlencoded',
        dataType: "json",
        success: success,
        error: error,
        async: false

    } );


    function success( forecastData ) {

        BaseIdArray = forecastData.BaseID;

    }

    function error(){
        errorHandler('Есть проблемы с получением данных.');
    }


}


//  Заполнение таблицы по выбранной точке
function fillSensorTable(BaseIdArray){


    var name2 = document.getElementById('name2');
    var address2 = document.getElementById('address');
    var location2 = document.getElementById('loc2');
    var district2 = document.getElementById('district');
    var status2 = document.getElementById('status');

    for (var i = 0 ; i < BaseIdArray.length; i++) {
        if (BaseIdArray[i].BaseID == baseId) {
            sensor_baseId = BaseIdArray[i];


        }
    }



    if (sensor_baseId != undefined && sensor_baseId.ID != undefined) {

        var bas_id = document.getElementById('id');
        bas_id.value = sensor_baseId.ID;

        id = sensor_baseId.ID;
    } else {
        id = undefined;
    }

    if (sensor_baseId.name !== undefined)
        name2.value = sensor_baseId.name;

    if (sensor_baseId.address !== undefined)
        address2.value = sensor_baseId.address;

    if (sensor_baseId.location !== undefined)
        location2.value = sensor_baseId.location;

    if (sensor_baseId.district !== undefined)
        district2.value = sensor_baseId.district;


    if (sensor_baseId.status !== undefined)
        status2.checked = sensor_baseId.status;

    if (sensor_baseId.sensors !== undefined) {
        for (var i = 0 ; i < sensor_baseId.sensors.length; i++){
            if (sensor_baseId.sensors[i].type === 'tm') {
                var num = sensor_baseId.sensors[i].sensorId;
                var name = document.getElementById('sensor_name_'+num);
                name.value = sensor_baseId.sensors[i].name;

                var idS = document.getElementById('sensor_id'+num);
                idS.value = sensor_baseId.sensors[i].id;

                var min = document.getElementById('sensor_min_'+num);
                min.value = sensor_baseId.sensors[i].min;

                var max = document.getElementById('sensor_max_'+num);
                max.value = sensor_baseId.sensors[i].max;

                var used = document.getElementById('sensor_on_'+num);
                used.checked = sensor_baseId.sensors[i].used;



            } else if(sensor_baseId.sensors[i].type === 'dt'){
                var num = sensor_baseId.sensors[i].sensorId;
                var name = document.getElementById('sensor_dt_name_'+num);
                name.value = sensor_baseId.sensors[i].name;

                var idS = document.getElementById('sensor_dt_id'+num);
                idS.value = sensor_baseId.sensors[i].id;

                var min  = sensor_baseId.sensors[i].min;

                var max = sensor_baseId.sensors[i].max;


                var inverse = document.getElementById('sensor_inverse_'+num);

                inverse.checked = min > max;


                var used = document.getElementById('sensor_dt_on_'+num);
                used.checked = sensor_baseId.sensors[i].used;

            }
        }

    }


}


function sentData() {

    var formSens = document.forms.data;

    var data = { };
    if (id !== undefined)
        data.ID = id;
//                document.getElementById('id').value;
    data.BaseID =  baseId;
    data.name = formSens.elements.namedItem('name').value;
    data.location = formSens.elements.namedItem('location').value;
    data.address = formSens.elements.namedItem('address').value;
    data.district = ~~formSens.elements.namedItem('district').value;
    data.status = formSens.elements.namedItem('status').checked;




    var sensors = [];


    for (var i = 0; i < 24; i++) {
//
        var num = i + 1;
        var used = formSens.elements.namedItem('sensor_on_' + num).checked;
        var name = document.getElementById('sensor_name_' + num).value;
        name = name.trim();
        if (used || document.getElementById('sensor_name_' + num).value) {

            var sens = {};
            var idS = document.getElementById('sensor_id'+num).value;
            if (idS !== undefined)
                sens.id = idS;

            if (id !== undefined)
                sens.baseId = id;
            sens.sensorId = i + 1;
            sens.type = 'tm';
            sens.name = document.getElementById('sensor_name_' + num).value;
            sens.min = ~~document.getElementById('sensor_min_' + num).value;
            sens.max = ~~document.getElementById('sensor_max_' + num).value;
            sens.used = used;
//                    sens.used = document.getElementById('sensor_on_' + num).checked;

            sensors[i] = sens;

        }


    }

    var countTm = sensors.length;

    for (var i = 0; i < 16; i++) {
        var num =  i + 1;

        var currentSens = countTm + i;

        var used = formSens.elements.namedItem('sensor_dt_on_' + num).checked;
        if (used || document.getElementById('sensor_dt_name_' + num).value) {


            var sens = {};
            var idS = document.getElementById('sensor_dt_id'+num).value;
            if (idS !== undefined)
                sens.id = idS;

            if (id !== undefined)
                sens.baseId = id;
            sens.sensorId = i + 1;
            sens.type = 'dt';
            sens.name = document.getElementById('sensor_dt_name_' + num).value;
            if (!document.getElementById('sensor_inverse_' + num).checked) {
                sens.min = 0;
                sens.max = 1;
            } else {
                sens.min = 1;
                sens.max = 0;
            }

            sens.used = used;
//                    sens.used = document.getElementById('sensor_on_' + num).checked;

            sensors[currentSens] = sens;



        }
    }

    data.sensors = sensors;

    console.log(data);


    sendToServer(data);





}




function sendToServer( data){


    $.ajax( "http://52.11.54.112:5555/term/getset", {
		  // $.ajax( "http://localhost:5555/term/getset", {
        beforeSend: function (xhr) {
            xhr.setRequestHeader('json', 'true');
            xhr.setRequestHeader('type', 'set');
        },
        scriptCharset: "utf-8",
        contentType: 'application/json; charset=UTF-8',
        cache: true,
        type: 'POST',
        data: JSON.stringify(data),
        dataType: "json",
        error: error,
        success: success
    } );


    function success( forecastData ) {
if(forecastData.result === "ok") {
			successHandler("Добавлено!");
//                alert("Добавлено!");
        loadTermData();
        errCount = 0;
		} else {
			error();
		}

    }

    function error(){
        if (errCount < 30){
            errCount++;
            sendToServer( data);
        } else {
            errorHandler('Есть проблемы с передачей данных на сервер.');
        }
    }


}




//        Вывод информации об ошибке с текстом ошибки

function errorHandler(data) {
    var alert = document.getElementById('message');
    var error = document.createElement('div');
    error.setAttribute('class','alert alert-danger');
    error.setAttribute('role','alert');
    error.setAttribute('style','padding: 15px');
    alert.appendChild(error);

    var button =  document.createElement('button');
    button.type = "button";
    button.setAttribute('class','close');
    button.setAttribute('data-dismiss',  'alert');
    button.setAttribute('aria-label', 'Close');
//            error.appendChild(button);

    var span = document.createElement('span');
    span.setAttribute('aria-hidden', 'false');
    var textSpan = document.createTextNode('&times;');
//            button.appendChild(span);

    var strong = document.createElement('strong');
    var textStr = document.createTextNode(data);
    strong.appendChild(textStr);
    error.appendChild(strong);
    error.focus();
    error.scrollIntoView();
    alert.addEventListener('click', function(){
        alert.innerHTML='';
    });

}



//        Вывод информации об успешном добавлении данных

function successHandler(data) {
    var alert = document.getElementById('message');
    var error = document.createElement('div');
    error.setAttribute('class','alert alert-success');
    error.setAttribute('role','alert');
    error.setAttribute('style','padding: 15px');
    alert.appendChild(error);

    var button =  document.createElement('button');
    button.type = "button";
    button.setAttribute('class','close');
    button.setAttribute('data-dismiss',  'alert');
    button.setAttribute('aria-label', 'Close');
//            error.appendChild(button);

    var span = document.createElement('span');
    span.setAttribute('aria-hidden', 'false');

    var textSpan = document.createTextNode('&times;');
    button.appendChild(span);

    var strong = document.createElement('strong');
    var textStr = document.createTextNode(data);
    strong.appendChild(textStr);
    error.appendChild(strong);
    error.focus();
    error.scrollIntoView();
    alert.addEventListener('click', function(){
        alert.innerHTML='';
    });


}



function getUniqBaseId(){



    $.ajax( "http://52.11.54.112:5555/term/getset", {
		  // $.ajax( "http://localhost:5555/term/getset", {
        beforeSend: function (xhr) {
            xhr.setRequestHeader('json', 'true');
            xhr.setRequestHeader('type', 'basename');
        },
        scriptCharset: "utf-8",
        contentType: 'application/json; charset=UTF-8',
        cache: true,
        type: 'POST',
        data: {},
        dataType: "json",
        success: success,
        error: error
    } );


    function success( forecastData ) {
        var baseIdSelect = document.getElementById('baseIdSelect');

        var baseIdcont  = document.createElement('OPTION');
        baseIdcont.setAttribute("value", '');
        var name = document.createTextNode('');
        baseIdcont.appendChild(name);
        baseIdSelect.appendChild(baseIdcont);

        for (var i =0; i < forecastData.BaseID.length; i++){
            var titleBaseId = forecastData.BaseID[i];
            var baseIdcont  = document.createElement('OPTION');
            baseIdcont.setAttribute("value", '');
            baseIdcont.setAttribute("value", titleBaseId);
            var name = document.createTextNode(titleBaseId);
            baseIdcont.appendChild(name);
            baseIdSelect.appendChild(baseIdcont);

        }


        baseIdSelect.addEventListener("change", function() {

            baseId = baseIdSelect.options[baseIdSelect.selectedIndex].text;
            if (baseId !== ''){
                loadTermData();

            } else {

                var main = document.getElementById('table_sensors_data');
                main.innerHTML = '';
            }




        });




    }

    function error() {
//                alert( "Есть проблемы с получением данных об существующих объектах" );
        errorHandler("Есть проблемы с получением данных об существующих объектах");

    }
}


function initMap(){



    var testOp = {
        zoom: 12,
        center: {lat: 48.464717, lng: 35.046183}
    };


    var block = document.getElementById("map");
    var centerLatLng = new google.maps.LatLng('48.4647,35.0461');

    map = new google.maps.Map(block, testOp);


    map.addListener('dblclick', function(even) {
        addMarkerWithTimeout(even.latLng, 0);
        map.panTo(even.latLng);
    });

    var b_width = document.documentElement.clientWidth -150;
    var b_height = document.documentElement.clientHeight - 80;
    block.style.width = b_width + "px"; // Устанавливаем новую ширину блока
    block.style.height = b_height + "px"; // Устанавливаем новую высоту блока



}


function addMarkerWithTimeout(position, timeout) {
    window.setTimeout(function() {
        var marker = new google.maps.Marker({
            position: position,
            map: map,
            animation: google.maps.Animation.DROP,
            draggable:true

        });

        map.panTo(position);


        var Message = document.createElement('div');
        var textMess = document.createElement('div');
        textMess.textContent = 'Выбрать данную точку?';
        var buttonOk = document.createElement('button');
        buttonOk.setAttribute('onclick','setAddress()');
//                buttonOk.addEventListener('onclick', setAddress(), true);

        buttonOk.id = 'ok_button';
        buttonOk.setAttribute('value','Сохранить');
        buttonOk.textContent = 'Да';
        Message.appendChild(textMess);
        Message.appendChild(buttonOk);
        attachSecretMessage(marker, Message);


        markers.push(marker);
    }, timeout);

}



function attachSecretMessage(marker, Message) {
    var infowindow = new google.maps.InfoWindow({
        content: Message
    });

    marker.addListener('click', function() {
        pos =  marker.getPosition().toString();
        console.log(pos);
        infowindow.open(marker.get('map'), marker);
    });
}


function setAddress(){
    var div = document.getElementById('loc2');
//            var  pos =  marker.getPosition().toString();
    loc = pos.toString();
    loc = loc.replace('(','');
    loc = loc.replace(' ','');
    loc = loc.replace(')','');

    var splLoc = loc.split(',');

    loc = splLoc[0].substring(12, -1) + ',' + splLoc[1].substring(12, -1);
    div.value = loc;
    console.log(loc);

    var map = document.getElementById('map');
    map.innerHTML = '';

    map.style.width = 0 + "px"; // Устанавливаем новую ширину блока
    map.style.height = 0 + "px"; // Устанавливаем новую высоту блока





}







function getAdress() {



    var data = {};
    clearMarkers();
    data.address = document.getElementById('address').value;
    data.sensor = false;
    data.language = 'ru';
    console.log(data.address);


    $.ajax("http://maps.googleapis.com/maps/api/geocode/json", {


        cache: true,
        type: 'GET',
        data: data,
        contentType: 'application/x-www-form-urlencoded',
        dataType: "json",
        success: successMap,
        error: error

    });


    function successMap(searchAddressResult) {
        console.log(searchAddressResult);
        initMap();

        var resultAddress = searchAddressResult.results;






        for  (var i = 0; i < resultAddress.length; i++){

            var marker = resultAddress[i].geometry.location;

            addMarkerWithTimeout(marker, i * 400);

        }





    }

    function error() {

    }



    function clearMarkers() {
        for (var i = 0; i < markers.length; i++) {
            markers[i].setMap(null);
        }
        markers = [];
    }





}





    function createTableSelectBaseId(){

        getFromServer();


        var main = document.getElementById('table_sensors_data');
        main.innerHTML = '';
		  main.className = "";
		  var buttonBack =  document.getElementById('buttonBack');
		  buttonBack.innerHTML = "";


        var nameOfTable = document.createElement('caption');
        var nameOfTableDiv = document.createElement('h3');
        nameOfTableDiv.textContent = 'Действующие объекты';
        nameOfTable.appendChild(nameOfTableDiv);
        var table = document.createElement('TABLE');
        table.id = 'myTable';
        table.className = 'table_edit_base';
        table.appendChild(nameOfTable);


        var th1 = document.createElement("TH");
        var th2 = document.createElement("TH");
        var th3 = document.createElement("TH");
        var th4 = document.createElement("TH");
        th1.textContent = '#';
        th2.textContent = 'Название';
        th3.textContent = 'Адрес';
        th4.textContent = 'Base_id';

        table.appendChild(th1);
        table.appendChild(th2);
        table.appendChild(th3);
        table.appendChild(th4);



        for (var i = 0; i < BaseIdArray.length; i++){
            var num = i +1;
            rows = table.insertRow(-1);
            cell1 =  rows.insertCell(-1);
            cell2 =  rows.insertCell(-1);
            cell3 =  rows.insertCell(-1);
            cell4 =  rows.insertCell(-1);
            rows.id = 'table_row_' + i;
            cell1.textContent = num;
            cell2.textContent = BaseIdArray[i].name;
            cell3.textContent = BaseIdArray[i].address;
            cell4.textContent = BaseIdArray[i].BaseID;

            var base = BaseIdArray[i].BaseID;

            var clickHandler =  function(base) {
                return function() {

                    baseId =  base;
                    console.log(baseId);

                    loadTermData();
                };

            };
            rows.onclick = clickHandler(base);


        }
        main.appendChild(table);

        var button_panel = document.getElementById('button_panel');
      button_panel.innerHTML = ' <button class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mdl-button--colored" onclick="getNewBaseId()" style="padding: 28px;margin: 15px" id="addNewBase" disabled><i class="material-icons">add</i></button>';
		  
		  getBaseId();

    }
    
    
	 
    function getBaseId(){



        $.ajax( "http://52.11.54.112:5555/term/getset", {
            beforeSend: function (xhr) {
                xhr.setRequestHeader('json', 'true');
                xhr.setRequestHeader('type', 'basename');
            },
            scriptCharset: "utf-8",
            contentType: 'application/json; charset=UTF-8',
            cache: true,
            type: 'POST',
            data: {},
            dataType: "json",
            success: successBase,
            error: error
        } );
		  
		  
		  function successBase( successBase ) {
			  
			 
			  
			  if (BaseIdArray.length != successBase.BaseID.length){
			  	
				  document.getElementById("addNewBase").disabled = false;
				
			  }
			  
		  }
		  
		  
        function error() {

            errorHandler("Есть проблемы с получением данных об существующих объектах");

        }
		  
		  
	  }
	  
	 
    function getUniqBaseId(){



        $.ajax( "http://52.11.54.112:5555/term/getset", {
            beforeSend: function (xhr) {
                xhr.setRequestHeader('json', 'true');
                xhr.setRequestHeader('type', 'basename');
            },
            scriptCharset: "utf-8",
            contentType: 'application/json; charset=UTF-8',
            cache: true,
            type: 'POST',
            data: {},
            dataType: "json",
            success: success,
            error: error
        } );


        function success( forecastData ) {
            //var baseIdSelect = document.getElementById('baseIdSelect');

            var unicBaseId =  forecastData.BaseID;
            console.log(unicBaseId);

            var main = document.getElementById('table_sensors_data');
            main.innerHTML = '';


            var nameOfTable = document.createElement('caption');
            var nameOfTableDiv = document.createElement('h3');
            nameOfTableDiv.textContent = 'Новые объекты';
            nameOfTable.appendChild(nameOfTableDiv);
            var table = document.createElement('TABLE');
            table.id = 'myTable';
            table.className = 'table_edit_base';
            table.style.width = "400px";
            table.appendChild(nameOfTable);


            var th1 = document.createElement("TH");
            var th2 = document.createElement("TH");
            th1.textContent = '#';
            th2.textContent = 'Base_id';

            table.appendChild(th1);

            table.appendChild(th2);


            var num = 1;
            for (var i = 0; i < unicBaseId.length; i++) {


                function containsBaseId(element, index, array) {
                    return element.BaseID === unicBaseId[i];
                }

                if (!BaseIdArray.some(containsBaseId)){





                    rows = table.insertRow(-1);
                    cell1 = rows.insertCell(-1);
                    cell2 = rows.insertCell(-1);

                    rows.id = 'table_row_' + i;
                    cell1.textContent = num;
                    cell2.textContent = unicBaseId[i];


                    var base = unicBaseId[i];

                    var clickHandler = function (base) {
                        return function () {

                            baseId = base;
                            console.log(baseId);

                            loadTermData();
                        };

                    };
                    rows.onclick = clickHandler(base);

                    num++;


                }
                main.appendChild(table);

                var button_panel = document.getElementById('button_panel');
                button_panel.innerHTML = '';

            }



        }

        function error() {
//                    alert( "Есть проблемы с получением данных об существующих объектах" );
            errorHandler("Есть проблемы с получением данных об существующих объектах");

        }





    }

    function getNewBaseId() {



        getFromServer();

        getUniqBaseId();




    }
    






