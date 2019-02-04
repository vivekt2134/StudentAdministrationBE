package org.sas.writer.jasper.datasource;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Vivek Tiwari
 */
public class CollectionDataSource<T> extends JRBeanCollectionDataSource implements
    MutableDataSource<T> {


  public CollectionDataSource() {
    super(new ConcurrentLinkedQueue<T>());
  }

  public CollectionDataSource(Collection<T> beanCollection) {
    super(beanCollection);
  }


  public CollectionDataSource(Collection<T> beanCollection, boolean isUseFieldDescription) {
    super(beanCollection, isUseFieldDescription);
  }


  public void write(T item) {
    ((Collection<T>) getData()).add(item);
  }

  @Override
  public void clear() {
    getData().clear();
  }

  @Override
  public int size() {
    return this.getData().size();
  }
}
