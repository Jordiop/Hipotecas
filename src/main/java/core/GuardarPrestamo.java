package core;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GuardarPrestamo
 */
@WebServlet("/guardarprestamo")
public class GuardarPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuardarPrestamo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		response.addHeader("Access-Control-Allow-Origin", "*");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String meses_req = request.getParameter("meses");
		String interes_req = request.getParameter("interes");
		String capital_req = request.getParameter("capital");
		double meses = Double.parseDouble(meses_req);
		double interes = Double.parseDouble(interes_req);
		double capital = Double.parseDouble(capital_req);
		if (meses > 0 && interes > 0 && capital > 0) {
			try {
				Bbdd.guardarPrestamo(interes, meses, capital, username);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		response.addHeader("Access-Control-Allow-Origin", "*");
		
	}

}
