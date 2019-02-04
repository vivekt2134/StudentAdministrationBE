package org.sas.writer.jasper.datasource;

import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Vivek Tiwari
 */
public interface MutableDataSource<T> extends JRDataSource {

	void write(T item);
	void clear();
	int size();
}
