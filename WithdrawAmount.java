

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WithdrawAmount extends HttpServlet {
	
    public WithdrawAmount() {
        super();
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		int accno=Integer.parseInt(request.getParameter("a1"));
		double wamt=Double.parseDouble(request.getParameter("a2"));
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "xe", "manager");
			PreparedStatement ps=con.prepareStatement("Update accounts3 set balance=balance-? where acc_id=?");
			ps.setDouble(1, wamt);
			ps.setInt(2, accno);
			ps.executeUpdate();
			out.println("Withdraw Successful");
			ps.close();
			
		}
		catch(Exception e) {
			out.println(e.getMessage());
			
		}
		out.println("<br><a href='./'>Click Here </a> To Perform Another Withdraw");
	}

}
