package fr.edenyorke.tourdegarde.bean;

import java.io.Serializable;
import java.util.Date;

public class Garde implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6489107039483952640L;
	
	private String id;
	private String name;
	private Date dateDebut;
	private Date dateFin;
	private boolean estPeriodeDeGarde;
	private Periode periode;

	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public boolean isEstPeriodeDeGarde() {
		return estPeriodeDeGarde;
	}
	public void setEstPeriodeDeGarde(boolean estPeriodeDeGarde) {
		this.estPeriodeDeGarde = estPeriodeDeGarde;
	}
	public Periode getPeriode() {
		return periode;
	}
	public void setPeriode(Periode periode) {
		this.periode = periode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Garde [id=" + id + ", name=" + name + ", dateDebut="
				+ dateDebut + ", dateFin=" + dateFin + ", estPeriodeDeGarde="
				+ estPeriodeDeGarde + ", periode=" + periode + "]";
	}
	
	
	
	
	
	
	

}
