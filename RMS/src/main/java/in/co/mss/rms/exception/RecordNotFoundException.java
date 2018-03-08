package in.co.mss.rms.exception;
/**
 * RecordNotFoundException thrown when a record not found occurred
 * 
 * @author Session Facade
 * @version 1.0
 * 
 * 
 */


public class RecordNotFoundException extends Exception {
	/**
     * @param msg
     *            error message
     */
    public RecordNotFoundException(String msg) {
        super(msg);

    }


}
