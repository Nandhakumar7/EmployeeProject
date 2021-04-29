<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<h1 style="text-align: center;background-color: white;">ProjectManagement</h1>
<body style="background-color:lightblue;text-align:center">
    <h2>Create New Project</h2>
    <form action="project?action=add" method = "post">
        <label>Project id:</label><input value="199" type = "number" name = "id" required><br><br>
        <label>Project name:</label> <input type = "text" name = "name" required><br><br>
        <label>Project manager:</label> <input type = "text" name = "manager" required><br><br>
        <label>Department:</label> <input type = "text" name = "department" required><br><br>
        <label>TimePeriod:</label> <input type = "number" name = "timePeriod" required><br><br>
        <input type = "submit"> 
    </form>
<br><br>
<a href = "project.jsp"><button class = "gfg"> back</button></a>
</body>
</html>