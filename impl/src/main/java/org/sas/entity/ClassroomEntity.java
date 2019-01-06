package org.sas.entity;

import lombok.Getter;
import lombok.Setter;
import org.sas.enums.Section;

import javax.persistence.*;

/**
 * @author Vivek Tiwari
 */
@Getter
@Setter
@Entity
@Table(name = "classroom")
public class ClassroomEntity extends BaseEntity {

	private String name;
	@Column(columnDefinition = "smallint")
	private int year;
	private String remark;

	@Enumerated
	@Column(columnDefinition = "smallint")
	private Section section;

}
