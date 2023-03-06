package controller;

import model.Hechizos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class AuthorController {

  private Connection connection;
  private EntityManagerFactory entityManagerFactory;

  public AuthorController(Connection connection) {
    this.connection = connection;
  }

  public AuthorController(Connection connection, EntityManagerFactory entityManagerFactory) {
    this.connection = connection;
    this.entityManagerFactory = entityManagerFactory;
  }

  public List<Hechizos> readAuthorsFile(String filename) throws IOException {
    int id;
    String name, year, country;
    boolean active;
    List<Hechizos> authorsList = new ArrayList<Hechizos>();

    BufferedReader br = new BufferedReader(new FileReader(filename));
    String linea = "";
    while ((linea = br.readLine()) != null) {
      StringTokenizer str = new StringTokenizer(linea, ",");
      id = Integer.parseInt(str.nextToken());
      name = str.nextToken();
      year = str.nextToken();
      country = str.nextToken();
      active = Boolean.parseBoolean(str.nextToken());
      // System.out.println(id + name + country + year + active);
      authorsList.add(new Hechizos(id, name, country, year, active));

    }
    br.close();

    return authorsList;
  }

  public void printAutors(ArrayList<Hechizos> authorsList) {
    for (int i = 0; i < authorsList.size(); i++) {
      System.out.println(authorsList.get(i).toString());
    }
  }


  /* Method to CREATE an Autor in the database */
  public void addAuthor(Hechizos hechizos) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    Hechizos authotExists = (Hechizos) em.find(Hechizos.class, hechizos.getHechizoId());
    if (authotExists == null ){
      System.out.println("insert autor");
      em.persist(hechizos);
    }
    em.getTransaction().commit();
    em.close();
  }


  /* Method to READ all Autors */
  public void listAuthors() {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    List<Hechizos> result = em.createQuery("from Author", Hechizos.class)
        .getResultList();
    for (Hechizos hechizos : result) {
      System.out.println(hechizos.toString());
    }
    em.getTransaction().commit();
    em.close();
  }

  /* Method to UPDATE activity for an author */
  public void updateAutor(Integer authorId, boolean active) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    Hechizos hechizos = (Hechizos) em.find(Hechizos.class, authorId);
    hechizos.setActive(active);
    em.merge(hechizos);
    em.getTransaction().commit();
    em.close();
  }

  /* Method to DELETE an Author from the records */
  public void deleteAuthor(Integer authorId) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    Hechizos hechizos = (Hechizos) em.find(Hechizos.class, authorId);
    em.remove(hechizos);
    em.getTransaction().commit();
    em.close();
  }

}
