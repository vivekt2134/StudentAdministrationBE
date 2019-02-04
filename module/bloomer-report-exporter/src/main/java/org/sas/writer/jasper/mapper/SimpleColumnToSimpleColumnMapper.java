package org.sas.writer.jasper.mapper;

import ar.com.fdvs.dj.domain.entities.columns.SimpleColumn;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.sas.report.jasper.SimpleColumnDto;

/**
 * @author Vivek Tiwari
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy= ReportingPolicy.IGNORE)
public interface SimpleColumnToSimpleColumnMapper {

  SimpleColumn sourceToDestination(SimpleColumnDto simpleColumnDto);

  SimpleColumnDto destinationToSource(SimpleColumn simpleColumn);
}
