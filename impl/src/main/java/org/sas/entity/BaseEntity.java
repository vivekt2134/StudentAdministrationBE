package org.sas.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Vivek Tiwari
 */
@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(of = {"guid"})
class BaseEntity {

	@Id
	@GeneratedValue
	@Column(unique = true)
	@Access(AccessType.PROPERTY)
	private UUID guid;

	@Column(updatable = false, nullable = false)
	@CreatedDate
	private LocalDateTime createdAt;

	@Column(nullable = false)
	@LastModifiedDate
	private LocalDateTime modifiedAt;
}
