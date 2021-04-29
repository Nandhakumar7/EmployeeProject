<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.LinkedList"%>
<%@ page import="java.util.List"%>
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
<h1 style="text-align: center;background-color: white;">EmployeeManagement</h1>
<body style="background-color:lightblue;text-align:center">
<form action="Employee?action=view" method = "post">
        Employee Id: <input type = "number" name = "id"><br><br>
        <input type = "submit"> 
</form>
<br><br>
<% List<List<String>> employee = (List<List<String>>)request.getAttribute("view"); %>
<%if(null != employee) { %>
<% List<String> employeeAddress = employee.get(1); %>
<% List<String> employeeDetails = employee.get(0); %>
<%if(1 == employee.size()) { %>
</head>
<body>
<div class="alert">
  <span class="closebtn">&times;</span>  
  <strong>Project</strong>Not Exists
</div>
<%} else { %>
<% session.setAttribute("id",employeeDetails.get(0)); %>
<% session.setAttribute("name",employeeDetails.get(1)); %>
<% session.setAttribute("salary",employeeDetails.get(2)); %>
<% session.setAttribute("mobileNumber",employeeDetails.get(3)); %>
<% session.setAttribute("dateOfBirth",employeeDetails.get(4)); %>
<table align= "center" style="width:70%;">
<tr>
<th>Id</th>
<th>Name</th>
<th>Salary</th>
<th>MobileNumber</th>
<th>DateOfBirth</th>
<th>Edit</th>
</tr>
<tr>
<th><%= employeeDetails.get(0)%></th>
<th><%= employeeDetails.get(1)%></th>
<th><%= employeeDetails.get(2)%></th>
<th><%= employeeDetails.get(3)%></th>
<th><%= employeeDetails.get(4)%></th>
<th><a href = "editProject.jsp"><button class = "gfg">Edit</button></a></th>
</tr>
</table>
<br><br>
<table align= "center" style="width:70%;">
<tr>
<th>Address</th>
</tr>
<%for(int i =0;i<employeeAddress.size();i++){
%>
<tr>
<th><%= employeeAddress.get(i) %></th>
</tr>
<% } %>
</table>
<br><br><br>
<%if(6 <= employeeDetails.size()) { %>
<table align= "center" style="width:70%;">
<tr>
<th>ProjectId</th>
<th>Project Name</th>
</tr>
<%for(int i =5;i<employeeDetails.size();i++){
%>
<tr>
<th><%= employeeDetails.get(i)%></th>
<th><%= employeeDetails.get(i+1)%></th>
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