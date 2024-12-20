<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Balance Information</title>
<style>
    /* Basic Reset */
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    body {
        font-family: Arial, sans-serif;
        background-color: #f7f8f9;
        text-align: center;
        padding: 50px;
    }

    nav {
        background-color: #009933;
        padding: 15px 20px;
        text-align: left;
        width: 100%;
        margin-bottom: 30px;
    }

    nav a {
        color: white;
        text-decoration: none;
        font-size: 18px;
        font-weight: bold;
        padding: 10px 15px;
        border-radius: 5px;
        margin-right: 15px;
    }

    nav a:hover {
        background-color: #33cc33;
        color: white;
    }

    .balance {
        background-color: #ffffff;
        padding: 30px;
        border-radius: 8px;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        display: inline-block;
        margin-top: 20px;
        width: 50%;
        text-align: center;
    }

    h1 {
        color: #009933;
        font-size: 36px;
    }

    h4 {
        color: #666;
        font-size: 18px;
    }

    .error-message {
        color: red;
        font-size: 18px;
    }
</style>
</head>
<body>

<!-- Navigation Links -->
<nav>
    <a href="Index.html" id="home">Home</a>
    <a href="Login.html" id="login">Logout</a>
    <a href="Dashboard.html" id="dashboard">Dashboard</a>
</nav>

<%
    try {
        // Load the database driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish connection to database
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "W7301@jqir#");

        // Prepare the SQL statement
        PreparedStatement statement = connection.prepareStatement("SELECT balance FROM BankingApp.profile WHERE userid=?");

        // Get the session and fetch user ID
        HttpSession session1 = request.getSession();
        statement.setString(1, (String) session.getAttribute("userid"));

        // Execute the query
        ResultSet result = statement.executeQuery();

        // Check if the result is available
        if (result.next()) {
            int bal = Integer.parseInt(result.getString("balance"));
%>
            <!-- Balance Information -->
            <div class="balance">
                <h1>Your balance is:</h1>
                <h1><%= bal %></h1>
                <h4>Thank you for banking with us!</h4>
            </div>
<%
        } else {
%>
            <!-- Error Message: No balance found -->
            <div class="error-message">
                <h3>No balance information found for the user.</h3>
            </div>
<%
        }

        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
%>
        <!-- Error Message: Something went wrong -->
        <div class="error-message">
            <h3>Something went wrong. Please try again later.</h3>
        </div>
<%
    }
%>

</body>
</html>
