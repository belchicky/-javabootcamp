package jtm.activity15;

//import java.util.ArrayList; //unnecessary
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus; //unnecessary

import jtm.activity13.Teacher;
import jtm.activity13.TeacherManager;

@Controller
@RequestMapping(value = "/", produces = "text/html;charset=UTF-8")
public class JettyController {

	TeacherManager manager;

	/**
	 * method which is invoked when root folder (i.e. http://localhost:8801/) of web
	 * application is requested. This method doesn't take any parameters passed in
	 * URL (address).
	 * 
	 * @return string as HTML response to the request using UTF-8 encoding for
	 *         non-Latin characters.
	 */
	@GetMapping("")
	@ResponseBody
	// This method should work without declared name parameter, request and
	// response objects,
	// but it shows, how passed request and returned response can be used inside
	// method
	public String homePage(@RequestParam(value = "name", required = false) String name, HttpServletRequest request,
			HttpServletResponse response) {
		StringBuilder sb = new StringBuilder();
		sb.append("<a href='/insertTeacher'>Insert teacher<a><br/>\n");
		sb.append("<a href='/findTeacher'>Find teacher<a><br/>\n");
		sb.append("<a href='/deleteTeacher'>Delete teacher<a><br/>\n");
		// Following is also redundant because status is OK by default:
		response.setStatus(HttpServletResponse.SC_OK);
		return sb.toString();
	}

	// Implement insertTeacher() method
	@GetMapping("insertTeacher")
	@ResponseBody
	public String insertTeacher(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "surname", required = false) String surname, HttpServletRequest request,
			HttpServletResponse response) {
		setTeacherManager();
		StringBuilder sb = new StringBuilder();
		if (name == null || surname == null) {
			sb.append("<form action=''>\n");
			sb.append("Name: <input type='text' name='name' value=''><br/>\n");
			sb.append("Surname: <input type='text' name='surname' value=''><br/>\n");
			sb.append("<input type='submit' value='Insert'></form><br/>\n");
			sb.append("<a href='/'>Back</a>\n");
		} else {
			if ("".equals(name) || "".equals(surname)) {
				sb.append("false<br/>\n");
				sb.append("<a href='/'>Back</a>\n");
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			} else {
				manager.insertTeacher(name, surname);
				sb.append("true<br/>\n");
				sb.append("<a href='/'>Back</a>\n");
			}
		}
		return sb.toString();
	}

	// Implement findTeacher() method
	@GetMapping("findTeacher")
	@ResponseBody
	public String findTeacher(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "surname", required = false) String surname, HttpServletRequest request,
			HttpServletResponse response) {
		setTeacherManager();
		StringBuilder sb = new StringBuilder();
		if (name == null || surname == null) {
			sb.append("<form action=''>\n");
			sb.append("Name: <input type='text' name='name' value=''><br/>\n");
			sb.append("Surname: <input type='text' name='surname' value=''><br/>\n");
			sb.append("<input type='submit' value='Find'></form><br/>\n");
			sb.append("<a href='/'>Back</a>\n");
		} else {
			List<Teacher> teachers = manager.findTeacher(name, surname);
			sb.append("<form action=''>\n");
			sb.append("Name: <input type='text' name='name' value=''><br/>\n");
			sb.append("Surname: <input type='text' name='surname' value=''><br/>\n");
			sb.append("<input type='submit' value='Find'></form><br/>\n");
			sb.append("<table>\n");
			for (Teacher teacher : teachers) {
				sb.append("<tr>\n");
				sb.append("<td>" + teacher.getId() + "</td>\n");
				sb.append("<td>" + teacher.getFirstName() + "</td>\n");
				sb.append("<td>" + teacher.getLastName() + "</td>\n");
				sb.append("</tr>\n");
			}
			sb.append("</table><br>\n");
			sb.append("<a href='/'>Back</a>\n");
		}
		return sb.toString();
	}

	// Implement deleteTeacher() method
	@GetMapping("deleteTeacher")
	@ResponseBody
	public String deleteTeacher(@RequestParam(value = "id", required = false) String id, HttpServletRequest request,
			HttpServletResponse response) {
		setTeacherManager();
		StringBuilder sb = new StringBuilder();
		if (id == null) {
			sb.append("<form action=''>\n");
			sb.append("ID: <input type='text' name='id' value=''><br/>\n");
			sb.append("<input type='submit' value='Delete'></form><br/>\n");
			sb.append("<a href='/'>Back</a>\n");
		} else {
			boolean status;
			try {
				status = manager.deleteTeacher(Integer.parseInt(id));
			} catch (Exception e) {
				status = manager.deleteTeacher(0);
			}
			if (status) {
				sb.append("true<br/>\n");
				sb.append("<a href='/'>Back</a>\n");
			} else {
				sb.append("false<br/>\n");
				sb.append("<a href='/'>Back</a>\n");
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}

		}
		return sb.toString();
	}

	void setTeacherManager() {
		if (manager == null) {
			manager = new TeacherManager();
		}
	}
}
