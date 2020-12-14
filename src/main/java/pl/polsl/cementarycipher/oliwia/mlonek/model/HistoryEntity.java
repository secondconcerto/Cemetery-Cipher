
package pl.polsl.cementarycipher.oliwia.mlonek.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


/**
 * Entity representing data that can be persisted to the database. The data stores history of operations.
 *
 * @author Oliwia Mlonek
 * @version 5.0
 */
@Entity
public class HistoryEntity implements Serializable {
   
    /** Version number,used during deserialization  */
    private static final long serialVersionUID = 1L;
     
    /** ID of the record */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

     /** Column that stores exact date of performed operation */
    @Column(name = "dateOf", nullable = false)
    private Date date = java.util.Calendar.getInstance().getTime();  
    
    /** Column that stores key to OperationsEntity (one to one relationship) */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private OperationsEntity operationsEntity;
    
     /**
     * Non-parameter constructor of the entity.
     */
    public HistoryEntity() {  
    super();  
    }  
    
     /**
     * Parameter constructor of the entity.
     * @param operation to be set
     */
     public HistoryEntity(OperationsEntity operation)
     {
             this.operationsEntity = operation;
     }

    /**
     * Returns date of an operation.
     *
     * @return a date of operation
     */
    public Date getNow() {
        return date;
    }

     /**
     * Set date of operation to a certain record.
     *
     * @param now date to be set
     */
    public void setNow(Date now) {
        this.date = now;
    }


     /**
     * Returns operations entity assigned to a certain record.
     *
     * @return a operations entity object
     */
    public OperationsEntity getOperationsEntity() {
        return operationsEntity;
    }

    
    /**
     * Set operations entity to a certain record.
     *
     * @param operationsEntity a operations entity to be set
     */
    public void setOperationsEntity(OperationsEntity operationsEntity) {
        this.operationsEntity = operationsEntity;
    }
    
    
    
    /**
     * Returns ID assigned to a certain record.
     *
     * @return a numercial value of ID
     */
     public Integer getId() {
        return id.intValue();
    }

     /**
     * Sets ID to a certain record.
     *
     * @param id an ID to be set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns hashcode of a given entity.
     *
     * @return a hashcode
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    /**
     * Compares two object of entities.
     *
     * @param object object to be comapred
     * @return bollean value, based on the result of comparision
     */
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

    
}
