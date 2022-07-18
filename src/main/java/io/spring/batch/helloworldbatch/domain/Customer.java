package io.spring.batch.helloworldbatch.domain;

import javax.persistence.*;
import java.io.Serializable;
//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlElementWrapper;
//import javax.xml.bind.annotation.XmlRootElement;
//import java.util.List;

//@XmlRootElement
@Entity
@Table(name = "Customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "middleInitial")
    private String middleInitial;

    @Column(name = "lastName")
    private String lastName;

    private String address;
    private String city;
    private String state;
    private String zipCode;

    public Customer(Long id, String firstName, String middleInitial, String lastName, String address, String city, String state, String zipCode) {
        this.id = id;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    //    private List<Transaction> transactions;

//    public List<Transaction> getTransactions() {
//        return transactions;
//    }

//    @XmlElementWrapper(name = "transactions")
//    @XmlElement(name = "transaction")
//    public void setTransactions(List<Transaction> transactions) {
//        this.transactions = transactions;
//    }

    public Customer(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleInitial='" + middleInitial + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }

    //    @Override
//    public String toString() {
//        StringBuilder output = new StringBuilder();
//
//        output.append(firstName);
//        output.append(" ");
//        output.append(middleInitial);
//        output.append(". ");
//        output.append(lastName);
//
//        if(transactions != null && transactions.size() > 0) {
//            output.append(" has ");
//            output.append(transactions.size());
//            output.append(" transactions.");
//        } else {
//            output.append(" has no transactions.");
//        }
//
//        return output.toString();
//    }
}
