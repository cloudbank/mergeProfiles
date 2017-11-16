package com.helpscout.mergeProfiles.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {
  
  @PersistenceContext
  private EntityManager entityManager;

  public List<Object[]> duplicates(String fname, String lname){
     StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("duplicates");
     
     // Set the parameters of the stored procedure.
     String firstParam = "firstParam";
     String secondParam = "secondParam";
     storedProcedure.registerStoredProcedureParameter(firstParam, String.class, ParameterMode.IN);
     storedProcedure.setParameter(firstParam, fname);
     storedProcedure.registerStoredProcedureParameter(secondParam, String.class, ParameterMode.IN);
     storedProcedure.setParameter(secondParam, lname);

     // Call the stored procedure. 
     
     List<Object[]> storedProcedureResults = storedProcedure.getResultList();
     return  (List<Object[]>) storedProcedureResults;

  }

}
