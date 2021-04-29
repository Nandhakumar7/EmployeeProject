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
<h1 style="text-align: center;background-color: white;">ProjectManagement</h1>
<body style="background-color:lightblue;text-align:center">
<a href = "addProject.jsp"><button class = "gfg">  CreateProject</button></a><br><br>
<a href = "viewProject.jsp"><button class = "gfg">  showProject</button></a><br><br>
<a href = "project?action=showAll"><button class = "gfg">  showAllProject</button></a><br><br>
<a href = "mainPage.html"><button class = "gfg">Back</button></a>
<br> <br>
<% LinkedList<List<String>> project = (LinkedList<List<String>>)request.getAttribute("allProject"); %>
<% if(null != project) { %>
<h1 style="text-align: center;background-color: white;">Projects</h1>
<table align= "center" style="width:70%;">
<tr>
<th>Id</th>
<th>Name</th>
<th>ManagerName</th>
<th>Department</th>
<th>TimePeriod</th>
</tr>
<%for(int i =0;i<project.size();i++){
%>
<tr>
<th><%= (project.get(i)).get(0) %></th>
<th><%= (project.get(i)).get(1) %></th>
<th><%= (project.get(i)).get(2) %></th>
<th><%= (project.get(i)).get(3) %></th>
<th><%= (project.get(i)).get(4) %></th>
</tr>
<% } %>
</table>
<% } %>
</body>
</html>