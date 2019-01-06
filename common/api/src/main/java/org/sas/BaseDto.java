package org.sas;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Vivek Tiwari
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"guid"})
public class BaseDto {

	private UUID guid;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
}

