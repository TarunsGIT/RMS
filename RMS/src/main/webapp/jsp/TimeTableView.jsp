
<%@page import="java.util.List"%>
<%@page import="in.co.mss.rms.models.TimeTableModel"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.mss.rms.controller.TimeTableListCtl"%>
<%@page import="in.co.mss.rms.controller.TimeTableCtl"%>
<%@page import="in.co.mss.rms.controller.BaseCtl"%>
<%@page import="in.co.mss.rms.util.DataUtility"%>
<%@page import="in.co.mss.rms.util.ServletUtility"%>
<%@page import="in.co.mss.rms.util.HTMLUtility"%>
<html>
<body>
	<form action="<%=ORSView.TIMETABLE_CTL%>" method="POST">
		<%@ include file="Header.jsp"%>
		<script type="text/javascript" src="../js/calendar.js"></script>

		<jsp:useBean id="bean" class="in.co.mss.rms.bean.TimeTableBean"
			scope="request"></jsp:useBean>

		<%
			List l1 = (List) request.getAttribute("courseList");
		%>

		<center>
			<%
				TimeTableModel model = new TimeTableModel();
				long id = DataUtility.getLong(request.getParameter("id"));
				if (id > 0) {
			%>

			<h1>Edit Time Table</h1>
			<%
				} else {
			%>
			<h1>Add Time Table</h1>
			<%
				}
			%>
			<H2>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</H2>
			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H2>

			<input type="hidden" name="id" value="<%=bean.getId()%>">

			<table>
				<tr>
					<th align="left">Course*</th>
					<td><%=HTMLUtility.getList("courseId",
					String.valueOf(bean.getCourseId()), l1)%><font color="RED">
							<%=ServletUtility.getErrorMessage("Course", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Subject*</th>
					<td>
						<%
							HashMap map1 = new HashMap();

							/* map1.put("", "select"); */
							map1.put("Maths", "Maths");
							map1.put("Physics", "Physics");
							map1.put("Chemistry", "Chemistry");

							String htmlSubjectlist = HTMLUtility.getList("Subject",
									bean.getSubject(), map1);
						%><%=htmlSubjectlist%> <font color="red"> <%=ServletUtility.getErrorMessage("Subject", request)%></font>
					</td>
				</tr>
				<tr>

					<th align="left">Examination Date*</th>
					<td><input type="text" name="ExaminationDate"
						readonly="readonly"
						value="<%=DataUtility.getDateString(bean.getExaminationDate())%>">
						<a
						href="javascript:getCalendar(document.forms[0].ExaminationDate);">
							<img src="../image/cal.jpg" width="16" height="15" border="0"
							alt="Calender">
					</a><font color="red"> <%=ServletUtility
					.getErrorMessage("ExaminationDate", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Time Slot*</th>
					<td>
						<%
							HashMap map3 = new HashMap();

							/* map3.put("", "select"); */
							map3.put("10:00 AM - 1:00 AM", "10:00 AM - 1:00 AM");
							map3.put("2:00 AM - 5:00 AM", "2:00 AM - 5:00 AM");
							/* map3.put("3:00AM-6:00PM", "3:00AM-6:00PM"); */

							String htmlExtime = HTMLUtility.getList("Time", bean.getTime(),
									map3);
						%> <%=htmlExtime%><font color="red"> <%=ServletUtility.getErrorMessage("Time", request)%></font>
					</td>
				</tr>
				<%-- 				<tr>
					<th align="left">Day*</th>
					<td>
						<%
							HashMap map4 = new HashMap();
							/* map4.put("", "Select"); */
							map4.put("Monday", "Monday");
							map4.put("Tuesday", "Tuesday");
							map4.put("Wednesday", "Wednesday");
							map4.put("Thursday", "Thursday");
							map4.put("Friday", "Friday");
							map4.put("Saturday", "Saturday");

							String htmlExday = HTMLUtility.getList("Day", bean.getDay(), map4);
						%><%=htmlExday%><font color="red"> <%=ServletUtility.getErrorMessage("Day", request)%></font>
					</td>
				</tr> --%>
				<tr>
					<th></th>
					<td colspan="2"><input type="submit" name="operation"
						value="<%=TimeTableCtl.OP_SAVE%>">&emsp; <input
						type="submit" name="operation" value="<%=TimeTableCtl.OP_CANCEL%>"></td>
				</tr>
			</table>
	</form>
	</center>
	<%@ include file="Footer.jsp"%>
</body>
</html>
