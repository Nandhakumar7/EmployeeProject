<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.LinkedList"%>
<!DOCTYPE html>
<html>
<head>
<style type = "text/css">
    label{
        width:100px;
        display:inline-block;
     }
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<% LinkedList<String> project = (LinkedList<String>)request.getAttribute("view"); %>
<h1 style="text-align: center;background-color: white;">ProjectManagement</h1>
<body style="background-color:lightblue;text-align:center">
    <h2>Create New Project</h2>
    <form action="project?action=edit" method = "post">
        <label>Project id:</label> <input value="<%= session.getAttribute("id") %>" type = "number" name = "id" readonly><br><br>
        <label>Project name:</label> <input value="<%= session.getAttribute("name") %>" type = "text" name = "name" required><br><br>
        <label>Project manager:</label> <input value="<%= session.getAttribute("managerName") %>" type = "text" name = "manager" required><br><br>
        <label>Department:</label> <input value="<%= session.getAttribute("department") %>" type = "text" name = "department" required><br><br>
        <label>TimePeriod:</label> <input value="<%= session.getAttribute("timePeriod") %>" type = "number" name = "timePeriod" required><br><br>
        <input type = "submit"> 
    </form>
<br><br>
<a href = "editProject.jsp"><button class = "gfg">Edit</button></a><br><br>
<a href = "project?action=delete&id=<%= session.getAttribute("id") %>"><button class = "gfg">Delete Project</button></a><br><br>
<a href = "project.jsp"><button class = "gfg"> back</button></a>
</body>
</html>