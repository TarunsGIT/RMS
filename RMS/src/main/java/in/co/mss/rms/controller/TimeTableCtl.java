package in.co.mss.rms.controller;

import in.co.mss.rms.bean.BaseBean;
import in.co.mss.rms.bean.TimeTableBean;
import in.co.mss.rms.exception.ApplicationException;
import in.co.mss.rms.exception.DatabaseException;
import in.co.mss.rms.exception.DuplicateRecordException;
import in.co.mss.rms.models.CollegeModel;
import in.co.mss.rms.models.CourseModel;
import in.co.mss.rms.models.TimeTableModel;
import in.co.mss.rms.util.DataUtility;
import in.co.mss.rms.util.DataValidator;
import in.co.mss.rms.util.PropertyReader;
import in.co.mss.rms.util.ServletUtility;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Time Table functionality Controller. Performs operation for add, update,
 * delete and get Time Table
 * 
 * @author Session Facade
 * @version 1.0
 *
 */
@WebServlet(name = "TimeTableCtl", urlPatterns = { "/ctl/TimeTableCtl.do" })
public class TimeTableCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(TimeTableCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
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

		log.debug("TimeTableCtl Method validate Started");
		String course = request.getParameter("courseId");
		String ExaminationDate = request.getParameter("ExaminationDate");

		boolean pass = true;

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date d;
		TimeTableBean bean = new TimeTableBean();
		try {
			d = format.parse(ExaminationDate);
			DateFormat format1 = new SimpleDateFormat("EEEE");
			String day = format1.format(d);
			bean.setDay(day);
			request.setAttribute("day", day);
			System.out.println(day);

		} catch (ParseException e) {
			e.printStackTrace();

		}

		if (DataValidator.isNull(course)) {
			request.setAttribute("Course",
					PropertyReader.getValue("error.require", "Course"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("Subject"))) {
			request.setAttribute("Subject",
					PropertyReader.getValue("error.require", "Subject"));
			pass = false;
		}

		if (DataValidator.isNull(ExaminationDate)) {
			request.setAttribute("ExaminationDate", PropertyReader.getValue(
					"error.require", "Examination Date"));
			pass = false;
		} else if (!DataValidator.isPastDate(ExaminationDate)) {
			request.setAttribute("ExaminationDate",
					PropertyReader.getValue("error.past", " Examination Date"));
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("Time"))) {
			request.setAttribute("Time",
					PropertyReader.getValue("error.require", "Time"));
			pass = false;
		}
		if (!DataValidator.isNull(ExaminationDate)) {

			SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
			Date d1;
			try {
				d1 = format1.parse(ExaminationDate);
				DateFormat format2 = new SimpleDateFormat("EEEE");
				String day1 = format2.format(d1);
				System.out.println(day1);
				if (day1.equalsIgnoreCase("SUNDAY")) {
				request.setAttribute("ExaminationDate",PropertyReader.getValue("error.sunday",
											""));
				pass = false;

				}

			} catch (ParseException e) {
				e.printStackTrace();

			}

		}
		/*
		 * if (DataValidator.isNull(request.getParameter("Day"))) {
		 * request.setAttribute("Day", PropertyReader.getValue("error.require",
		 * "Day")); pass = false; }
		 */

		log.debug("TimeTableCtl Method validate Ended");
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("TimeTableCtl Method populatebean Started");
		TimeTableBean bean = new TimeTableBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));
		bean.setCourse(DataUtility.getString(request.getParameter("Course")));
		bean.setSubject(DataUtility.getString(request.getParameter("Subject")));
		bean.setExaminationDate(DataUtility.getDate(request
				.getParameter("ExaminationDate")));
		bean.setTime(DataUtility.getString(request.getParameter("Time")));
		String day = (String) request.getAttribute("day");
		bean.setDay(day);

		/* bean.setDay(DataUtility.getString(request.getParameter("Day"))); */

		System.out.println("Population done");
		log.debug("TimeTableCtl Method populatebean Ended");
		populateDTO(bean, request);
		return bean;
	}

	/*********************************** DO GET *****************************************************************/
	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("TimeTableCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		TimeTableModel model = new TimeTableModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0) {
			TimeTableBean bean;
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
		log.debug("TimeTableCtl Method doGet Ended");
	}

	/******************************** DO POST(SUBMIT_LOGIC) *********************************************************/
	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.debug("TimeTableCtl Method doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		TimeTableModel model = new TimeTableModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op)) {

			TimeTableBean bean = (TimeTableBean) populateBean(request);
			try {
				if (id > 0) {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage(
							"Time Table Edited Successfully", request);
				} else {
					long pk = model.add(bean);
					bean.setId(pk);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage(
							"Time Table Added Successfully", request);
				}
				/*
				 * ServletUtility.setBean(bean, request);
				 * ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request,
				 * response); return;
				 */

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility
						.setErrorMessage(
								"Exam is already been scheduled for this subject in this course",
								request);
			} catch (DatabaseException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage(
						"This date is already been occupied for this course",
						request);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			TimeTableBean bean = (TimeTableBean) populateBean(request);
			System.out.println("in try");
			try {
				model.delete(bean);
				ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request,
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
				ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request,
						response);
				return;

			}

			else if (DataValidator.isNull(request.getParameter("Course"))
					&& DataValidator.isNull(request.getParameter("Subject"))
					&& DataValidator.isNull(request
							.getParameter("ExaminationDate"))
					&& DataValidator.isNull(request.getParameter("Time"))
					&& DataValidator.isNull(request.getParameter("Day"))) {
				ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request,
						response);
				return;
			}/*
			 * else { ServletUtility.redirect(ORSView.TIMETABLE_CTL, request,
			 * response); return; }
			 */
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("MarksheetCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		return ORSView.TIMETABLE_VIEW;
	}

}
