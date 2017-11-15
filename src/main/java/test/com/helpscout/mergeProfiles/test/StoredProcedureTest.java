package com.helpscout.mergeProfiles.test;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.helpscout.mergeProfiles.model.User;
import com.helpscout.mergeProfiles.repository.UserRepository;
import com.helpscout.mergeProfiles.repository.UserRepositoryImpl;
import junit.framework.Assert;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StoredProcedureTest {



  @Autowired
  private UserRepositoryImpl userRepository;

  @Autowired
  private UserRepository userRepo;

  @Before
  public void setup() {
    User u = new User();
    u.setFirstName("Egon");
    u.setLastname("Spengler");
    userRepo.save(u);
    u = new User();
    u.setFirstName("Egon");
    userRepo.save(u);
  }

  @After
  public void teardown() throws Exception {
    userRepo.deleteAll();
  }

  @Test
  public void testWithRepository() {
    List<Object[]> users = userRepository.duplicates("Egon", "Spengler");
    Assert.assertEquals(2, users.size());
    // Assert.assertEquals("HelloKoding", ((User)users.get(0)).getName());
  }

  @Test
  public void testWithRepositoryFirstname() {
    List<Object[]> users = userRepository.duplicates("Egon","");
    Object[] o = users.get(0);
    // User u = new User();
    Assert.assertEquals("Egon", o[3]);
  }

  @Test
  public void testWithRepositoryFirstnameNullLastName() {
    List<Object[]> users = userRepository.duplicates("Egon", "Spengler");
    Object[] o = users.get(0);
    // User u = new User();
    Assert.assertNull(o[4]);
  }

  @Test
  public void testWithRepositoryFirstnameLastName() {
    List<Object[]> users = userRepository.duplicates("Egon", "Spengler");
    Object[] o = users.get(1);
    // User u = new User();
    Assert.assertEquals("Spengler",o[4]);
  }
  
}

