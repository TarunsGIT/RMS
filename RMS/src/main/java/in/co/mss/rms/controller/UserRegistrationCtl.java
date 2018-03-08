package in.co.mss.rms.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sun.mail.iap.Response;

import in.co.mss.rms.bean.BaseBean;
import in.co.mss.rms.bean.RoleBean;
import in.co.mss.rms.bean.UserBean;
import in.co.mss.rms.exception.ApplicationException;
import in.co.mss.rms.exception.DuplicateRecordException;
import in.co.mss.rms.models.UserModel;
import in.co.mss.rms.util.DataUtility;
import in.co.mss.rms.util.DataValidator;
import in.co.mss.rms.util.PropertyReader;
import in.co.mss.rms.util.ServletUtility;

/**
 * User registration functionality Controller. Performs operation for User
 * Registration
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */
@WebServlet(name = "UserRegistrationCtl", urlPatterns = { "/ctl/UserRegistrationCtl" })
public class UserRegistrationCtl extends BaseCtl {
	public static final String OP_SIGN_UP = "SignUp";
	private static Logger log = Logger.getLogger(UserRegistrationCtl.class);

	/**************************** VALIDATION ***********************************************************************/

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("UserRegistrationCtl Method validate Started");
		boolean pass = true;
		String login = request.getParameter("login");
		String dob = request.getParameter("dob");

		/*
		 * if (DataValidator.isNull(request.getParameter("firstName"))) {
		 * request.setAttribute("firstName",
		 * PropertyReader.getValue("error.require", "First Name")); pass =
		 * false; } if (DataValidator.isNull(request.getParameter("lastName")))
		 * { request.setAttribute("lastName",
		 * PropertyReader.getValue("error.require", "Last Name")); pass = false;
		 * }
		 */

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

		if (DataValidator.isNull(login)) {
			request.setAttribute("login",
					PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		} else if (!DataValidator.isEmail(login)) {
			request.setAttribute("login",
					PropertyReader.getValue("error.login", ""));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password",
					PropertyReader.getValue("error.require", "Password"));
			pass = false;
		} else if ((request.getParameter("password")).length() < 6
				|| (request.getParameter("password")).length() > 11) {
			request.setAttribute("password",
					PropertyReader.getValue("error.pass", "Password"));
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", PropertyReader.getValue(
					"error.require", "Confirm Password"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender",
					PropertyReader.getValue("error.require", "Gender"));
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

		if (!request.getParameter("password").equals(
				request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword",
					PropertyReader.getValue("error.cpass", ""));

			/*
			 * ServletUtility.setErrorMessage(
			 * "Password and Confirm Password does not match!!!!.", request);
			 */
			pass = false;
		}
		log.debug("UserRegistrationCtl Method validate Ended");
		return pass;
	}

	/**************************** POPULATE BEAN *****************************************************************/

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("UserRegistrationCtl Method populatebean Started");
		UserBean bean = new UserBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setRoleId(RoleBean.STUDENT);
		bean.setFirstName(DataUtility.getString(request
				.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		bean.setConfirmPassword(DataUtility.getString(request
				.getParameter("confirmPassword")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		populateDTO(bean, request);
		log.debug("UserRegistrationCtl Method populatebean Ended");
		return bean;
	}

	/********************* DO GET (DISPLAY USER REGISTRATION VIEW) ****************************************************/

	/**
	 * Display concept of user registration
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("UserRegistrationCtl Method doGet Started");
		ServletUtility.forward(getView(), request, response);

	}

	/************************ SUBMIT USER REGISTRATION DATA ******************************************************/

	/**
	 * Submit concept of user registration
	 */

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("IN DO_POST(SUBMIT CONCEPT)");
		log.debug("UserRegistrationCtl Method doPost Started");
		String op = DataUtility.getString(request.getParameter("operation"));

		System.out.println(op);
		// get model
		UserModel model = new UserModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SIGN_UP.equalsIgnoreCase(op)) {
			UserBean bean = (UserBean) populateBean(request);
			try {
				long pk = model.registerUser(bean);
				bean.setId(pk);
				request.getSession().setAttribute("UserBean", bean);
				ServletUtility
						.setSuccessMessage(
								"You have sucessfully registered with ORS!!!! Check your mail",
								request);
				/*
				 * ServletUtility.setSuccessMessage("Please Sign in to continue!!"
				 * , request);
				 */
				ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
				/*
				 * ServletUtility.setBean(bean, request);
				 * ServletUtility.setErrorMessage(
				 * "Please check your internet connection !!!!", request);
				 * ServletUtility.forward(getView(), request, response); return;
				 */
				/*
				 * ServletUtility.handleException(e, request, response); return;
				 */
			} catch (DuplicateRecordException e) {
				log.error(e);
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Login id already exists",
						request);
				ServletUtility.forward(getView(), request, response);
			}
		}
		log.debug("UserRegistrationCtl Method doPost Ended");
	}

	/********************************** RETURN JSP PAGE OF PARTICULAR CONTROLLER ***********************************/

	@Override
	protected String getView() {
		return ORSView.USER_REGISTRATION_VIEW;
	}

}
