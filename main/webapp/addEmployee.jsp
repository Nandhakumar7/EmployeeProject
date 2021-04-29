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
<h1 style="text-align: center;background-color: white;">EmployeeManagement</h1>
<body style="background-color:lightblue;text-align:center">
    <h2>Create New Project</h2>
    <form action="Employee?action=add" method = "post">
        <label>Name</label><input type = "text" name = "name" required><br><br>
        <label>Salary</label> <input type = "number" name = "salary" required><br><br>
        <label>MobileNumber</label> <input type = "text" pattern ="[6789][0-9]{9}" name = "mobileNumber" required><br><br>
        <label>DateOfBirth</label> <input type = "date" name = "dateOfBirth" required><br><br>
        Permanent Address<br><br>
        <label>DoorNumber</label> <input type = "number" name = "doorNumber" required><br><br>
        <label>StreetName</label> <input type = "text" name = "streetName" required><br><br>
        <label>District</label> <input type = "text" name = "district" required><br><br>
        <label>State</label> <input type = "text" name = "state" required><br><br>
        <label>Country</label> <input type = "text" name = "country" required><br><br>
        <label>DoorNumber</label> <input type = "number" name = "pincode" required><br><br>
        Temporary Address:<br><br>
        <label>DoorNumber</label> <input type = "number" name = "doorNumber1" required><br><br>
        <label>StreetName</label> <input type = "text" name = "streetName1" required><br><br>
        <label>District</label> <input type = "text" name = "district1" required><br><br>
        <label>State</label> <input type = "text" name = "state1" required><br><br>
        <label>Country</label> <input type = "text" name = "country1" required><br><br>
        <label>DoorNumber</label> <input type = "number" name = "pincode1" required><br><br>
        <input type = "submit"> 
    </form>
<br><br>
<a href = "project.jsp"><button class = "gfg"> back</button></a>
</body>
</html>