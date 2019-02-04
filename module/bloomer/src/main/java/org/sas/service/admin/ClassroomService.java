package org.sas.service.admin;

import lombok.RequiredArgsConstructor;
import org.sas.Classroom;
import org.sas.mapper.ClassroomMapper;
import org.sas.repository.ClassroomRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author Vivek Tiwari
 */
@Service
@RequiredArgsConstructor
public class ClassroomService {

  private final ClassroomRepository classroomRepository;
  private final ClassroomMapper classroomMapper;

  public Classroom saveClassroom(Classroom classroom) {
    return classroomMapper.destinationToSource(
        classroomRepository.save(classroomMapper.sourceToDestination(classroom)));
  }

  @Transactional
  public void deleteClassroomByGuid(UUID id) {
    classroomRepository.deleteByUuid(id);
  }

  public Set<Classroom> getAllClassroom() {
    Set<Classroom> classrooms = new HashSet<>();
    classroomRepository.findAll().forEach(
        classroomEntity -> classrooms.add(classroomMapper.destinationToSource(classroomEntity))
    );
    return classrooms;
  }

  @Transactional
  public Classroom getClassroom(UUID id) {
    return classroomMapper.destinationToSource(classroomRepository.findByUuid(id));
  }
}
