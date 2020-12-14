
package pl.polsl.cementarycipher.oliwia.mlonek.servlets;

import java.util.*;
import javax.persistence.*;
import pl.polsl.cementarycipher.oliwia.mlonek.model.HistoryEntity;
import pl.polsl.cementarycipher.oliwia.mlonek.model.OperationsEntity;


/**
 * Class responsible for managing actions performed in or on database.
 *
 * @author Oliwia Mlonek
 * @version 5.0
 */

public class Manager {


    /**
     * Method responsible for adding a new record to operations and history entities.
     *
     * @param textToEncode text to be encoded/decoded
     * @param output text after encoding/decoding
     * @param em used to interact with the persistence context
     * @throws PersistenceException thrown by the persistence provider when a problem with database occurs
     */
    public void addRecord(String textToEncode, String output, EntityManager em) throws PersistenceException
    {
        try {
             OperationsEntity oe = new OperationsEntity(textToEncode, output);
            em.getTransaction().begin();
            em.persist(oe);
            em.getTransaction().commit();
            HistoryEntity he = new HistoryEntity(oe);
            em.getTransaction().begin();
            em.persist(he);
            em.getTransaction().commit();
            em.detach(oe);
            
        } catch (PersistenceException e) {
              throw e;
        }
       
     }
    
    
    /**
     * Method responsible for reading and returning back all records from history entity.
     *
     * @param em used to interact with the persistence context
     * @return list of records 
     * @throws PersistenceException thrown by the persistence provider when a problem with database occurs
     */
    List<HistoryEntity> selectHistory(EntityManager em) throws PersistenceException{
        try {
       
            List<HistoryEntity> resultList = em.createQuery("Select h from HistoryEntity h ", HistoryEntity.class).getResultList();

            return resultList;
            
        } catch (PersistenceException e) {
             throw e;
        }
        
         
    }
    /**
     * Method responsible for reading and returning back all records from operations entity.
     *
     * @param em used to interact with the persistence context
     * @return list of records 
     * @throws PersistenceException thrown by the persistence provider when a problem with database occurs
     */
    List<OperationsEntity> selectOperations(EntityManager em) throws PersistenceException {
        try {
       
            List<OperationsEntity> resultList = em.createQuery("Select o from OperationsEntity o ", OperationsEntity.class).getResultList();

            return resultList;
            
        } catch (PersistenceException e) {

              throw e;
        }
        
         
    }
}
