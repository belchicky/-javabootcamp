package jtm.activity14;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.List;

//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static jtm.testsuite.AllTests.user;
import static jtm.testsuite.AllTests.password;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DatabaseUnitTest1 {
    static StudentManager manager;
    static Student student1;
    static Student student2;
    static int id1 = 100;
    static int id2;
    static int wrongId;
    static String wrongName = "idk bro";
    static String name = "Student";
    static String suffix1 = "1";
    static String suffix2 = "2";
    static String suffix3 = "3";
    static String name1 = name + suffix1;
    static String name2 = name + suffix2;
    static String name3 = name + suffix3;
    
    @Test
    public void test01StudentManager() {
        manager = new StudentManager();
    }

    @Test
    public void test02InsertStudentStringString() {
        student1 = new Student(id1, name1, name1);
        assertTrue("insertStudent(Student) error",manager.insertStudent(student1));
        assertFalse("insertStudent(duplicated Student) error",manager.insertStudent(student1));
    }

    @Test
    public void test03InsertStudentStudent() {
        student2 = new Student();
        student2.setFirstName(name2);
        student2.setLastName(name2);
        assertTrue("insertStudent(String,String) error",manager.insertStudent(name2, name2));
    }

    @Test
    public void test04FindStudentInt() {
        Student tmp = manager.findStudent(id1);
        assertEquals("findStudent(int) error",student1.toString(), tmp.toString());
        assertEquals("findStudent(wrongId) error","null null",manager.findStudent(wrongId).toString());
    }

    @Test
    public void test05FindStudentStringString() {
        List<Student> students = manager.findStudent(name, name);
        id2 = students.get(1).getId();
        student2.setId(id2);
    }

    @Test
    public void test06UpdateStudent() {
        student2 = new Student(id2, name3, name3);
        manager.updateStudent(student2);
    }

    @Test
    public void test07DeleteStudent() {
        manager.deleteStudent(id1);
        assertEquals("deleteStudent(id1)","null null",manager.findStudent(id1).toString());
        manager.deleteStudent(id2);
        assertEquals("deleteStudent(id2)","null null",manager.findStudent(id2).toString());
    }

    @Test
    public void test08CloseConnecion() {
    	
        manager.closeConnecion();
    }

	@Test
	public void test09Exception() {
		try {	
			Connection mockedConn = spy(DriverManager.getConnection( 
					"jdbc:mysql://localhost/?autoReconnect=true&serverTimezone=UTC&characterEncoding=utf8", user,
					password));
			doThrow(new SQLException("Mocked commit() exception")).when(mockedConn).commit();
			manager.conn = mockedConn;
			manager.insertStudent("", "");
		} catch (Exception e) {
			assertFalse("Unexpected exception was caught", false);
		}
	}	
	
	
	}
