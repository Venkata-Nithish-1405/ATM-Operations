

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DepositAmount extends HttpServlet {
	
    public DepositAmount() {
        super();
        
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		int accno=Integer.parseInt(request.getParameter("a3"));
		double damt=Double.parseDouble(request.getParameter("a4"));
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "xe", "manager");
			PreparedStatement ps=con.prepareStatement("Update accounts3 set balance=balance+? where acc_id=?");
			ps.setDouble(1, damt);
			ps.setInt(2, accno);
			ps.executeUpdate();
			out.println("Deposit Successful");
			ps.close();
			
		}
		catch(Exception e) {
			out.println(e.getMessage());
			
		}
		out.println("<br><a href='./'>Click Here </a> To Perform Add Deposit.");
		
	}

}
