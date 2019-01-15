package org.sas.service.admin;

import org.sas.mapper.ClassroomMapper;
import org.sas.repository.ClassroomRepository;
import lombok.RequiredArgsConstructor;
import org.sas.Classroom;
import org.springframework.stereotype.Service;

/**
 * @author Vivek Tiwari
 */
@Service
@RequiredArgsConstructor
public class ClassroomService {

	private final ClassroomRepository classroomRepository;
	private final ClassroomMapper classroomMapper;

	public void saveClassroom(Classroom classroom) {
		classroomRepository.save(classroomMapper.sourceToDestination(classroom));
	}
}
