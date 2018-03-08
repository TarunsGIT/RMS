package in.co.mss.rms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.mss.rms.bean.BaseBean;
import in.co.mss.rms.bean.UserBean;
import in.co.mss.rms.exception.ApplicationException;
import in.co.mss.rms.exception.DuplicateRecordException;
import in.co.mss.rms.models.RoleModel;
import in.co.mss.rms.models.UserModel;
import in.co.mss.rms.util.DataUtility;
import in.co.mss.rms.util.DataValidator;
import in.co.mss.rms.util.PropertyReader;
import in.co.mss.rms.util.ServletUtility;

/**
 * * User functionality Controller. Performs operation for add, update and get
 * User
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */
@WebServlet(name = "UserCtl", urlPatterns = { "/ctl/UserCtl.do" })
public class UserCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(UserCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {
		RoleModel model = new RoleModel();
		try {
			List l = model.list();// ROLE MODEL LIST METHOD
			request.setAttribute("roleList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}
	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("UserCtl Method validate Started");
		boolean pass = true;
		String login = request.getParameter("login");
		String dob = request.getParameter("dob");
		String role = request.getParameter("roleId");

		/*
		 * UserBean bean = new UserBean(); bean.setPassword(password);
		 * request.setAttribute("pass", password);
		 * System.out.println("*****************" + password);
		 */

		if (DataValidator.isNull(request.getParameter("firstName"))) {

			request.setAttribute("firstName",
					PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		} else if (DataValidator.isNotAlpha(request.getParameter("firstName"))) {
			request.setAttribute("firstName",
					PropertyReader.getValue("error.alpha", "First Name"));

		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {

			request.setAttribute("lastName",
					PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		} else if (DataValidator.isNotAlpha(request.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.alpha", "Last Name"));
			pass = false;
		} else if (!DataValidator.isAlphabetsOnly(request
				.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.last", "Last Name"));
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
		if (DataValidator.isNull(role)) {
			request.setAttribute("role",
					PropertyReader.getValue("error.require", "Role"));
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

			pass = false;
		}

		/*
		 * long id1 = DataUtility.getLong(request.getParameter("id"));
		 * 
		 * if (id1 == 0) {
		 * 
		 * if (!request.getParameter("password").equals(
		 * request.getParameter("confirmPassword")) &&
		 * !"".equals(request.getParameter("confirmPassword"))) {
		 * request.setAttribute("confirmPassword",
		 * PropertyReader.getValue("error.cpass", ""));
		 * 
		 * pass = false; } } else {
		 * 
		 * String password = request.getParameter("password");
		 * 
		 * UserBean bean = new UserBean(); bean.setPassword(password);
		 * request.setAttribute("pass", password);
		 * System.out.println("*****************" + password);
		 * bean.setPassword(password);
		 * 
		 * pass = true; }
		 */log.debug("UserCtl Method validate Ended");
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("UserCtl Method populatebean Started");
		UserBean bean = new UserBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setRoleId(DataUtility.getLong(request.getParameter("roleId")));
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
		log.debug("UserCtl Method populatebean Ended");
		return bean;
	}

	/**
	 * Contains DIsplay logics
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("UserCtl Method doGet Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		UserModel model = new UserModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			System.out.println("in id > 0  condition");
			UserBean bean;
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
		log.debug("UserCtl Method doGet Ended");
	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("UserCtl Method doPost Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		UserModel model = new UserModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op)) {
			UserBean bean = (UserBean) populateBean(request);

			try {
				if (id > 0) {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("User edited sucessfully",
							request);

				} else {
					long pk = model.add(bean);
					bean.setId(pk);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage(
							"User added successfully ", request);
				}
				/*
				 * ServletUtility.setBean(bean, request);
				 * ServletUtility.redirect(ORSView.USER_LIST_CTL, request,
				 * response);
				 * 
				 * return;
				 */
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Login id already exists",
						request);
			}
		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			UserBean bean = (UserBean) populateBean(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(ORSView.USER_LIST_CTL, request,
						response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			if (id > 0) {
				ServletUtility.redirect(ORSView.USER_LIST_CTL, request,
						response);
				return;

			}

			else if (DataValidator.isNull(request.getParameter("firstName"))
					&& DataValidator.isNull(request.getParameter("lastName"))
					&& DataValidator.isNull(request.getParameter("login"))
					&& DataValidator.isNull(request.getParameter("password"))
					&& DataValidator.isNull(request
							.getParameter("confirmPassword"))
					&& DataValidator.isNull(request.getParameter("dob"))) {
				ServletUtility.redirect(ORSView.USER_LIST_CTL, request,
						response);
				return;
			}/*
			 * else { ServletUtility.redirect(ORSView.USER_CTL, request,
			 * response); return; }
			 */
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("UserCtl Method doPostEnded");
	}

	@Override
	protected String getView() {
		log.debug("UserCtl getView() Started");
		return ORSView.USER_VIEW;
	}

}
