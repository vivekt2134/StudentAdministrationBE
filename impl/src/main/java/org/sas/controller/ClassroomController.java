package org.sas.controller;

import org.sas.service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.sas.Classroom;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vivek Tiwari
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/classroom")
public class ClassroomController {

	private final ClassroomService classroomService;

	@PostMapping
	public void addClassroom(Classroom classroom) {
		classroomService.saveClassroom(classroom);
	}
}
