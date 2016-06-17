/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.student.jpa.servlet.student.service;

import th.co.geniustree.student.jpa.servlet.student.dao.TransactionManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import th.co.geniustree.student.jpa.servlet.student.dao.StudentRepo;
import th.co.geniustree.student.jpa.servlet.student.model.Student;

/**
 *
 * @author best
 */
public class StudentService {

    public void save(List<Student> students) {
        StudentRepo studentRepo = new StudentRepo();
        TransactionManager transactionManager = new TransactionManager();
        transactionManager.begin();
        for (int i = 0; i < students.size(); i++) {
            studentRepo.save(students.get(i));
        }
        transactionManager.commit();
    }
}
