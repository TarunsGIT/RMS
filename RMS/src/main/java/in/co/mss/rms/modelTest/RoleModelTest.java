package in.co.mss.rms.modelTest;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.management.modelmbean.ModelMBean;

import in.co.mss.rms.bean.RoleBean;
import in.co.mss.rms.models.UserModel;
import in.co.mss.rms.models.RoleModel;
import in.co.mss.rms.exception.ApplicationException;
import in.co.mss.rms.exception.DuplicateRecordException;

public class RoleModelTest {
	public static void main(String[] args) throws ApplicationException, ParseException {
		 //testFindByName();
		//testupdate();
		testSearch();
	}

	

	/************************** TEST FindByName(); **********************************************/

	private static void testFindByName() {
		try {
			RoleBean bean = new RoleBean();
			RoleModel model = new RoleModel();
			bean = model.findByName("IMS");
			System.out
					.println("ID\tNAME\tDESCRIPTION\tCREATED BY\tMODIFIED BY\tCREATED_DATETIME\tMODIFIED_DATETIME");
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.print("\t" + bean.getModifiedDatetime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	
	/************************************** TEST UPDATE() **********************************/

	/** AVOID DUPLICATION OF ROLE **/
	
	/** setName **/
	
	private static void testupdate() {
		try {
			RoleModel model = new RoleModel();
			RoleBean bean = new RoleBean();
			bean = model.findByPK(7L);
			bean.setName("Finance");
			bean.setDescription("Finance Dept");
			bean.setCreatedBy("CCD MYSORE");
			bean.setModifiedBy("CCD MYSORE");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			model.update(bean);
			RoleBean updateBean = model.findByPK(1);
			if ("12".equals(updateBean.getName())) {
				System.out.println("UPDATE RECORD FAILED");
			} else {
				System.out.println("RECORD UPDATED SUCCESSFULLY");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

/**************************************** SEARCH ROLE 
 * * @throws ApplicationException ***************************************/

/******************************* CAN BE SEARCHED BY ID,NAME,DESCRIPITION ******************/
	
	private static void testSearch() throws ApplicationException{
   
		RoleModel model=new RoleModel();
		RoleBean bean =new RoleBean();
		List list=new ArrayList();
		
		/*bean.setName("FINANCE");*/
		/*bean.setId(4);*/
		bean.setDescription("f");
		list=model.search(bean, 0, 0);
		if(list.size()<0){
			System.out.println("TEST SEARCH FALIDED");
		}
		System.out.println("ID\tNAME\tDESCRIPTON\tCREATED BY\tMODIFIED BY\tCREATED DATE TIME\tMODIFIED DATE TIME");
		Iterator it=list.iterator();
		while(it.hasNext()){
			bean=(RoleBean)it.next();
			System.out.print("\n"+bean.getId());
			System.out.print("\t"+bean.getName());
			System.out.print("\t"+bean.getDescription());
			System.out.print("\t"+bean.getCreatedBy());
			System.out.print("\t"+bean.getModifiedBy());
			System.out.print("\t"+bean.getCreatedDatetime());
			System.out.print("\t"+bean.getModifiedDatetime());
			
			}/*catch (ApplicationException e) {
				e.printStackTrace();
 			}*/
		
	}
}
