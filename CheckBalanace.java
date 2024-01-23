

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CheckBalance extends HttpServlet {
	
    public CheckBalance() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		int accno=Integer.parseInt(request.getParameter("a5"));
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "xe", "manager");
			PreparedStatement ps=con.prepareStatement("select balance from accounts3 where acc_id=?");
			ps.setInt(1, accno);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				double CurrentBalance=rs.getDouble(1);
				out.println("Balance Fetched Successfully. Your Current Balance is "+CurrentBalance);
				ps.close();
			}
			
			
		}
		catch(Exception e) {
			out.println(e.getMessage());
			
		}
		out.println("<br><a href='./'>Click Here </a> To Go Back.");
		
	}

}
