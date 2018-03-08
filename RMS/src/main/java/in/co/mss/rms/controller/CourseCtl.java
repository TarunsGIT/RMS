package in.co.mss.rms.controller;

import in.co.mss.rms.bean.BaseBean;
import in.co.mss.rms.bean.CourseBean;

import in.co.mss.rms.exception.ApplicationException;
import in.co.mss.rms.exception.DuplicateRecordException;
import in.co.mss.rms.models.CourseModel;

import in.co.mss.rms.util.DataUtility;
import in.co.mss.rms.util.DataValidator;
import in.co.mss.rms.util.PropertyReader;
import in.co.mss.rms.util.ServletUtility;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Course functionality Controller. Performs operation for add, update and get
 * Course
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */
@WebServlet(name = "CourseCtl", urlPatterns = { "/ctl/CourseCtl.do" })
public class CourseCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(CourseCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("CourseCtl Method validate Started");
		boolean pass = true;
		String duration = request.getParameter("duration");
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
		if (DataValidator.isNull(request.getParameter("duration"))) {
			request.setAttribute("duration",
					PropertyReader.getValue("error.require", "Duration"));
			pass = false;
		} else if (DataValidator.isAlpha(request.getParameter("duration"))) {
			request.setAttribute("duration",
					PropertyReader.getValue("error.duration", ""));
			pass = false;

		} else if (DataValidator.isInvalidDuration(request
				.getParameter("duration"))) {
			request.setAttribute("duration",
					PropertyReader.getValue("error.induration", ""));
			pass = false;
		} 

		log.debug("CourseCtl Method validate Ended");
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("CourseCtl Method populatebean Started");
		CourseBean bean = new CourseBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request
				.getParameter("description")));
		bean.setDuration(DataUtility.getInt(request.getParameter("duration")));
		populateDTO(bean, request);

		log.debug("CourseCtl Method populatebean Ended");
		return bean;
	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("CourseCtl Method doGet Started");

		System.out.println("In do get");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		CourseModel model = new CourseModel();

		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			CourseBean bean;
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
		log.debug("CourseCtl Method doGetEnded");
	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("CourseCtl Method doGet Started");

		System.out.println("In do get");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		CourseModel model = new CourseModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			CourseBean bean = (CourseBean) populateBean(request);

			try {
				if (id > 0) {
					model.update(bean);

					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage(
							"Course edited sucessfully", request);
				} else {
					long pk = model.add(bean);
					bean.setId(pk);

					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage(
							"Course is sucessfully saved", request);
				}
				/*
				 * ServletUtility.setBean(bean, request);
				 * ServletUtility.setSuccessMessage
				 * ("Course is sucessfully saved", request);
				 */

				// IF NOT RETURNED IT WILL THROW ILLEGAL STATE
				// EXCEPTION

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility
						.setErrorMessage("Course already exists", request);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			CourseBean bean = (CourseBean) populateBean(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request,
						response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			if (id > 0) {
				ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request,
						response);
				return;
			}

			else if (DataValidator.isNull(request.getParameter("name"))
					&& DataValidator
							.isNull(request.getParameter("description"))
					&& DataValidator.isNull(request.getParameter("duration")))

			{
				ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request,
						response);
				return;

			}/*
			 * else { ServletUtility.redirect(ORSView.COURSE_CTL, request,
			 * response); return; }
			 */

		}

		ServletUtility.forward(getView(), request, response);

		log.debug("CourseCtl Method doPOst Ended");
	}

	@Override
	protected String getView() {
		return ORSView.COURSE_VIEW;
	}

}
