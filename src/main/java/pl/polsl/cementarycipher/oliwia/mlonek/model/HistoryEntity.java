/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.cementarycipher.oliwia.mlonek.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import org.apache.derby.client.am.DateTime;


/**
 *
 * @author roza
 */
@Entity
public class HistoryEntity implements Serializable {
   
   // DateFormat df = new SimpleDateFormat("ddMMyyyyHHmm");
    
    
 
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "dateOf", nullable = false)
    Calendar now = Calendar.getInstance();

    public Calendar getNow() {
        return now;
    }

    public void setNow(Calendar now) {
        this.now = now;
    }


    public OperationsEntity getOperationsEntity() {
        return operationsEntity;
    }

    public void setOperationsEntity(OperationsEntity operationsEntity) {
        this.operationsEntity = operationsEntity;
    }
    
     
    @OneToOne
    @JoinColumn
    private OperationsEntity operationsEntity;
    
    public HistoryEntity() {  
    super();  
      
}  

    
     public HistoryEntity(OperationsEntity operation)
     {
             this.operationsEntity = operation;
     }

    public OperationsEntity getUser() {
        return operationsEntity;
    }

   public void setOperation(OperationsEntity operation) {
        this.operationsEntity = operation;
    }
   


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoryEntity)) {
            return false;
        }
        HistoryEntity other = (HistoryEntity) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

     @Override
    public String toString() {
        return "pl.polsl.cementarycipher.oliwia.mlonek.model.OperationsEntity[ id=" + id + "]";
    }
    
}
