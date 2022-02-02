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

public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MyServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// Step 01: register the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// Step 02: Open the connection
			String databaseURL = "jdbc:mysql://localhost:3306/sljdbc";
			String username = "root";
			String password = "root";
			Connection connection = DriverManager.getConnection(databaseURL, username, password);
			
			// Step 03: prepare the sentence
			String myQuery = "select * from course";
			Statement query = connection.createStatement();
			
			// Step 04: execute the query
			if(query.execute(myQuery)) {
				ResultSet resultSet = query.executeQuery(myQuery);
				PrintWriter out = response.getWriter();
				while(resultSet.next()) {
					int courseId = resultSet.getInt(1);
					String credit = resultSet.getString(2);
					String title = resultSet.getString(3);
					out.println(courseId + " " + credit + " " + title + " ");
				}
				resultSet.close();
			}
			
			// Step 05: close the connection
			query.close();
			connection.close();
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
