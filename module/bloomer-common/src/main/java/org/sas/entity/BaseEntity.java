package org.sas.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.sas.entity.generator.UUIDGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Vivek Tiwari
 */
@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(of = {"id"})
@EntityListeners({AuditingEntityListener.class, UUIDGenerator.class})
class BaseEntity {

	@Id
	@GeneratedValue
	@Column(unique = true)
	@Access(AccessType.PROPERTY)
	private Long id;

	@Column(updatable = false, nullable = false)
	@CreatedDate
	private LocalDateTime createdAt;

	@Column(nullable = false)
	@LastModifiedDate
	private LocalDateTime modifiedAt;
}
