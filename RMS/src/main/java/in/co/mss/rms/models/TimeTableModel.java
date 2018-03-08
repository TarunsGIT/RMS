package in.co.mss.rms.models;

import in.co.mss.rms.bean.CourseBean;
import in.co.mss.rms.bean.TimeTableBean;
import in.co.mss.rms.exception.ApplicationException;
import in.co.mss.rms.exception.DatabaseException;
import in.co.mss.rms.exception.DuplicateRecordException;
import in.co.mss.rms.util.JDBCDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * JDBC Implementation of Time Table Model
 * 
 * @author Session Facade
 * @version 1.0
 * 
 */

public class TimeTableModel {

	private static Logger log = Logger.getLogger(TimeTableModel.class);

	/***************************** NEXT PRIMARY KEY OF Time Table **********************************************************/

	/**
	 * Find next PK of Time Table
	 * 
	 * @throws DatabaseException
	 */

	public Integer nextPK() throws DatabaseException { // FIND NEXT PK OF ROLE
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("SELECT MAX(ID) FROM TIMETABLE");
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

	/************************************ FIND TIME TABLE BY PK (FIND BY PK) **********************************************/

	public TimeTableBean findByPK(long pk) throws ApplicationException { // FIND
																			// ROLE
																			// BY
																			// PK
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM TIMETABLE WHERE ID=?");
		TimeTableBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourse(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setExaminationDate(rs.getDate(5));
				bean.setTime(rs.getString(6));
				bean.setDay(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}

	/***************************** FIND BY COURSE **************************************************/

	public TimeTableBean findByCourse(String course, String subject)
			throws ApplicationException { // FIND USER BY ROLE
		log.debug("Model findBy Course Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM TIMETABLE WHERE COURSE=? AND SUBJECT=?");
		TimeTableBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, course);
			pstmt.setString(2, subject);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourse(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setExaminationDate(rs.getDate(5));
				bean.setTime(rs.getString(6));
				bean.setDay(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting Time Table by Course");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy Course End");
		return bean;
	}

	/***************************** FIND BY SUBJECT **************************************************/

	public TimeTableBean findBySubject(String subject)
			throws ApplicationException { // FIND USER BY ROLE
		log.debug("Model findBy Subject Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM TIMETABLE WHERE SUBJECT=?");
		TimeTableBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, subject);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourse(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setExaminationDate(rs.getDate(5));
				bean.setTime(rs.getString(6));
				bean.setDay(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting Time Table by Subject");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy EmailId End");
		return bean;
	}

	/***************************** FIND BY EXAMINATION DATE **************************************************/

	public List findByExaminationDate(Date examinationDate, String course)
			throws ApplicationException { // FIND USER BY ROLE
		log.debug("Model findBy Course Started");
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM TIMETABLE WHERE EXAMINATIONDATE=? AND COURSE=?");
		ArrayList list = new ArrayList();
		TimeTableBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setDate(1, new java.sql.Date(examinationDate.getTime()));
			pstmt.setString(2, course);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourse(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setExaminationDate(rs.getDate(5));
				bean.setTime(rs.getString(6));
				bean.setDay(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting Time Table by Examination Date");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy Examination Date End");
		return list;
	}

	/***********************************
	 * ADD TIME TABLE
	 * 
	 * @throws DuplicateRecordException
	 * @throws DatabaseException
	 *******************************************************/

	public long add(TimeTableBean bean) throws ApplicationException,
			DuplicateRecordException, DatabaseException { // ADD A ROLE
		log.debug("Model add Started");
		Connection conn = null;
		// get Course Name According to Course ID
		CourseModel model = new CourseModel();
		CourseBean coursebean = model.findByPK(bean.getCourseId());
		bean.setCourse(coursebean.getName());

		TimeTableBean duplicateCourse = findByCourse(bean.getCourse(),
				bean.getSubject());
		List duplicateExaminationDate = findByExaminationDate(
				bean.getExaminationDate(), bean.getCourse());

		int pk = 0;
		if (duplicateCourse != null) {
			throw new DuplicateRecordException(
					"Exam is already been scheduled for this subject in this course");
		} else if (!duplicateExaminationDate.isEmpty()) {
			throw new DatabaseException(
					"This date is already been occupied for this course");

		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO TIMETABLE VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getCourseId());
			pstmt.setString(3, bean.getCourse());
			pstmt.setString(4, bean.getSubject());
			pstmt.setDate(5, new java.sql.Date(bean.getExaminationDate()
					.getTime()));
			pstmt.setString(6, bean.getTime());
			pstmt.setString(7, bean.getDay());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException(
						"Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException(
					"Exception : Exception in add Time table");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	/***********************************
	 * UPDATE TIME TABLE
	 * 
	 * @throws DatabaseException
	 *****************************************************************/

	public void update(TimeTableBean bean) throws ApplicationException, // UPDATE
																		// A
																		// Time
																		// Table
			DuplicateRecordException, DatabaseException {
		log.debug("Model update Started");
		Connection conn = null;

		// get Course Name According to Course ID
		CourseModel model = new CourseModel();
		CourseBean coursebean = model.findByPK(bean.getCourseId());
		bean.setCourse(coursebean.getName());

		TimeTableBean duplicateCourse = findByCourse(bean.getCourse(),
				bean.getSubject());
		List duplicateExaminationDate = findByExaminationDate(
				bean.getExaminationDate(), bean.getCourse());

		if (duplicateCourse != null && duplicateCourse.getId() != bean.getId()) {
			throw new DuplicateRecordException(
					"Exam is already been scheduled for this subject in this course");
		} else if (!duplicateExaminationDate.isEmpty()) {
			throw new DatabaseException(
					"This date is already been occupied for this course");

		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE TIMETABLE SET COURSE_ID=?,COURSE=?,SUBJECT=?,EXAMINATIONDATE=?,TIME=?,DAY=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setLong(1, bean.getCourseId());
			pstmt.setString(2, bean.getCourse());
			pstmt.setString(3, bean.getSubject());
			pstmt.setDate(4, new java.sql.Date(bean.getExaminationDate()
					.getTime()));
			pstmt.setString(5, bean.getTime());
			pstmt.setString(6, bean.getDay());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			pstmt.setLong(11, bean.getId());
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
			throw new ApplicationException("Exception in updating Role ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	public List search(TimeTableBean bean) throws ApplicationException {
		return search(bean, 0, 0);

	}

	/********************************** DELETE ROLE ***********************************************************/

	public void delete(TimeTableBean bean) throws ApplicationException { // DELETE
																			// A
																			// ROLE
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("DELETE FROM TIMETABLE WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			System.out.println("ROLE DELETED AT " + bean.getId());
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
					"Exception : Exception in delete Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}

	/************************************* LIST OF ALL TIME TABLE *******************************************************/

	/**
	 * Get List of TIME TABLE with pagination
	 * 
	 * @return list : List of timetable
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer(
				"select * from TIMETABLE order by ID desc ");
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
				TimeTableBean bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourse(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setExaminationDate(rs.getDate(5));
				bean.setTime(rs.getString(6));
				bean.setDay(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in getting list of Time Table");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model list End");
		return list;

	}

	/*********************************** SEARCH TIME TABLE *****************************************************************/

	/**
	 * Search TIME TABLE with pagination
	 * 
	 * @return list : List of Time Table
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws DatabaseException
	 */
	public List search(TimeTableBean bean, int pageNo, int pageSize)
			throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM TIMETABLE WHERE 1=1");
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getCourse() != null && bean.getCourse().length() > 0) {
				sql.append(" AND COURSE LIKE '" + bean.getCourse() + "%'");
			}
			if (bean.getSubject() != null && bean.getSubject().length() > 0) {
				sql.append(" AND SUBJECT LIKE '" + bean.getSubject() + "%'");
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
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourse(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setExaminationDate(rs.getDate(5));
				bean.setTime(rs.getString(6));
				bean.setDay(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException(
					"Exception : Exception in search Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model search End");
		return list;
	}

	/*************************** GET LIST OF ROLE ************************************************************/

	public List list() throws ApplicationException { // GET LIST OF ROLE
		return list(0, 0);
	}

}
