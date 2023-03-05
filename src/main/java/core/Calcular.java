package core;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Calcular
 */
@WebServlet("/Calcular")
public class Calcular extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Calcular() {
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
		String resultat = "";
		try {
			String meses_req = request.getParameter("meses");
			String interes_req = request.getParameter("interes");
			String capital_req = request.getParameter("capital");
			double meses = Double.parseDouble(meses_req);
			double interes = Double.parseDouble(interes_req);
			double capital = Double.parseDouble(capital_req);
			// Comprovam que cap dels 3 paràmetres sigui 0
			if (meses > 0 && interes > 0 && capital > 0) {
				double i = ((interes/100) / 12);
		        double cuota_mensual = capital / ((1-Math.pow((1+i),-meses))/i);
			    resultat = "Su cuota mensual seria de: " + cuota_mensual + " euros";
			    Registros.registros(meses+" "+interes+" "+capital);
			}
		} catch (Exception e) {
			resultat = "Error ejecutando";
			GestionErrores.escribirErrores(e.toString());
		}
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.getWriter().append(resultat);
	}

}
