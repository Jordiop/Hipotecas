package core;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalculoTabla
 */
@WebServlet("/CalculoTabla")
public class CalculoTabla extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculoTabla() {
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
			DecimalFormat df = new DecimalFormat("#.00");
			String meses_req = request.getParameter("meses");
			String interes_req = request.getParameter("interes");
			String capital_req = request.getParameter("capital");
			double meses = Double.parseDouble(meses_req);
			double interes = Double.parseDouble(interes_req);
			double capital = Double.parseDouble(capital_req);
			// Comprovam que cap dels 3 paràmetres sigui 0
			if (meses > 0 && interes > 0 && capital > 0) {
				Registros.registros(meses+" "+interes+" "+capital);
				double i = ((interes/100) / 12);
		        double cuota_mensual = capital / ((1-Math.pow((1+i),-meses))/i);
			    // Montamos la tabla de HTML desde JAVA
			    resultat = "<table class=\"text-center border-separate border-spacing-2 border border-slate-500\" border=\"1\">"
						+ "<tr>"
						+ "<th>Capital pendiente anterior</th>"
						+ "<th>Cuota a pagar</th>"
						+ "<th>Parte de cuota amortizacion</th>"
						+ "<th>Parte de cuota interes</th>"
						+ "<th>Capital pendiente posterior</th>"
						+ "</tr>";
			    
			    while (capital>0) {
			    	resultat = resultat 
			    			+ "<tr>"
			    			+ "<td>"+df.format(capital)+"</td>"
			    			+ "<td>"+df.format(cuota_mensual)+"</td>"
			    			+ "<td>"+df.format(cuota_mensual-(capital*i))+"</td>"
			    			+ "<td>"+df.format(capital*i)+"</td>"
			    			+ "<td>"+df.format(capital - cuota_mensual)+"</td>"
			    			+ "</tr>";
			    	capital = capital - cuota_mensual;
			    }
			    		
			}
		} catch (Exception e) {
			resultat = "Error ejecutando";
			GestionErrores.escribirErrores(e.toString());
		}
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.getWriter().append(resultat);
	}

}
