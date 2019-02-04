package org.sas;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.sas.enums.Section;

import java.util.UUID;

/**
 * @author Vivek Tiwari
 */
@Getter
@Setter
@EqualsAndHashCode(of = {}, callSuper = true)
public class Classroom extends BaseDto implements UUIDIdentity {

	private UUID uuid;
	private int name;
	private String displayName;
	private int year;
	private String remark;
	private Section section;

}
