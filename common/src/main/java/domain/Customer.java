/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 *
 * @author walre888
 */
public class Customer implements Serializable {
        
    @SerializedName("id") //Represented As "id" In Json
    private String id;
    @SerializedName("first_name") //Represented As ... In Json
    private String firstName;
    @SerializedName("last_name") //Represented As ... In Json
    private String lastName;
    @SerializedName("email") //Represented As ... In Json
    private String email;
    @SerializedName("group") //Represented As ... In Json
    private String group;

    public Customer() { //Constructor With No Parameters
    }

    public Customer(String id, String firstName, String lastName, String email) { //Constructor With Certain Parameters
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    
    public Customer(String id, String firstName, String lastName, String email, String group) { //Constructor With Certain Parameters
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.group = group;
    }
    
    //Auto-Generated Methods
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + '}';
    }
              
}
