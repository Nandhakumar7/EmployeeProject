<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.LinkedList"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<style>
table,th,tr{
border:1px solid black;
}
table.conter{
margin-left: auto;
margin-right: auto;
}</style>
<title>Project</title>
</head>
<h1 style="text-align: center;background-color: white;">EmployeeManagement</h1>
<body style="background-color:lightblue;text-align:center">
<a href = "addEmployee.jsp"><button class = "gfg">  CreateEmployee</button></a><br><br>
<a href = "showEmployee.jsp"><button class = "gfg">  showEmploye</button></a><br><br>
<a href = "Employee?action=showAll"><button class = "gfg">  showAllEmployee</button></a><br><br>
<a href = "mainPage.html"><button class = "gfg">Back</button></a>
<br> <br>
<% LinkedList<List<String>> employee = (LinkedList<List<String>>)request.getAttribute("allProject"); %>
<% if(null != employee) { %>
<h1 style="text-align: center;background-color: white;">Projects</h1>
<table align= "center" style="width:70%;">
<tr>
<th>Id</th>
<th>Name</th>
<th>Salary</th>
<th>MobileNumber</th>
<th>DateOfBirth</th>
</tr>
<%for(int i =0;i<employee.size();i++){
%>
<tr>
<th><%= (employee.get(i)).get(0) %></th>
<th><%= (employee.get(i)).get(1) %></th>
<th><%= (employee.get(i)).get(2) %></th>
<th><%= (employee.get(i)).get(3) %></th>
<th><%= (employee.get(i)).get(4) %></th>
</tr>
<% } %>
</table>
<% } %>
</body>
</html>