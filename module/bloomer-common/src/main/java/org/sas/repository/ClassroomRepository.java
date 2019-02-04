package org.sas.repository;

import org.sas.entity.ClassroomEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author Vivek Tiwari
 */
public interface ClassroomRepository extends CrudRepository<ClassroomEntity, Long> {

	void deleteByUuid(UUID uuid);

	ClassroomEntity findByUuid(UUID uuid);
}
