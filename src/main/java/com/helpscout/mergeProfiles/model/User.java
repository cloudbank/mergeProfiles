package com.helpscout.mergeProfiles.model;

import javax.persistence.*;
import org.springframework.data.repository.query.Param;

@Entity
@Table(name = "user")
@NamedStoredProcedureQueries({@NamedStoredProcedureQuery(name = "duplicates",
    procedureName = "duplicates", resultClasses = {User.class},
    parameters = {
        @StoredProcedureParameter(name = "fname", type = String.class, mode = ParameterMode.IN),
        @StoredProcedureParameter(name = "lname", type = String.class, mode = ParameterMode.IN),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "outParam1",
            type = User.class)})})

public class User {
  private int id;
  private String lastname;
  private String email;
  private String address;
  private String firstname;
  private String phone;

  public User() {

  }

  @Column(name = "lastname")
  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  @Column(name = "firstname")
  public String getFirstName() {
    return firstname;
  }

  public void setFirstName(String firstname) {
    this.firstname = firstname;
  }

  // @Column(name = "EMAIL", unique = true, nullable = false, scale = 0)
  @Column(name = "EMAIL")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID", unique = true, nullable = false, scale = 0)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

 


}
