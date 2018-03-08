package in.co.mss.rms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.mss.rms.bean.BaseBean;
import in.co.mss.rms.bean.MarksheetBean;
import in.co.mss.rms.exception.ApplicationException;
import in.co.mss.rms.exception.DuplicateRecordException;
import in.co.mss.rms.models.MarksheetModel;
import in.co.mss.rms.models.StudentModel;
import in.co.mss.rms.util.DataUtility;
import in.co.mss.rms.util.DataValidator;
import in.co.mss.rms.util.PropertyReader;
import in.co.mss.rms.util.ServletUtility;

/**
 * Marksheet functionality Controller. Performs operation for add, update,
 * delete and get Marksheet
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */

@WebServlet(name = "MarksheetCtl", urlPatterns = { "/ctl/MarksheetCtl.do" })
public class MarksheetCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(MarksheetCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		log.debug("preload inside MarksheetCtl");
		StudentModel model = new StudentModel();
		try {
			List l = model.list();// LIST METHOD OF STUDENT MODEL
			request.setAttribute("studentList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}
	}

	/********************************* VALIDATE ************************************************/

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("MarksheetCtl Method validate Started");
		boolean pass = true;
		String name = request.getParameter("studentId");

		if (DataValidator.isNull(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo",
					PropertyReader.getValue("error.require", "Roll Number"));
			pass = false;
		} else if (!DataValidator
				.isAlphaNumeric(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo",
					PropertyReader.getValue("error.alphanum", "Roll Number"));
			pass = false;
		}
		/*
		 * else if (DataValidator.isSpecial(request.getParameter("rollNo"))) {
		 * request.setAttribute("rollNo", PropertyReader.getValue("error.spc",
		 * "Roll Number")); pass = false; }
		 */
		if (DataValidator.isNull(name)) {
			request.setAttribute("Name",
					PropertyReader.getValue("error.require", "Name"));

		}
		if (DataValidator.isNull(request.getParameter("physics"))) {
			request.setAttribute("physics",
					PropertyReader.getValue("error.require", "Physics"));
			pass = false;
		} else if (DataValidator.isAlpha(request.getParameter("physics"))) {
			request.setAttribute("physics",
					PropertyReader.getValue("error.int", ""));
			pass = false;

		} else if (DataUtility.getInt(request.getParameter("physics")) > 100) {
			request.setAttribute("physics", "Marks Should be Out Of 100");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("chemistry"))) {
			request.setAttribute("chemistry",
					PropertyReader.getValue("error.require", "Chemistry"));
			pass = false;
		} else if (DataValidator.isAlpha(request.getParameter("chemistry"))) {
			request.setAttribute("chemistry",
					PropertyReader.getValue("error.int", ""));
			pass = false;

		} else if (DataUtility.getInt(request.getParameter("chemistry")) > 100) {
			request.setAttribute("chemistry", "Marks Should be Out Of 100");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("maths"))) {
			request.setAttribute("maths",
					PropertyReader.getValue("error.require", "Maths"));
			pass = false;
		} else if (DataValidator.isAlpha(request.getParameter("maths"))) {
			request.setAttribute("maths",
					PropertyReader.getValue("error.int", ""));
			pass = false;

		} else if (DataUtility.getInt(request.getParameter("maths")) > 100) {
			request.setAttribute("maths", "Marks Should be Out Of 100");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("studentId"))) {
			request.setAttribute("studentId",
					PropertyReader.getValue("error.require", "Student Name"));
			pass = false;
		}
		log.debug("MarksheetCtl Method validate Ended");
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("MarksheetCtl Method populatebean Started");
		MarksheetBean bean = new MarksheetBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setRollNo(DataUtility.getString(request.getParameter("rollNo")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setPhysics(DataUtility.getInt(request.getParameter("physics")));
		bean.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));
		bean.setMaths(DataUtility.getInt(request.getParameter("maths")));
		bean.setStudentId(DataUtility.getLong(request.getParameter("studentId")));
		populateDTO(bean, request);
		System.out.println("Population done");
		log.debug("MarksheetCtl Method populatebean Ended");
		return bean;
	}

	/*********************************** DO GET *****************************************************************/
	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("MarksheetCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		MarksheetModel model = new MarksheetModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0) {
			MarksheetBean bean;
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
		log.debug("MarksheetCtl Method doGet Ended");
	}

	/******************************** DO POST(SUBMIT_LOGIC) *********************************************************/
	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("MarksheetCtl Method doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		MarksheetModel model = new MarksheetModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			MarksheetBean bean = (MarksheetBean) populateBean(request);
			try {
				if (id > 0) {
					model.update(bean);// UPDATE METHOD OF MARKSHEET MODEL
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage(
							"Marksheet edited sucessfully", request);
				} else {
					long pk = model.add(bean);// ADD METHOD OF MARKSHEET MODEL
					bean.setId(pk);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage(
							"Marksheet Added sucessfully", request);
				}

				/*
				 * ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request,
				 * response);
				 */
				/* return; */

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Roll no already exists",
						request);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			MarksheetBean bean = (MarksheetBean) populateBean(request);
			System.out.println("in try");
			try {
				model.delete(bean);
				ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request,
						response);
				System.out.println("in try");
				return;
			} catch (ApplicationException e) {
				System.out.println("in catch");
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			if (id > 0) {
				ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request,
						response);
				return;

			}  

			else if (DataValidator.isNull(request.getParameter("rollNo"))
					&& DataValidator.isNull(request.getParameter("chemistry"))
					&& DataValidator.isNull(request.getParameter("physics"))
					&& DataValidator.isNull(request.getParameter("maths"))) {
				ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request,
						response);
				return;
			}/*
			 * else { ServletUtility.redirect(ORSView.MARKSHEET_CTL, request,
			 * response); return; }
			 */
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("MarksheetCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		return ORSView.MARKSHEET_VIEW;
	}

}
