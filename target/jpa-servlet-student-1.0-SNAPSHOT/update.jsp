<%-- 
    Document   : update
    Created on : Jun 16, 2016, 10:36:55 AM
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
            String id = request.getParameter("id");
            String name = request.getParameter("name");
        %>
        <form method="post" action="update-student"> 
            <div>
                Id : <input disabled value="<%=id%>">
                <input name="id" type="hidden" value="<%=id%>">
            </div>
            <div>
                Name : <input name="name" value="<%=name%>">
            </div> 
            <div>
                <button>Save</button>
            </div>
        </form>
            <a href="index.html">หน้าเรก</a>
    </body>
</html>
