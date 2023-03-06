package controller;

import model.Campeones;
import model.Hechizos;
import model.Objetos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ArticleController {

  private Connection connection;
  private EntityManagerFactory entityManagerFactory;

  private MagazineController magazineController = new MagazineController(connection);
  private AuthorController authorController = new AuthorController(connection);

  public ArticleController(Connection connection) {
    this.connection = connection;
  }

  public ArticleController(Connection connection, EntityManagerFactory entityManagerFactory) {
    this.connection = connection;
    this.entityManagerFactory = entityManagerFactory;
  }

  /**
   * @param articlesFile Aquest String correspon amb l'arxiu on s'emmagatzemen les
   *                     dades de les isntancies de Revista
   * @return ArrayList d'objectes Revista, amb els seus articles i la
   * informació de l'autor
   * @throws IOException <dt><b>Preconditions:</b>
   *                     <dd>
   *                     filename<>nil </br> llistaRevistes<>nil </br>
   *                     llistaRevistes.getRevista(i).getArticles()== nil</br>
   *                     <dt><b>Postconditions:</b>
   *                     <dd>
   *                     llistaRevistes.getRevistes()<>nil</br>
   *                     llistaRevistes.getRevista(i).getArticles()<>nil</br>
   *                     llistaRevistes.getRevista(i).getArticle(j)<>nil</br>
   *                     llistaRevistes
   *                     .getRevista(i).getArticle(j).getAutor()<>nil</br>
   */
  public List<Objetos> readArticlesFile(String articlesFile, String magazinesFile, String authorsFile)
      throws IOException {
    int articleId, magazineId, authorId;
    String title;
    Date creationDate;
    boolean publishable;
    DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");

    BufferedReader br = new BufferedReader(new FileReader(articlesFile));
    String linea = "";

    List<Objetos> magazinesList = magazineController.readMagazinesFile(magazinesFile);
    List<Hechizos> hechizosList = authorController.readAuthorsFile(authorsFile);

    while ((linea = br.readLine()) != null) {
      StringTokenizer str = new StringTokenizer(linea, ",");
      articleId = Integer.parseInt(str.nextToken());
      magazineId = Integer.parseInt(str.nextToken());
      authorId = Integer.parseInt(str.nextToken());
      title = str.nextToken();

      try {
        creationDate = dateFormat.parse(str.nextToken());
        publishable = Boolean.parseBoolean(str.nextToken());

        magazinesList.get(magazineId - 1).addArticle(new Campeones(articleId, title, creationDate, publishable, hechizosList.get(authorId - 1)));

      } catch (ParseException e) {

        e.printStackTrace();
      }

    }
    br.close();

    return magazinesList;
  }

  public List<Campeones>  readArticlesFile(String articlesFile, String authorsFile) throws IOException {
    int articleId, magazineId, authorId;
    String title;
    Date creationDate;
    boolean publishable;
    DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");

    BufferedReader br = new BufferedReader(new FileReader(articlesFile));
    String linea = "";
    List<Hechizos> hechizosList = authorController.readAuthorsFile(authorsFile);
    List<Campeones> articlesList = new ArrayList<Campeones>();

    while ((linea = br.readLine()) != null) {
      StringTokenizer str = new StringTokenizer(linea, ",");
      articleId = Integer.parseInt(str.nextToken());
      magazineId = Integer.parseInt(str.nextToken());
      authorId = Integer.parseInt(str.nextToken());
      title = str.nextToken();

      try {
        creationDate = dateFormat.parse(str.nextToken());
        publishable = Boolean.parseBoolean(str.nextToken());

        articlesList.add(new Campeones(articleId, title, creationDate, publishable, hechizosList.get(authorId - 1)));

      } catch (ParseException e) {

        e.printStackTrace();
      }

    }
    br.close();
    return articlesList;

  }

  /* Method to CREATE an Article in the database */
  public void addArticle(Campeones campeones) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    em.merge(campeones);
    em.getTransaction().commit();
    em.close();
  }

  /* Method to READ all Articles */
  public void listArticles() {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    List<Campeones> result = em.createQuery("from Article", Campeones.class)
        .getResultList();
    for (Campeones campeones : result) {
      System.out.println(campeones.toString());
    }
    em.getTransaction().commit();
    em.close();
  }

  /* Method to UPDATE activity for an Article */
  public void updateArticle(Integer articleId) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    Campeones campeones = (Campeones) em.find(Campeones.class, articleId);
    em.merge(campeones);
    em.getTransaction().commit();
    em.close();
  }

  /* Method to DELETE an Article from the records */
  public void deleteArticle(Integer articleId) {
    EntityManager em = entityManagerFactory.createEntityManager();
    em.getTransaction().begin();
    Campeones campeones = (Campeones) em.find(Campeones.class, articleId);
    em.remove(campeones);
    em.getTransaction().commit();
    em.close();
  }


}
