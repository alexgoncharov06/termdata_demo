<%@page import="com.github.alexwolfgoncharov.termdata.dao.TermDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.github.alexwolfgoncharov.termdata.dao.Factory"%>
<%@ page import="com.github.alexwolfgoncharov.termdata.interfaces.ThermData"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Энергополис. Получение информации об объектах</title>
<script>
function fresh() {
    location.reload();
}
setInterval("fresh()",30000);
</script>
</head>
<body>
	<%@include file="hello.jsp"%>

	</br>
	</br>
	<center>
		<table id="base_id" border="1">
			<tr>
				<th>№ п/п</th>
				<th>Название точки</th>
				<th>Время</th>
				<th>Температура 1 (&#176;С)</th>
				<th>Температура 2 (&#176;С)</th>
				<th>Температура 3 (&#176;С)</th>
				<th>Температура 4 (&#176;С)</th>
				<th>Температура 5 (&#176;С)</th>
				<th>Влажность (%)</th>
				<th>Баланс, грн.</th>
				
				
				<%
					int num = 1;
					TermDao dao = new Factory().getTermDAO();
					List<String> listBaseId = dao.getAllBase();
					for (String baseId : listBaseId) {
					/* 	String baseId = i.next(); */
						ThermData cl1 = dao.getLastByBase(baseId);
					
				%>
			
			<tr>
				<td><%=num%></td>
				<td><a href="detail.jsp?base_id=<%=cl1.getBaseID()%>"><%=cl1.getBaseID()%></a></td>
				<td><%=cl1.getTime()%></td>
				<td><%=cl1.getTerm1()%> &#176;</td>
				<td><%=cl1.getTerm2()%> &#176;</td>
				<td><%=cl1.getTerm3()%> &#176;</td>
				<td><%=cl1.getTerm4()%> &#176;</td>
				<td><%=cl1.getTerm5()%> &#176;</td>
				<td><%=cl1.getHum()%> %</td>
				<td><%=cl1.getBalance()%></td>
				
				
				
			</tr>
			<%
				num++;
				}
			%>

		</table>
		</br>
	</center>
</body>
</html>