<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.*"%>
<%
Date day = new Date();
String stringDay = "";
String time = "" + day.toString();
stringDay+= time.substring((time.length()-2),time.length());
stringDay+="/";
int moun = day.getMonth() +1;
String mounth = "";
if (moun < 10)  mounth+="0"+ moun;
stringDay+= mounth;
stringDay+="/";
stringDay+= day.getDate();
stringDay+=",";

stringDay+= time.substring((time.length()-18), (time.length()-10));
stringDay+="+02";
	String base = (String) request.getParameter("base_id");
%>

time:<%=stringDay%>