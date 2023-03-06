package controller;

import model.Objetos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MagazineController {

  private Connection connection;
  private EntityManagerFactory entityManagerFactory;

  public MagazineController(Connection connection) {
    this.connection = connection;
  }

  public MagazineController(Connection connection, EntityManagerFactory entityManagerFactory) {
    this.connection = connection;
    this.entityManagerFactory = entityManagerFactory;
  }

  /**
   * @param filename Aquest String correspon amb l'arxiu on s'emmagatzemen les
   *                 dades de les instancies de Revista
   * @throws IOException <dt><b>Preconditions:</b>
   *                     <dd>
   *                     filename<>nil </br> llistaRevistes == nil
   *                     <dt><b>Postconditions:</b>
   *                     <dd>
   *                     llistaRevistes<>nil
   */

  public List<Objetos> readMagazinesFile(String filename) throws IOException {
    int magazineId;
    String title;
    Date publicationDate;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
    List<Objetos> magazinesList = new ArrayList();

    BufferedReader br = new BufferedReader(new FileReader(filename));
    String linea = "";
    while ((linea = br.readLine()) != null) {
      StringTokenizer str = new StringTokenizer(linea, ",");
      magazineId = Integer.parseInt(str.nextToken());
      title = str.nextToken();

      try {
        publicationDate = dateFormat.parse(str.nextToken());
        magazinesList.add(new Objetos(magazineId, title, publicationDate));

      } catch (ParseException e) {
        System.err.println("Errada format data al fitxer");
        e.printStackTrace();
      }

    }
    br.close();
    return magazinesList;
  }

  public void printMagazines(ArrayList<Objetos> magazinesList) {
    for (int i = 0; i < magazinesList.size(); i++) {
      System.out.println(magazinesList.get(i).toString());
    }
  }

  /* Method to CREATE a Magazine  in the database */
  public void addMagazine(Objetos objetos) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    em.merge(objetos);

    em.getTransaction().commit();
    em.close();
  }

  /* Method to READ all Magazines */
  public void listMagazines() {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    List<Objetos> result = em.createQuery("from Magazine", Objetos.class)
        .getResultList();



    for (Objetos objetos : result) {
      System.out.println(objetos.toString());
    }
    em.getTransaction().commit();
    em.close();
  }

  /* Method to UPDATE activity for an Magazine */
  public void updateMagazine(Integer magazineId) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    Objetos objetos = (Objetos) em.find(Objetos.class, magazineId);
    em.merge(objetos);
    em.getTransaction().commit();
    em.close();
  }

  /* Method to DELETE an Magazine from the records */
  public void deleteAutor(Integer magazineId) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    Objetos objetos = (Objetos) em.find(Objetos.class, magazineId);
    em.remove(objetos);
    em.getTransaction().commit();
    em.close();
  }
}
