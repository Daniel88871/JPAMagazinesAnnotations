package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Access(AccessType.FIELD)
@Table(name = "objetos")
public class Objetos implements Serializable {
  @Id
  @Column(name = "id_objeto")
  private String objetoId;
  @Column(name = "popularidad", length = 30)
  private String popularidad;
  @Column(name = "porcentaje_de_victoria")
  private String porcentajedeVictoria;

  public Objetos(String objetoId, String popularidad, String porcentajedeVictoria) {
    super();
    this.popularidad = popularidad;
    this.porcentajedeVictoria = porcentajedeVictoria;
    this.objetoId = objetoId;
  }

  public Objetos() {
    super();
  }

  public String getPopularidad() {
    return popularidad;
  }

  public void setPopularidad(String popularidad) {
    this.popularidad = popularidad;
  }

  public Date getPorcentajedeVictoria() {
    return porcentajedeVictoria;
  }

  public void setPorcentajedeVictoria(Date porcentajedeVictoria) {
    this.porcentajedeVictoria = porcentajedeVictoria;
  }

  public int getObjetoId() {
    return objetoId;
  }

  public void setObjetoId(int objetoId) {
    this.objetoId = objetoId;
  }

  public void addArticle(Campeones art) {
    campeones.add(art);
  }

  public Campeones getArticle(int i) {
    return campeones.get(i);
  }

  public List<Campeones> getArticles() {
    return campeones;
  }

  public void setArticles(List<Campeones> campeones) {
    this.campeones = campeones;
  }


  @Override
  public String toString() {
    String result = "Revista [id_revista=" + objetoId + ",titol=" + popularidad + ", data_publicacio="
        + porcentajedeVictoria.toString() + "]";

    result += "\n Llista d'articles: [ \n";

    for (Campeones a : campeones) {
      result += "\t";
      result += a.toString();
      result += "\n";
    }

    result += "] \n";

    return result;
  }

}
