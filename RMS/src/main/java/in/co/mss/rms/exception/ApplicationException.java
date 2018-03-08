package in.co.mss.rms.exception;
/**
 * ApplicationException is propogated from Service classes when an business
 * logic exception occurered.
 * 
 * @author Session Facade
 * @version 1.0
 * 
 * 
 */

public class ApplicationException extends Exception {
	/**
     * @param msg
     *            : Error message
     */
    public ApplicationException(String msg) {
        super(msg);
    }

}
