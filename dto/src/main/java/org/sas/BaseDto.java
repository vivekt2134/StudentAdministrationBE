package org.sas;

import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Vivek Tiwari
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class BaseDto {

	private Long id;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
}

