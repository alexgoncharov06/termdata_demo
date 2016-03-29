package com.github.alexwolfgoncharov.termdata.servlets;

import com.github.alexwolfgoncharov.termdata.dao.Factory;
import com.github.alexwolfgoncharov.termdata.interfaces.OnlineData;
import com.github.alexwolfgoncharov.termdata.interfaces.ThermData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet(name = "servlets", urlPatterns = { "/get" })
public class Getter extends HttpServlet {

	private static final long serialVersionUID = -9182603768298319465L;
	private static final Logger log = Logger.getLogger(Getter.class.getName());
	private static final String id = "id";

	@Override
	public void init() throws ServletException {
		log.info("init servlets method getter.");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			process(request, response);
		} catch (SQLException e) {
			log.severe(e.getMessage());
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// req.getRequestDispatcher("index.jsp").forward(req, resp);
		try {
			process(req, resp);
		} catch (SQLException e) {
			log.severe(e.getMessage());
		}

	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		Enumeration<String> e = request.getParameterNames();
		Map<String, String[]> paramH = request.getParameterMap();
		ArrayList<String> arrList = new ArrayList<String>();
		if (paramH.containsKey("id") && !paramH.get("id").equals("")){
			log.info("getParameterMap() is good");
		};
		while (e.hasMoreElements()) {
			arrList.add(e.nextElement());
		}
		ThermData termData = new ThermData();
		StringBuilder str1 = new StringBuilder();
		termData.setBaseID(request.getParameter(id));
		if (arrList.contains("json")) {
			if ("true".equals(request.getParameter("json"))) {
				response.setHeader("Access-Control-Allow-Origin", "*");
				response.setContentType("application/json");
				str1.append("{ \"ThermData\" :");
				str1.append(getOneListBaseIDJson(request, response));
				str1.append("}");
			} else if ("all".equals(request.getParameter("json"))) {
				response.setHeader("Access-Control-Allow-Origin", "*");
				response.setContentType("application/json");
				str1.append("{ \"ThermData\" :");
				int limit;
				if (arrList.contains("limit")) {
					limit = Integer.parseInt(request.getParameter("limit"));
				} else {
					limit = 500;
				}

				log.severe("ID: " + " | "
						+ (request.getParameter(id) + " | Limit:  " + limit));
				String rez = getAllByBaseIDJson(request.getParameter(id), limit);
				str1.append(rez);
				str1.append("}");

			}

		} else {
			if (arrList.contains("term1")
					&& !(request.getParameter("term1").equals("")))
				termData.setTerm1(new Float(request.getParameter("term1")));
			if (arrList.contains("term2")
					&& !(request.getParameter("term2").equals("")))
				termData.setTerm2(new Float(request.getParameter("term2")));
			if (arrList.contains("term3")
					&& !(request.getParameter("term3").equals("")))
				termData.setTerm3(new Float(request.getParameter("term3")));
			if (arrList.contains("term4")
					&& !(request.getParameter("term4").equals("")))
				termData.setTerm4(new Float(request.getParameter("term4")));
			if (arrList.contains("term5")
					&& !(request.getParameter("term5").equals("")))
				termData.setTerm5(new Float(request.getParameter("term5")));
			if (arrList.contains("term6")
					&& !(request.getParameter("term6").equals("")))
				termData.setTerm6(new Float(request.getParameter("term6")));
			if (arrList.contains("term7")
					&& !(request.getParameter("term7").equals("")))
				termData.setTerm7(new Float(request.getParameter("term7")));
			if (arrList.contains("term8")
					&& !(request.getParameter("term8").equals("")))
				termData.setTerm8(new Float(request.getParameter("term8")));
			if (arrList.contains("term9")
					&& !(request.getParameter("term9").equals("")))
				termData.setTerm9(new Float(request.getParameter("term9")));
			if (arrList.contains("term10")
					&& !(request.getParameter("term10").equals("")))
				termData.setTerm10(new Float(request.getParameter("term10")));
			if (arrList.contains("term11")
					&& !(request.getParameter("term11").equals("")))
				termData.setTerm11(new Float(request.getParameter("term11")));
			if (arrList.contains("term12")
					&& !(request.getParameter("term12").equals("")))
				termData.setTerm12(new Float(request.getParameter("term12")));
			if (arrList.contains("term13")
					&& !(request.getParameter("term13").equals("")))
				termData.setTerm13(new Float(request.getParameter("term13")));
			if (arrList.contains("term14")
					&& !(request.getParameter("term14").equals("")))
				termData.setTerm14(new Float(request.getParameter("term14")));
			if (arrList.contains("term15")
					&& !(request.getParameter("term15").equals("")))
				termData.setTerm15(new Float(request.getParameter("term15")));
			if (arrList.contains("term16")
					&& !(request.getParameter("term16").equals("")))
				termData.setTerm16(new Float(request.getParameter("term16")));
			if (arrList.contains("term17")
					&& !(request.getParameter("term17").equals("")))
				termData.setTerm17(new Float(request.getParameter("term17")));
			if (arrList.contains("term18")
					&& !(request.getParameter("term18").equals("")))
				termData.setTerm18(new Float(request.getParameter("term18")));
			if (arrList.contains("term19")
					&& !(request.getParameter("term19").equals("")))
				termData.setTerm19(new Float(request.getParameter("term19")));
			if (arrList.contains("term20")
					&& !(request.getParameter("term20").equals("")))
				termData.setTerm20(new Float(request.getParameter("term20")));
			if (arrList.contains("term21")
					&& !(request.getParameter("term21").equals("")))
				termData.setTerm21(new Float(request.getParameter("term21")));
			if (arrList.contains("term22")
					&& !(request.getParameter("term22").equals("")))
				termData.setTerm22(new Float(request.getParameter("term22")));
			if (arrList.contains("term23")
					&& !(request.getParameter("term23").equals("")))
				termData.setTerm23(new Float(request.getParameter("term23")));
			if (arrList.contains("term24")
					&& !(request.getParameter("term24").equals("")))
				termData.setTerm24(new Float(request.getParameter("term24")));
			if (arrList.contains("location")
					&& !(request.getParameter("location").equals(""))) {
				termData.setLocation(new String(request
						.getParameter("location")));
					} else {
						termData.setLocation("no location");
					}

			if (arrList.contains("signal1")
					&& !(request.getParameter("signal1").equals("")))
				termData.setSignal1(new Integer(request.getParameter("signal1")));
			if (arrList.contains("signal2")
					&& !(request.getParameter("signal2").equals("")))
				termData.setSignal2(new Integer(request.getParameter("signal2")));
			if (arrList.contains("signal3")
					&& !(request.getParameter("signal3").equals("")))
				termData.setSignal3(new Integer(request.getParameter("signal3")));
			if (arrList.contains("signal4")
					&& !(request.getParameter("signal4").equals("")))
				termData.setSignal4(new Integer(request.getParameter("signal4")));
			if (arrList.contains("signal5")
					&& !(request.getParameter("signal5").equals("")))
				termData.setSignal5(new Integer(request.getParameter("signal5")));
			if (arrList.contains("signal6")
					&& !(request.getParameter("signal6").equals("")))
				termData.setSignal6(new Integer(request.getParameter("signal6")));
			if (arrList.contains("signal7")
					&& !(request.getParameter("signal7").equals("")))
				termData.setSignal7(new Integer(request.getParameter("signal7")));
			if (arrList.contains("signal8")
					&& !(request.getParameter("signal8").equals("")))
				termData.setSignal8(new Integer(request.getParameter("signal8")));
			if (arrList.contains("signal9")
					&& !(request.getParameter("signal9").equals("")))
				termData.setSignal9(new Integer(request.getParameter("signal9")));
			if (arrList.contains("signal10")
					&& !(request.getParameter("signal10").equals("")))
				termData.setSignal10(new Integer(request
						.getParameter("signal10")));
			if (arrList.contains("signal11")
					&& !(request.getParameter("signal11").equals("")))
				termData.setSignal11(new Integer(request
						.getParameter("signal11")));
			if (arrList.contains("signal12")
					&& !(request.getParameter("signal12").equals("")))
				termData.setSignal12(new Integer(request
						.getParameter("signal12")));
			if (arrList.contains("signal13")
					&& !(request.getParameter("signal13").equals("")))
				termData.setSignal13(new Integer(request
						.getParameter("signal13")));
			if (arrList.contains("signal14")
					&& !(request.getParameter("signal14").equals("")))
				termData.setSignal14(new Integer(request
						.getParameter("signal14")));
			if (arrList.contains("signal15")
					&& !(request.getParameter("signal15").equals("")))
				termData.setSignal15(new Integer(request
						.getParameter("signal15")));
			if (arrList.contains("signal16")
					&& !(request.getParameter("signal16").equals("")))
				termData.setSignal16(new Integer(request
						.getParameter("signal16")));
			if (arrList.contains("hum")
					&& !(request.getParameter("hum").equals("")))
				termData.setHum(new Float(request.getParameter("hum")));
			if (arrList.contains("balance")
					&& !(request.getParameter("balance").equals("")))
				termData.setBalance(new Float(request.getParameter("balance")));
			if (arrList.contains("v")
					&& !(request.getParameter("v").equals("")))
				termData.setVersion(new String(request.getParameter("v")));
			if (arrList.contains("t1")
					&& !(request.getParameter("t1").equals("")))
				termData.setTerm1(new Float(request.getParameter("t1")));
			if (arrList.contains("t2")
					&& !(request.getParameter("t2").equals("")))
				termData.setTerm2(new Float(request.getParameter("t2")));
			if (arrList.contains("t3")
					&& !(request.getParameter("t3").equals("")))
				termData.setTerm3(new Float(request.getParameter("t3")));
			if (arrList.contains("t4")
					&& !(request.getParameter("t4").equals("")))
				termData.setTerm4(new Float(request.getParameter("t4")));
			if (arrList.contains("t5")
					&& !(request.getParameter("t5").equals("")))
				termData.setTerm5(new Float(request.getParameter("t5")));
			if (arrList.contains("t6")
					&& !(request.getParameter("t6").equals("")))
				termData.setTerm6(new Float(request.getParameter("t6")));
			if (arrList.contains("t7")
					&& !(request.getParameter("t7").equals("")))
				termData.setTerm7(new Float(request.getParameter("t7")));
			if (arrList.contains("t8")
					&& !(request.getParameter("t8").equals("")))
				termData.setTerm8(new Float(request.getParameter("t8")));
			if (arrList.contains("t9")
					&& !(request.getParameter("t9").equals("")))
				termData.setTerm9(new Float(request.getParameter("t9")));
			if (arrList.contains("t10")
					&& !(request.getParameter("t10").equals("")))
				termData.setTerm10(new Float(request.getParameter("t10")));
			if (arrList.contains("t11")
					&& !(request.getParameter("t11").equals("")))
				termData.setTerm11(new Float(request.getParameter("t11")));
			if (arrList.contains("t12")
					&& !(request.getParameter("t12").equals("")))
				termData.setTerm12(new Float(request.getParameter("t12")));
			if (arrList.contains("t13")
					&& !(request.getParameter("t13").equals("")))
				termData.setTerm13(new Float(request.getParameter("t13")));
			if (arrList.contains("t14")
					&& !(request.getParameter("t14").equals("")))
				termData.setTerm14(new Float(request.getParameter("t14")));
			if (arrList.contains("t15")
					&& !(request.getParameter("t15").equals("")))
				termData.setTerm15(new Float(request.getParameter("t15")));
			if (arrList.contains("t16")
					&& !(request.getParameter("t16").equals("")))
				termData.setTerm16(new Float(request.getParameter("t16")));
			if (arrList.contains("t17")
					&& !(request.getParameter("t17").equals("")))
				termData.setTerm17(new Float(request.getParameter("t17")));
			if (arrList.contains("t18")
					&& !(request.getParameter("t18").equals("")))
				termData.setTerm18(new Float(request.getParameter("t18")));
			if (arrList.contains("t19")
					&& !(request.getParameter("t19").equals("")))
				termData.setTerm19(new Float(request.getParameter("t19")));
			if (arrList.contains("t20")
					&& !(request.getParameter("t20").equals("")))
				termData.setTerm20(new Float(request.getParameter("t20")));
			if (arrList.contains("t21")
					&& !(request.getParameter("t21").equals("")))
				termData.setTerm21(new Float(request.getParameter("t21")));
			if (arrList.contains("t22")
					&& !(request.getParameter("t22").equals("")))
				termData.setTerm22(new Float(request.getParameter("t22")));
			if (arrList.contains("t23")
					&& !(request.getParameter("t23").equals("")))
				termData.setTerm23(new Float(request.getParameter("t23")));
			if (arrList.contains("t24")
					&& !(request.getParameter("t24").equals("")))
				termData.setTerm24(new Float(request.getParameter("t24")));
			if (arrList.contains("s1")
					&& !(request.getParameter("s1").equals("")))
				termData.setSignal1(new Integer(request.getParameter("s1")));
			if (arrList.contains("s2")
					&& !(request.getParameter("s2").equals("")))
				termData.setSignal2(new Integer(request.getParameter("s2")));
			if (arrList.contains("s3")
					&& !(request.getParameter("s3").equals("")))
				termData.setSignal3(new Integer(request.getParameter("s3")));
			if (arrList.contains("s4")
					&& !(request.getParameter("s4").equals("")))
				termData.setSignal4(new Integer(request.getParameter("s4")));
			if (arrList.contains("s5")
					&& !(request.getParameter("s5").equals("")))
				termData.setSignal5(new Integer(request.getParameter("s5")));
			if (arrList.contains("s6")
					&& !(request.getParameter("s6").equals("")))
				termData.setSignal6(new Integer(request.getParameter("s6")));
			if (arrList.contains("s7")
					&& !(request.getParameter("s7").equals("")))
				termData.setSignal7(new Integer(request.getParameter("s7")));
			if (arrList.contains("s8")
					&& !(request.getParameter("s8").equals("")))
				termData.setSignal8(new Integer(request.getParameter("s8")));
			if (arrList.contains("s9")
					&& !(request.getParameter("s9").equals("")))
				termData.setSignal9(new Integer(request.getParameter("s9")));
			if (arrList.contains("s10")
					&& !(request.getParameter("s11").equals("")))
				termData.setSignal10(new Integer(request.getParameter("s10")));
			if (arrList.contains("s11")
					&& !(request.getParameter("s11").equals("")))
				termData.setSignal11(new Integer(request.getParameter("s11")));
			if (arrList.contains("s12")
					&& !(request.getParameter("s12").equals("")))
				termData.setSignal12(new Integer(request.getParameter("s12")));
			if (arrList.contains("s13")
					&& !(request.getParameter("s13").equals("")))
				termData.setSignal13(new Integer(request.getParameter("s13")));
			if (arrList.contains("s14")
					&& !(request.getParameter("s14").equals("")))
				termData.setSignal14(new Integer(request.getParameter("s14")));
			if (arrList.contains("s15")
					&& !(request.getParameter("s15").equals("")))
				termData.setSignal15(new Integer(request.getParameter("s15")));
			if (arrList.contains("s16")
					&& !(request.getParameter("s16").equals("")))
				termData.setSignal16(new Integer(request.getParameter("s16")));

			log.severe(termData.toString());
			try {
				long id = Factory.getInstance().getTermDAO().add(termData);

				Factory.getInstance().getDataService().add(new OnlineData(termData));
				
				str1.append(id);
			} catch (SQLException exc) {
				log.severe(exc.getMessage());

				str1.append("error");

			}
		}

		PrintWriter out = response.getWriter();
		out.println(str1.toString());
		out.close();
	}

	private String getOneListBaseIDJson(HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException {
		List<String> baseList = Factory.getInstance().getTermDAO().getAllBase();
		List<ThermData> setTermLast = new ArrayList<ThermData>();
		for (String base : baseList) {
			ThermData td;
			td = new ThermData(Factory.getInstance().getDataService().getLastByBase(base));
			setTermLast.add(td);
		}

		Gson jsonPos = new Gson();
		String js = jsonPos.toJson(setTermLast);
		log.severe(js);
		return js;

	}

	private String getAllByBaseIDJson(String baseId, int limit) {
		List<ThermData> setTermLast = new ArrayList<ThermData>();
		try {
			setTermLast = Factory.getInstance().getTermDAO()
					.getAllByBaseLimit(baseId, limit);
		} catch (SQLException e) {
			log.severe(e.getMessage());
		}
		Gson jsonPos = new GsonBuilder().setDateFormat("HH:mm:ss dd-MM-yyyy")
				.create();

		String js = jsonPos.toJson(setTermLast);
		// log.severe(js);
		return js;

	}
}
