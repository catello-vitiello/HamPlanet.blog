<%@page import="profile.entity.Ham_user"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
	Collection<?> cw = (Collection<?>) request.getAttribute("CW");
		if(cw == null){
			response.sendRedirect("GetCWListS?filter=content_writer");
			return;
		}
	%>

	<h1>TUTTI I CONTENT WRITER</h1>
	<table>
		<tr>
			<td>ID</td>
			<td>USERNAME</td>
			<td>EMAIL</td>
			<td>COMPETENZE</td>
		</tr>
		
		<%
				if(cw != null && cw.size()>0){
						Iterator<?> iterator = cw.iterator();
						while(iterator.hasNext()){
							Ham_user bean = (Ham_user) iterator.next();
				%>
			
			<tr>
				<td><%= bean.getId() %></td>
				<td><%= bean.getUserName() %></td>
				<td><%= bean.getEmail() %></td>
				<td><%= bean.getCompetenze() %></td>
			</tr>
			
		<%
				}
			} else {
		%>
			
			<tr>
				<td colspan="4">NON CI SONO Content Writer</td>
			</tr>
			
		<%
			}
		%>
	</table>


</body>
</html>