 
<%@page import="in.co.mss.rms.models.UserModel"%>
<%@page import="in.co.mss.rms.controller.UserCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.mss.rms.controller.BaseCtl"%>
<%@page import="in.co.mss.rms.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.mss.rms.util.DataUtility"%>
<%@page import="in.co.mss.rms.util.ServletUtility"%>
<html>
<body>
	<form action="<%=ORSView.USER_CTL%>" method="POST">
		<%@ include file="Header.jsp"%>
		<script type="text/javascript" src="../js/calendar.js"></script>
		<jsp:useBean id="bean" class="in.co.mss.rms.bean.UserBean"
			scope="request"></jsp:useBean>

		<%
			List l = (List) request.getAttribute("roleList");
		%>

		<center>
			<%
				UserModel model = new UserModel();
				long id = DataUtility.getLong(request.getParameter("id"));
				if (id > 0) {
			%>

			<h1>Edit User</h1>
			<%
				} else {
			%>
			<h1>Add User</h1>
			<%
				}
			%>


			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H2>

			<H2>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</H2>

			<input type="hidden" name="id" value="<%=bean.getId()%>">

			<table>
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
					<th align="left">LoginId*</th>
					<td><input type="text" name="login"
						value="<%=DataUtility.getStringData(bean.getLogin())%>"
						<%=(bean.getId() > 0) ? "readonly" : ""%>><font
						color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
				</tr>

				<tr>
					<th align="left">Password*</th>
					<td><input type="password" name="password"
						value="<%=DataUtility.getStringData(bean.getPassword())%>"
						<%=(bean.getId() > 0) ? "readonly" : ""%>><font
						color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Confirm Password*</th>
					<td><input type="password" name="confirmPassword"
						<%if (bean.getId() > 0) {%>
						value="<%=DataUtility.getStringData(bean.getPassword())%>"
						<%} else {%>
						value="<%=DataUtility.getStringData(bean.getConfirmPassword())%>"
						<%}%> <%=(bean.getId() > 0) ? "readonly" : ""%>><font
						color="red"> <%=ServletUtility
					.getErrorMessage("confirmPassword", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Gender*</th>
					<td>
						<%
							HashMap map = new HashMap();
							/* map.put("", "Select"); */

							map.put("Male", "Male");
							map.put("Female", "Female");

							String htmlList = HTMLUtility.getList("gender", bean.getGender(),
									map);
						%> <%=htmlList%><font color="RED"> <%=ServletUtility.getErrorMessage("gender", request)%></font>
					</td>
				</tr>
				<tr>
					<th align="left"><b>Role*</b></th>
					<td><%=HTMLUtility.getList("roleId",
					String.valueOf(bean.getRoleId()), l)%><font color="RED"> <%=ServletUtility.getErrorMessage("role", request)%></font>

					</td>
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
					<th></th>
					<td colspan="2"><input type="submit" name="operation"
						value="<%=UserCtl.OP_SAVE%>">&emsp; <input type="submit"
						name="operation" value="<%=UserCtl.OP_CANCEL%>"></td>
				</tr>
			</table>
	</form>
	</center>
	<%@ include file="Footer.jsp"%>
</body>
</html>

 