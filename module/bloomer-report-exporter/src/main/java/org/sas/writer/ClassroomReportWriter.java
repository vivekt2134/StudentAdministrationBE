package org.sas.writer;

import org.sas.Classroom;
import org.sas.writer.jasper.AbstractStreamJasperItemStreamWriter;
import org.springframework.stereotype.Service;

/**
 * @author Vivek Tiwari
 */
@Service
public class ClassroomReportWriter extends AbstractStreamJasperItemStreamWriter<Classroom> {

	@Override
	public void write(Classroom classroom) {
		getCurrentJasperContext().getDataSource().write(classroom);
	}

}
