package in.co.mss.rms.util;


	import java.util.Collections;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Set;

	import in.co.mss.rms.bean.DropdownListBean;
	/**
	 * HTML Utility class to produce HTML contents like Dropdown List.
	 * 
	 * @author Session Facade
	 * @version 1.0
	 * 
	 * 
	 */


	public class HTMLUtility {
 		   
	    public static String getList(String name, String selectedVal,
	       
	    		HashMap<String, String> map) {
	    	 
	     

	    StringBuffer sb = new StringBuffer("<select style= 'width:172px' class='form-control' name='" + name + "'>"); 
	    sb.append("<option value= >" +" " +"Select"+  "</option>");  //*****SELECT ****//FOR HASH MAP  
	    Set<String> keys = map.keySet();
	        String val = null;

	        for (String key : keys) {
	            val = map.get(key);
	            if (key.trim().equals(selectedVal)) {
	                sb.append("<option selected value='" + key + "'>" + val
	                        + "</option>");
	                
	            } else {
	                sb.append("<option value='" + key + "'>" + val + "</option>");
	            }
	        }
	        sb.append("</select>");
	        return sb.toString();
	    }
	    /**
	     * Create HTML SELECT List from List parameter
	     * 
	     * @param name
	     * @param selectedVal
	     * @param list
	     * @return String
	     */
	    public static String getList(String name, String selectedVal, List list) {

	        Collections.sort(list);

	        List<DropdownListBean> dd = (List<DropdownListBean>) list;

	        StringBuffer sb = new StringBuffer("<select style='width:172px' class='form-control' name='" + name + "'>");
            sb.append("<option value= >" +" " +"Select"+  "</option>");//****SELECT
	        String key = null;
	        String val = null;

	        for (DropdownListBean obj : dd) {//FOR EACH LOOP
	            key = obj.getKey();
	            val = obj.getValue();

	            if (key.trim().equals(selectedVal)) {
	                sb.append("<option selected value='" + key + "'>" + val
	                        + "</option>");
	            } else {
	                sb.append("<option value='" + key + "'>" + val + "</option>");
	            }
	        }
	        sb.append("</select>");
	        return sb.toString();
	    }




	}



