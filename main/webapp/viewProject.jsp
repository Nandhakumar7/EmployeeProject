<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.LinkedList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
table,th,tr{
border:1px solid black;
}
table.conter{
margin-left: auto;
margin-right: auto;
}
</style>
<h1 style="text-align: center;background-color: white;">ProjectManagement</h1>
<body style="background-color:lightblue;text-align:center">
<form action="project?action=view" method = "post">
        Project id: <input type = "number" name = "id"><br><br>
        <input type = "submit"> 
</form>
<br><br>
<% LinkedList<String> project = (LinkedList<String>)request.getAttribute("view"); %>
<%if(null != project) { %>
<%if(1 == project.size()) { %>
</head>
<body>
<div class="alert">
  <span class="closebtn">&times;</span>  
  <strong>Project</strong>Not Exists
</div>
<%} else { %>
<% session.setAttribute("id",project.get(0)); %>
<% session.setAttribute("name",project.get(1)); %>
<% session.setAttribute("managerName",project.get(2)); %>
<% session.setAttribute("department",project.get(3)); %>
<% session.setAttribute("timePeriod",project.get(4)); %>
<table align= "center" style="width:70%;">
<tr>
<th>Id</th>
<th>Name</th>
<th>ManagerName</th>
<th>Department</th>
<th>TimePeriod</th>
<th>Edit</th>
</tr>
<tr>
<th><%= project.get(0)%></th>
<th><%= project.get(1)%></th>
<th><%= project.get(2)%></th>
<th><%= project.get(3)%></th>
<th><%= project.get(4)%></th>
<th><a href = "project?action=show"><button class = "gfg">Edit</button></a></th>
</tr>
</table>
<br><br>
<%if(6 <= project.size()) { %>
<table align= "center" style="width:70%;">
<tr>
<th>EmployeeId</th>
<th>Name</th>
</tr>
<%for(int i =5;i<project.size();i++){
%>
<tr>
<th><%= project.get(i)%></th>
<th><%= project.get(i+1)%></th>
</tr>
<%i= i+1; %>
<%} %>
</table>
<%} %>
<%}%>
<%}%>
<br> <br>
<a href = "project.jsp"><button class = "gfg"> back</button></a>
</body>
</html>