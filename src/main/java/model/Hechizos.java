package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Access(AccessType.FIELD)
@Table(name = "hechizos")
public class Hechizos implements Serializable {
  @Id
  @Column(name = "id_hechizo")
  int hechizoId;
  @Column(name = "nombre", length = 30)
  String nombre;
  @Column(name = "popularidad", length = 12)
  String popularidad;
  @Column(name = "porcentaje_de_victoria", length = 4)
  String porcentaje_de_victoria;

  public Hechizos(int hechizoId, String nombre, String popularidad, String porcentaje_de_victoria) {
    super();
    this.porcentaje_de_victoria = porcentaje_de_victoria;
    this.nombre = nombre;
    this.popularidad = popularidad;
    this.hechizoId = hechizoId;
  }

  public Hechizos() {

  }

  public int getHechizoId() {
    return hechizoId;
  }

  public void setHechizoId(int hechizoId) {
    this.hechizoId = hechizoId;
  }

  public String getPorcentaje_de_victoria() {
    return porcentaje_de_victoria;
  }

  public void setPorcentaje_de_victoria(String porcentaje_de_victoria) {
    this.porcentaje_de_victoria = porcentaje_de_victoria;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getPopularidad() {
    return popularidad;
  }

  public void setPopularidad(String popularidad) {
    this.popularidad = popularidad;
  }


  @Override
  public String toString() {
    return "Autor [id_autor=" + hechizoId + ", nom=" + nombre + ", any_naixement=" + porcentaje_de_victoria
        + ", nacionalitat=" + popularidad + "]";
  }
}
