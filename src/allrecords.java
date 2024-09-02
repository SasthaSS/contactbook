


import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException; 

/**
 * Servlet implementation class allrecords
 */
@WebServlet("/allrecords")
public class allrecords extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public allrecords() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException  {
		// TODO Auto-generated method stub
		
			try {
				process(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
protected void process(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException {
	 String dbURL = "jdbc:mysql://localhost:3306/student"; 
     // Database name to access 
     
     String dbUsername = "root"; 
     
     Connection con=null;
		
     try {
    	Class.forName("com.mysql.jdbc.Driver");
		
   
			con = DriverManager.getConnection(dbURL, dbUsername,"12345678");
		
    
		Statement stmt = (Statement) con.createStatement();
ArrayList name=new ArrayList();
ArrayList phone=new ArrayList();
		// Result set get the result of the SQL query
		ResultSet rst = stmt.executeQuery("select * from register");
		
		PrintWriter out=response.getWriter();
	
		    while (rst.next()) {  //retrieve data
		        name.add( rst.getString("name"));
		        phone.add( rst.getString("phoneno"));
		     }
		    request.setAttribute("names", name);
		    request.setAttribute("phone", phone);
		  

			 RequestDispatcher rd=request.getRequestDispatcher("allrecords.jsp");
				rd.forward(request, response);
         // Close all the connections 
         stmt.close(); 
         con.close(); 
         
	 }catch(SQLException se){
	      //Handle errors for JDBC
		 System.out.println("error ouccccccred"+se);
	      se.printStackTrace();
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
		
}
}
}
