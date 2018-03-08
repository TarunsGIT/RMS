package in.co.mss.rms.modelTest;

import in.co.mss.rms.bean.FacultyBean;
import in.co.mss.rms.bean.TimeTableBean;
import in.co.mss.rms.exception.ApplicationException;
import in.co.mss.rms.exception.DatabaseException;
import in.co.mss.rms.exception.DuplicateRecordException;
import in.co.mss.rms.models.TimeTableModel;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TimeTableModelTest {
	public static TimeTableBean bean = new TimeTableBean();
	public static TimeTableModel model = new TimeTableModel();

	public static void main(String[] args) throws ParseException, DatabaseException {
		testAdd();
		// testList();
	}

	private static void testList() {
		try {
			TimeTableBean bean = new TimeTableBean();
			List list = new ArrayList();
			list = model.list(1, 10);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (TimeTableBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getCourse());
				System.out.println(bean.getSubject());
				System.out.println(bean.getExaminationDate());
				System.out.println(bean.getTime());
				System.out.println(bean.getDay());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getCreatedDatetime());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getModifiedDatetime());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	private static void testAdd() throws ParseException, DatabaseException {
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

			// bean.setId(1L);
			bean.setCourseId(1L);
			bean.setCourse("BE");
			bean.setSubject("Maths");
			bean.setExaminationDate(sdf.parse("01-05-2016"));
			bean.setTime("1:00am-4:00am");
			/* bean.setDay("Monday"); */
			bean.setCreatedBy("ETA@gmail.com");
			bean.setModifiedBy("ETA@gmail.com");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			long pk = model.add(bean);
			TimeTableBean addedbean = model.findByPK(pk);
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
