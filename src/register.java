

import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		out.println("data received in get");
		try {
			process(request,response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		out.println("data received");
		try {
			process(request,response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			out.println("errror occured fuck");
			e.printStackTrace();
		}
	}
	
	protected void process(HttpServletRequest request,HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException, ServletException  {
		 String dbDriver = "com.mysql.jdbc.Driver"; 
	        String dbURL = "jdbc:mysql://localhost:3306/student"; 
	        // Database name to access 
	       
	        String dbUsername = "root"; 
	        String dbPassword = "root"; 
	        Connection con = null;
	  try {
	        Class.forName("com.mysql.jdbc.Driver"); 
	         con = DriverManager.getConnection(dbURL, dbUsername,"12345678"); 
	        PreparedStatement st = (PreparedStatement) con.prepareStatement("insert into register values(?, ?)"); 
	  
	            // For the first parameter, 
	            // get the data using request object 
	            // sets the data to st pointer 
	            st.setString(1,request.getParameter("name")); 
	  
	            // Same for second parameter 
	            st.setString(2, request.getParameter("phone")); 
	  
	            // Execute the insert command using executeUpdate() 
	            // to make changes in database 
	            st.executeUpdate(); 
	  
	            // Close all the connections 
	            st.close(); 
	            con.close(); 
	 }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	      System.out.println("error ouccccccred"+se);
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(con!=null)
	            con.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	        String name=request.getParameter("name");
			String phone=request.getParameter("phone");
			
			PrintWriter out=response.getWriter();
			
		         
			register stud=new register();
			request.setAttribute("name", name);
			request.setAttribute("phone", phone);
			 RequestDispatcher rd=request.getRequestDispatcher("display.jsp");
			rd.forward(request, response);
	}

}
}



