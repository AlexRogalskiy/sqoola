package com.sensiblemetrics.api.sqoola.common.model.dao.listeners.event;

import com.boraji.tutorial.hibernate.entity.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.LoadEvent;
import org.hibernate.event.spi.LoadEventListener;

public class LoadEventListenerImp implements LoadEventListener {
   
   private static final long serialVersionUID = 1L;
   private static Logger logger = LogManager.getLogger(LoadEventListenerImp.class);

   @Override
   public void onLoad(LoadEvent e, LoadType type) throws HibernateException {
      logger.info("onLoad is called.");
      Object obj = e.getResult();
      if (obj instanceof Book) {
         Book book = (Book) obj;
         logger.info(book);
      }
   }

}
