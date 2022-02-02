package com.simplilearn.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JdbcConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public JdbcConnection() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// Step :01 Register the driver.
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// Step 02: get the connection
			String databaseURL = "jdbc:mysql://localhost:3306/sljdbc";
			String username = "root";
			String password = "root";
			
			Connection connection = DriverManager.getConnection(databaseURL, username, password);
			
			// Step 03: Create statement
			Statement query = connection.createStatement();
			
			//Step 04.1: prepare and execute the query
			String myQuery = "select * from student";
			ResultSet resultSet = query.executeQuery(myQuery);
			
			//Step 04.2: manipulate the resultSet
			while(resultSet.next()) {
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String email = resultSet.getString(3);
				PrintWriter out = response.getWriter();
				out.println(id + " " + name + " " + email);				
			}
			
			//Step 05: Close the connection
			resultSet.close();
			query.close();
			connection.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
