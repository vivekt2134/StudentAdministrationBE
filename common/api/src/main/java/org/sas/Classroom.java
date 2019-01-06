package org.sas;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.sas.enums.Section;

/**
 * @author Vivek Tiwari
 */
@Getter
@Setter
@EqualsAndHashCode(of = {}, callSuper = true)
public class Classroom extends BaseDto {

	private String name;
	private int year;
	private String remark;
	private Section section;

}
