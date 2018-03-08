package in.co.mss.rms.modelTest;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import in.co.mss.rms.bean.StudentBean;
import in.co.mss.rms.exception.ApplicationException;
import in.co.mss.rms.exception.DuplicateRecordException;

import in.co.mss.rms.models.StudentModel;

public class StudentModelTest {
	public static StudentModel model = new StudentModel();

	public static void main(String[] args) throws ParseException {
		 //testAdd();
		//testDelete();
		testFindByEmailId();

	}

	/*********************** TEST ADD ***********************************************/

	private static void testAdd() throws ParseException {
		try {
			StudentBean bean = new StudentBean();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			// bean.setId(1L);
			bean.setFirstName("ROBIN");
			bean.setLastName("SHERGILL");
			bean.setDob(sdf.parse("31/12/1990"));
			bean.setMobileNo("8080808080");
			bean.setEmail("Robin_Gill@gmail.com");
			bean.setCollegeId(15L);
			bean.setCreatedBy("ETA");
			bean.setModifiedBy("ETA");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			long pk = model.add(bean);
			StudentBean addedbean = model.findByPK(pk);
			if (addedbean == null) {
				System.out.println("Test add fail");
			} else {
				System.out.println("RECORD ADDED");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	/*********************************** TEST DELETE ******************************************/

	private static void testDelete() {

		try {
			StudentBean bean = new StudentBean();
			long pk = 27L;
			bean.setId(pk);
			model.delete(bean);
			System.out.println("RECORD DELETED SUCESSFULLY AT ID"
					+ bean.getId());
			StudentBean deletedBean = model.findByPK(pk);
			if (deletedBean == null) {
				System.out.println("DELETE FAILED");
			}

		} catch (ApplicationException e) {
			e.printStackTrace();

		}

	}

	/******************************** TEST FIND BY EMAIL ID ***********************************/

	private static void testFindByEmailId() {
		try {
			StudentBean bean = new StudentBean();
			bean = model.findByEmailId("prakash@gmail.com");
			if (bean == null) {
				System.out.println("NO RECORD EXISTS TO CORROSPONDING EMAIL " +bean.getEmail());
			} else {
				System.out.println("\nRECORD EXISTS BY EMAIL " + bean.getEmail());
			
			System.out
					.println("ID\tCOLLEGE_ID\tCOLLEGE_NAME\tFIRST NAME\tLAST NAME\tDOB\tMOBILE NO\tEMAIL\tCREATED BY\tMODIFIED BY\t CREATED DATETIME\tMODIFIED DATETIME");
			System.out.print(bean.getId());
			System.out.print("\t"+bean.getCollegeId());
			System.out.print("\t\t"+bean.getCollegeName());
			System.out.print("\t\t"+bean.getFirstName());
			System.out.print("\t\t"+bean.getLastName());
			System.out.print("\t\t"+bean.getDob());
			System.out.print("\t"+bean.getMobileNo());
			System.out.print("\t"+bean.getEmail());
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
