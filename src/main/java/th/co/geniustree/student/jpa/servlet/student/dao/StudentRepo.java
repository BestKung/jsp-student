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
import th.co.geniustree.student.jpa.servlet.student.service.StudentService;

/**
 *
 * @author best
 */
public class StudentRepo {

    public List<Student> getAll(Student search) {
        List<Student> list = new ArrayList<Student>();
        ResultSet resultSet = null;
        if (search == null) {
            search.setId("%" + null + "%");
            search.setName("%" + null + "%");
        }
        if (search.getId() == null) {
            search.setId("%" + null + "%");
        }

        if (search.getName() == null) {
            search.setName("%" + null + "%");
        }
        String sql = "SELECT * FROM student WHERE id LIKE '%" + search.getId() + "%' AND name LIKE '%" + search.getName() + "%'";
        Statement statement = null;
        try {
            Connection connection = TransactionManager.connectionThreadLocal.get();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getString("id"));
                student.setName(resultSet.getString("name"));
                list.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Student> getAll() {
        List<Student> list = new ArrayList<Student>();
        Statement stmt = null;

        String sql = "SELECT * FROM student";
        try {
            Connection connection = TransactionManager.connectionThreadLocal.get();
            stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getString("id"));
                student.setName(resultSet.getString("name"));
                list.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentRepo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public void save(Student student) {
        String sql = "INSERT INTO student(id , name) values(?,?)";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = TransactionManager.connectionThreadLocal.get().prepareStatement(sql);
            preparedStatement.setString(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            try {
                TransactionManager.connectionThreadLocal.get().rollback();
            } catch (SQLException ex1) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(ex);
        }

    }

    public void delete(String id) {
        String sql = "DELETE FROM student WHERE id = ?";
        PreparedStatement preparedStatement;
        try {
            Connection connection = TransactionManager.connectionThreadLocal.get();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("delete fail...");
        }
    }

    public void update(Student student) {
        Connection connection = TransactionManager.connectionThreadLocal.get();
        String sql = "UPDATE student SET name = ? WHERE id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("update fail...");
        }
    }

    public Connection connectionH2() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/studenttest;AUTO_SERVER=TRUE");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentRepo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StudentRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

}
