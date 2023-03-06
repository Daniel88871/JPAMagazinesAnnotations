package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Access(AccessType.FIELD)
@Table(name = "campeones")
public class Campeones implements Serializable {
  @Id
  @Column(name = "id_campeon")
  String campeonId;
  @Column(name = "nombre", length = 30)
  String nombre;
  @Column(name = "popularidad")
  String popularidad;
  @Column(name = "porcentaje_de_victoria")
  String porcentajedeVictoria;

  @Column(name = "porcentaje_de_baneo")
  String porcentajedeBaneo;

  @Column(name = "kda")
  String Kda;

  @Column(name = "pentas_por_partida")
  String pentasporPartida;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "id_campeon")
  public Hechizos hechizos;

  public Campeones(String campeonId, String nombre, String popularidad, String porcentajedeVictoria, String porcentajedeBaneo, String Kda, String pentasporPartida) {
    super();
    this.campeonId = campeonId;
    this.nombre = nombre;
    this.popularidad = popularidad;
    this.porcentajedeVictoria = porcentajedeVictoria;
    this.porcentajedeBaneo = porcentajedeBaneo;
    this.Kda = Kda;
    this.pentasporPartida = pentasporPartida;
    this.hechizos = hechizos;
  }

  public Campeones() {
    super();

  }

  public String getCampeonId() {
    return campeonId;
  }

  public void setCampeonId(String campeonId) {
    this.campeonId = campeonId;
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

  public String getPorcentajedeVictoria() {return porcentajedeVictoria;}

  public void setPorcentajedeVictoria(String porcentajedeVictoria) {
    this.porcentajedeVictoria = porcentajedeVictoria;
  }

  public String getPorcentajedeBaneo() {
    return porcentajedeBaneo;
  }

  public void setPorcentajedeBaneo(String porcentajedeBaneo) {this.porcentajedeBaneo = porcentajedeBaneo;}
  public String getKda() {
    return Kda;
  }

  public void setKda(String kda) {
    this.Kda = Kda;
  }
  public String getPentasporPartida() {
    return pentasporPartida;
  }

  public void setPentasporPartida(String pentasporPartida) {
    this.pentasporPartida = pentasporPartida;
  }

  @Override
  public String toString() {
    return "Campeones{" +
        "campeonId=" + campeonId +
        ", nombre='" + nombre + '\'' +
        ", popularidad=" + popularidad +
        ", porcentaje de victoria=" + porcentajedeVictoria +
        ", porcentaje de baneo=" + porcentajedeBaneo +
            ", kda=" + Kda +
            ", pentas por partida=" + pentasporPartida +
        '}';
  }
}
