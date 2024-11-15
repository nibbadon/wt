

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HiddenFormServlet1
 */
@WebServlet("/HiddenForm1")
public class HiddenFormServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HiddenFormServlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String name = request.getParameter("username");
        String pass = request.getParameter("password");
        String email=request.getParameter("email");
        if(pass.equals("rajanakash@123"))
        	
        {
             out.println("<form action='HiddenForm2' >");
             out.println("<input type='hidden' name='user' value=' "+name+" '>");
             out.println("<input type='hidden' name='email' value=' "+email+" '>");    
             out.println("<input type='submit' value='submit' >");
             out.println("</form>");
             return;
        }
        else
	out.println("<h1> Invalid User</h1>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
