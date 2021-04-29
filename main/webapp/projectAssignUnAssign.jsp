<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="project?action=edit" method = "post">

<% for(int i=0;i<2;i++) { %>
        <label><%=i %></label> <input value="item<%=i %>" type = "checkbox" name = "id" readonly><br><br>
  <%} %>
        <input type = "submit"> 
    </form>
</body>
</html>