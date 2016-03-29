<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="DAO.LoginDao"%>
<%@ page import="DAO.Factory"%>
<%@ page import="interfaces.Login"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>auth</title>
</head>
<body>
	<%
		DAO.LoginDao dao = Factory.getLoginDAO();
		Login log = new Login();
		log.setLogin(request.getParameter("login"));
		log.setPass(request.getParameter("password"));
		Login byLog = null;
		boolean correctPass = false;
		String error = "";
		int rezCom = -1;
		try {
			byLog = dao.getByLogin(request.getParameter("login"));
			rezCom = log.compareTo(byLog);
		} catch (Exception e) {
			error = "Не возможно сравнить данные по логину/паролю </br>"
					+ request.getParameter("login") + " / "
					+ request.getParameter("password") + "</br>"
					+ byLog.toString() + "</br> compareTo =" + rezCom;

		} finally {

		}
		if (rezCom == 0)
			correctPass = true;
		if (correctPass) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("edit.jsp");
			session.setAttribute("login", log.getLogin());
			if (dispatcher != null) {

				dispatcher.forward(request, response);

			}
		} else {
	%>
	<p>
		<%=error%>
	</p>
	<p>Не вверный Логин/Пароль</p>
	<%
		}
	%>
</body>
</html>