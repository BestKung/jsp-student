<%-- 
    Document   : view
    Created on : Jun 15, 2016, 6:52:17 PM
    Author     : best
--%>

<%@page import="th.co.geniustree.student.jpa.servlet.student.dao.StudentRepo"%>
<%@page import="th.co.geniustree.student.jpa.servlet.student.model.Student"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" action="display-controller">
            Id : <input type="text" name="id">
            Name : <input type="text" name="name">
            <input type="submit" value="ค้นหา">
        </form>
        <table>
            <tr>
                <td>id</td>
                <td>name</td>
                <td>delete</td>
                <td>Update</td>
            </tr>
            <%
                List<Student> students = (List<Student>) request.getAttribute("list");
                for (int i = 0; i < students.size(); i++) {%>
            <tr>
                <td><%=students.get(i).getId()%></td>
                <td><%=students.get(i).getName()%></td>
                <td><form method="post" action="delete-student">
                        <input type="hidden" value="<%=students.get(i).getId()%>" name="id">
                        <input type="submit" value="Delete">
                    </form>
                </td>
                <td><form method="post" action="update.jsp">
                        <input type="hidden" value="<%=students.get(i).getId()%>" name="id">
                        <input type="hidden" value="<%=students.get(i).getName()%>" name="name">
                        <input type="submit" value="Update">
                    </form></td>
            </tr>
            <%
                }
            %>
        </table>

        <a href="index.html">หน้าเรก</a>
        <style>
            td{
                border: solid black 1px;
            }

        </style>

    </body>
</html>
