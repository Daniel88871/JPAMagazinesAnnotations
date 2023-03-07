package controller;

import model.Campeones;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class CampeonController {

  private Connection connection;
  private EntityManagerFactory entityManagerFactory;

  public CampeonController(Connection connection) {
    this.connection = connection;
  }

  public CampeonController(Connection connection, EntityManagerFactory entityManagerFactory) {
    this.connection = connection;
    this.entityManagerFactory = entityManagerFactory;
  }

  public List<Campeones> readCampeonesFile(String filename) throws IOException {
    int id;
    String nombre, popularidad, porcentaje_de_victorias, porcentaje_de_baneo, kda, pentas_por_partida;
    List<Campeones> campeonesList = new ArrayList<Campeones>();

    BufferedReader br = new BufferedReader(new FileReader(filename));
    String linea = "";
    while ((linea = br.readLine()) != null) {
      StringTokenizer str = new StringTokenizer(linea, ",");
      id = Integer.parseInt(str.nextToken());
      nombre = str.nextToken();
      popularidad = str.nextToken();
      porcentaje_de_victorias = str.nextToken();
      porcentaje_de_baneo = str.nextToken();
      kda = str.nextToken();
      pentas_por_partida = str.nextToken();
      // System.out.println(id + name + country + year + active);
      campeonesList.add(new Campeones(id, nombre, popularidad, porcentaje_de_victorias, porcentaje_de_baneo, kda, pentas_por_partida));

    }
    br.close();

    return campeonesList;
  }

  public void printCampeones(ArrayList<Campeones> campeonesList) {
    for (int i = 0; i < campeonesList.size(); i++) {
      System.out.println(campeonesList.get(i).toString());
    }
  }


  /* Method to CREATE an Autor in the database */
  public void addCampeones(Campeones campeones) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    Campeones campeonExists = (Campeones) em.find(Campeones.class, campeones.getCampeonId());
    if (campeonExists == null ){
      System.out.println("inserta un campeon");
      em.persist(campeones);
    }
    em.getTransaction().commit();
    em.close();
  }


  /* Method to READ all Autors */
  public void listCampeones() {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    List<Campeones> result = em.createQuery("from Campeones", Campeones.class)
        .getResultList();
    for (Campeones campeones : result) {
      System.out.println(campeones.toString());
    }
    em.getTransaction().commit();
    em.close();
  }

  /* Method to UPDATE activity for an author */
  public void updateCampeon(Integer campeonId, boolean active) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    Campeones campeones = (Campeones) em.find(Campeones.class, campeonId);
    campeones.setActive(active);
    em.merge(campeonId);
    em.getTransaction().commit();
    em.close();
  }

  /* Method to DELETE an Author from the records */
  public void deleteCampeon(Integer campeonId) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    Campeones campeones = (Campeones) em.find(Campeones.class, campeonId);
    em.remove(campeones);
    em.getTransaction().commit();
    em.close();
  }
}