package com.simplilearn.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServletDos
 */
public class MyServletDos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServletDos() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    private Properties getProperties(String resourcePath) throws IOException {
    	
    	ServletContext servletContext = getServletContext();
		InputStream inputStream = servletContext.getResourceAsStream(resourcePath);
		Properties properties = new Properties();
		properties.load(inputStream);
		return properties;
    	
    }
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Properties properties = getProperties("/WEB-INF/configuration.properties");
			String databaseURL = properties.getProperty("databaseURL");
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");
			//
			Connection connection = DriverManager.getConnection(databaseURL, username, password);
			
			Statement statement = connection.createStatement();
			
			String myQuery = "select * from student";
			
			ResultSet resultSet = statement.executeQuery(myQuery);
			
			while(resultSet.next()) {
				int studentId = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String email = resultSet.getString(3);
				
				PrintWriter out = response.getWriter();
				
				out.println(studentId + " " + name + " " + email);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
