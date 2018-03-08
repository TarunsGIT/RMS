package in.co.mss.rms.models;
import in.co.mss.rms.bean.RoleBean;
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
 * JDBC Implementation of Role Model
 * 
 * @author Session Facade
 * @version 1.0
 
 */


public class RoleModel {
	
	private static Logger log = Logger.getLogger(RoleModel.class);//LOGGERS OBJECT
	
 
/***************************** NEXT PRIMARY KEY OF ROLE **********************************************************/
	
	/** 
     * Find next PK of Role 
     *  
     * @throws DatabaseException 
     */ 

    public Integer nextPK() throws DatabaseException {  //FIND NEXT PK OF ROLE
        log.debug("Model nextPK Started"); 
        Connection conn = null; 
        int pk = 0; 
        try { 
            conn = JDBCDataSource.getConnection(); 
            PreparedStatement pstmt = conn 
                    .prepareStatement("SELECT MAX(ID) FROM ST_ROLE"); 
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
    
  
 /*********************************** ADD ROLE *******************************************************/
    
    public long add(RoleBean bean) throws ApplicationException, DuplicateRecordException{ //ADD A ROLE
        log.debug("Model add Started"); 
        Connection conn = null; 
        int pk = 0; 
        RoleBean duplicataRole = findByName(bean.getName()); 
 
        // Check if create Role already exist 
        if (duplicataRole != null) { 
            throw new DuplicateRecordException("Role already exists"); 
        } 
            try { 
            conn = JDBCDataSource.getConnection(); 
            pk = nextPK(); 
         
            // Get auto-generated next primary key 
            System.out.println(pk + " in ModelJDBC"); 
            conn.setAutoCommit(false); // Begin transaction 
            PreparedStatement pstmt = conn 
                    .prepareStatement("INSERT INTO ST_ROLE VALUES(?,?,?,?,?,?,?)"); 
            pstmt.setInt(1, pk); 
            pstmt.setString(2, DataUtility.getCapital(bean.getName())); 
            pstmt.setString(3, DataUtility.getCapital(bean.getDescription())); 
            pstmt.setString(4, bean.getCreatedBy()); 
            pstmt.setString(5, bean.getModifiedBy()); 
            pstmt.setTimestamp(6, bean.getCreatedDatetime()); 
            pstmt.setTimestamp(7, bean.getModifiedDatetime()); 
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
            throw new ApplicationException("Exception : Exception in add Role"); 
        } finally { 
            JDBCDataSource.closeConnection(conn); 
        } 
        log.debug("Model add End"); 
        return pk; 
    } 
 
  
 /********************************** DELETE ROLE ***********************************************************/
    
    public void delete(RoleBean bean) throws ApplicationException { //DELETE A ROLE
        log.debug("Model delete Started"); 
        Connection conn = null; 
        try { 
            conn = JDBCDataSource.getConnection(); 
            conn.setAutoCommit(false); // Begin transaction 
            PreparedStatement pstmt = conn 
                    .prepareStatement("DELETE FROM ST_ROLE WHERE ID=?"); 
            pstmt.setLong(1, bean.getId());
            System.out.println("ROLE DELETED AT " +bean.getId());
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

 
/***************************** FIND USER BY ROLE (FIND BY NAME)**************************************************/
    
    public RoleBean findByName(String name) throws ApplicationException { //FIND USER BY ROLE
        log.debug("Model findBy EmailId Started"); 
        StringBuffer sql = new StringBuffer( 
                "SELECT * FROM ST_ROLE WHERE NAME=?"); 
        RoleBean bean = null; 
        Connection conn = null; 
        try { 
            conn = JDBCDataSource.getConnection(); 
            PreparedStatement pstmt = conn.prepareStatement(sql.toString()); 
            pstmt.setString(1, name); 
            ResultSet rs = pstmt.executeQuery(); 
            while (rs.next()) { 
                bean = new RoleBean(); 
                bean.setId(rs.getLong(1)); 
                bean.setName(rs.getString(2)); 
                bean.setDescription(rs.getString(3)); 
                bean.setCreatedBy(rs.getString(4)); 
                bean.setModifiedBy(rs.getString(5)); 
                bean.setCreatedDatetime(rs.getTimestamp(6)); 
                bean.setModifiedDatetime(rs.getTimestamp(7)); 
            } 
            rs.close(); 
        } catch (Exception e) { 
            log.error("Database Exception..", e); 
            throw new ApplicationException( 
                    "Exception : Exception in getting User by emailId"); 
        } finally { 
            JDBCDataSource.closeConnection(conn); 
        } 
        log.debug("Model findBy EmailId End"); 
        return bean; 
    } 

 
/************************************ FIND ROLE BY PK (FIND BY PK) **********************************************/
    
    public RoleBean findByPK(long pk) throws ApplicationException { //FIND ROLE BY PK
        log.debug("Model findByPK Started"); 
        StringBuffer sql = new StringBuffer("SELECT * FROM ST_ROLE WHERE ID=?"); 
        RoleBean bean = null; 
        Connection conn = null; 
        try { 
            conn = JDBCDataSource.getConnection(); 
            PreparedStatement pstmt = conn.prepareStatement(sql.toString()); 
            pstmt.setLong(1, pk); 
            ResultSet rs = pstmt.executeQuery(); 
            while (rs.next()) { 
                bean = new RoleBean(); 
                bean.setId(rs.getLong(1)); 
                bean.setName(rs.getString(2)); 
                bean.setDescription(rs.getString(3)); 
                bean.setCreatedBy(rs.getString(4)); 
                bean.setModifiedBy(rs.getString(5)); 
                bean.setCreatedDatetime(rs.getTimestamp(6)); 
                bean.setModifiedDatetime(rs.getTimestamp(7)); 
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
  
 
/*********************************** UPDATE ROLE *****************************************************************/
    
    public void update(RoleBean bean) throws ApplicationException,  //UPDATE A ROLE
            DuplicateRecordException { 
        log.debug("Model update Started"); 
        Connection conn = null; 
        RoleBean duplicataRole = findByName(bean.getName()); 
 
        // Check if updated Role already exist 
        if (duplicataRole != null && duplicataRole.getId() != bean.getId()) { 
            throw new DuplicateRecordException("Role already exists"); 
        } 
        try { 
            conn = JDBCDataSource.getConnection(); 
            conn.setAutoCommit(false); // Begin transaction 
            PreparedStatement pstmt = conn 
                    .prepareStatement("UPDATE ST_ROLE SET NAME=?,DESCRIPTION=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?"); 
            pstmt.setString(1, DataUtility.getCapital(bean.getName())); 
            pstmt.setString(2, DataUtility.getCapital(bean.getDescription())); 
            pstmt.setString(3, bean.getCreatedBy()); 
            pstmt.setString(4, bean.getModifiedBy()); 
            pstmt.setTimestamp(5, bean.getCreatedDatetime()); 
            pstmt.setTimestamp(6, bean.getModifiedDatetime()); 
            pstmt.setLong(7, bean.getId()); 
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
 
    public List search(RoleBean bean) throws ApplicationException { //SEARCH A ROLE
        return search(bean, 0, 0); 
    } 


/*********************************** SEARCH ROLE *****************************************************************/
    
    /** 
     * Search Role with pagination 
     *  
     * @return list : List of Roles 
     * @param bean 
     *            : Search Parameters 
     * @param pageNo 
     *            : Current Page No. 
     * @param pageSize 
     *            : Size of Page 
     *  
     * @throws DatabaseException 
     */ 
    public List search(RoleBean bean, int pageNo, int pageSize) 
            throws ApplicationException { 
        log.debug("Model search Started"); 
        StringBuffer sql = new StringBuffer("SELECT * FROM ST_ROLE WHERE 1=1"); 
        if (bean != null) { 
            if (bean.getId() > 0) { 
                sql.append(" AND id = " + bean.getId()); 
            } 
            if (bean.getName() != null && bean.getName().length() > 0) { 
                sql.append(" AND NAME LIKE '" + bean.getName() + "%'"); 
            } 
            if (bean.getDescription() != null && bean.getDescription().length() > 0) { 
                sql.append(" AND DESCRIPTION LIKE '" + bean.getDescription() + "%'"); 
            }sql.append(" ORDER BY id DESC "); 
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
                bean = new RoleBean(); 
                bean.setId(rs.getLong(1)); 
                bean.setName(rs.getString(2)); 
                bean.setDescription(rs.getString(3)); 
                bean.setCreatedBy(rs.getString(4)); 
                bean.setModifiedBy(rs.getString(5)); 
                bean.setCreatedDatetime(rs.getTimestamp(6)); 
                bean.setModifiedDatetime(rs.getTimestamp(7)); 
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
    
    public List list() throws ApplicationException { //GET LIST OF ROLE
        return list(0, 0); 
    } 


/************************************* LIST OF ALL ROLE *******************************************************/
    
    /** 
     * Get List of Role with pagination 
     *  
     * @return list : List of Role 
     * @param pageNo 
     *            : Current Page No. 
     * @param pageSize 
     *            : Size of Page 
     * @throws DatabaseException 
     */ 
    public List list(int pageNo, int pageSize) throws ApplicationException { 
        log.debug("Model list Started"); 
        ArrayList list = new ArrayList(); 
        StringBuffer sql = new StringBuffer("select * from ST_ROLE"); 
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
                RoleBean bean = new RoleBean(); 
                bean.setId(rs.getLong(1)); 
                bean.setName(rs.getString(2)); 
                bean.setDescription(rs.getString(3)); 
                bean.setCreatedBy(rs.getString(4)); 
                bean.setModifiedBy(rs.getString(5)); 
                bean.setCreatedDatetime(rs.getTimestamp(6)); 
                bean.setModifiedDatetime(rs.getTimestamp(7)); 
                list.add(bean); 
            } 
            rs.close(); 
        } catch (Exception e) { 
            log.error("Database Exception..", e); 
            throw new ApplicationException( 
                    "Exception : Exception in getting list of Role"); 
        } finally { 
            JDBCDataSource.closeConnection(conn); 
        } 
        log.debug("Model list End"); 
        return list; 
 
    } 
}
