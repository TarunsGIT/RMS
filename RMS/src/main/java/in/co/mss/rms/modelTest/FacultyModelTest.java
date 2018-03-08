/*package in.co.mss.rms.modelTest;

import in.co.mss.rms.bean.CourseBean;
import in.co.mss.rms.bean.FacultyBean;
import in.co.mss.rms.exception.ApplicationException;
import in.co.mss.rms.exception.DatabaseException;
import in.co.mss.rms.exception.DuplicateRecordException;
import in.co.mss.rms.models.FacultyModel;
import in.co.mss.rms.util.HTMLUtility;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class FacultyModelTest {
	 public static FacultyBean bean=new FacultyBean(); 
	public static FacultyModel model = new FacultyModel();

	public static void main(String[] args) throws ParseException,
			DatabaseException {
		// testAdd();
		// testUpdate();
		// testList();
		// testFindByCourse();
		// testFindBySubject();
		// testFindByCollege();
	}

	private static void testFindByCollege() {

		try {
			FacultyBean bean = new FacultyBean();
			bean = model.findByCollege(15);
			System.out
					.println("ID COLLEGE_ID COLLEGE_NAME COURSE_ID COURSE_NAME SUBJECT FIRST_NAME LAST_NAME");
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}

			System.out.print("\n" + bean.getId());
			System.out.print("\t" + bean.getCollegeId());
			System.out.print("\t" + bean.getCollegeName());
			System.out.print("\t\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getSubject());
			System.out.print("\t\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	private static void testFindBySubject() {
		try {
			FacultyBean bean = new FacultyBean();
			bean = model.findBySubject("PHYSICS");
			System.out
					.println("ID COLLEGE_ID COLLEGE_NAME COURSE_ID COURSE_NAME SUBJECT FIRST_NAME LAST_NAME");
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}

			System.out.print("\n" + bean.getId());
			System.out.print("\t" + bean.getCollegeId());
			System.out.print("\t" + bean.getCollegeName());
			System.out.print("\t\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getSubject());
			System.out.print("\t\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	private static void testFindByCourse() {
		try {
			FacultyBean bean = new FacultyBean();
			bean = model.findByCourse(2);
			System.out
					.println("ID COLLEGE_ID COLLEGE_NAME COURSE_ID COURSE_NAME SUBJECT FIRST_NAME LAST_NAME");
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}

			System.out.print("\n" + bean.getId());
			System.out.print("\t" + bean.getCollegeId());
			System.out.print("\t" + bean.getCollegeName());
			System.out.print("\t\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getSubject());
			System.out.print("\t\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testList() {

		try {
			FacultyBean bean = new FacultyBean();
			List list = new ArrayList();
			list = model.list(1, 10);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (FacultyBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getCollegeName());
				System.out.println(bean.getCourseName());
				System.out.println(bean.getFirstName());
				System.out.println(bean.getLastName());
				System.out.println(bean.getQualification());
				System.out.println(bean.getDob());
				System.out.println(bean.getMobileNo());
				System.out.println(bean.getEmail());
				System.out.println(bean.getAddress());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getCreatedDatetime());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getModifiedDatetime());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	private static void testUpdate() throws ParseException, DatabaseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {

			FacultyBean bean = model.findByPK(2L);
			 bean.setId(2L); 
			bean.setCollegeId(15L);
			bean.setCourseId(5L);
			bean.setFirstName("ankit");
			bean.setLastName("sharma");
			bean.setQualification("MS");
			bean.setDob(sdf.parse("12/12/1992"));
			bean.setMobileNo("8080808080");
			bean.setEmail("Ankit@gmail.com");
			bean.setAddress("10 Downing street");

			model.update(bean);

			FacultyBean updatedbean = model.findByPK(2L);
			if (!"rr".equals(updatedbean.getFirstName())) {
				System.out.println("Test Update fail");
			} else {
				System.out.println("UPDATE RECORD AT" + bean.getId());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
	}

	private static void testAdd() throws ParseException, DatabaseException {
		try {
			FacultyBean bean = new FacultyBean();
			FacultyModel model = new FacultyModel();

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			// bean.setId(1L);
			bean.setCollegeId(11L);
			 bean.setCollegeName("IIST"); 
			bean.setCourseId(6L);
			 bean.setCourseName("BE"); 
			bean.setSubject("Physics");
			bean.setFirstName("GLENN");
			bean.setLastName("MAXWELL");
			bean.setQualification("MS");
			bean.setDob(sdf.parse("31/12/1990"));
			bean.setMobileNo("8080808080");
			bean.setEmail("ma@gmail.com");
			bean.setAddress("10 Downing Street Sydney Australia");
			bean.setCreatedBy("ETA");
			bean.setModifiedBy("ETA");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			long pk = model.add(bean);
			FacultyBean addedbean = model.findByPK(pk);
			if (addedbean == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

}
*/