package jtm.activity14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import static jtm.testsuite.AllTests.database;
import static jtm.testsuite.AllTests.user;
import static jtm.testsuite.AllTests.password;

public class StudentManager {

	protected Connection conn;

	public StudentManager() {

		/*-
		 *  #1 When new StudentManager is created, create connection to the database server:
		 * - url = "jdbc:mysql://localhost/?autoReconnect=true&serverTimezone=UTC&characterEncoding=utf8"
		 * - user = AllTests.user
		 * - pass = AllTests.password
		 * Notes:
		 * 1. Use database name imported from jtm.testsuite.AllTests.database
		 * 2. Do not pass database name into url, because some statements in tests need to be executed
		 * server-wise, not just database-wise.
		 * 3. Set AutoCommit to false and use conn.commit() where necessary in other methods
		 */

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/?autoReconnect=true&serverTimezone=UTC&characterEncoding=utf8", user,
					password);
			conn.setAutoCommit(false);
		} catch (Exception e) {
//			try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
		}
	}

	/**
	 * Returns a Student instance represented by the specified ID.
	 *
	 * @param id the ID of student
	 * @return a Student object
	 */

	public Student findStudent(int id) {
		// #2 Write an sql statement that searches student by ID.
		// If student is not found return Student object with zero or null in
		// its fields!
		// Hint: Because default database is not set in connection,
		// use full notation for table "databaseXX.Student"
		Student student = new Student();

		try {
			String sql = "SELECT * FROM " + database + ".Student where id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			student = new Student(rs.getInt(1), rs.getString(2), rs.getString(3));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	/**
	 * Returns a list of Student object that contain the specified first name and
	 * last name. This will return an empty List of no match is found.
	 *
	 * @param firstName the first name of student.
	 * @param lastName  the last name of student.
	 * @return a list of Student object.
	 */

	public List<Student> findStudent(String firstName, String lastName) {
		// #3 Write an sql statement that searches student by first and
		// last name and returns results as List<Student>.
		// Note that search results of partial match
		// in form ...like '%value%'... should be returned
		// Note, that if nothing is found return empty list!
		List<Student> students = new LinkedList<>();
		try {
			String sql = "SELECT * FROM " + database + ".Student where firstname like ? and lastname like ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + firstName + "%");
			stmt.setString(2, "%" + lastName + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				students.add(new Student(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return students;
	}

	/**
	 * Insert an new student (first name and last name) into the repository.
	 *
	 * @param firstName the first name of student
	 * @param lastName  the last name of student
	 * @return true if success, else false.
	 */
	public boolean insertStudent(String firstName, String lastName) {
		// #4 Write an sql statement that inserts student in database.
		boolean status = false;
		try {
			String sql = "INSERT INTO " + database + ".Student (firstname, lastname) VALUES (?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			int rs = stmt.executeUpdate();
			conn.commit();
			if (rs > 0)
				status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	/**
	 * Insert student object into database
	 *
	 * @param student
	 * @return true on success, false on error (e.g. non-unique id)
	 */

	public boolean insertStudent(Student student) {
		// #5 Write an sql statement that inserts student in database.
		boolean status = false;
		try {
			String sql = "INSERT INTO " + database + ".Student VALUES (?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, student.getId());
			stmt.setString(2, student.getFirstName());
			stmt.setString(3, student.getLastName());
			int rs = stmt.executeUpdate();
			conn.commit();
			if (rs > 0)
				status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	/**
	 * Updates an existing Student in the repository with the values represented by
	 * the Student object.
	 *
	 * @param student a Student object, which contain information for updating.
	 * @return true if row was updated.
	 */

	public boolean updateStudent(Student student) {
		// #6 Write an sql statement that updates student information.
		boolean status = false;
		try {
			String sql = "UPDATE " + database + ".Student SET firstname = ?," + " lastname = ? WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, student.getFirstName());
			stmt.setString(2, student.getLastName());
			stmt.setInt(3, student.getId());
			int rs = stmt.executeUpdate();
			conn.commit();
			if (rs > 0)
				status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	/**
	 * Delete an existing Student in the repository with the values represented by
	 * the ID.
	 *
	 * @param id the ID of student.
	 * @return true if row was deleted.
	 */

	public boolean deleteStudent(int id) {
		// #7 Write an sql statement that deletes student from database.
		boolean status = false;
		try {
			String sql = "DELETE FROM " + database + ".Student WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			int rs = stmt.executeUpdate();
			conn.commit();
			if (rs > 0)
				status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public void closeConnecion() {
		// Close connection to the database server
		try {
			conn.close();
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	}

}