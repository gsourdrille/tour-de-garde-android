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
	private TypePeriode typePeriode;
	private int valeurPeriode;
	
	
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
	public TypePeriode getTypePeriode() {
		return typePeriode;
	}
	public void setTypePeriode(TypePeriode typePeriode) {
		this.typePeriode = typePeriode;
	}
	public int getValeurPeriode() {
		return valeurPeriode;
	}
	public void setValeurPeriode(int valeurPeriode) {
		this.valeurPeriode = valeurPeriode;
	}
	
	
	
	

}
