 <%@page import="in.co.mss.rms.controller.MyProfileCtl"%>
<%@page import="in.co.mss.rms.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.mss.rms.util.DataUtility"%>
<%@page import="in.co.mss.rms.util.ServletUtility"%>
<html>
<body>

<form action="<%=ORSView.MY_PROFILE_CTL%>"method="post">

        <%@ include file="Header.jsp"%>
        <script type="text/javascript" src="../js/calendar.js"></script>
        <jsp:useBean id="bean" class="in.co.mss.rms.bean.UserBean"
            scope="request"></jsp:useBean>

        <center>
            <h1>My Profile</h1>
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
                    <th align="left">LoginId*</th>
                    <td><input type="text" name="login"
                        value="<%=DataUtility.getStringData(bean.getLogin())%>"readonly="readonly"><font
                        color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
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
                    <th align="left">Gender*</th>
                    <td>  <!-- <option value="">I am...</option> -->
                    
                        <%
                            HashMap map = new HashMap();
                            /* map.put("", "Select"); */

                            map.put("Male", "Male");
                            map.put("Female", "Female");


                            String htmlList = HTMLUtility.getList("gender", bean.getGender(),
                                    map);
                        %> <%=htmlList%><font color="RED"><%=ServletUtility
					.getErrorMessage("gender", request)%></font>
                    </td>
                </tr>
                <tr>
                    <th align="left">Mobile No*</th>
                    <td><input type="text" name="mobileNo"
                        value="<%=DataUtility.getStringData(bean.getMobileNo())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>
                </tr>

                <tr>
                    <th align="left">Date Of Birth*(dd/mm/yyyy)</th>
                    <td><input type="text" name="dob" readonly="readonly" 
                        value="<%=DataUtility.getDateString(bean.getDob())%>">
                    <a href="javascript:getCalendar(document.forms[0].dob);">
                            <img src="../image/cal.jpg" width="16" height="15" border="0"
                            alt="Calender">
                    </a><font
                        color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
                </tr>
                
            <%-- <H2>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H2> --%>
                
                <tr>
                    <th></th>
                    <td colspan="2"><input type="submit" name="operation"
                        value="<%=MyProfileCtl.OP_CHANGE_MY_PASSWORD%>"> &nbsp; <input type="submit"
                        name="operation" value="<%=MyProfileCtl.OP_SAVE %>"> &nbsp;</td>
                </tr>
            </table>
    </form>
    </center>
    <%@ include file="Footer.jsp"%>
</body>
</html>
 