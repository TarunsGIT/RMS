 package in.co.mss.rms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.mss.rms.bean.BaseBean;
import in.co.mss.rms.bean.RoleBean;
import in.co.mss.rms.bean.UserBean;
import in.co.mss.rms.exception.ApplicationException;
import in.co.mss.rms.models.RoleModel;
import in.co.mss.rms.models.UserModel;
import in.co.mss.rms.util.DataUtility;
import in.co.mss.rms.util.DataValidator;
import in.co.mss.rms.util.PropertyReader;
import in.co.mss.rms.util.ServletUtility;

/**
 * Login functionality Controller. Performs operation for Login
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */
@WebServlet(name="LoginCtl",urlPatterns={"/ctl/LoginCtl"})
public class LoginCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;
	public static final String OP_REGISTER = "Register";
	public static final String OP_SIGN_IN = "SignIn";
	public static final String OP_SIGN_UP = "SignUp";
	public static final String OP_LOG_OUT = "logout";

	private static Logger log = Logger.getLogger(LoginCtl.class);
	
 /************************* SUPER IMPLEMENTATION OF VALIDATE(BASE_CTL) ************************************************************/
	
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("LoginCtl Method validate Started");
		boolean pass =true;
		String op = request.getParameter("operation");
		if (OP_SIGN_UP.equals(op) || OP_LOG_OUT.equals(op)) {
			return pass;
		}
		String login = request.getParameter("login");
		if (DataValidator.isNull(login)) {
			request.setAttribute("login", PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		} else if (!DataValidator.isEmail(login)) {
			request.setAttribute("login", PropertyReader.getValue("error.login", ""));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getValue("error.require", "Password"));
			pass = false;
		}

		log.debug("LoginCtl Method validate Ended");

		return pass;
	}

	/************************** SUPER IMPLEMENTATION OF POPULATE BEAN IN BASECTL *************************************/
	
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("LoginCtl Method populatebean Started");
		UserBean bean = new UserBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		log.debug("LoginCtl Method populatebean Ended");
		return bean;
	}
	

/*************************** DISPLAY LOGIC IN DO GET() **********************************************************/
	
	/**
	 * Display Login form
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		System.out.println("1:IN DO_GET(LOGIN_CTL)");
		log.debug(" Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		UserModel model = new UserModel();//USER MODEL
		RoleModel role = new RoleModel();//ROLE MODEL
		System.out.println("2:BESIDE ROLE IN DO_GET(LOGIN_CTL)");

		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0) {
			UserBean userbean;
			try {
				userbean = model.findByPK(id);
				ServletUtility.setBean(userbean, request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
			/***LOGOUT OPERATION ********/
		} else if (OP_LOG_OUT.equals(op)) {
			session = request.getSession();
			session.invalidate();
			System.out.println("LOGOUT OPERATION(IN DO_GET)");
			ServletUtility.setErrorMessage("YOU HAVE LOGGED OUT !!!!", request);
			ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);
           /* ServletUtility.redirect(ORSView.LOGIN_CTL, request, response);*/
			return;
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("UserCtl Method doPost Ended");

	}

   /********************** ON SIGNIN THIS METHOD IS CALLED *****************************************************/	
	
	/**
	 * Submitting or login action performing
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		log.debug(" Method doPost Started");
        System.out.println("3:IN DO_POST(LOGIN_CTL)");
		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		UserModel model = new UserModel();//USER MODEL
		RoleModel role = new RoleModel();//ROLE MODEL

		long id = DataUtility.getLong(request.getParameter("id"));
		
		System.out.println(id);

		if (OP_SIGN_IN.equalsIgnoreCase(op)) {

			UserBean bean = (UserBean) populateBean(request);

			try {

				bean = model.authenticate(bean.getLogin(), bean.getPassword());

				if (bean != null) {
					session.setAttribute("user", bean);
					long rollId = bean.getRoleId();

					RoleBean rolebean = role.findByPK(rollId);

					if (rolebean != null) {
						session.setAttribute("role", rolebean.getName());
					}

					ServletUtility.forward(ORSView.WELCOME_VIEW, request, response);
					return;
				} else {
					bean = (UserBean) populateBean(request);
					ServletUtility.setBean(bean, request);
					ServletUtility.setErrorMessage("Invalid LoginId And Password", request);
				}

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

			/***** LOGOUT OPERATION *********/
		} else if (OP_LOG_OUT.equals(op)) {
			session = request.getSession();
			session.invalidate();
			ServletUtility.redirect(ORSView.LOGIN_CTL, request, response);
			
			return;
		} else if (OP_SIGN_UP.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.USER_REGISTRATION_CTL, request, response);
			return;

		}

		ServletUtility.forward(getView(), request, response);

		log.debug("UserCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		return ORSView.LOGIN_VIEW;
	}

}
