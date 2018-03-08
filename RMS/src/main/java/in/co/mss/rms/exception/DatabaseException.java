package in.co.mss.rms.exception;
/**
 * DatabaseException is propogated by DAO classes when an unhandled Database
 * exception occurred
 * 
 * @author Session Facade
 * @version 1.0
 * 
 * 
 */

 


public class DatabaseException extends Exception {
	/**
     * @param msg
     *            : Error message
	 * @return 
     */
    public DatabaseException(String msg) {
        super(msg);
    }
}
