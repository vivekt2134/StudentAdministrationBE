package org.sas.mapper;

import org.mapstruct.Mapper;
import org.sas.Classroom;
import org.sas.entity.ClassroomEntity;

/**
 * @author Vivek Tiwari
 */
@Mapper(componentModel = "spring")
public interface ClassroomMapper {

	ClassroomEntity sourceToDestination(Classroom classroom);

	Classroom destinationToSource(ClassroomEntity classroomEntity);
}
