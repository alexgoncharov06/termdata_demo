package com.github.alexwolfgoncharov.termdata.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.alexwolfgoncharov.termdata.dao.ErrorDao;
import com.github.alexwolfgoncharov.termdata.dao.Factory;
import com.github.alexwolfgoncharov.termdata.dao.TermDao;
import com.github.alexwolfgoncharov.termdata.interfaces.ErrorCodes;
import com.github.alexwolfgoncharov.termdata.interfaces.ErrorHistory;
import com.github.alexwolfgoncharov.termdata.interfaces.ErrorStatus;

@WebServlet(name = "error", urlPatterns = { "/error" })
public class Error extends HttpServlet {

	private static final long serialVersionUID = -9182603768298319465L;
	private static final Logger log = Logger.getLogger(Error.class.getName());
	private static Factory fac;
	private static ErrorDao errDao;
	private static TermDao termDao;

	@Override
	public void init() throws ServletException {
		log.info("init method error.");
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

	@SuppressWarnings("static-access")
	private void process(HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException {
		if (fac == null)
			fac.getInstance();
		if (errDao == null)
			errDao = fac.getErrorDAO();
		if (termDao == null)
			termDao = fac.getTermDAO();

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");


		ErrorCodes	error = errDao.getById(new Integer(request.getParameter("id")));
		List<ErrorHistory> listHistor = error.getError_history();
		ErrorHistory hs = new ErrorHistory(
				error);
		hs.setStatus(ErrorStatus.OK
				.toString());
		// history.setStatus(textOfError);
		hs.setTextStatus("error closed");
		listHistor.add(hs);
		error.setError_history(listHistor);
		error.setClosed(true);
		try {
			errDao.update(error);
		} catch (SQLException e) {
			// TODO Auto-generated catch
			// block
			e.printStackTrace();
		}
		
		// List <ErrorHistory> lstError= error.getError_history();
		// lstError.add(hist);
		// error.setError_history(lstError);
		// DAOOkpo dao = new DAOOkpo();
		log.severe("Error ID: " + error.getID() + "is closed");

		PrintWriter out = response.getWriter();
		out.println("ok");
		out.close();
	}
}
