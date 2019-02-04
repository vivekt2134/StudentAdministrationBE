package org.sas.entity.generator;

import javax.persistence.PrePersist;
import java.util.Objects;
import java.util.UUID;
import org.sas.UUIDIdentity;

/**
 * @author Vivek Tiwari
 */
public class UUIDGenerator {

	@PrePersist
	public void setGuid(Object target) {
		UUIDIdentity uuidIdentifier;
		if (target instanceof UUIDIdentity && Objects.isNull((uuidIdentifier = (UUIDIdentity) target).getUuid())) {
			uuidIdentifier.setUuid(UUID.randomUUID());
		}
	}
}
