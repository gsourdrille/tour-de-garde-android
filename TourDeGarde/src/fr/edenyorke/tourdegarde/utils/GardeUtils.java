package fr.edenyorke.tourdegarde.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import fr.edenyorke.tourdegarde.bean.Garde;

public class GardeUtils {
	
	public final static List<Garde> getGardeSansPeriode(List<Garde> listeGardeTotal){
		List<Garde> listeGardeSansPeriode = new ArrayList<Garde>();
		for(Garde garde : listeGardeTotal){
			if(garde.getPeriode() == null){
				listeGardeSansPeriode.add(garde);
			}
		}
		return listeGardeSansPeriode;
	}
	
	public final static List<Garde> getGardeAvecPeriode(List<Garde> listeGardeTotal){
		List<Garde> listeGardeAvecPeriode = new ArrayList<Garde>();
		for(Garde garde : listeGardeTotal){
			if(garde.getPeriode() != null){
				listeGardeAvecPeriode.add(garde);
			}
		}
		return listeGardeAvecPeriode;
	}
	
	public final static Garde isDateBeetween(List<Garde> listeGarde, Date date){
		Garde gardeInDate  = null;
		Iterator<Garde> iterator = listeGarde.iterator();
		while(iterator.hasNext() && gardeInDate == null){
			Garde garde = iterator.next();
			if((garde.getDateDebut().before(date) || garde.getDateDebut().equals(date)) && (garde.getDateFin().after(date)  || garde.getDateFin().equals(date))){
				gardeInDate = garde;
			}
		}
		return gardeInDate;
	}

}
