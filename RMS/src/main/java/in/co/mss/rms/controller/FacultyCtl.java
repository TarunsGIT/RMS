package in.co.mss.rms.controller;

import in.co.mss.rms.bean.BaseBean;
import in.co.mss.rms.bean.FacultyBean;
import in.co.mss.rms.bean.StudentBean;
import in.co.mss.rms.exception.ApplicationException;
import in.co.mss.rms.exception.DatabaseException;
import in.co.mss.rms.exception.DuplicateRecordException;
import in.co.mss.rms.models.CollegeModel;
import in.co.mss.rms.models.CourseModel;
import in.co.mss.rms.models.FacultyModel;
import in.co.mss.rms.models.StudentModel;
import in.co.mss.rms.util.DataUtility;
import in.co.mss.rms.util.DataValidator;
import in.co.mss.rms.util.PropertyReader;
import in.co.mss.rms.util.ServletUtility;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Faculty functionality Controller. Performs operation for add, update, delete
 * and get Faculty
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */
@WebServlet(name = "FacultyCtl", urlPatterns = { "/ctl/FacultyCtl.do" })
public class FacultyCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(FacultyCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		CollegeModel model = new CollegeModel();
		try {
			List l = model.list();
			request.setAttribute("collegeList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}

		CourseModel cmodel = new CourseModel();
		try {
			List l1 = cmodel.list();
			request.setAttribute("courseList", l1);
		} catch (ApplicationException e) {
			log.error(e);

		}
	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("FacultyCtl Method validate Started");
		boolean pass = true;
		String op = DataUtility.getString(request.getParameter("operation"));
		String email = request.getParameter("email");
		String dob = request.getParameter("dob");
		String college = request.getParameter("collegeId");
		String course = request.getParameter("courseId");
		System.out.println("COLLEGE ID=" + college);

		if (DataValidator.isNull(college)) {
			request.setAttribute("College",
					PropertyReader.getValue("error.require", "College Name"));
			pass = false;

		}
		if (DataValidator.isNull(course)) {
			request.setAttribute("Course",
					PropertyReader.getValue("error.require", "Course Name"));
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("primarySubject"))) {
			request.setAttribute("primarySubject", PropertyReader.getValue(
					"error.require", " Primary Subject"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("secondarySubject"))) {
			request.setAttribute("secondarySubject", PropertyReader.getValue(
					"error.require", " Secondary Subject"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("firstName"))) {

			request.setAttribute("firstName",
					PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		} else if (DataValidator.isNotAlpha(request.getParameter("firstName"))) {
			request.setAttribute("firstName",
					PropertyReader.getValue("error.alpha", "First Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {

			request.setAttribute("lastName",
					PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		} else if (!DataValidator.isAlphabetsOnly(request
				.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.last", "Last Name"));
			pass = false;
		} else if (DataValidator.isNotAlpha(request.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.alpha", "Last Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("qualification"))) {
			request.setAttribute("qualification",
					PropertyReader.getValue("error.require", "Qualification"));
			pass = false;

		} else if (DataValidator.isNotAlpha(request
				.getParameter("qualification"))) {
			request.setAttribute("qualification",
					PropertyReader.getValue("error.alpha", "Qualification"));
			pass = false;
		}
		if (DataValidator.isNull(dob)) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.require", "Date Of Birth"));
			pass = false;
		} else if (!DataValidator.isValidDate(new Date(dob))) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.date", "Date Of Birth"));
			pass = false;
		} else if (DataValidator.isUnderAge(request.getParameter("dob"))) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.underage", ""));
			pass = false;

		} else if (DataValidator.isOverAge(request.getParameter("dob"))) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.overage", ""));
			pass = false;

		}

		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo",
					PropertyReader.getValue("error.require", "Mobile No."));
			pass = false;
		} else if (DataValidator.isAlpha(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo",
					PropertyReader.getValue("error.phone", "Mobile No."));
			pass = false;

		} else if (DataValidator
				.isNotMobileNo(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo",
					PropertyReader.getValue("error.nmob", ""));
			pass = false;
		}

		else if ((request.getParameter("mobileNo")).length() != 10) {
			request.setAttribute("mobileNo",
					PropertyReader.getValue("error.length", "Mobile No."));
			pass = false;

		}

		if (DataValidator.isNull(email)) {
			request.setAttribute("email",
					PropertyReader.getValue("error.require", "Email "));
			pass = false;
		} else if (!DataValidator.isEmail(email)) {
			request.setAttribute("email",
					PropertyReader.getValue("error.email", ""));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address",
					PropertyReader.getValue("error.require", "Address"));
			pass = false;
		}

		/*
		 * else if (!DataValidator.isValidDate(new Date(dob))) {
		 * request.setAttribute("dob", PropertyReader.getValue("error.date",
		 * "Date Of Birth")); pass = false; }
		 */
		log.debug("FacultyCtl Method validate Ended");
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("StudentCtl Method populatebean Started");
		FacultyBean bean = new FacultyBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));
		bean.setPrimarySubject(DataUtility.getString(request
				.getParameter("primarySubject")));
		bean.setSecondarySubject(DataUtility.getString(request
				.getParameter("secondarySubject")));
		bean.setFirstName(DataUtility.getString(request
				.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setQualification(DataUtility.getString(request
				.getParameter("qualification")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		populateDTO(bean, request);
		log.debug("StudentCtl Method populatebean Ended");
		return bean;
	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("FaculytCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		// get model

		FacultyModel model = new FacultyModel();
		if (id > 0 || op != null) {
			FacultyBean bean;
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
		log.debug("FacultyCtl Method doGett Ended");
	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("FacultyCtl Method doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model

		FacultyModel model = new FacultyModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			FacultyBean bean = (FacultyBean) populateBean(request);

			try {
				if (id > 0) {
					model.update(bean);

					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage(
							"Faculty Edited Successfully", request);
				} else {
					long pk = model.add(bean);
					bean.setId(pk);

					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage(
							"Faculty Added Successfully", request);
				}

				/*
				 * ServletUtility.setBean(bean, request);
				 * ServletUtility.setSuccessMessage
				 * ("Faculty Successfully Added", request);
				 * ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request,
				 * response); return;
				 */

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage(
						"Faculty's Email Id already exists", request);

			} catch (DatabaseException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility
						.setErrorMessage(
								"Primary and Secondary subject cannot be same",
								request);

			}

		}

		else if (OP_DELETE.equalsIgnoreCase(op)) {

			FacultyBean bean = (FacultyBean) populateBean(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request,
						response);
				return;

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			if (id > 0) {
				ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request,
						response);
				return;

			}

			else if (DataValidator.isNull(request.getParameter("firstName"))
					&& DataValidator.isNull(request.getParameter("lastName"))
					&& DataValidator.isNull(request.getParameter("email"))
					&& DataValidator.isNull(request.getParameter("dob"))
					&& DataValidator.isNull(request.getParameter("mobileNo"))
					&& DataValidator.isNull(request.getParameter("address"))
					&& DataValidator.isNull(request
							.getParameter("qualification"))) {
				ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request,
						response);
				return;
			} /*
			 * else { ServletUtility.redirect(ORSView.FACULTY_CTL, request,
			 * response); return; }
			 */

		}
		ServletUtility.forward(getView(), request, response);

		log.debug("FacultyCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		return ORSView.FACULTY_VIEW;
	}

}
