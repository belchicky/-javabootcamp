package jtm.activity13;

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

public class TeacherManager {

	protected Connection conn;

	public TeacherManager() {

		/*-
		 *  #1 When new TeacherManager is created, create connection to the database server:
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
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Returns a Teacher instance represented by the specified ID.
	 *
	 * @param id the ID of teacher
	 * @return a Teacher object
	 */

	public Teacher findTeacher(int id) {
		// #2 Write an sql statement that searches teacher by ID.
		// If teacher is not found return Teacher object with zero or null in
		// its fields!
		// Hint: Because default database is not set in connection,
		// use full notation for table "databaseXX.Teacher"
		Teacher teacher = new Teacher();

		try {
			String sql = "SELECT * FROM " + database + ".Teacher where id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			teacher = new Teacher(rs.getInt(1), rs.getString(2), rs.getString(3));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return teacher;
	}

	/**
	 * Returns a list of Teacher object that contain the specified first name and
	 * last name. This will return an empty List of no match is found.
	 *
	 * @param firstName the first name of teacher.
	 * @param lastName  the last name of teacher.
	 * @return a list of Teacher object.
	 */

	public List<Teacher> findTeacher(String firstName, String lastName) {
		// #3 Write an sql statement that searches teacher by first and
		// last name and returns results as List<Teacher>.
		// Note that search results of partial match
		// in form ...like '%value%'... should be returned
		// Note, that if nothing is found return empty list!
		List<Teacher> teachers = new LinkedList<>();
		try {
			String sql = "SELECT * FROM " + database + ".Teacher where firstname like ? and lastname like ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + firstName + "%");
			stmt.setString(2, "%" + lastName + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				teachers.add(new Teacher(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return teachers;
	}

	/**
	 * Insert an new teacher (first name and last name) into the repository.
	 *
	 * @param firstName the first name of teacher
	 * @param lastName  the last name of teacher
	 * @return true if success, else false.
	 */
	public boolean insertTeacher(String firstName, String lastName) {
		// #4 Write an sql statement that inserts teacher in database.
		boolean status = false;
		try {
			String sql = "INSERT INTO " + database + ".Teacher (firstname, lastname) VALUES (?, ?)";
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
	 * Insert teacher object into database
	 *
	 * @param teacher
	 * @return true on success, false on error (e.g. non-unique id)
	 */

	public boolean insertTeacher(Teacher teacher) {
		// #5 Write an sql statement that inserts teacher in database.
		boolean status = false;
		try {
			String sql = "INSERT INTO " + database + ".Teacher VALUES (?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, teacher.getId());
			stmt.setString(2, teacher.getFirstName());
			stmt.setString(3, teacher.getLastName());
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
	 * Updates an existing Teacher in the repository with the values represented by
	 * the Teacher object.
	 *
	 * @param teacher a Teacher object, which contain information for updating.
	 * @return true if row was updated.
	 */

	public boolean updateTeacher(Teacher teacher) {
		// #6 Write an sql statement that updates teacher information.
		boolean status = false;
		try {
			String sql = "UPDATE " + database + ".Teacher SET firstname = ?," + " lastname = ? WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, teacher.getFirstName());
			stmt.setString(2, teacher.getLastName());
			stmt.setInt(3, teacher.getId());
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
	 * Delete an existing Teacher in the repository with the values represented by
	 * the ID.
	 *
	 * @param id the ID of teacher.
	 * @return true if row was deleted.
	 */

	public boolean deleteTeacher(int id) {
		// #7 Write an sql statement that deletes teacher from database.
		boolean status = false;
		try {
			String sql = "DELETE FROM " + database + ".Teacher WHERE id = ?";
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

	public static void main(String[] args) {
		TeacherManager manage = new TeacherManager();
		// System.out.println(manage.findTeacher(1));
	}
}