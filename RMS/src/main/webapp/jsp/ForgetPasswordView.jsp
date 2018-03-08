
<%@page import="in.co.mss.rms.controller.ForgetPasswordCtl"%>
<%@page import="in.co.mss.rms.util.DataUtility"%>
<%@page import="in.co.mss.rms.util.ServletUtility"%>
<html>
<body>
	<form action="<%=ORSView.FORGET_PASSWORD_CTL%>" method="post">

		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="in.co.mss.rms.bean.UserBean"
			scope="request"></jsp:useBean>

		<center>
			<h1>Forgot Password?</h1>
			<input type="hidden" name="id" value="<%=bean.getId()%>">
			<H2>
				<center>
					<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
					</font>
				</center>
			</H2>

			<H2>
				<center>
					<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
					</font>
				</center>
			</H2>


			<table>
				<lable>Submit your email address below and we'll send you a
				password.</lable>
				<br>
				<br>
				<h3>

					<font color="RED"><%=ServletUtility.getErrorMessage("login", request)%></font>
				</h3>
				<label>Email Id* :</label>&emsp;
				<input type="text" name="login" placeholder="Enter ID Here"
					value="<%=ServletUtility.getParameter("login", request)%>">&emsp;
				<input type="submit" name="operation"
					value="<%=ForgetPasswordCtl.OP_GO%>">
				<br>
				<%--  <font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font> --%>
			</table>


			<%-- <H2>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2> --%>
	</form>
	</center>
	<%@ include file="Footer.jsp"%>
</body>
</html>
