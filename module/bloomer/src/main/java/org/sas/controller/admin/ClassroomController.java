package org.sas.controller.admin;

import lombok.RequiredArgsConstructor;
import org.sas.Classroom;
import org.sas.service.admin.ClassroomService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

/**
 * @author Vivek Tiwari
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/classroom")
public class ClassroomController {

	private final ClassroomService classroomService;

	@PostMapping
	public Classroom addClassroom(@RequestBody Classroom classroom) {
		return classroomService.saveClassroom(classroom);
	}

	@DeleteMapping("/{id}")
	public void deleteClassroom(@PathVariable UUID id) {
		classroomService.deleteClassroomByGuid(id);
	}

	@GetMapping("/all")
	public Set<Classroom> getClassroom() {
		return classroomService.getAllClassroom();
	}

	@GetMapping("/{id}")
	public Classroom getClassroom(@PathVariable UUID id) {
		return classroomService.getClassroom(id);
	}
}
