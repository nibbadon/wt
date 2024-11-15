

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieServlet1      */

@WebServlet("/Cookie1")
public class CookieServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CookieServlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
        String name = request.getParameter("username");
        String pass = request.getParameter("password");
        String email=request.getParameter("email");
        
            Cookie ck = new Cookie("username", name);
            Cookie ck1=new Cookie("emailaddr",email);
            
            response.addCookie(ck);
            response.addCookie(ck1);
            out.println("<h1> Hello, welcome " + name 
                    + "!!! </h1>"); 
        out.println( 
            "<h1><a href =\"Cookie2\">Course Details</a></h1>");

        	     		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
