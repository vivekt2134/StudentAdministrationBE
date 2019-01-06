package org.sas.mapper;

import org.sas.entity.ClassroomEntity;
import org.mapstruct.Mapper;
import org.sas.Classroom;

/**
 * @author Vivek Tiwari
 */
@Mapper(componentModel = "spring")
public interface ClassroomMapper {
	ClassroomEntity sourceToDestination(Classroom classroom);
	Classroom destinationToSource(ClassroomEntity classroomEntity);
}
