package in.co.mss.rms.modelTest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.mss.rms.bean.CourseBean;
import in.co.mss.rms.exception.ApplicationException;
import in.co.mss.rms.exception.DuplicateRecordException;
import in.co.mss.rms.models.CourseModel;

public class CourseModelTest {

	public static CourseModel model = new CourseModel();
	public static CourseBean bean = new CourseBean();

	public static void main(String[] args) {
		testAdd();
		// testUpdate();
		// testFindByPK();
		// testDelete();//
		// testFindByName();//
		// testSearch();//
		//testList();//

	}

	private static void testList() {
		try {
			CourseBean bean = new CourseBean();
			List list = new ArrayList();
			list = model.list(1, 10);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			System.out.println("ID\tNAME\tDESCRIPTION\tDURATION_IN_YEARS");
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (CourseBean) it.next();
				System.out.print("\n" + bean.getId());
				System.out.print("\t" + bean.getName());
				System.out.print("\t" + bean.getDescription());
				System.out.print("\t" + bean.getDuration());

			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	private static void testSearch() {
		try {
			CourseBean bean = new CourseBean();
			List list = new ArrayList();
			bean.setName("student");
			list = model.search(bean, 0, 0);
			if (list.size() < 0) {
				System.out.println("Test Serach fail");
			}
			Iterator it = list.iterator();
			System.out.println("ID\tNAME\tDESCRIPTION\tDURATION_IN_YEARS");
			while (it.hasNext()) {
				bean = (CourseBean) it.next();
				System.out.print("\n" + bean.getId());
				System.out.print("\t" + bean.getName());
				System.out.print("\t" + bean.getDescription());
				System.out.print("\t" + bean.getDuration());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	private static void testFindByName() {
		try {
			CourseBean bean = new CourseBean();
			bean = model.findByName("MTECH");
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	private static void testDelete() {
		try {
			CourseBean bean = new CourseBean();
			long pk = 5L;
			bean.setId(pk);
			model.delete(bean);
			CourseBean deletedbean = model.findByPK(pk);
			if (deletedbean != null) {
				System.out.println("Test Delete fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	private static void testAdd() {
		try {
			CourseBean bean = new CourseBean();
			// SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

			// bean.setId(1L);
			bean.setName("MCA");
			bean.setDescription("MASTER Of Computer Application");
			bean.setDuration(4);
			bean.setCreatedBy("ETA@gmail.com");
			bean.setModifiedBy("ETA@gmail.com");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			long pk = model.add(bean);
			CourseBean addedbean = model.findByPK(pk);
			if (addedbean == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	public static void testUpdate() {

		try {
			CourseBean bean = model.findByPK(2L);
			bean.setName("BTECH");
			bean.setDescription("Bachlor Of technology");
			bean.setDuration(4);
			bean.setCreatedBy("ETA@gmail.com");
			bean.setModifiedBy("ETA@gamil.com");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			model.update(bean);

			CourseBean updatedbean = model.findByPK(6L);
			if ("12".equals(updatedbean.getName())) {
				System.out.println("Test Update fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
	}

	public static void testFindByPK() {
		try {
			CourseBean bean = new CourseBean();
			long pk = 1L;
			bean = model.findByPK(pk);
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println("ID \tNAME\t DESCRIPTION \tDURATION");
			System.out.print("\n" + bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getDuration());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

}
