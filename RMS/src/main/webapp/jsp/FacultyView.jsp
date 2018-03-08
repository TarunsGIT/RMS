

<%@page import="in.co.mss.rms.models.FacultyModel"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.mss.rms.controller.FacultyCtl"%>
<%@page import="in.co.mss.rms.util.HTMLUtility"%>
<%@page import="in.co.mss.rms.bean.DropdownListBean"%>
<%@page import="in.co.mss.rms.controller.BaseCtl"%>
<%@page import="in.co.mss.rms.util.DataUtility"%>
<%@page import="in.co.mss.rms.util.ServletUtility"%>
<%@page import="java.util.List"%>

<html>
<body>
	<form action="<%=ORSView.FACULTY_CTL%>" method="POST">
		<%@ include file="Header.jsp"%>
		<script type="text/javascript" src="../js/calendar.js"></script>
		<jsp:useBean id="bean" class="in.co.mss.rms.bean.FacultyBean"
			scope="request"></jsp:useBean>

		<%
			List l = (List) request.getAttribute("collegeList");
		%>

		<%
			List l1 = (List) request.getAttribute("courseList");
		%>

		<center>
			<%
				FacultyModel model = new FacultyModel();
				long id = DataUtility.getLong(request.getParameter("id"));
				if (id > 0) {
			%>

			<h1>Edit Faculty</h1>
			<%
				} else {
			%>
			<h1>Add Faculty</h1>
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
					<th align="left">College*</th>
					<td><%=HTMLUtility.getList("collegeId",
					String.valueOf(bean.getCollegeId()), l)%><font color="RED">
							<%=ServletUtility.getErrorMessage("College", request)%></font></td>

				</tr>
				<tr>
					<th align="left">Course*</th>
					<td><%=HTMLUtility.getList("courseId",
					String.valueOf(bean.getCourseId()), l1)%><font color="RED">
							 <%=ServletUtility.getErrorMessage("Course", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Primary Subject*</th>
					<td>
						<%
							HashMap map = new HashMap();

							map.put("Physics", "Physics");
							map.put("Chemistry", "Chemistry");
							map.put("Maths", "Maths");

							String htmlList = HTMLUtility.getList("primarySubject",
									bean.getPrimarySubject(), map);
						%><%=htmlList%><font color="RED"> <%=ServletUtility.getErrorMessage("primarySubject", request)%></font>

					</td>
				</tr>
				<th align="left">Secondary Subject*</th>
				<td>
					<%
						HashMap map1 = new HashMap();

						map.put("Physics", "Physics");
						map.put("Chemistry", "Chemistry");
						map.put("Maths", "Maths");

						String htmlList1 = HTMLUtility.getList("secondarySubject",
								bean.getSecondarySubject(), map);
					%><%=htmlList1%><font color="RED"> <%=ServletUtility.getErrorMessage("secondarySubject",
					request)%></font>

				</td>
				</tr>

				<tr>
					<th align="left">First Name*</th>
					<td><input type="text" name="firstName"
						value="<%=DataUtility.getStringData(bean.getFirstName())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Last Name*</th>
					<td><input type="text" name="lastName"
						value="<%=DataUtility.getStringData(bean.getLastName())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Qualification*(Highest Qualification)</th>
					<td><input type="text" name="qualification"
						value="<%=DataUtility.getStringData(bean.getQualification())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("qualification", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Date Of Birth*(dd/mm/yyyy)</th>
					<td><input type="text" name="dob" readonly="readonly"
						value="<%=DataUtility.getDateString(bean.getDob())%>"> <a
						href="javascript:getCalendar(document.forms[0].dob);"> <img
							src="../image/cal.jpg" width="16" height="15" border="0"
							alt="Calender">
					</a><font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
				</tr>
				<tr>
					<th align="left">MobileNo*</th>
					<td><input type="text" name="mobileNo"
						value="<%=DataUtility.getStringData(bean.getMobileNo())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Email ID*</th>
					<td><input type="text" name="email"
						value="<%=DataUtility.getStringData(bean.getEmail())%>"
						<%=(bean.getId() > 0) ? "readonly" : ""%>> <font
						color="red"> <%=ServletUtility.getErrorMessage("email", request)%></font></td>
				</tr>
				<tr>
				<th align="left">Address*</th>
				<td><textarea style="width: 172px;border-width: 2px"    rows="" name="address"><%=DataUtility.getStringData(bean.getAddress())%></textarea> <font 
				color="red"><%=ServletUtility.getErrorMessage("address",request) %>
				
				</font>
				</tr>
				<%-- <tr>
					<th align="left">Address*</th>
					<td><input type="text" name="address"
						value="<%=DataUtility.getStringData(bean.getAddress())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("address", request)%></font></td>
				</tr> --%>


				<tr>
					<th></th>
					<td colspan="2"><input type="submit" name="operation"
						value="<%=FacultyCtl.OP_SAVE%>"> <%
 	if (bean.getId() > 0) {
 %> <%-- &emsp;<input type="submit" name="operation"
						value="<%=FacultyCtl.OP_DELETE%>"> --%> <%
 	}
 %>&emsp; <input type="submit" name="operation"
						value="<%=FacultyCtl.OP_CANCEL%>"></td>
				</tr>
			</table>
	</form>
	</center>
	<%@ include file="Footer.jsp"%>
</body>
</html>
