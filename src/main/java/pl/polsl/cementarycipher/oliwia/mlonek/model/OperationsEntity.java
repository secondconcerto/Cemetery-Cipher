
package pl.polsl.cementarycipher.oliwia.mlonek.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

/**
 * Entity representing data that can be persisted to the database. The data stores all of performed operations.
 *
 * @author Oliwia Mlonek
 * @version 5.0
 */
@Entity
public class OperationsEntity implements Serializable {

     /** Version number,used during deserialization  */
    private static final long serialVersionUID = 1L;
    
    /** ID of the record */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    /** Column that stores userInput */
    @Column(name = "userInput", nullable = false)
    private String userInput;
   
    /** Column that stores userOutput */
    @Lob
    @Column(name = "userOuput", nullable = false, length = 1000)
    private String userOuput;
    
    /** Column that stores key to HistoryEntity (one to one relationship) */
    @OneToOne(mappedBy = "operationsEntity", cascade = CascadeType.ALL,
              fetch = FetchType.EAGER, optional = false)
    private HistoryEntity history;
    
    
    /**
     * Returns user input from certain record.
     *
     * @return a String containing user input
     */
    public String getUserInput() {
        return userInput;
    }

    /**
     * Set user input to a certain record.
     *
     * @param userInput user input to be set
     */
    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Returns coded output from certain record.
     *
     * @return a String containing coded output
     */
    public String getUserOuput() {
        return userOuput;
    }

    /**
     * Set coded output to a certain record.
     *
     * @param userOuput coded output to be set
     */
    public void setUserOuput(String userOuput) {
        this.userOuput = userOuput;
    }

     /**
     * Returns history entity assigned to a certain record.
     *
     * @return a history entity object
     */
    public HistoryEntity getHistoryEntity() {
        return history;
    }

     /**
     * Set history entity to a certain record.
     *
     * @param  historyEntity a history entity to be set
     */
    public void setHistoryEntity(HistoryEntity historyEntity) {
        this.history = historyEntity;
    }
    
    /**
     * Non-parameter constructor of the entity.
     */
    public OperationsEntity()
    {
    }
    
    /**
     * Parameter constructor of the entity.
     * @param input user input to be assigned to this entity 
     * @param output coded output to be assigned to this entity 
     */
    public OperationsEntity(String input, String output)
    {
        this.userInput = input;
        this.userOuput = output;
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
    public void setId(Integer id) {
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
        hash += (id != null ? id.hashCode() : 0);
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
        if (!(object instanceof OperationsEntity)) {
            return false;
        }
        OperationsEntity other = (OperationsEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    
}
