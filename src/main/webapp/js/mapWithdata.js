/**
 * Created by Alex on 27.09.15.
 */



var  map;
var markers = [];
var dataBaseIdOld = [];
var showAll = true;
var audio = new Audio(); // Создаём новый элемент Audio
audio.src = './audio/Space Alarm.mp3'; // Указываем путь к звуку "клика"
audio.autoplay = false; // Автоматически не запускаем
audio.loop = false;
console.log(audio.duration );


//        var link = document.querySelector('link[rel="import"]').import;
//        var content = link.querySelector('.menu');;
//        var menu = document.getElementById('top_menu');
//            menu.appendChild(content);



// Запуск звука ошибки
function soundErrorRun(){
    audio.load();
    audio.play();

}
// Остановка ошибки
function suondErrorStop(){
    audio.pause();
}




//        Методы для работы с картой
function initMap(){



    var testOp = {
        zoom: 9,
        center: {lat: 48.464717, lng: 35.046183}
    };


    var block = document.getElementById("map");
    var centerLatLng = new google.maps.LatLng('48.4647,35.0461');

    map = new google.maps.Map(block, testOp);




    var b_width = document.documentElement.clientWidth;
    var b_height = document.documentElement.clientHeight - 90;
    block.style.width = b_width + "px"; // Устанавливаем новую ширину блока
    block.style.height = b_height + "px"; // Устанавливаем новую высоту блока



}


function addMarkerWithTimeout( baseWith,  timeout) {
    console.log("Добавление маркера по объекту " + baseWith.name);

    var icon = {};
    icon.labelOrigin = new google.maps.Point(21, 21);
    icon.size = new google.maps.Size(42, 72);

    switch (baseWith.type) {
        case 'red' :

            icon.url = './img/red.png';

            break;
        case 'green':
            icon.url = './img/green.png';
            break;
        case 'yelow':
            icon.url = './img/yelow.png';
            break;
        case 'dark_blue':
            icon.url = './img/dark_blue.png';
            break;
        case 'blue':
            icon.url = './img/blue.png';
            break;
        case 'orange':
            icon.url = './img/orange.png';
            break;

    }

    window.setTimeout(function() {
        var marker = new google.maps.Marker({
            position: baseWith.location,
            map: map,
            icon: icon,
            title: baseWith.name,
            label: "" + baseWith.errorsCount,
//                    animation: google.maps.Animation.DROP,
            draggable:false

        });

        map.panTo(baseWith.location);



        var Message = document.createElement('div');



        var textMess = document.createElement('h4');
        textMess.id = "firstHeading";
        textMess.class = 'firstHeading';
        textMess.textContent = baseWith.name;
        Message.appendChild(textMess);

        if (baseWith.errors.length == 0) {
            var textMess = document.createElement('div');
            textMess.textContent = 'Объект работает нормально, аварий нет.';
            Message.appendChild(textMess);
        } else {

            soundErrorRun();
            var textMess = document.createElement('b');
            textMess.textContent = 'Текущие аварии:';
            Message.appendChild(textMess);

            for (var i = 0; i < baseWith.errors.length; i++) {
                var num = i +1;
                var textMess = document.createElement('div');
                textMess.textContent = num + ') ' + baseWith.errors[i];
                Message.appendChild(textMess);
            }

        }






        var textBr = document.createElement('br');
//                textBr.innerText = '<br>';
        Message.appendChild(textBr);
        var buttonDiv = document.createElement('div');
        buttonDiv.id =  'button_center';

        var buttonOk = document.createElement('button');
        buttonOk.textContent = 'Просмотреть все данные';
//                buttonOk.setAttribute('style','right: 45%');
//                buttonOk.id = 'button_center';
        buttonOk.className = "mdl-button mdl-js-button mdl-js-ripple-effect";

        buttonOk.addEventListener('click', function() {
            window.open("view.jsp?base_Id="+baseWith.ID );
        });


        buttonDiv.appendChild(buttonOk);

        Message.appendChild(buttonDiv);
        var otstup = document.createElement('div');
        otstup.innerHTML = '<br>';
        //Message.appendChild(otstup);
        //Message.appendChild(otstup);

        attachSecretMessage(marker, Message);

        clearMarker(marker);

        markers.push(marker);
    }, timeout);

}



//      Метод добавления подсказки к маркеру
function attachSecretMessage(marker, Message) {
    var infowindow = new google.maps.InfoWindow({
        content: Message
    });

    marker.addListener('click', function() {
        infowindow.open(marker.get('map'), marker);
        suondErrorStop();
    });
}



//      Метод получения данных с сервера и отображения на карте
function getDataFronServer(){



    $.ajax( "http://52.11.54.112:5555/term/getset", {
//            $.ajax( "http://localhost:5555/term/getset", {

        beforeSend: function (xhr) {
            xhr.setRequestHeader('json', 'true');
            xhr.setRequestHeader('type', 'baseid_whith_set_data');
        },

        cache: true,
        type: 'POST',
        data: {},
        contentType: 'application/x-www-form-urlencoded',
        dataType: "json",
        success: success,
        error: error

    } );


    function success( BaseID_Data ) {


        var dataBaseId = BaseID_Data.BaseID_Data;
        var baseIdArray;
        baseIdArray = [];
          var currTime = new Date().getTime();

        for (var i = 0; i < dataBaseId.length; i++) {

            var baseWith = {};
            baseWith.name = dataBaseId[i].baseid.name;
            baseWith.location = dataBaseId[i].baseid.location;
            baseWith.ID = dataBaseId[i].baseid.BaseID;
            var errorsCount = 0;
            var errors = [];
            var errorNoSignal = false;
                var lastData = new Date(dataBaseId[i].data.time).getTime();
                if ((currTime - lastData) > 901000) {
                    errors.push( "Не передаются данные");
                    errorNoSignal = true;
                    errorsCount++;
                }
                    

            for (var j = 0; j < dataBaseId[i].baseid.sensors.length; j++) {
                var sensor = dataBaseId[i].baseid.sensors[j];
                var num = sensor.sensorId;
                if (sensor.used) {
                    if (sensor.type == 'tm') {

                        var temp = dataBaseId[i].data['term' + num];

                        if (sensor.min > temp) {


                            errorsCount++;
                            errors.push('Температура датчика "' + sensor.name + '" меньше чем ' + sensor.min + '°С.');

                        } else if (sensor.max < temp) {
                            errorsCount++;
                            errors.push('Температура датчика "' + sensor.name + '" больше чем ' + sensor.max + '°С.');
                        }


                    } else if (sensor.type == 'dt') {
                        var signal = dataBaseId[i].data['signal' + num];
                        if (signal == sensor.max) {

                            errorsCount++;
                            errors.push(sensor.name);

                        }


                    }
                }

            }


            var location = {};
            var split_loc = baseWith.location.split(',');
            location.lat = parseFloat(split_loc[0]);
            location.lng = parseFloat(split_loc[1]);
            if (isNaN(location.lat) || isNaN(location.lng)) {
                continue;
            }
//                        new google.maps.LatLng(baseWith.location);
            baseWith.location = location;
            baseWith.errorsCount = errorsCount;
            baseWith.errors = errors;

            var type;
            var needAdd;
            if (errorsCount > 0 || errorNoSignal) {
                if (errorsCount > 9){
                
                    baseWith.errorsCount  = 'A';
                    baseWith.name += '. Аварий: ' + errorsCount;
                }
               type = 'red';
                if (errorNoSignal){
                  baseWith.errorsCount  = 'E';
                   type = 'dark_blue';
                }
               
                needAdd = true;
            }
            else {
                type = 'green';
                if (showAll) {
                    needAdd = true;
                } else {
                    needAdd = false;
                }
            }


            baseWith.type = type;


            if (needAdd)  {
                baseIdArray.push(baseWith);


                if (dataBaseIdOld.length != 0) {
                    //                        console.log('ha-ha-1');

                    var nayden = false;

                    for (var jj = 0; jj < dataBaseIdOld.length; jj++) {

                        //                            console.log('Поиск имени: ' + baseWith.name);
                        if (baseWith.name === dataBaseIdOld[jj].name) {

                            nayden = true;
                            console.log('Нашли имя: ' + baseWith.name);
                            if (baseWith.errors.toString() != dataBaseIdOld[jj].errors.toString()) {
                                //                                if (baseWith.errorsCount != dataBaseIdOld[jj].errorsCount) {
                                nayden = false;
                                //                                    console.log('ha-ha-2');
                            }

                        } else {

                            //                                console.log('нет записей об ошибках');

                        }
                    }

                    if (!nayden) {

                        addMarkerWithTimeout(baseWith, 100);
                    }
                } else {
                    //                        console.log('ha-ha-3');

                    addMarkerWithTimeout(baseWith, 100);
                }
            }


        }

        dataBaseIdOld = [];
        for (var i = 0; i < baseIdArray.length; i++) {
            var ob = {};
            var current = baseIdArray[i];
            for (var key in current) {
                ob[key] = current[key];
            }
            dataBaseIdOld.push(ob);
        }

//                console.log(baseIdArray);

    }










}

function error() {
}



function clearMarker(marker) {
    for (var i = 0; i < markers.length; i++) {
        var localMarker = markers[i];
//                console.log(localMarker.position.toString());
//                console.log(marker.position.toString());
        if ( localMarker.position.toString() ===  marker.position.toString()) {
            localMarker.setMap(null);
        }
    }
//            markers = [];
}

function showAllChange(){
    var typeMap = document.getElementById('typeMap');
    switch (typeMap.value){
        case 'all':
            showAll = true;
            break;
        case 'errors':
            showAll = false;
            break;

    }


    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);

    }

    getDataFronServer();

    dataBaseIdOld = [];


}






function onLoad(){
    initMap();
    getDataFronServer();
    setInterval("getDataFronServer()",15000);
}
