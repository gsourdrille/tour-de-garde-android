package fr.edenyorke.tourdegarde.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import android.util.Log;

import fr.edenyorke.tourdegarde.bean.Garde;

public class GardeUtils {
	
	   private static final int MILLISECONDE = 1000;

	    private static final int NB_SECONDES_MINUTE = 60;

	    private static final int NB_MINUTES_HEURE = 60;

	    private static final int NB_HEURES_JOUR = 24;
	
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
	
	public final static Garde isDateBeetween(List<Garde> listeGarde,Calendar date){
		Garde gardeInDate  = null;
		Iterator<Garde> iterator = listeGarde.iterator();
		while(iterator.hasNext() && gardeInDate == null){
			Garde garde = iterator.next();
			if((garde.getDateDebut().before(date.getTime()) || garde.getDateDebut().equals(date.getTime())) && (garde.getDateFin().after(date.getTime())  || garde.getDateFin().equals(date.getTime()))){
				gardeInDate = garde;
			}
		}
		return gardeInDate;
	}
	
	public final static Garde isDateBeetweenAvecPeriode(List<Garde> listeGarde, Calendar date){
		Log.d("GardeUtils", "Debut isDateBeetweenAvecPeriode");
		Garde gardeInDate  = null;
		Iterator<Garde> iterator = listeGarde.iterator();
		while(iterator.hasNext() && gardeInDate == null){
			Garde garde = iterator.next();
			//Log.d("GardeUtils", garde.toString());
			Calendar calendarDebut = Calendar.getInstance();
			calendarDebut.setTime(garde.getDateDebut());
			Calendar calendarFin = Calendar.getInstance();
			calendarFin.setTime(garde.getDateFin());
	
			while(calendarFin.before(date) || calendarFin.equals(date)){
				Log.d("GardeUtils", "Avant : "+DateUtils.formatDate(calendarDebut.getTime()) + " - "+DateUtils.formatDate(calendarFin.getTime()) + " - " + DateUtils.formatDate(date.getTime()));
				addXTimeToDate(garde, calendarDebut, calendarFin);
				Log.d("GardeUtils", "Apres : "+DateUtils.formatDate(calendarDebut.getTime()) + " - "+DateUtils.formatDate(calendarFin.getTime()) + " - " + DateUtils.formatDate(date.getTime()));
			}
			
			if((calendarDebut.before(date) || calendarDebut.equals(date)) && (calendarFin.after(date)  || calendarFin.equals(date))){
				Log.d("GardeUtils", "TROUVE");
				gardeInDate = garde;
			}
		}
		Log.d("GardeUtils", "Fin isDateBeetweenAvecPeriode");
		return gardeInDate;
	}
	
	public final static Garde isNotDateBeetweenAvecPeriode(List<Garde> listeGarde, Calendar date){
		Log.d("GardeUtils", "Debut isNotDateBeetweenAvecPeriode");
		Garde gardeInDate  = null;
		Iterator<Garde> iterator = listeGarde.iterator();
		while(iterator.hasNext() && gardeInDate == null){
			Garde garde = iterator.next();
			Calendar calendarDebut = Calendar.getInstance();
			calendarDebut.setTime(garde.getDateDebut());
			Calendar calendarFin = Calendar.getInstance();
			calendarFin.setTime(garde.getDateFin());
	
			while(calendarDebut.before(date) || calendarDebut.equals(date)){
				Log.d("GardeUtils", "Avant : "+DateUtils.formatDate(calendarDebut.getTime()) + " - "+DateUtils.formatDate(calendarFin.getTime()) + " - " + DateUtils.formatDate(date.getTime()));
				addXTimeToDate(garde, calendarDebut, calendarFin);
				Log.d("GardeUtils", "Apres : "+DateUtils.formatDate(calendarDebut.getTime()) + " - "+DateUtils.formatDate(calendarFin.getTime()) + " - " + DateUtils.formatDate(date.getTime()));

			}
			
			if(calendarDebut.after(date)){
				Log.d("GardeUtils", "TROUVE");
				gardeInDate = garde;
			}
		}
		Log.d("GardeUtils", "Fin isNotDateBeetweenAvecPeriode");
		return gardeInDate;
	}
	

	private static void addXTimeToDate(Garde garde, Calendar calendarDebut,
			Calendar calendarFin) {
		switch(garde.getPeriode().getTypePeriode()){
		case WEEK :
			calendarDebut.add(Calendar.DAY_OF_YEAR, 7*garde.getPeriode().getNumberPeriode());
			calendarFin.add(Calendar.DAY_OF_YEAR, 7*garde.getPeriode().getNumberPeriode());
			break;
		case MONTH : 
			calendarDebut.add(Calendar.MONTH, garde.getPeriode().getNumberPeriode()-1);
			calendarFin.add(Calendar.MONTH, garde.getPeriode().getNumberPeriode()-1);
			break;
		case TIMES : 
			int nbJourBeetween = calculerNombreJoursEntre2Dates(calendarDebut,calendarFin);
			calendarDebut.add(Calendar.DAY_OF_YEAR,nbJourBeetween * garde.getPeriode().getNumberPeriode());
			calendarFin.add(Calendar.DAY_OF_YEAR, nbJourBeetween * garde.getPeriode().getNumberPeriode());
			break;
		}
	}
	
	/**
     * Calcule le nombre jours entre 2 dates.
     * @param dateDebut
     * @param dateFin
     * @return
     */
    public static int calculerNombreJoursEntre2Dates(final Calendar pDateDebut, final Calendar pDateFin) {
        Long nbJours = Long.valueOf(0);
        if (pDateDebut != null && pDateFin != null) {
            double ms = pDateFin.getTimeInMillis() - pDateDebut.getTimeInMillis();
            nbJours = Math.round(ms / (NB_HEURES_JOUR * NB_SECONDES_MINUTE * NB_MINUTES_HEURE * MILLISECONDE));
        }
        return nbJours.intValue();
    }

}
