
<%@page import="in.co.mss.rms.bean.RoleBean"%>
<%@page import="in.co.mss.rms.bean.UserBean"%>
<%@page import="in.co.mss.rms.controller.ORSView"%>
<%@page import="in.co.mss.rms.controller.LoginCtl"%>
<%
	UserBean userBean = (UserBean) session.getAttribute("user");

	boolean userLoggedIn = userBean != null;

	String welcomeMsg = "Welcome  ";

	if (userLoggedIn) {
		String role = (String) session.getAttribute("role");
		welcomeMsg += userBean.getFirstName() + " (" + role + ")";
	} else {
		welcomeMsg += "Guest";
	}
%>

<table width="100%" border="0">
	<tr>
		<td width="90%"><a href="<%=ORSView.WELCOME_CTL%>"><b>Welcome</b></a> <b>|</b> <%
				if (userLoggedIn) {
			%> <a
			href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>"><b>Logout</b></a>

			<%
				} else {
			%> <a href="<%=ORSView.LOGIN_CTL%>"><b>Login</b></a> <%
 	}
 %></td>
		<td rowspan="2">
			<h1 align="Right">
				<img src="<%=ORSView.APP_CONTEXT%>/image/customLogo.png" width="300"
					height="90">
			</h1>
		</td>

	</tr>

	<tr>
		<td>
			<h3>
				<%=welcomeMsg%></h3>
		</td>
	</tr>


	<%
		if (userLoggedIn) {
	%>

	<tr>
		<td colspan="2">
			<%
				if (userBean.getRoleId() == RoleBean.ADMIN) {
			%> <a href="<%=ORSView.USER_CTL%>"><b>Add User</b></a> | <a
			href="<%=ORSView.USER_LIST_CTL%>"><b>User List</b></a> | <a
			href="<%=ORSView.GET_MARKSHEET_CTL%>"><b>Get Marksheet</b></a> | <a
			href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><b>Marksheet
					Merit List</b></a> | <a href="<%=ORSView.MARKSHEET_CTL%>"><b>Add
					Marksheet</b></a> | <a href="<%=ORSView.MARKSHEET_LIST_CTL%>"><b>Marksheet
					List</b></a> | <a href="<%=ORSView.COLLEGE_CTL%>"><b>Add College</b></a> |
			<a href="<%=ORSView.COLLEGE_LIST_CTL%>"><b>College List</b></a> | <a
			href="<%=ORSView.STUDENT_CTL%>"><b>Add Student</b></a> | <a
			href="<%=ORSView.STUDENT_LIST_CTL%>"><b>Student List</b></a> | <a
			href="<%=ORSView.ROLE_CTL%>"><b>Add Role</b></a> | <a
			href="<%=ORSView.ROLE_LIST_CTL%>"><b>Role List</b></a> | <a
			href="<%=ORSView.COURSE_CTL%>"><b>Add Course</b></a> | <a
			href="<%=ORSView.COURSE_LIST_CTL%>"><b>Course List</b></a> | <a
			href="<%=ORSView.FACULTY_CTL%>"><b>Add Faculty</b></a> | <a
			href="<%=ORSView.FACULTY_LIST_CTL%>"><b>Faculty List</b></a> | <a
			href="<%=ORSView.TIMETABLE_CTL%>"><b>Add Time Table</b></a> | <a
			href="<%=ORSView.TIMETABLE_LIST_CTL%>"><b>Time Table List</b></a> | <a
			href="<%=ORSView.JAVA_DOC_VIEW%>" target="_blank"><b>Java Doc</b></a>
			| <a href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><b>Change
					Password</b></a> | <a href="<%=ORSView.MY_PROFILE_CTL%>"><b>MyProfile</b></a>
			| <%
 	}
 		if (userBean.getRoleId() == RoleBean.STUDENT) {
 %> <a href="<%=ORSView.MY_PROFILE_CTL%>"><b>MyProfile</b></a> | <a
			href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><b>Change Password</b></a> |
			<a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><b>Marksheet
					Merit List</b></a> | <a href="<%=ORSView.TIMETABLE_LIST_CTL%>"><b>Exam
					Schedule</b></a> <%
 	}

 		if (userBean.getRoleId() == RoleBean.FACULTY) {
 %>
			<h4>
				<font color="blue">Note :It is Recommend to change your
					password on very first login*</font>
			</h4>
			<h4>
				<font color="blue">Please kindly ignore if already done</font>
			</h4> <a href="<%=ORSView.MY_PROFILE_CTL%>"><b>MyProfile</b></a> | <a
			href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><b>Change Password</b></a> |
			<a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><b>Marksheet
					Merit List</b></a> | <a href="<%=ORSView.GET_MARKSHEET_CTL%>"><b>Get
					Marksheet</b></a> | <a href="<%=ORSView.TIMETABLE_LIST_CTL%>"><b>Exam
					Schedule</b></a> <%
 	}
 		if (userBean.getRoleId() == RoleBean.COLLEGE_SCHOOL) {
 %> <a href="<%=ORSView.MY_PROFILE_CTL%>"><b>MyProfile</b></a> | <a
			href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><b>Change Password</b></a> |
			<a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><b>Marksheet
					Merit List</b></a> <a href="<%=ORSView.GET_MARKSHEET_CTL%>"><b>Get
					Marksheet</b></a> <%
 	}
 		if (userBean.getRoleId() == RoleBean.KIOSK) {
 %> <a href="<%=ORSView.MY_PROFILE_CTL%>"><b>MyProfile</b></a> | <a
			href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><b>Change Password</b></a> |
			<a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><b>Marksheet
					Merit List</b></a> | <a href="<%=ORSView.GET_MARKSHEET_CTL%>"><b>Get
					Marksheet</b></a> | <a href="<%=ORSView.TIMETABLE_LIST_CTL%>"><b>Exam
					Schedule</b></a> <%
 	}
 %>
		</td>

	</tr>
	<%
		}
	%>
</table>
<hr>
