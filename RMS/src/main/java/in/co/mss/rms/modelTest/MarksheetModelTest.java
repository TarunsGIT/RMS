package in.co.mss.rms.modelTest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.mss.rms.bean.MarksheetBean;
import in.co.mss.rms.bean.StudentBean;
import in.co.mss.rms.exception.ApplicationException;
import in.co.mss.rms.exception.DuplicateRecordException;
import in.co.mss.rms.models.MarksheetModel;

public class MarksheetModelTest {
	public static MarksheetModel model = new MarksheetModel();

	public static void main(String[] args) throws ApplicationException,
			DuplicateRecordException {

		//testadd();
		//testDelete();
		testMeritList();
	}


	/******************************** TEST MERIT LIST *****************************************/
	
	private static void testMeritList() {
		try{
			MarksheetBean Mbean= new MarksheetBean();
			List list=new ArrayList();
			list=model.getMeritList(1, 7);
			if(list.size()<0){
				System.out.println("TEST LIST FAIL");
				}
			System.out.println("ID\tROLL NO\tSTUDENT ID\tNAME\tPHYSICS\tCHEMISTRY MATHS");
			Iterator it=list.iterator();
			while(it.hasNext()){
				Mbean=(MarksheetBean)it.next();
				System.out.print("\n"+Mbean.getId());
				System.out.print("\t"+Mbean.getRollNo());
				System.out.print("\t"+Mbean.getStudentId());
				System.out.print("\t"+Mbean.getName());
				System.out.print("\t"+Mbean.getPhysics());
				System.out.print("\t"+Mbean.getChemistry());
				System.out.print("\t"+Mbean.getMaths());
			}
			
		}catch(ApplicationException e){
			e.printStackTrace();
			
		}
		
	}


/************************************* TEST DELETE *****************************************/
	
	private static void testDelete() throws ApplicationException {
		try{
		MarksheetBean Mbean=new MarksheetBean();
		long pk=5L;
		Mbean.setId(pk);
		model.delete(Mbean);
		System.out.println("DELETED SUCESSFULLY "+Mbean.getId());
		MarksheetBean DeleteBean=model.findByPK(pk);
		if(DeleteBean==null){
			System.out.println("DELETE FAILED");
			}
		
		}catch(ApplicationException e){
			e.printStackTrace();
			
		}
	}
	

/*********************************** TEST ADD ********************************************/
	
	private static void testadd() throws ApplicationException,
			DuplicateRecordException {
		try {

			MarksheetBean Mbean = new MarksheetBean();
			//Mbean.setId(3);
			Mbean.setRollNo("1852");
			Mbean.setStudentId(26L);
			Mbean.setName("HANI PRAKASH");
			Mbean.setMaths(80);
			Mbean.setChemistry(80);
			Mbean.setPhysics(80);
			Mbean.setCreatedBy("ETA DEPT");
			Mbean.setModifiedBy("ETA");
			Mbean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			Mbean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			System.out.println("BEFORE ADD");
			long pk = model.add(Mbean);
			MarksheetBean AddedBean = model.findByPK(pk);
			System.out.println("ADDITION SUCESSFUL");
			if (AddedBean == null) {
				System.out.println("ADDITION FAILED");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();

		}catch(DuplicateRecordException e){
			e.printStackTrace();
			
		}
	}

	

}
