package fr.edenyorke.tourdegarde.bean;

import java.io.Serializable;

public class Garde implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6489107039483952640L;
	
	private String name;
	private String dateDebut;
	private String dateFin;
	private boolean estPeriodeDeGarde;
	private Periode periode;

	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}
	public String getDateFin() {
		return dateFin;
	}
	public void setDateFin(String dateFin) {
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
	
	
	

}
