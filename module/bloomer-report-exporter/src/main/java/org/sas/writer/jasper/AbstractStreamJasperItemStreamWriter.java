package org.sas.writer.jasper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.sas.dto.JasperContext;
import org.sas.listener.ReportContextInjectorJobListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;

import java.util.List;

/**
 * @author Vivek Tiwari
 */
@Getter
@Setter
@RequiredArgsConstructor
public abstract class AbstractStreamJasperItemStreamWriter<T> implements ItemStreamWriter<T> {

	private JasperContext<T> currentJasperContext;

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		this.currentJasperContext = (JasperContext) executionContext.get(
				ReportContextInjectorJobListener.JASPER_CONTEXT);
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {

	}

	/**
	 * Print Jasper Report Or Send Jasper Context to a topic so that report service could use it for generating
	 * report
	 *
	 * @throws ItemStreamException
	 */
	@Override
	public void close() throws ItemStreamException {
		this.currentJasperContext = null;
	}

	protected abstract void write(T item);

	@Override
	public void write(List<? extends T> items) throws Exception {
		items.forEach(this::write);
	}
}
