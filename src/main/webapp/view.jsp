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
        <table>
            <tr>
                <td>id</td>
                <td>name</td>
            </tr>
            <%
                List<Student> students = (List<Student>) request.getAttribute("list");
                for (int i = 0; i < students.size(); i++) {%>
            <tr>
                <td><%=students.get(i).getId()%></td>
                <td><%=students.get(i).getName()%></td>
            </tr>
            <%
                }
            %>
            <tr>

            </tr>
        </table>



        <!--<h1><%= students%></h1>-->

        <style>
            td{
                border: solid black 1px;
            }
/*            table{
                border: solid black 1px;
            }*/
            /*            tr{
                            border: solid black 2px;
                        }*/
        </style>

    </body>
</html>
