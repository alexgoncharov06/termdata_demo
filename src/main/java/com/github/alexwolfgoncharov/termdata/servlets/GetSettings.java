package com.github.alexwolfgoncharov.termdata.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.github.alexwolfgoncharov.termdata.dao.Factory;
import com.github.alexwolfgoncharov.termdata.interfaces.BaseID;
import com.github.alexwolfgoncharov.termdata.interfaces.SensorsBaseId;
import com.github.alexwolfgoncharov.termdata.interfaces.ThermData;

@WebServlet(name = "getset", urlPatterns = { "/getset" })
public class GetSettings extends HttpServlet {

	private static final long serialVersionUID = -9182603768292231465L;
	private static final Logger log = Logger.getLogger(GetSettings.class
			.getName());

	@Override
	public void init() throws ServletException {
		log.info("init method getset.");
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

		try {
			process(req, resp);
		} catch (SQLException e) {
			log.severe(e.getMessage());
		}

	}

	// OPTIONS необходимы для нормальной работы POST запросов с личными
	// заголовками в AJAX
	// сначала получает OPTIONS и сверяются заголовки доступные для сервера,
	// а потом осуществляется POST запрос
	@Override
	protected void doOptions(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers",
				"Content-Type, X-Requested-With, type, json, accept");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
	}

	@SuppressWarnings("deprecation")
	private void process(HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		Factory.getInstance();

		Gson json = new Gson();

		Enumeration<String> e = request.getHeaderNames();
		ArrayList<String> arrList = new ArrayList<String>();
		while (e.hasMoreElements()) {
			arrList.add(e.nextElement());
		}
		StringBuilder str1 = new StringBuilder();
		Exit: if (arrList.contains("json")) {
			if ("true".equals(request.getHeader("json"))) {
				// Method for getting all BaseID with sensors  from DB
				// base_id & sensors_base_id
				log.info("Method for getting all BaseID with sensors data ");
				if ("get".equals(request.getHeader("type"))) {

					str1.append("{ \"BaseID\" :");
					List<BaseID> baseList = Factory.getInstance()
							.getBaseIdDAO().getAll();
					str1.append(json.toJson(baseList));
					str1.append("}");
				}

				// Method for add new BaseID with sensors settings
				else if ("set".equals(request.getHeader("type"))) {
					log.info(request.getRemoteAddr() +" | Method for add new or change BaseID with sensors settings");
					 BufferedReader bf = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF8"));
//					BufferedReader bf = request.getReader();
					StringBuilder str = new StringBuilder();
					while (bf.ready() && bf!= null) {
						str.append(bf.readLine());
					}
					if (str.length() == 0) {
						str1.append("{ \"result\" : \"error\"}");
						break Exit;
					}
					log.severe(str.toString());
					BaseID base = json.fromJson(str.toString(), BaseID.class);
					try{
						Factory.getInstance().getBaseIdDAO().seveOrUpdate(base);
					} catch(Exception ex){
						log.severe(ex.getMessage());
						str1.append("{ \"result\" : \"error\"}");
						break Exit;
					}
					

					str1.append("{ \"result\" : \"ok\"}");

				} else if ("basename".equals(request.getHeader("type"))) { // метод
																			// получения
																			// списка
																			// BASE_ID
																			// из
																			// таблицы
																			// с
																			// данными
																			// с
																			// точек
					log.info(request.getRemoteAddr() + " | Method for getting BaseID with sensors data");
					str1.append("{ \"BaseID\" :");
					List<String> nameOfBase = Factory.getInstance()
							.getTermDAO().getAllBase();
					str1.append(json.toJson(nameOfBase));
					str1.append("}");

				} else if ("baseid_whith_set_data".equals(request
						.getHeader("type"))) {
					log.info(request.getRemoteAddr() + " start baseid_whi`th_set_data");
					

					List<BaseID> baseList = Factory.getInstance()
							.getBaseIdDAO().getAll();
					List<BaseIdData> baseDataList = new ArrayList<GetSettings.BaseIdData>();

					for (BaseID base : baseList) {
						ThermData td = new ThermData(Factory.getInstance().getDataService().getLastByBase(base.getBaseID()));

//								Factory.getInstance().getTermDAO()
//								.getLastByBase(base.getBaseID());
						List<SensorsBaseId> sensList = base.getSensors();
						// Collections.sort(sensList);
						base.setSensors(sensList);
						BaseIdData bsData = new BaseIdData(base, td);
						baseDataList.add(bsData);
					}

					str1.append("{ \"BaseID_Data\" :");
					str1.append(json.toJson(baseDataList));
					str1.append("}");

				} else if ("new_baseid".equals(request.getHeader("type"))) {
					log.info(request.getRemoteAddr() + " start new_baseid");

					List<String> nameOfBase = Factory.getInstance()
							.getTermDAO().getAllBase();
					List<BaseID> baseList = Factory.getInstance()
							.getBaseIdDAO().getAll();
					List<String> newBaseID = new ArrayList<String>();

					List<String> currentBase = new ArrayList<String>();

					for (BaseID baseid : baseList) {

						currentBase.add(baseid.getBaseID());

					}
					log.info(json.toJson(currentBase));
					for (String base : nameOfBase) {
						if (!(currentBase.contains(base))) {
							newBaseID.add(base);
						}

					}

					str1.append("{ \"NewBaseID:\" :");
					str1.append(json.toJson(newBaseID));
					str1.append("}");

				} else if ("archive".equals(request.getHeader("type"))) {
					log.severe("start archive");
					String error = "";
					Enumeration<String> enumPar = request.getParameterNames();
					ArrayList<String> arrListPar = new ArrayList<String>();
					while (enumPar.hasMoreElements()) {
						arrListPar.add(enumPar.nextElement());
					}
					String dateFrom, dateTo, baseid;
					if (arrListPar.contains("datefrom")
							&& arrListPar.contains("baseid")) {
						baseid = request.getParameter("baseid");
						dateFrom = request.getParameter("datefrom");
						DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
						Date from = new Date(), to = new Date();
						try {
							from = format.parse(dateFrom);
							to = new Date(from.getTime());
							to.setDate(1);
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						if (arrListPar.contains("dateto")) {
							dateTo = request.getParameter("dateto");
							try {
								to = format.parse(dateTo);
							} catch (ParseException e1) {
								e1.printStackTrace();
							}

						}
						if (from != null && to != null) {
							ArrayList<ThermData> archiveData = (ArrayList<ThermData>) Factory
									.getInstance().getTermDAO()
									.getAllByBaseFromToDate(baseid, from, to);
							BaseID base = Factory.getInstance().getBaseIdDAO()
									.getByBaseID(baseid);
							Gson jsonArc = new GsonBuilder().setDateFormat(
									"HH:mm:ss dd.MM.yyyy").create();
							str1.append("{ \"Archive\" : { \"BaseId\":");
							str1.append(jsonArc.toJson(base));
							str1.append(", \"data\":");
							str1.append(jsonArc.toJson(archiveData));
							str1.append("}}");

						} else {
							error = "cannot convert date from request";
						}
					} else {

						str1.append("{ \"result\" : \"error\", \"error\" :");
						str1.append(error);
						str1.append("}");
					}

				}
			}

		}

		PrintWriter out = response.getWriter();
		out.println(str1.toString());
		out.close();
	}

	private class BaseIdData {

		private BaseID baseid;
		private ThermData data;

		BaseIdData() {

		}

		BaseIdData(BaseID baseid, ThermData data) {
			this.baseid = baseid;
			this.data = data;

		}

		public BaseID getBaseid() {
			return baseid;
		}

		public void setBaseid(BaseID baseid) {
			this.baseid = baseid;
		}

		public ThermData getData() {
			return data;
		}

		public void setData(ThermData data) {
			this.data = data;
		}

	}

}
