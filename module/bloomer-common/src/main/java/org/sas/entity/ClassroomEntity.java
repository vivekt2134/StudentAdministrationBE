package org.sas.entity;

import lombok.Getter;
import lombok.Setter;
import org.sas.UUIDIdentity;
import org.sas.enums.Section;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.UUID;

/**
 * @author Vivek Tiwari
 */
@Getter
@Setter
@Entity
@Table(name = "classroom")
public class ClassroomEntity extends BaseEntity implements UUIDIdentity {

	private UUID uuid;

	@Column(columnDefinition = "smallint")
	private int name;

	private String displayName;

	@Column(columnDefinition = "smallint")
	private int year;

	private String remark;

	@Enumerated
	@Column(columnDefinition = "smallint")
	private Section section;

}
