package in.co.mss.rms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.mss.rms.bean.BaseBean;
import in.co.mss.rms.bean.UserBean;
import in.co.mss.rms.exception.ApplicationException;
import in.co.mss.rms.exception.RecordNotFoundException;
import in.co.mss.rms.models.UserModel;
import in.co.mss.rms.util.DataUtility;
import in.co.mss.rms.util.DataValidator;
import in.co.mss.rms.util.PropertyReader;
import in.co.mss.rms.util.ServletUtility;

/**
 * Change Password functionality Controller. Performs operation for Change
 * Password
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */

@WebServlet(name = "ChangePasswordCtl", urlPatterns = { "/ctl/ChangePasswordCtl.do" })
public class ChangePasswordCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_CHANGE_MY_PROFILE = "Change My Profile";

	private static Logger log = Logger.getLogger(ChangePasswordCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("ChangePasswordCtl Method validate Started");
		boolean pass = true;
		String op = request.getParameter("operation");
		if (OP_CHANGE_MY_PROFILE.equalsIgnoreCase(op)) {
			return pass;
		}
		if (DataValidator.isNull(request.getParameter("oldPassword"))) {
			request.setAttribute("oldPassword",
					PropertyReader.getValue("error.require", "Old Password"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("newPassword"))) {
			request.setAttribute("newPassword",
					PropertyReader.getValue("error.require", "New Password"));
			pass = false;
		} else if ((request.getParameter("newPassword")).length() < 6
				|| (request.getParameter("newPassword")).length() > 11) {
			request.setAttribute("newPassword",
					PropertyReader.getValue("error.pass", "New Password"));
			pass = false;

		}

		else if (request.getParameter("oldPassword").equals(
				request.getParameter("newPassword"))) {
			ServletUtility.setErrorMessage(
					"New Password Cant be same as Old Password", request);
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", PropertyReader.getValue(
					"error.require", "Confirm Password"));
			pass = false;
		}
		if (!request.getParameter("newPassword").equals(
				request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword",
					PropertyReader.getValue("error.npass", ""));
			/*
			 * ServletUtility.setErrorMessage(
			 * "New and confirm passwords not matched", request);
			 */
			pass = false;
		}

		log.debug("ChangePasswordCtl Method validate Ended");
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("ChangePasswordCtl Method populatebean Started");
		UserBean bean = new UserBean();
		bean.setPassword(DataUtility.getString(request
				.getParameter("oldPassword")));
		bean.setConfirmPassword(DataUtility.getString(request
				.getParameter("confirmPassword")));
		populateDTO(bean, request);
		log.debug("ChangePasswordCtl Method populatebean Ended");
		return bean;
	}

	/**
	 * Display Logics inside this method
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Submit logic inside it
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		log.debug("ChangePasswordCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		UserModel model = new UserModel();

		UserBean bean = (UserBean) populateBean(request);

		UserBean UserBean = (UserBean) session.getAttribute("user");

		String newPassword = (String) request.getParameter("newPassword");

		long id = UserBean.getId();

		if (OP_SAVE.equalsIgnoreCase(op)) {
			try {
				System.out.println("in OP SAVE");
				boolean flag = model.changePassword(id, bean.getPassword(),
						newPassword);
				if (flag == true) {
					bean = model.findByLogin(UserBean.getLogin());
					session.setAttribute("user", bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage(
							"Password has been changed Successfully.", request);
                     
				}
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;

			} catch (RecordNotFoundException e) {
				ServletUtility.setErrorMessage("Invalid Old Password", request);
			}
		}
		
		else if (OP_CANCEL.equalsIgnoreCase(op)) {
			System.out.println("IN CANCEL CPCTL");
			if (DataValidator.isNull(request.getParameter("oldPassword"))
					&& DataValidator
							.isNull(request.getParameter("newPassword"))
					&& DataValidator.isNull(request
							.getParameter("confirmPassword"))) {
				System.out.println("IN CANCEL CPCTL");
				
				ServletUtility.redirect(ORSView.CHANGE_PASSWORD_CTL, request,
						response);

				return;

			} else {
				ServletUtility.redirect(ORSView.CHANGE_PASSWORD_CTL, request,
						response);
				return;
			}

		} else if (OP_CHANGE_MY_PROFILE.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.MY_PROFILE_CTL, request, response);
			return;
		}

		ServletUtility.forward(ORSView.CHANGE_PASSWORD_VIEW, request, response);
		log.debug("ChangePasswordCtl Method doGet Ended");
	}

	@Override
	protected String getView() {
		return ORSView.CHANGE_PASSWORD_VIEW;
	}

}
