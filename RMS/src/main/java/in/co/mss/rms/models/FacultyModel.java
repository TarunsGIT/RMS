package in.co.mss.rms.models;

import in.co.mss.rms.bean.CollegeBean;
import in.co.mss.rms.bean.CourseBean;
import in.co.mss.rms.bean.FacultyBean;
import in.co.mss.rms.exception.ApplicationException;
import in.co.mss.rms.exception.DatabaseException;
import in.co.mss.rms.exception.DuplicateRecordException;
import in.co.mss.rms.util.DataUtility;
import in.co.mss.rms.util.JDBCDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


/**
 * JDBC Implementation of Faculty Model
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */

public class FacultyModel {

	private static Logger log = Logger.getLogger(FacultyModel.class);

	/************************************* NEXT PRIMARY KEY OF STUDENT *************************/

	/**
	 * 
	 * Find next PK of Faculty
	 * 
	 * @throws DatabaseException
	 */
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("SELECT MAX(ID) FROM FACULTY");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}

	/******************************** FIND FACULTY BY PK *****************************************/

	/**
	 * Find Faculty by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public FacultyBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM FACULTY WHERE ID=?");
		FacultyBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FacultyBean();
				bean.setId(rs.getLong(1));
				bean.setCollegeId(rs.getLong(2));
				bean.setCollegeName(rs.getString(3));
				bean.setCourseId(rs.getLong(4));
				bean.setCourseName(rs.getString(5));
				bean.setPrimarySubject(rs.getString(6));
				bean.setSecondarySubject(rs.getString(7));
				bean.setFirstName(rs.getString(8));
				bean.setLastName(rs.getString(9));
				bean.setQualification(rs.getString(10));
				bean.setDob(rs.getDate(11));
				bean.setMobileNo(rs.getString(12));
				bean.setEmail(rs.getString(13));
				bean.setAddress(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting Faculty by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}

	/*********************************** FIND BY EMAIL ***************************************/

	/**
	 * Find User by Faculty
	 * 
	 * @param Email
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */
	public FacultyBean findByEmailId(String Email) throws ApplicationException {
		log.debug("Model findBy Email Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM FACULTY WHERE EMAIL=?");
		FacultyBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, Email);
			ResultSet rs = pstmt.executeQuery();// FOR SELECT STATMENT WE USE
												// executeQuery();
			while (rs.next()) {
				bean = new FacultyBean();
				bean.setId(rs.getLong(1));
				bean.setCollegeId(rs.getLong(2));
				bean.setCollegeName(rs.getString(3));
				bean.setCourseId(rs.getLong(4));
				bean.setCourseName(rs.getString(5));
				bean.setPrimarySubject(rs.getString(6));
				bean.setSecondarySubject(rs.getString(7));
				bean.setFirstName(rs.getString(8));
				bean.setLastName(rs.getString(9));
				bean.setQualification(rs.getString(10));
				bean.setDob(rs.getDate(11));
				bean.setMobileNo(rs.getString(12));
				bean.setEmail(rs.getString(13));
				bean.setAddress(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting Faculty by Email");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy Email End");
		return bean;
	}

	/**************************** FIND BY PRIMARY SUBJECT ****************************************/

	/**
	 * Find User by Faculty
	 * 
	 * @param Email
	 *            : get parameter
	 * @return bean
	 * 
	 */
	public FacultyBean findByPrimarySubject(String PrimarySubject)
			throws ApplicationException {
		log.debug("Model findBy Email Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM FACULTY WHERE PRIMARY_SUBJECT=?");
		FacultyBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, PrimarySubject);
			ResultSet rs = pstmt.executeQuery();// FOR SELECT STATMENT WE USE
												// executeQuery();
			while (rs.next()) {
				bean = new FacultyBean();
				bean.setId(rs.getLong(1));
				bean.setCollegeId(rs.getLong(2));
				bean.setCollegeName(rs.getString(3));
				bean.setCourseId(rs.getLong(4));
				bean.setCourseName(rs.getString(5));
				bean.setPrimarySubject(rs.getString(6));
				bean.setSecondarySubject(rs.getString(7));
				bean.setFirstName(rs.getString(8));
				bean.setLastName(rs.getString(9));
				bean.setQualification(rs.getString(10));
				bean.setDob(rs.getDate(11));
				bean.setMobileNo(rs.getString(12));
				bean.setEmail(rs.getString(13));
				bean.setAddress(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting Faculty by Primary Subject");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy Primary Subject End");
		return bean;
	}

	/********************************** FIND BY SECONDARY SUBJECT *****************************/

	/**************************** FIND BY PRIMARY SUBJECT ****************************************/

	/**
	 * Find User by Faculty
	 * 
	 * @param Email
	 *            : get parameter
	 * @return bean
	 * 
	 */
	public FacultyBean findBySecondarySubject(String SecondarySubject)
			throws ApplicationException {
		log.debug("Model findBy Email Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM FACULTY WHERE SECONDARY_SUBJECT=?");
		FacultyBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, SecondarySubject);
			ResultSet rs = pstmt.executeQuery();// FOR SELECT STATMENT WE USE
												// executeQuery();
			while (rs.next()) {
				bean = new FacultyBean();
				bean.setId(rs.getLong(1));
				bean.setCollegeId(rs.getLong(2));
				bean.setCollegeName(rs.getString(3));
				bean.setCourseId(rs.getLong(4));
				bean.setCourseName(rs.getString(5));
				bean.setPrimarySubject(rs.getString(6));
				bean.setSecondarySubject(rs.getString(7));
				bean.setFirstName(rs.getString(8));
				bean.setLastName(rs.getString(9));
				bean.setQualification(rs.getString(10));
				bean.setDob(rs.getDate(11));
				bean.setMobileNo(rs.getString(12));
				bean.setEmail(rs.getString(13));
				bean.setAddress(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting Faculty by Primary Subject");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy Secondary Subject End");
		return bean;
	}

	/*********************************
	 * ADD FACULTY
	 * 
	 * @throws DatabaseException
	 * 
	 * 
	 * 
	 * 
	 *********************************************/

	public long add(FacultyBean bean) throws ApplicationException,
			DuplicateRecordException, DatabaseException {
		log.debug("Model add Started");
		Connection conn = null;

		// get College Name According To College ID
		CollegeModel cModel = new CollegeModel();
		CollegeBean collegeBean = cModel.findByPK(bean.getCollegeId());
		bean.setCollegeName(collegeBean.getName());

		// get Course Name According to Course ID
		CourseModel model = new CourseModel();
		CourseBean coursebean = model.findByPK(bean.getCourseId());
		bean.setCourseName(coursebean.getName());

		FacultyBean duplicateName = findByEmailId(bean.getEmail());
		/*
		 * FacultyBean dupicatePrimary = findByPrimarySubject(bean
		 * .getPrimarySubject()); FacultyBean dupicateSecondary =
		 * findBySecondarySubject(bean .getSecondarySubject());
		 */

		int pk = 0;
		if (duplicateName != null) {
			throw new DuplicateRecordException("Email already exists");
		}
		if (bean.getPrimarySubject().equals(bean.getSecondarySubject())) {
			throw new DatabaseException(
					"Primary and Secondary subject cannot be same");

		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			// Get auto-generated next primary key
			System.out.println(" RECORD SUCESSFULLY ADDED WITH PRIMARY KEY:"
					+ pk + " in ModelJDBC");

			conn.setAutoCommit(false); // Begin transaction
			System.out.println("BESIDE SET AUTO COMMIT");
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO FACULTY VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getCollegeId());
			pstmt.setString(3, bean.getCollegeName());
			pstmt.setLong(4, bean.getCourseId());
			pstmt.setString(5, bean.getCourseName());
			pstmt.setString(6, bean.getPrimarySubject());
			pstmt.setString(7, bean.getSecondarySubject());
			pstmt.setString(8, DataUtility.getCapital(bean.getFirstName()));
			pstmt.setString(9, DataUtility.getCapital(bean.getLastName()));
			pstmt.setString(10, bean.getQualification());
			pstmt.setDate(11, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(12, bean.getMobileNo());
			pstmt.setString(13, bean.getEmail());
			pstmt.setString(14, DataUtility.getCapital(bean.getAddress()));
			pstmt.setString(15, bean.getCreatedBy());
			pstmt.setString(16, bean.getModifiedBy());
			pstmt.setTimestamp(17, bean.getCreatedDatetime());
			pstmt.setTimestamp(18, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException(
					"Exception : Exception in Add Faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	/******************************* DELETE FACULTY ***************************************/

	public void delete(FacultyBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("DELETE FROM FACULTY WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception : Delete rollback exception "
								+ ex.getMessage());
			}
			throw new ApplicationException(
					"Exception : Exception in Delete Faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}

	/*****************************
	 * UPDATE FACULTY
	 * 
	 * @throws DatabaseException
	 **********************************************/

	public void update(FacultyBean bean) throws ApplicationException,
			DuplicateRecordException, DatabaseException {
		log.debug("Model update Started");
		Connection conn = null;

		// get College Name
		CollegeModel cModel = new CollegeModel();
		CollegeBean collegeBean = cModel.findByPK(bean.getCollegeId());
		bean.setCollegeName(collegeBean.getName());

		// get Course Name According to Course ID
		CourseModel model = new CourseModel();
		CourseBean coursebean = model.findByPK(bean.getCourseId());
		bean.setCourseName(coursebean.getName());

		FacultyBean beanExist = findByEmailId(bean.getEmail());
		FacultyBean duplicateName = findByEmailId(bean.getEmail());

		if (beanExist != null && beanExist.getId() != bean.getId()) {
			throw new DuplicateRecordException("Email Id Already Exists");
		}

		if (bean.getPrimarySubject().equals(bean.getSecondarySubject())) {
			throw new DatabaseException(
					"Primary and Secondary subject cannot be same");

		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE FACULTY SET COLLEGE_ID=?,COLLEGE_NAME=?,COURSE_ID=?,COURSE_NAME=?,PRIMARY_SUBJECT=?,SECONDARY_SUBJECT=?,FIRST_NAME=?,LAST_NAME=?,QUALIFICATION=?,DATE_OF_BIRTH=?,MOBILE_NO=?,EMAIL=?,ADDRESS=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setLong(1, bean.getCollegeId());
			pstmt.setString(2, bean.getCollegeName());
			pstmt.setLong(3, bean.getCourseId());
			pstmt.setString(4, bean.getCourseName());
			pstmt.setString(5, bean.getPrimarySubject());
			pstmt.setString(6, bean.getSecondarySubject());
			pstmt.setString(7, DataUtility.getCapital(bean.getFirstName()));
			pstmt.setString(8, DataUtility.getCapital(bean.getLastName()));
			pstmt.setString(9, bean.getQualification());
			pstmt.setDate(10, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(11, bean.getMobileNo());
			pstmt.setString(12, bean.getEmail());
			pstmt.setString(13, DataUtility.getCapital(bean.getAddress()));
			pstmt.setString(14, bean.getCreatedBy());
			pstmt.setString(15, bean.getModifiedBy());
			pstmt.setTimestamp(16, bean.getCreatedDatetime());
			pstmt.setTimestamp(17, bean.getModifiedDatetime());
			pstmt.setLong(18, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception : Delete rollback exception "
								+ ex.getMessage());
			}
			throw new ApplicationException("Exception in Updating Faculty ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	public List search(FacultyBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/******************************** SEARCH FACULTY ******************************************/

	/**
	 * Search Faculty with pagination
	 * 
	 * @return list : List of Faculty
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws DatabaseException
	 */
	public List search(FacultyBean bean, int pageNo, int pageSize)
			throws ApplicationException {
		log.debug("Model search Started");

		StringBuffer sql = new StringBuffer("SELECT * FROM FACULTY WHERE 1=1");
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getCourseName() != null
					&& bean.getCourseName().length() > 0) {
				sql.append(" AND COURSE_NAME = " + bean.getCourseName());
			}
			if (bean.getCourseId() > 0) {
				sql.append(" AND COURSE_ID = " + bean.getCourseId());
			}
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" AND FIRST_NAME LIKE '" + bean.getFirstName()
						+ "%'");
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" AND LAST_NAME LIKE '" + bean.getLastName() + "%'");
			}
			if (bean.getQualification() != null
					&& bean.getQualification().length() > 0) {
				sql.append(" AND QUALIFICATION LIKE '"
						+ bean.getQualification() + "%'");
			}
			if (bean.getDob() != null && bean.getDob().getDate() > 0) {
				sql.append(" AND DOB = " + bean.getDob());
			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
				sql.append(" AND MOBILE_NO LIKE '" + bean.getMobileNo() + "%'");
			}
			if (bean.getEmail() != null && bean.getEmail().length() > 0) {
				sql.append(" AND EMAIL LIKE '" + bean.getEmail() + "%'");
			}
			if (bean.getCollegeName() != null
					&& bean.getCollegeName().length() > 0) {
				sql.append(" AND COLLEGE_NAME = " + bean.getCollegeName());
			}
			sql.append(" ORDER BY id DESC ");
		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}
		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FacultyBean();
				bean.setId(rs.getLong(1));
				bean.setCollegeId(rs.getLong(2));
				bean.setCollegeName(rs.getString(3));
				bean.setCourseId(rs.getLong(4));
				bean.setCourseName(rs.getString(5));
				bean.setPrimarySubject(rs.getString(6));
				bean.setSecondarySubject(rs.getString(7));
				bean.setFirstName(rs.getString(8));
				bean.setLastName(rs.getString(9));
				bean.setQualification(rs.getString(10));
				bean.setDob(rs.getDate(11));
				bean.setMobileNo(rs.getString(12));
				bean.setEmail(rs.getString(13));
				bean.setAddress(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in search Faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model search End");
		return list;
	}

	public List list() throws ApplicationException { // LIST OF STUDENT
		return list(0, 0);
	}

	/************************************** LIST FACULTY **************************************/

	/**
	 * Get List of Faculty with pagination
	 * 
	 * @return list : List of Student
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from FACULTY");

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				FacultyBean bean = new FacultyBean();
				bean.setId(rs.getLong(1));
				bean.setCollegeId(rs.getLong(2));
				bean.setCollegeName(rs.getString(3));
				bean.setCourseId(rs.getLong(4));
				bean.setCourseName(rs.getString(5));
				bean.setPrimarySubject(rs.getString(6));
				bean.setSecondarySubject(rs.getString(7));
				bean.setFirstName(rs.getString(8));
				bean.setLastName(rs.getString(9));
				bean.setQualification(rs.getString(10));
				bean.setDob(rs.getDate(11));
				bean.setMobileNo(rs.getString(12));
				bean.setEmail(rs.getString(13));
				bean.setAddress(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting list of Faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model list End");
		return list;

	}

}
