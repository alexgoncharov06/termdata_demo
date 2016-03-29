<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Энергополис. Мониторинговая система</title>
</head>
<body>
	<%
		String log = "";
	log = (String) session.getAttribute("login");
	Enumeration<String> param = (Enumeration<String>) request.getAttributeNames();
	List<String> paramlist = new ArrayList<String>();

	while (param.hasMoreElements()){
		String p1 = param.nextElement();
		if (!("".equals(p1))){
		paramlist.add(p1);
		}
		
	}
	if (paramlist.contains("resetCookie"))  {
		Cookie cookie = new Cookie ("JSESSIONID","");
		response.addCookie(cookie);
		session.invalidate();
	}
	//if ("".equals(log)) {
	if (session.isNew()) {
	%>
	<h2>Добро пожаловать!</h2>
	<p id="pred">Для просмотра информации авторизуйтесь на сайте:</p>
	<form action="auth.jsp" method="post">
		<div id="login">
			<b>Логин:</b><input type="text" name="login" size=15 />
		</div>
		<div id="password">
			<b>Пароль:</b><input type="password" name="password" size=15 />
		</div>
		</br> <input type="submit" value="Вход" id="enter">
	</form>
	<%
		}
	else {
		RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
		 
	    if (dispatcher != null) {

	        dispatcher.forward(request, response);
	
			//JSESSIONID
	    }
	}
	%>
</body>
</html>