

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/TransferAccount")
public class TransferAccount extends HttpServlet {
	
    public TransferAccount() {
        super();
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,NullPointerException{
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		int saccno=Integer.parseInt(request.getParameter("t1"));
		int taccno=Integer.parseInt(request.getParameter("t2"));
		double amt=Double.parseDouble(request.getParameter("t3"));
		
		if(amt>25000)
		{
			out.println("Exceeds Maximum Transaction Limit");
			return;
		}
		    try {
		    	
		
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "xe", "manager");
			PreparedStatement ps1=con.prepareStatement("SELECT * FROM ACCOUNTS3 WHERE ACC_ID=?");
			PreparedStatement ps2=con.prepareStatement("SELECT * FROM ACCOUNTS3 WHERE ACC_ID=?");
			
			ps1.setInt(1, saccno);
			ps2.setInt(1, taccno);
			
			ResultSet rs1=ps1.executeQuery();
			ResultSet rs2=ps2.executeQuery();
			
			if(rs1.next() && rs2.next())
			{
				PreparedStatement ps3=con.prepareStatement("select balance from accounts3 where acc_id=?");
				ps3.setInt(1, saccno);
				ResultSet rs3=ps3.executeQuery();
				if(rs3.next()) {
					double balance=rs3.getDouble(1);
					if(balance-amt<1000)
					{
						out.println("Minimum Balance Should Be Maintained in Account.");
						return;
					}
					else {
						PreparedStatement ps4=con.prepareStatement("UPDATE ACCOUNTS3 SET BALANCE= BALANCE -? WHERE ACC_ID=?");
						ps4.setDouble(1, amt);
						ps4.setInt(2, saccno);
						ps4.executeUpdate();
						
						PreparedStatement ps5=con.prepareStatement("UPDATE ACCOUNTS3 SET BALANCE= BALANCE +? WHERE ACC_ID=?");
						ps5.setDouble(1, amt);
						ps5.setInt(2, taccno);
						ps5.executeUpdate();
						
						out.println("Transaction is Success");
						
						ps5.close();
						ps4.close();
						
					}
					
					
				}
			}
			else
			{
				if(!rs1.next())
				{
					out.println("Invalid Source Account");
				}
				if(!rs2.next())
				{
					out.println("Invalid Target Account");
				}
			}
			ps2.close();
			ps1.close();			
			
		}
		catch(Exception e)
		{
			out.println(e.getMessage());
		}
		out.println("<br>Click <a href='./'>HERE</a> to Transfer Again");
		
	}

}
