
package tech.kennet.bankingsql.customer;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import tech.kennet.bankingsql.account.Account;


@Entity
@Table(name = "customers")
public class Customer {
    
    @Id
    @Column(name = "customerId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;
    private LocalDate dob;
    
    @Transient
    private Integer age;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable( 
        joinColumns = {@JoinColumn(name = "customerId")} ,
        inverseJoinColumns = {@JoinColumn(name = "accountId")}
    )
    @JsonManagedReference
    private Set<Account> accounts = new HashSet<Account>();
    
    public Customer(Set<Account> accounts, String name, String email, LocalDate dob) {
        this.accounts = accounts;
        this.name = name;
        this.email = email;
        this.dob = dob;
        
    }

    public Customer(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        
    }

    public Customer(Long id, String name, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        
    }

    public Customer() {
    }

    public Customer(Long id, String name, String email, LocalDate dob, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.age = age;
        
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

/*   @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", dob=" + dob + ", age=" + age +  ", account=" + getAccounts().toString() + "]";
    }
*/
}
