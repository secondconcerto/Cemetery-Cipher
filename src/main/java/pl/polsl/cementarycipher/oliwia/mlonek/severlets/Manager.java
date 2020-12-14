/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.cementarycipher.oliwia.mlonek.severlets;

import java.util.*;
import javax.persistence.*;
import pl.polsl.cementarycipher.oliwia.mlonek.model.HistoryEntity;
import pl.polsl.cementarycipher.oliwia.mlonek.model.OperationsEntity;



public class Manager {
    private  EntityManager em;


    Boolean addRecord(String textToEncode, String output, EntityManager em) {
        try {
             OperationsEntity oe = new OperationsEntity(textToEncode, output);
            em.getTransaction().begin();
            em.persist(oe);
            em.getTransaction().commit();
            HistoryEntity he = new HistoryEntity(oe);
            em.getTransaction().begin();
            em.persist(he);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
              throw e;
        }
       
     }
    
    
    List<HistoryEntity> selectHistory(EntityManager em) {
        try {
       
            List<HistoryEntity> resultList = em.createQuery("Select h from HistoryEntity h ", HistoryEntity.class).getResultList();

            return resultList;
            
        } catch (PersistenceException e) {
             throw e;
        }
        
         
    }
    
    List<OperationsEntity> selectOperations(EntityManager em) {
        try {
       
            List<OperationsEntity> resultList = em.createQuery("Select o from OperationsEntity o ", OperationsEntity.class).getResultList();

            return resultList;
            
        } catch (PersistenceException e) {

              throw e;
        }
        
         
    }
}
