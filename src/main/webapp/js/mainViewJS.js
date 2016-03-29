/**
 * Created by Alex on 12.09.15.
 */


var baseIdArray = [];
var dataBaseId;
var baseId;

//      Метод получения текущих данных с сервера
function getDataFronServer(){



    $.ajax( "http://52.11.54.112:5555/term/getset", {
           // $.ajax( "http://localhost:5555/term/getset", {

        beforeSend: function (xhr) {
            xhr.setRequestHeader('json', 'true');
            xhr.setRequestHeader('type', 'baseid_whith_set_data');
        },
        async: false,
        cache: true,
        type: 'POST',
        data: {},
        contentType: 'application/x-www-form-urlencoded',
        dataType: "json",
        success: success,
        error: error

    } );


    function success( BaseID_Data ) {


         dataBaseId = BaseID_Data.BaseID_Data;



        for (var i = 0; i < dataBaseId.length; i++) {

            var baseWith = {};
            baseWith.id = dataBaseId[i].baseid.BaseID;
            baseWith.name = dataBaseId[i].baseid.name;
            baseWith.location = dataBaseId[i].baseid.location;
            baseWith.address = dataBaseId[i].baseid.address;
            baseWith.data = dataBaseId[i].data;
            baseWith.sensors = dataBaseId[i].baseid.sensors;
            baseWith.location = dataBaseId[i].baseid.location;
            baseWith.status = dataBaseId[i].baseid.status;
            baseWith.errors = countErrors(dataBaseId[i]);
            baseIdArray.push(baseWith);

            }


    }


    function error() {
        getDataFronServer();

    }

}


function onLoad(){

    getDataFronServer();
    if (baseId !== 'null'){
        setDataOnTable();

    } else {
    createTableSelectBaseId();
 }


}



function createTableSelectBaseId(){




    var main = document.getElementById('table_sensors_data');
    main.innerHTML = '';



    var nameOfTable = document.createElement('caption');
    var nameOfTableDiv = document.createElement('h3');
    nameOfTableDiv.textContent = 'Действующие объекты';
    nameOfTable.appendChild(nameOfTableDiv);
    var table = document.createElement('TABLE');
    table.id = 'myTable';
    table.className = 'table_view_base';
    table.appendChild(nameOfTable);
	 table.style.width = "60%";


    var th1 = document.createElement("TH");
    var th2 = document.createElement("TH");
    var th3 = document.createElement("TH");
    var th4 = document.createElement("TH");
    th1.textContent = '#';
    th2.textContent = 'Название';
    th3.textContent = 'Адрес';
    th4.textContent = 'Кол-во аварий';

    table.appendChild(th1);
    table.appendChild(th2);
    table.appendChild(th3);
    table.appendChild(th4);



    for (var i = 0; i < baseIdArray.length; i++){
        var num = i +1;
        rows = table.insertRow(-1);
        cell1 =  rows.insertCell(-1);
        cell2 =  rows.insertCell(-1);
        cell3 =  rows.insertCell(-1);
        cell4 =  rows.insertCell(-1);
        rows.id = 'table_row_' + i;
        cell1.textContent = num;
		  cell1.style.textAlign =  "center";
        cell2.textContent = baseIdArray[i].name;
        cell3.textContent = baseIdArray[i].address;
        cell4.textContent = baseIdArray[i].errors;
		  cell4.style.textAlign =  "center";
        if (baseIdArray[i].errors != 0) {
            rows.className = 'errors';
        }

        var base = baseIdArray[i].id;

        var clickHandler =  function(base) {
            return function() {

                baseId =  base;
                console.log(baseId);

                setDataOnTable();
            };

        };
        rows.onclick = clickHandler(base);


    }
    main.appendChild(table);


}


function setSelector(){

    var selector = document.getElementById('selector');

    var baseIdSelect = document.createElement('SELECT');


    var baseIdcont  = document.createElement('OPTION');
    baseIdcont.setAttribute("value", '');
    var name = document.createTextNode('');
    baseIdcont.appendChild(name);
    baseIdSelect.appendChild(baseIdcont);
    //baseIdcont.disabled = true;

    for (var i =0; i < baseIdArray.length; i++){
        var titleBaseId = baseIdArray[i].name;
        var basId = baseIdArray[i].id;
        var baseIdcont  = document.createElement('OPTION');
        baseIdcont.setAttribute("value", basId);
        var name = document.createTextNode(titleBaseId);
        baseIdcont.appendChild(name);
        baseIdSelect.appendChild(baseIdcont);

    }


    baseIdSelect.addEventListener("change", function() {

        baseId = baseIdSelect.options[baseIdSelect.selectedIndex].value;
        if (baseId !== ''){
            setDataOnTable();

        }




    });

    if (baseId !== undefined){

        baseIdSelect.value = baseId;
        setDataOnTable();

    }

    selector.appendChild(baseIdSelect);
}


function setDataOnTable(){
    var main = document.getElementById('table_sensors_data');
    main.innerHTML = '';


    var buttonBack = document.createElement('button');
    buttonBack.onclick = function() {createTableSelectBaseId()};
    buttonBack.innerHTML = '<i class="material-icons">keyboard_backspace</i>';
    main.appendChild(buttonBack);
	 buttonBack.className = "mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab";
	 buttonBack.style.top = "100px";
	 buttonBack.style.position = "fixed";

    var table = document.createElement('TABLE');
	 table.style.width = "60%";
	 table.style.top = "120px";

    table.className = 'data_table';

    //if (baseId != undefined) {
        for (var i = 0; i < baseIdArray.length; i++){

            if (baseId == baseIdArray[i].id){


                var rows = table.insertRow(-1);
                var cell1 =  rows.insertCell(-1);
                var cell2 =  rows.insertCell(-1);
					 // cell1.style.width = "50%";
 // 					 cell2.style.width = "50%";

                var name = document.createElement('div');
                name.id = 'name';

                var bas_id = document.createElement('div');
                bas_id.id = 'id';
                bas_id.hidden = true;



                var name1 = document.createElement('div');
                name1.innerHTML = 'Название точки: ';
                name1.id = 'name1';
                name1.appendChild(bas_id);
                var sel = document.getElementById('selector');
                var name2 = document.createElement('div');
                    //sel.cloneNode(true);
                 name2.id = 'name2';
                //sel.innerHTML = '';
                //setSelector();


                name2.innerHTML = baseIdArray[i].name;
                cell1.appendChild(name1);
                cell2.appendChild(name2);


                //Address of point

                var address = document.createElement('div');
                var address1 = document.createElement('div');
                address1.innerHTML = 'Адрес точки: ';
                var address2 = document.createElement('input');
                address2.type = 'text';
                address2.name = 'address';
                address2.size = 50;
                address2.id = 'address';
                address2.disabled = true;
                address2.value = baseIdArray[i].address;



                rows = table.insertRow(-1);
                cell1 =  rows.insertCell(-1);
                cell2 =  rows.insertCell(-1);
                cell1.appendChild(address1);
                cell2.appendChild(address2);



                var location = document.createElement('div');
                var location1 = document.createElement('div');
                location1 .innerHTML = 'Геопозиция точки:';
                location1.id = 'loc1';
                var location2 = document.createElement('input');
                location2.type = 'text';
                location2.name = 'location';
                location2.size = 19;
                location2.id = 'loc2';
                location2.disabled = true;
                location2.value = baseIdArray[i].location;
                location.id = 'loc';

                var locMap = document.createElement('div');
//            locMap.id = 'map';


                rows = table.insertRow(-1);
                cell1 =  rows.insertCell(-1);
                cell2 =  rows.insertCell(-1);
                cell1.appendChild(location1);
                cell2.appendChild(location2);
                cell2.appendChild(locMap);



                var status1 = document.createElement('div');
                status1.innerHTML = 'Работает ';
                var status2 = document.createElement('input');
                status2.type = 'checkbox';
                status2.name = 'status';
                status2.checked = baseIdArray[i].status;
                status2.disabled = true;



                rows = table.insertRow(-1);
                cell1 =  rows.insertCell(-1);
                cell2 =  rows.insertCell(-1);
                cell1.appendChild(status1);
                cell2.appendChild(status2);


                var sensors = document.createElement('div');
                var sensorsName = document.createElement('div');
                sensorsName.innerHTML = '<b>Текущие показания:</b>';
                sensors.appendChild(sensorsName);



                rows = table.insertRow(-1);
                cell1 =  rows.insertCell(-1);
                cell1.appendChild(sensors);
					 var lastT = document.createElement('div');
					 var timeData = new Date(baseIdArray[i].data.time);
					 var lastTimeData = document.createElement('label');
					 lastTimeData.textContent = timeData.toLocaleTimeString() + ", " + timeData.toLocaleDateString();
					 lastTimeData.style.marginRight = "40px";
					 
					  
					 var buttonArchive = document.createElement('button');
					 buttonArchive.type = "button";
					 buttonArchive.textContent = "Перейти в Архив";
					 buttonArchive.className = "mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect";
					 buttonArchive.addEventListener('click', function() {
					             window.open("archive.jsp?base_Id="+baseId );
					         });
                cell2 =  rows.insertCell(-1);
					  cell2.appendChild(lastT);
					  lastT.appendChild(lastTimeData);
					 lastT.appendChild(buttonArchive);



                //            Добавление таблицы для температурных датчиков

                var typeSensor = document.createElement('caption');
                //typeSensor.innerHTML = '<b>Температурные датчики:</b>';
                var tableSensor  = document.createElement('TABLE');
                tableSensor.id = 'sensors_table';
                tableSensor.className = 'sensors_table';
					 tableSensor.style.width = "100%";
//            tableSensor.setAttribute('style','border: 1px solid grey; border-collapse: collapse;');

                rows = table.insertRow(-1);
                cell1 =  rows.insertCell(-1);
                //cell2 =  rows.insertCell(-1);
//            cell1.appendChild(typeSensor);
                cell1.setAttribute('align','char;');
                cell1.setAttribute('colspan', '2');
                cell1.appendChild(tableSensor);
                //tableSensor.appendChild(typeSensor);

                var rowsSensor= tableSensor.insertRow(-1);
                rowsSensor.className = 'sensors_head_table';

                var cellSensor1 =  rowsSensor.insertCell(-1);
                cellSensor1.id = 'numSensor'
                cellSensor1.textContent = '#';
                cellSensor1.class = 'sensors_head_table';
                cellSensor1.setAttribute('style','text-align:right;');
                var cellSensor2 =  rowsSensor.insertCell(-1);
                cellSensor2.textContent = 'Название датчика';

                var cellSensor3 =  rowsSensor.insertCell(-1);
                cellSensor3.textContent = 'Значение';
                cellSensor3.setAttribute('style','text-align:center;');

                var cellSensor4 =  rowsSensor.insertCell(-1);
                cellSensor4.textContent = 'Действующий предел';
                cellSensor4.setAttribute('style','text-align:center;');


                var num = 1;


                function drawSensTable(typeS) {
                for (var j = 0; j < baseIdArray[i].sensors.length; j++) {
                    var type = baseIdArray[i].sensors[j].type;
                    if (baseIdArray[i].sensors[j].used && type == typeS) {


                        var sensorNum = document.createElement('div');
                        sensorNum.textContent = '' + num;

                        var sensorName = document.createElement('div');
                        sensorName.textContent = baseIdArray[i].sensors[j].name;

                        var id = baseIdArray[i].sensors[j].sensorId;

                        var min = baseIdArray[i].sensors[j].min;
                        var max = baseIdArray[i].sensors[j].max;
                        var pred = document.createElement('div');


                        var nameOfSensor;
                        if (type == 'tm') {
                            nameOfSensor = 'term';

                        } else if (type == 'dt') {
                            nameOfSensor = 'signal';

                        }
                        nameOfSensor += id;

                        var data = baseIdArray[i].data[nameOfSensor];

                        var sensorData = document.createElement('div');
                        sensorData.textContent = data;

                        var color;

                        if (data >= min && data <= max) {
                            //green
                            color = 'rgba(127, 255, 0, 0.43)';
                            pred.textContent = min + ' < ... > ' + max;

                        } else if (data < min) {
                            //blue
                            color = 'rgba(30, 144, 255, 0.71)';
                            pred.textContent = '< ' + min;
                        } else if (data > max) {
                            //red
                            color = 'rgba(205, 92, 92, 0.7)';
                            pred.textContent = '> ' + max;
                        }

                        if (type == 'dt'){

                            if (data == min) {
                                color = 'rgba(127, 255, 0, 0.43)';
                                sensorData.textContent = '✓';

                            } else {
                                color = 'rgba(205, 92, 92, 0.7)';
                                sensorData.textContent = '✘';
                            }
							
							pred.textContent = '';
                        }


                        rowsSensor = tableSensor.insertRow(-1);
                        rowsSensor.setAttribute('style', 'background-color : ' + color + '; opacity : 0,7');
                        cellSensor1 = rowsSensor.insertCell(-1);
                        cellSensor1.appendChild(sensorNum);
                        cellSensor1.setAttribute('style', 'text-align:right;');
                        cellSensor2 = rowsSensor.insertCell(-1);
                        cellSensor2.appendChild(sensorName);
                        cellSensor3 = rowsSensor.insertCell(-1);
                        cellSensor3.appendChild(sensorData);
                        cellSensor3.setAttribute('style', 'text-align:center;');
                        cellSensor4 = rowsSensor.insertCell(-1);
                        cellSensor4.appendChild(pred);
                        cellSensor4.setAttribute('style', 'text-align:center;');
                        //cellSensor4.setAttribute('style', 'background-color : ' + color + '; opacity : 0,7');

                        num++;
                    }
                }



                }

                drawSensTable('tm');
                drawSensTable('dt');




            }
        }

    //}
    main.appendChild(table);

}




function countErrors(baseId){

var error = 0;


    for (var j = 0; j < baseId.baseid.sensors.length; j++) {
        var type = baseId.baseid.sensors[j].type;
        if (baseId.baseid.sensors[j].used) {

            var id = baseId.baseid.sensors[j].sensorId;

            var nameOfSensor;
            if (type == 'tm') {
                nameOfSensor = 'term';

            } else if (type == 'dt') {
                nameOfSensor = 'signal';

            }
            nameOfSensor += id;
            var min = baseId.baseid.sensors[j].min;
            var max = baseId.baseid.sensors[j].max;
            var data = baseId.data[nameOfSensor];
				
				
            if (type == 'tm') {
                if (data < min || data > max) {
                   error++;
                }
            } else if (type == 'dt') {

                if (data != min) {
                    error++;

            }
        }



    }


 }
var currTime = new Date().getTime();
var lastData = new Date(baseId.data.time).getTime();
if ((currTime - lastData) > 901000) error = "Не передаются данные"
    return error;
	 
}




function setTextContent(element, text) {
    while (element.firstChild!==null) {
    	 element.removeChild(element.firstChild); // remove all existing content
    }
       
    element.appendChild(document.createTextNode(text));
}





