/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.cementarycipher.oliwia.mlonek.severlets;

import java.util.*;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import pl.polsl.cementarycipher.oliwia.mlonek.model.HistoryEntity;
import pl.polsl.cementarycipher.oliwia.mlonek.model.OperationsEntity;



public class Manager {
    private  EntityManager em;


    void addRecord(String textToEncode, String output, EntityManager em) {
        try {
             OperationsEntity oe = new OperationsEntity(textToEncode, output);
            em.getTransaction().begin();
            em.persist(oe);
            em.getTransaction().commit();
            HistoryEntity he = new HistoryEntity(oe);
            em.getTransaction().begin();
            em.persist(he);
            em.getTransaction().commit();
            
        } catch (Exception e) {
        }
       
     }
}
