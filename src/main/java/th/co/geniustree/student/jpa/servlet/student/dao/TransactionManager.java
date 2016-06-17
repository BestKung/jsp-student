/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.student.jpa.servlet.student.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import th.co.geniustree.student.jpa.servlet.student.dao.StudentRepo;

/**
 *
 * @author best
 */
public class TransactionManager {

    public final static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

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

    public void begin() {
        connectionThreadLocal.set(connectionH2());
        try {
            connectionThreadLocal.get().setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(StudentRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void commit() {
        connectionThreadLocal.set(connectionH2());
        try {
            connectionThreadLocal.get().commit();
        } catch (SQLException ex) {
            Logger.getLogger(StudentRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
