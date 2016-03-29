<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Сохранение файла</title>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="./js/vendor/jquery.ui.widget.js"></script>
<script src="./js/jquery.iframe-transport.js"></script>
<script src="./js/jquery.fileupload.js"></script>

 <style>
 .bar {
     height: 18px;
     background: green;
 }
 </style>

 
</head>
 
<body >
 <center>
	 <div id="progress">
	     <div class="bar" style="width: 0%;"></div>
	 </div>
  <input id="fileupload" type="file" name="files[]" data-url="./upload" multiple>
   <script>
  $('#fileupload').fileupload({
         dataType: 'json',
         add: function (e, data) {
             data.context = $('<p/>').text('Uploading...').appendTo(document.body);
             data.submit();
         },
         done: function (e, data) {
             data.context.text('Upload finished.');
         }
     });
</script>
 </center>
</html>