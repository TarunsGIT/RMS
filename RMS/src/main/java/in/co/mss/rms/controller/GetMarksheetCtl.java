package in.co.mss.rms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.mss.rms.bean.BaseBean;
import in.co.mss.rms.bean.MarksheetBean;
import in.co.mss.rms.exception.ApplicationException;
import in.co.mss.rms.models.MarksheetModel;
import in.co.mss.rms.util.DataUtility;
import in.co.mss.rms.util.DataValidator;
import in.co.mss.rms.util.PropertyReader;
import in.co.mss.rms.util.ServletUtility;

/** 
 * Get Marksheet functionality Controller. Performs operation for Get Marksheet 
 *  
 * @author Session Facade
 * @version 1.0 
 * 
 */ 
@WebServlet(name="GetMarksheetCtl",urlPatterns={"/ctl/GetMarksheetCtl.do"})
public class GetMarksheetCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
private static Logger log = Logger.getLogger(GetMarksheetCtl.class); 
    
    @Override 
    protected boolean validate(HttpServletRequest request) { 
        log.debug("GetMarksheetCTL Method validate Started"); 
        boolean pass = true; 
        if (DataValidator.isNull(request.getParameter("rollNo"))) { 
            request.setAttribute("rollNo",PropertyReader.getValue("error.require", "Roll Number")); 
            pass = false; 
        } 
        log.debug("GetMarksheetCTL Method validate Ended"); 
        return pass; 
    } 
 
    @Override 
    protected BaseBean populateBean(HttpServletRequest request) { 
        log.debug("GetMarksheetCtl Method populatebean Started"); 
        MarksheetBean bean = new MarksheetBean(); 
        bean.setId(DataUtility.getLong(request.getParameter("id"))); 
        bean.setRollNo(DataUtility.getString(request.getParameter("rollNo"))); 
        bean.setName(DataUtility.getString(request.getParameter("name"))); 
        bean.setPhysics(DataUtility.getInt(request.getParameter("physics"))); 
        bean.setChemistry(DataUtility.getInt(request.getParameter("chemistry"))); 
        bean.setMaths(DataUtility.getInt(request.getParameter("maths"))); 
        log.debug("GetMarksheetCtl Method populatebean Ended"); 
        return bean; 
    } 
    /**
     * Concept of Display method
     * 
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        ServletUtility.forward(getView(), request, response);
    }

    /**
     * Concept of Submit Method
     * 
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        log.debug("GetMarksheetCtl Method doGet Started");
        String op = DataUtility.getString(request.getParameter("operation"));

        // get model
        MarksheetModel model = new MarksheetModel();

        MarksheetBean bean = (MarksheetBean) populateBean(request);

        /*long id = DataUtility.getLong(request.getParameter("id"));*/

        if (OP_GO.equalsIgnoreCase(op)) {

            try {
                bean = model.findByRollNo(bean.getRollNo());
                if (bean != null) {
                    ServletUtility.setBean(bean, request);
                } else {
                    ServletUtility.setErrorMessage("RollNo Does Not exists",
                            request);
                }
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }

        }
        ServletUtility.forward(getView(), request, response);
        log.debug("MarksheetCtl Method doGet Ended");
    }
    @Override 
    protected String getView() { 
        return ORSView.GET_MARKSHEET_VIEW; 
    } 

 

}