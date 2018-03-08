package in.co.mss.rms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
 

import org.apache.log4j.Logger;

import in.co.mss.rms.bean.BaseBean;
import in.co.mss.rms.bean.CollegeBean;
import in.co.mss.rms.exception.ApplicationException;
import in.co.mss.rms.exception.DuplicateRecordException;
import in.co.mss.rms.models.CollegeModel;
import in.co.mss.rms.util.DataUtility;
import in.co.mss.rms.util.DataValidator;
import in.co.mss.rms.util.PropertyReader;
import in.co.mss.rms.util.ServletUtility; 

/** 
 * College functionality Controller. Performs operation for add, update, delete 
 * and get College 
 *  
 * @author Session Facade
 * @version 1.0 
 * 
 */ 


@WebServlet(name="CollegeCtl",urlPatterns={"/ctl/CollegeCtl"})
 public class CollegeCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

    
    private static Logger log = Logger.getLogger(CollegeCtl.class); 
 
    @Override 
    protected boolean validate(HttpServletRequest request) { 
        log.debug("CollegeCtl Method validate Started"); 
        boolean pass = true; 
         if(DataValidator.isNull(request.getParameter("name"))){ 
     
            request.setAttribute("name", 
                    PropertyReader.getValue("error.require", "Name")); 
            pass = false;
        }
         else if (DataValidator.isNotAlpha(request.getParameter("name")) ){ 
             request.setAttribute("name", 
                     PropertyReader.getValue("error.alpha", "Name")); 
             pass = false; 
         } 
         
        if (DataValidator.isNull(request.getParameter("address"))) { 
            request.setAttribute("address", 
                    PropertyReader.getValue("error.require", "Address")); 
            pass = false; 
        } 
        if(DataValidator.isNull(request.getParameter("state"))){ 
            
            request.setAttribute("state", 
                    PropertyReader.getValue("error.require", "State")); 
            pass = false;
        }
        /*else if(DataValidator.isNotAlpha(request.getParameter("state"))){
        	request.setAttribute("state", 
                    PropertyReader.getValue("error.alpha", "State")); 
            pass = false;
        	
        }*/
         /*else if (DataValidator.isInvalidState(request.getParameter("state")) ){ 
             request.setAttribute("state", 
                     PropertyReader.getValue("error.alpha", "State")); 
             pass = false; 
         } */
         
        if(DataValidator.isNull(request.getParameter("city"))){ 
            
            request.setAttribute("city", 
                    PropertyReader.getValue("error.require", "City")); 
            pass = false;
        }
         else if (DataValidator.isNotAlpha(request.getParameter("city")) ){ 
             request.setAttribute("city", 
                     PropertyReader.getValue("error.alpha", "City")); 
             pass = false; 
         } 
         
     
        if (DataValidator.isNull(request.getParameter("phoneNo"))) { 
            request.setAttribute("phoneNo", 
                    PropertyReader.getValue("error.require", "Phone No.")); 
            pass = false; 
        }/*else if(DataValidator.isNotMobileNo(request.getParameter("phoneNo"))){
        	request.setAttribute("phoneNo", PropertyReader.getValue("error.nmob", "Phone No."));
        }*/
        else if(DataValidator.isAlpha(request.getParameter("phoneNo")))
        {
            request.setAttribute("phoneNo", 
                    PropertyReader.getValue("error.phone", "Phone No.")); 
            pass = false; 

        }else if(DataValidator.isNotMobileNo("phoneNo")){
        	request.setAttribute("phoneNo", 
                    PropertyReader.getValue("error.nmob", "Phone No.")); 
            pass = false; 
        }
        else if((request.getParameter("phoneNo")).length()!=10)
        {
            request.setAttribute("phoneNo", 
                    PropertyReader.getValue("error.length", "Phone No.")); 
            pass = false; 

        }
        log.debug("CollegeCtl Method validate Ended"); 
        return pass; 
    } 
 
    @Override 
    protected BaseBean populateBean(HttpServletRequest request) { 
        log.debug("CollegeCtl Method populatebean Started"); 
        CollegeBean bean = new CollegeBean(); 
        bean.setId(DataUtility.getLong(request.getParameter("id"))); 
        bean.setName(DataUtility.getString(request.getParameter("name"))); 
        bean.setAddress(DataUtility.getString(request.getParameter("address"))); 
        bean.setState(DataUtility.getString(request.getParameter("state"))); 
        bean.setCity(DataUtility.getString(request.getParameter("city"))); 
        bean.setPhoneNo(DataUtility.getString(request.getParameter("phoneNo"))); 
        populateDTO(bean, request);
        log.debug("CollegeCtl Method populatebean Ended"); 
        
        return bean; 
    } 
 
    /**
     * Contains display logic
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String op = DataUtility.getString(request.getParameter("operation"));

        // get model
        CollegeModel model = new CollegeModel();

        long id = DataUtility.getLong(request.getParameter("id"));

        if (id > 0) {
            CollegeBean bean;
            try {
                bean = model.findByPK(id);
                ServletUtility.setBean(bean, request);
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }
        }

        ServletUtility.forward(getView(), request, response);
    }

    /**
     * Contains Submit logics
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        log.debug("CollegeCtl Method doPost Started");

        String op = DataUtility.getString(request.getParameter("operation"));

        // get model
        CollegeModel model = new CollegeModel();

        long id = DataUtility.getLong(request.getParameter("id"));

        if (OP_SAVE.equalsIgnoreCase(op)) {

            CollegeBean bean = (CollegeBean) populateBean(request);

            try {
                if (id > 0) {
                    model.update(bean);
                    ServletUtility.setBean(bean, request);
                    ServletUtility.setSuccessMessage("College Edited Sucessfully",
							request);
                } else {
                    long pk = model.add(bean);
                    bean.setId(pk);
                    ServletUtility.setBean(bean, request);
                    ServletUtility.setSuccessMessage("College Added Sucessfully",
							request);
                }
                
                /*ServletUtility.redirect(ORSView.COLLEGE_LIST_CTL, request, 
                        response);
                return;*/
            } catch (ApplicationException e) {
                e.printStackTrace();
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("College Name already exists",
                        request);
            }

        } else if (OP_DELETE.equalsIgnoreCase(op)) {

            CollegeBean bean = (CollegeBean) populateBean(request);
            try {
                model.delete(bean);
                ServletUtility.redirect(ORSView.COLLEGE_LIST_CTL, request,
                        response);
                return;

            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
        	if(id>0){
        		ServletUtility.redirect(ORSView.COLLEGE_LIST_CTL, request,
						response);
				return;
				
        		
        	}

        	else if (DataValidator.isNull(request.getParameter("name"))
					&& DataValidator.isNull(request.getParameter("address"))
					&& DataValidator.isNull(request.getParameter("city"))
					&& DataValidator.isNull(request.getParameter("state"))
					&& DataValidator.isNull(request.getParameter("phoneNo"))) {
				ServletUtility.redirect(ORSView.COLLEGE_LIST_CTL, request, response);
				return;
			}/*
			 * else { ServletUtility.forward(ORSView.COLLEGE_VIEW, request,
			 * response); return; }
			 */
 
        }

        ServletUtility.forward(getView(), request, response);

        log.debug("CollegeCtl Method doGet Ended");
    }
 
    @Override 
    protected String getView() { 
        return ORSView.COLLEGE_VIEW; 
    } 

 

}
