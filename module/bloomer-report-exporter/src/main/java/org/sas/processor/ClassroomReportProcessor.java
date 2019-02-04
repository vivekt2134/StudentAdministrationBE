package org.sas.processor;

import lombok.RequiredArgsConstructor;
import org.sas.Classroom;
import org.sas.entity.ClassroomEntity;
import org.sas.mapper.ClassroomMapper;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

/**
 * @author Vivek Tiwari
 */
@Service
@RequiredArgsConstructor
public class ClassroomReportProcessor implements ItemProcessor<ClassroomEntity, Classroom> {

	private final ClassroomMapper classroomMapper;

	@Override
	public Classroom process(ClassroomEntity classroomEntity) throws Exception {
		return classroomMapper.destinationToSource(classroomEntity);
	}
}
