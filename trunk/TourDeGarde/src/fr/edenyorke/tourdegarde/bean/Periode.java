package fr.edenyorke.tourdegarde.bean;


public class Periode {
	
	private TypePeriode typePeriode;
	
	private int numberPeriode;
	
	

	public Periode() {
		this.typePeriode = TypePeriode.WEEK;
		this.numberPeriode = 2;
	}

	public TypePeriode getTypePeriode() {
		return typePeriode;
	}

	public void setTypePeriode(TypePeriode typePeriode) {
		this.typePeriode = typePeriode;
	}

	public int getNumberPeriode() {
		return numberPeriode;
	}

	public void setNumberPeriode(int numberPeriode) {
		this.numberPeriode = numberPeriode;
	}
	
	
	

}
