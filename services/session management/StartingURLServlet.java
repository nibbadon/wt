import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/StartingURL")
public class StartingURLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public StartingURLServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
	  PrintWriter out=response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        
        String name = request.getParameter("username");
        String pass = request.getParameter("password");
        String email=request.getParameter("email");
        String sessionid=response.encodeURL(email);
        if(pass.equals("rajanakash@123"))
        {
            
        	out.print("<br><a href='Ending?name="+name+"&email="+sessionid+"'>visit</a>");

        	}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
