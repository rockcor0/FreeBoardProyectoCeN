package com.project.freeboard.service;

import java.util.List;

import javax.jdo.annotations.Transactional;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.NotFoundException;
import com.project.freeboard.dao.StudentsDAO;
import com.project.freeboard.entity.Students;

@Api(name = "freeboard", version = "v1", namespace = @ApiNamespace(ownerDomain = "service.freeboard.project.com", ownerName = "service.freeboard.project.com", packagePath = ""))
public class EpStudent {
	
	private StudentsDAO sDAO;
	
	/**
	 * API Student Entity
	 */
	@ApiMethod(name = "updateStudent", path = "updateStudent", httpMethod = ApiMethod.HttpMethod.PUT)
	public Students updateStudent(Students s) throws NotFoundException {
		sDAO = new StudentsDAO();
		if (sDAO.updateStudent(s)) {
			return s;
		} else {
			throw new NotFoundException("Student doesn't exist.");
		}
	}

	@Transactional
	@ApiMethod(name = "removeStudent", path = "removeStudent/{cc}", httpMethod = ApiMethod.HttpMethod.DELETE)
	public void removeStudent(@Named("cc") String cc) throws NotFoundException {
		sDAO = new StudentsDAO();
		if (!sDAO.removeStudent(cc)) {
			throw new NotFoundException("Student doesn't exist.");
		}
	}

	@ApiMethod(name = "getAllStudents", path = "students", httpMethod = ApiMethod.HttpMethod.GET)
	public List<Students> getStudents() {

		sDAO = new StudentsDAO();
		List<Students> students = sDAO.getStudents();

		return students;
	}

	@ApiMethod(name = "getStudentByCC", path = "students/{cc}", httpMethod = ApiMethod.HttpMethod.GET)
	public Students getStudentByCC(@Named("cc") String cc) throws NotFoundException {
		sDAO = new StudentsDAO();
		Students students = sDAO.getStudentByCC(cc);
		if (students != null) {
			return students;
		} else {
			throw new NotFoundException("Student doesn't exist.");
		}
	}

}
