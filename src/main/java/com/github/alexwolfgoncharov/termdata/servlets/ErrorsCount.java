package com.github.alexwolfgoncharov.termdata.servlets;

import com.github.alexwolfgoncharov.termdata.countErrors.CountErrors;
import com.github.alexwolfgoncharov.termdata.errorsUtil.ErrorsUtil;
import com.github.alexwolfgoncharov.termdata.errorsUtil.ErrorsUtilImpl;
import com.github.alexwolfgoncharov.termdata.interfaces.BaseID;
import com.github.alexwolfgoncharov.termdata.interfaces.User;
import com.github.alexwolfgoncharov.termdata.interfaces.UserRoleEnum;
import com.github.alexwolfgoncharov.termdata.services.UserService;
import com.github.alexwolfgoncharov.termdata.services.UserServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

@WebServlet(name = "countErrors", urlPatterns = { "/getErrors" })
public class ErrorsCount extends HttpServlet {

	private static final long serialVersionUID = -9182603768298319465L;
	private static final Logger log = Logger.getLogger(ErrorsCount.class.getName());
	private static final String id = "id";

	@Override
	public void init() throws ServletException {
		log.info("init method countErrors.");
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

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		 Map<String, String[]> allParametr =  request.getParameterMap();
        ErrorsUtil errUtil = new ErrorsUtilImpl();
        Gson gson = new Gson();
        StringBuffer buf = new StringBuffer();
        PrintWriter out = response.getWriter();
        UserService userServ = new UserServiceImpl();
		boolean forUser = false;
		User user = new User();

		if (allParametr.containsKey("all")){

			List<CountErrors> allCurrentErrors = new ArrayList<CountErrors>();
			if (allParametr.containsKey("login")){
				user = userServ.getUser(request.getParameter("login"));
                if (user != null) {
                    if (user.getRole().equals("USER")) {
						allCurrentErrors = errUtil.getAllCurrentErrorsByLogin(user.getLogin());

                    } else{
						allCurrentErrors= errUtil.getAllCurrentErrors();

					}

                } else {

                    out.print("\"Error\" : \"No login: " + request.getParameter("login") + "\"");
                    out.close();
                    return;
                }



            } else {

				allCurrentErrors = errUtil.getAllCurrentErrors();

			}

            gson.toJson(allCurrentErrors, out);
//			log.info(allCurrentErrors.toString());

        } else if (allParametr.containsKey("baseId")){
            CountErrors curError = errUtil.getCurrentErrors(request.getParameter("baseId"));
            gson.toJson(curError, out);
        } else if (allParametr.containsKey("count")){

			int count = 0;
			List<CountErrors> allCurrentErrors = null;


			if (allParametr.containsKey("login")){
                user = userServ.getUser(request.getParameter("login"));
                if (user != null) {
                    if (user.getRole().equals("USER")) {
						allCurrentErrors = errUtil.getAllCurrentErrorsByLogin(user.getLogin());
                    } else {

						allCurrentErrors = errUtil.getAllCurrentErrors();

					}


                } else {

                    out.print("\"Error\" : \"No login: " + request.getParameter("login") + "\"");
                    out.close();
                    return;
                }


			} else {
				allCurrentErrors = errUtil.getAllCurrentErrors();

			}


				for (CountErrors errors : allCurrentErrors){

					if (errors.isNoSignal() || errors.getCount() > 0) {

							count++;



					}

				}






            gson.toJson(count, out);



        } else {

            String error = "No available request";
            gson.toJson(error, out);
        }









//		out.println();
		out.close();
	}

}
