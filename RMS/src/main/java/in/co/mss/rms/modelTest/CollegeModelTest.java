package in.co.mss.rms.modelTest;

import in.co.mss.rms.bean.CollegeBean;
import in.co.mss.rms.exception.ApplicationException;
import in.co.mss.rms.models.CollegeModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollegeModelTest {
	private static CollegeModel model=new CollegeModel();
	public static void main(String[] args) {
		//testDelete();
		testSearch();
	}
	

/******************************* TEST DELETE **********************************************/
	
	private static void testDelete() {
		try{
			CollegeBean bean = new CollegeBean();
			long pk=12L;
			bean.setId(pk);
			model.delete(bean);
			System.out.println("TEST DELETE SUCESS");
			CollegeBean deletedbean=model.findByPK(pk);
			if(deletedbean!=null){
				System.out.println("DELETE FAILED");
			}
			
		}catch(ApplicationException e){
			e.printStackTrace();
		}
		
	}

/************************************ TEST SEARCH ****************************************/
	
	public static void testSearch() { 
        try { 
            CollegeBean bean = new CollegeBean(); 
            List list = new ArrayList(); 
            bean.setName("IIST"); 
            //bean.setAddress("borawan"); 
            list = model.search(bean, 1, 5); 
            if (list.size() < 0) { 
                System.out.println("Test Search fail"); 
            } 
            System.out.println("ID\tNAME\tADDRESS\tSTATE\t\tCITY\tPHONE_NO\tCREATED BY\tMODIFIED BY\tCREATED DATETIME\tMODIFIED DATETIME");
            Iterator it = list.iterator(); 
            while (it.hasNext()) { 
                bean = (CollegeBean) it.next(); 
                System.out.print("\n"+bean.getId()); 
                System.out.print("\t"+bean.getName()); 
                System.out.print("\t"+bean.getAddress()); 
                System.out.print("\t"+bean.getState()); 
                System.out.print("\t"+bean.getCity()); 
                System.out.print("\t"+bean.getPhoneNo()); 
                System.out.print("\t"+bean.getCreatedBy()); 
                System.out.print("\t"+bean.getModifiedBy());
                System.out.print("\t"+bean.getCreatedDatetime()); 
                System.out.print("\t"+bean.getModifiedDatetime()); 
            } 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
    } 


}
