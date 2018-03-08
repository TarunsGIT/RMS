package in.co.mss.rms.util;
import java.util.HashMap;


/**
 * Class that build Application Email messages
 * 
 * @author Session Facade
 * @version 1.0
 *
 * 
 */


public class EmailBuilder {

/******************************** USER REGISTRATION MESSAGE ********************************/
	
	//RETURN USER REGISTRATION MESSAGE
    public static String getUserRegistrationMessage(HashMap<String, String> map) {
        StringBuilder msg = new StringBuilder();

        msg.append("<HTML><BODY>");
        msg.append("Registration is successful for RMS");
        msg.append("<H1>Hi! Greetings from MSS!</H1>");
        msg.append("<P>Congratulations for registering on RMS! You can now access your RMS account online - anywhere, anytime and  enjoy the flexibility to check the Marksheet Details.</P>");
       
        msg.append("<P><B>Login Id : " + map.get("login") + "<BR>"
                + " Password : " + map.get("password") + "</B></p>");

        msg.append("<P> As a security measure, we recommended that you change your password after you first log in.</p>");
        msg.append("<p>For any assistance, please feel free to call us at +91 8080808080 helpline numbers.</p>");
        msg.append("<p>You may also write to us at hrd@mss.com</p>");
        msg.append("<p>We assure you the best service at all times and look forward to a warm and long-standing association with you.</p>");
        
        msg.append("</BODY></HTML>");

        return msg.toString();//convert to String
    }

/*********************** FORGOT PASSWORD MESSAGE ********************************************/

    //return messages on email of forget passsword
    public static String getForgetPasswordMessage(HashMap<String, String> map) {
        StringBuilder msg = new StringBuilder();

        msg.append("<HTML><BODY>");
        msg.append("<H1>Your password is recovered !! " + map.get("firstName")
                + " " + map.get("lastName") + "</H1>");
        msg.append("<P><B>To access account user Login Id : "
                + map.get("login") + "<BR>" + " Password : "
                + map.get("password") + "</B></p>");
        msg.append("</BODY></HTML>");

        return msg.toString();
    }


 /******************************** CHANGE PASSWORD MESSAGE **********************************/
    
    //returns email messages of change password
    public static String getChangePasswordMessage(HashMap<String, String> map) {
        StringBuilder msg = new StringBuilder();

        msg.append("<HTML><BODY>");
        msg.append("<H1>Your Password has been changed Successfully !! "
                + map.get("firstName") + " " + map.get("lastName") + "</H1>");
        
        msg.append("<P><B>To access account user Login Id : "
                + map.get("login") + "<BR>" + " Password : "
                + map.get("password") + "</B></p>");
        msg.append("</BODY></HTML>");

        return msg.toString();
    }


}
