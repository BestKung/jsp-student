<%-- 
    Document   : info
    Created on : Jun 15, 2016, 6:59:04 PM
    Author     : best
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String name = request.getParameter("name");
            String name2 = request.getParameter("name2");
        %>
        บันทึกข้อมูล <%=name%> เเละ <%=name2%> เรียบร้อย
    </body>
</html>
