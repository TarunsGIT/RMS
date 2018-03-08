package in.co.mss.rms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.mss.rms.bean.BaseBean;
import in.co.mss.rms.bean.RoleBean;
import in.co.mss.rms.exception.ApplicationException;
import in.co.mss.rms.exception.DuplicateRecordException;
import in.co.mss.rms.models.RoleModel;
import in.co.mss.rms.util.DataUtility;
import in.co.mss.rms.util.DataValidator;
import in.co.mss.rms.util.PropertyReader;
import in.co.mss.rms.util.ServletUtility;

/**
 * Role functionality Controller. Performs operation for add, update and get
 * Role
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */

@WebServlet(name = "RoleCtl", urlPatterns = { "/ctl/RoleCtl.do" })
public class RoleCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(RoleCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("RoleCtl Method validate Started");
		boolean pass = true;
		/*
		 * if
		 * (DataValidator.isNull(request.getParameter("name"))||DataValidator.
		 * isNotAlpha(request.getParameter("name"))) {
		 * request.setAttribute("name", PropertyReader.getValue("error.require",
		 * "Name")); pass = false; } if
		 * (DataValidator.isNull(request.getParameter
		 * ("description"))||DataValidator
		 * .isNotAlpha(request.getParameter("description"))) {
		 * request.setAttribute("description",
		 * PropertyReader.getValue("error.require", "Description")); pass =
		 * false; }
		 */
		if (DataValidator.isNull(request.getParameter("name"))) {

			request.setAttribute("name",
					PropertyReader.getValue("error.require", "Name"));
			pass = false;
		} else if (DataValidator.isNotAlpha(request.getParameter("name"))) {
			request.setAttribute("name",
					PropertyReader.getValue("error.alpha", "Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("description"))) {

			request.setAttribute("description",
					PropertyReader.getValue("error.require", "Description"));
			pass = false;
		} else if (DataValidator
				.isNotAlpha(request.getParameter("description"))) {
			request.setAttribute("description",
					PropertyReader.getValue("error.alpha", "Description"));
			pass = false;
		}
		/*
		 * else if
		 * (DataValidator.isNotAlpha(request.getParameter("description")) ){
		 * request.setAttribute("description",
		 * PropertyReader.getValue("error.var", "Description")); pass = false; }
		 */

		log.debug("RoleCtl Method validate Ended");
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("RoleCtl Method populatebean Started");
		RoleBean bean = new RoleBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request
				.getParameter("description")));
		populateDTO(bean, request);
		log.debug("RoleCtl Method populatebean Ended");
		return bean;
	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("RoleCtl Method doGet Started");

		System.out.println("In do get");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		RoleModel model = new RoleModel();

		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			RoleBean bean;
			try {
				bean = model.findByPK(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("RoleCtl Method doGetEnded");
	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("RoleCtl Method doGet Started");

		System.out.println("In do get");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		RoleModel model = new RoleModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			RoleBean bean = (RoleBean) populateBean(request);

			try {
				if (id > 0) {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage(
							"Role Edited Successfully", request);
				} else {
					long pk = model.add(bean);
					bean.setId(pk);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Role Added Successfully",
							request);
				}

				/*
				 * ServletUtility.setBean(bean, request);
				 * ServletUtility.setSuccessMessage("Role Successfully Saved",
				 * request); ServletUtility.redirect(ORSView.ROLE_LIST_CTL,
				 * request, response); return;
				 */

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Role already exists", request);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			RoleBean bean = (RoleBean) populateBean(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request,
						response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			if (id > 0) {
				ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request,
						response);
				return;

			}

			else if (DataValidator.isNull(request.getParameter("name"))
					&& DataValidator
							.isNull(request.getParameter("description"))) {
				ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request,
						response);
				return;
			}/*
			 * else { ServletUtility.redirect(ORSView.TIMETABLE_CTL, request,
			 * response); return; }
			 */
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("TimeTableCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		return ORSView.ROLE_VIEW;
	}

}
