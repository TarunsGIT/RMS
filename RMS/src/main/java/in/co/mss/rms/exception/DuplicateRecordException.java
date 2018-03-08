package in.co.mss.rms.exception;
/**
 * DuplicateRecordException thrown when a duplicate record occurred
 * 
 * @author Session Facade
 * @version 1.0
 * 
 * 
 */


public class DuplicateRecordException extends Exception {

    /**
     * @param msg
     *            error message
     */
    public DuplicateRecordException(String msg) {
        super(msg);
    }


}
