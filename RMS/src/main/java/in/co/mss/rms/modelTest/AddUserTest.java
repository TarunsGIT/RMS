package in.co.mss.rms.modelTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import in.co.mss.rms.bean.UserBean;
import in.co.mss.rms.exception.DuplicateRecordException;
import in.co.mss.rms.models.UserModel;
import in.co.mss.rms.exception.ApplicationException;

public class AddUserTest {
	public static UserModel model = new UserModel();
	public static SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");

	public static void main(String[] args) throws ParseException,
			DuplicateRecordException {
		// testAdd();
		 testList();
		// testDelete();
		// testSearch();
		//testGetRoles();

	}

	/****************************** TEST LIST() ********************************/
	/**
	 * Tests get List.
	 */
	public static void testList() {

		try {
			UserBean bean = new UserBean();
			List list = new ArrayList();
			list = model.list(1, 10);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			System.out
					.println("ID\t FIRST NAME\tLAST NAME\t LOGIN\t\t PASSWORD"
							+ " \tDOB \tROLE ID \tUNSUCESSFULL LOGIN\t GENDER\t LAST LOGIN\t LOCK"
							+ "\tMOBILE NO \tCREATED BY\t MODIFIED BY \tCREATED DATE TIME \tMODIFIED DATE TIME");
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (UserBean) it.next();
				System.out.println("\n" + bean.getId());
				System.out.print("\t" + bean.getFirstName());
				System.out.print("\t\t" + bean.getLastName());
				System.out.print("\t" + bean.getLogin());
				System.out.print("\t" + bean.getPassword());
				System.out.print("\t" + bean.getDob());
				System.out.print("\t" + bean.getRoleId());
				System.out.print("\t" + bean.getUnSuccessfulLogin());
				System.out.print("\t" + bean.getGender());
				System.out.print("\t" + bean.getLastLogin());
				System.out.print("\t" + bean.getLock());
				System.out.print("\t" + bean.getMobileNo());
				System.out.print("\t" + bean.getCreatedBy());
				System.out.print("\t" + bean.getModifiedBy());
				System.out.print("\t" + bean.getCreatedDatetime());
				System.out.print("\t" + bean.getModifiedDatetime());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/********************************* TEST ADD() **********************/

	private static void testAdd() throws ParseException,
			DuplicateRecordException {
		try {
			UserBean bean = new UserBean();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/yyyy");
			bean.setId(1001);
			bean.setFirstName("AKSHAY");
			bean.setLastName("RAWAL");
			bean.setLogin("akshayrawal270@gmail.com");
			bean.setPassword("me22@Bean");
			bean.setDob(sdf.parse("12/12/1992"));
			bean.setMobileNo("8080808080");
			bean.setRoleId(1L);
			bean.setUnSuccessfulLogin(4);
			bean.setGender("Male");
			bean.setLastLogin(new Timestamp(new Date().getTime()));
			bean.setLock("Yes");
			bean.setRegisteredIP("127.0.0.1");
			bean.setLastLoginIP("192.168.0.1");
			bean.setCreatedBy("CCD MYSORE DC");
			bean.setModifiedBy("CCD MYSORE DC");
			bean.setConfirmPassword("me22@Bean");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			System.out.println("before add");
			long pk = model.add(bean);
			UserBean addedbean = model.findByPK(pk);
			System.out.println("Test add succ");
			if (addedbean == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/************************* TEST DELETE() **********************************/

	/**
	 * Tests delete a User
	 */
	public static void testDelete() {

		try {
			UserBean bean = new UserBean();
			long pk = 39;
			bean.setId(pk);
			model.delete(bean);
			System.out.println("DELETED SUCESSFULLY  " + bean.getId());
			UserBean deletedbean = model.findByPK(pk);
			if (deletedbean == null) {
				System.out.println("DELETION FAILED");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/*************************** TEST SEARCH *********************************/

	/**
	 * Tests get Search
	 */
	public static void testSearch() throws ParseException {

		try {
			UserBean bean = new UserBean();
			List list = new ArrayList();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			bean.setDob(sdf.parse("12/12/1992"));
			// bean.setFirstName("a");
			// bean.setLastName("KETWAS");
			// bean.setMobileNo("8080808080");
			// bean.setCreatedBy("CCD MYSORE DC");

			list = model.search(bean, 0, 0);
			if (list.size() < 0) {
				System.out.println("SEARCH FAILED");
			}
			System.out
					.println("ID\t FIRST NAME\tLAST NAME\t LOGIN\t\t PASSWORD"
							+ " \tDOB \tROLE ID \tUNSUCESSFULL LOGIN\t GENDER\t LAST LOGIN\t LOCK"
							+ "\tMOBILE NO \tCREATED BY\t MODIFIED BY \tCREATED DATE TIME \tMODIFIED DATE TIME");
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (UserBean) it.next();
				System.out.print("\n" + bean.getId());
				System.out.print("\t" + bean.getFirstName());
				System.out.print("\t" + bean.getLastName());
				System.out.print("\t" + bean.getLogin());
				System.out.print("\t" + bean.getPassword());
				System.out.print("\t" + bean.getDob());
				System.out.print("\t" + bean.getRoleId());
				System.out.print("\t" + bean.getUnSuccessfulLogin());
				System.out.print("\t" + bean.getGender());
				System.out.print("\t" + bean.getLastLogin());
				System.out.print("\t" + bean.getLock());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/******************************** TEST GET ROLES ********************************************/

	private static void testGetRoles() {
		try {
			UserBean bean = new UserBean();
			List list = new ArrayList();
			bean.setRoleId(1L);
			list = model.getRoles(bean);
			if (list.size() < 0) {
				System.out.println("Test Get Roles fail");
			}
			System.out
					.println("ID\t FIRST NAME\tLAST NAME\t LOGIN\t\t PASSWORD"
							+ " \tDOB \tROLE ID \tUNSUCESSFULL LOGIN\t GENDER\t LAST LOGIN\t LOCK"
							+ "\tMOBILE NO \tCREATED BY\t MODIFIED BY \tCREATED DATE TIME \tMODIFIED DATE TIME");
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (UserBean) it.next();
				System.out.print("\n" + bean.getId());
				System.out.print("\t" + bean.getFirstName());
				System.out.print("\t" + bean.getLastName());
				System.out.print("\t" + bean.getLogin());
				System.out.print("\t" + bean.getPassword());
				System.out.print("\t" + bean.getDob());
				System.out.print("\t" + bean.getRoleId());
				System.out.print("\t" + bean.getUnSuccessfulLogin());
				System.out.print("\t" + bean.getGender());
				System.out.print("\t" + bean.getLastLogin());
				System.out.print("\t" + bean.getLock());
				System.out.print("\t" + bean.getMobileNo());
				System.out.print("\t" + bean.getCreatedBy());
				System.out.print("\t" + bean.getModifiedBy());
				System.out.print("\t" + bean.getCreatedDatetime());
				System.out.print("\t" + bean.getModifiedDatetime());

			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

}
