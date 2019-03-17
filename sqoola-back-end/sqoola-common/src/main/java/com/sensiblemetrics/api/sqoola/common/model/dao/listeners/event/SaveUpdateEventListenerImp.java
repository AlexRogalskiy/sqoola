package com.sensiblemetrics.api.sqoola.common.model.dao.listeners.event;

import com.boraji.tutorial.hibernate.entity.Book;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.event.spi.SaveOrUpdateEventListener;

public class SaveUpdateEventListenerImp implements SaveOrUpdateEventListener {
   
   private static Logger logger = LogManager
            .getLogger(SaveUpdateEventListenerImp.class);
   private static final long serialVersionUID = 1L;

   @Override
   public void onSaveOrUpdate(SaveOrUpdateEvent e) throws HibernateException {

      logger.info("onSaveOrUpdate is called.");

      Object obj = e.getEntity();
      if (obj instanceof Book) {
         Book book = (Book) obj;
         logger.info(book);
      }
   }
}
