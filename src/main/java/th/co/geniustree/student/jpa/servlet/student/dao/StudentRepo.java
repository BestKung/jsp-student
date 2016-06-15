/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.student.jpa.servlet.student.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import th.co.geniustree.student.jpa.servlet.student.model.Student;

/**
 *
 * @author best
 */
public class StudentRepo {

    public List<Student> getAll() throws SQLException, ClassNotFoundException {
        List<Student> list = new ArrayList<Student>();
        Statement stmt;
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection("jdbc:h2:~/studenttest;AUTO_SERVER=TRUE");

        String sql = "SELECT * FROM student";
        stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);

        while (resultSet.next()) {
            Student student = new Student();
            student.setId(resultSet.getString("id"));
            student.setName(resultSet.getString("name"));
            list.add(student);
        }

        return list;
    }

    public void save(Student student, Connection connection) {
        String sql = "INSERT INTO student(id , name) values(?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(StudentRepo.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }

    }
}
