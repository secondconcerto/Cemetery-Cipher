/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.cementarycipher.oliwia.mlonek.severlets;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import pl.polsl.cementarycipher.oliwia.mlonek.model.OperationsEntity;


/**
 *
 * @author tomek
 */
public class OperationCR {
    private EntityManager em = Persistence
            .createEntityManagerFactory("nr1")
            .createEntityManager();
    
    public Optional<OperationsEntity> get(Integer id) {
        return Optional.ofNullable(em.find(OperationsEntity.class, id));
    }
    
    public List<OperationsEntity> get() {
        CriteriaQuery<OperationsEntity> cq = em.getCriteriaBuilder()
                .createQuery(OperationsEntity.class);
        return em.createQuery(cq.select(cq.from(OperationsEntity.class)))
                .getResultList();
    }
    
    public void save(OperationsEntity entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }
    
    public void delete(OperationsEntity entity) {
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit(); 
    }
}
