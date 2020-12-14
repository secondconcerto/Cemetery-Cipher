/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author roza
 */
@Entity
public class OperationsEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name = "userInput", nullable = false)
    public String userInput;
   
    @Lob
    @Column(name = "userOuput", nullable = false, length = 1000)
    private String userOuput;
    
    @OneToOne(mappedBy = "operationsEntity", cascade = CascadeType.ALL,
              optional = false)
    private HistoryEntity history;
    
    

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public String getUserOuput() {
        return userOuput;
    }

    public void setUserOuput(String userOuput) {
        this.userOuput = userOuput;
    }

    public HistoryEntity getHistoryEntity() {
        return history;
    }

    public void setHistoryEntity(HistoryEntity historyEntity) {
        this.history = historyEntity;
    }
    
    public OperationsEntity()
    {
    }
    
    public OperationsEntity(String input, String output)
    {
        this.userInput = input;
        this.userOuput = output;
    }
    
    

    public Integer getId() {
        return id.intValue();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

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

//    @Override
//    public String toString() {
//        return "pl.polsl.cementarycipher.oliwia.mlonek.model.OperationsEntity[ id=" + id + userInput + userOuput + "]";
//    }
    
}
