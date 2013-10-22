package fr.edenyorke.tourdegarde;

import java.util.Calendar;
import java.util.Date;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import fr.edenyorke.tourdegarde.bean.Constantes;
import fr.edenyorke.tourdegarde.bean.Garde;
import fr.edenyorke.tourdegarde.bean.Periode;
import fr.edenyorke.tourdegarde.dialog.CustomDatePickerDialog;
import fr.edenyorke.tourdegarde.dialog.CustomPeriodePickerDialog;
import fr.edenyorke.tourdegarde.utils.DateUtils;
import fr.edenyorke.tourdegarde.utils.FilesUtils;

public class SettingsActivity extends Activity implements OnClickListener{
	
	private LinearLayout dateDebutBouton;
	private LinearLayout dateFinBouton;
	private LinearLayout periodeBouton;
	private LinearLayout estPeriodeBouton;
	private TextView dateDebutValue;
	private TextView dateFinValue;
	private TextView periodeValue;
	private TextView estPeriodeValue;
	private Calendar dateDebutCalendar;
	private Calendar dateFinCalendar;
	private Periode periode;
	
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.settings);

        // Get IHM Components
        initScreenComponent();
        
        //Init value
        initValues();
        
        // Add listener
        initListener();
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
    		ActionBar actionBar = getActionBar();
    		actionBar.setDisplayHomeAsUpEnabled(true);
    	}
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    	case android.R.id.home:
			onBackPressed();
			return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }
    
    /**
     * Init ihm component
     */
    private void initScreenComponent() {
    	dateDebutBouton = (LinearLayout)findViewById(R.id.dateDebutBouton);
    	dateFinBouton = (LinearLayout)findViewById(R.id.dateFinBouton);
    	periodeBouton = (LinearLayout)findViewById(R.id.periodeBouton);
    	estPeriodeBouton = (LinearLayout)findViewById(R.id.estPeriodeBouton);
    	dateDebutValue = (TextView) findViewById(R.id.dateDebutValue);
    	dateFinValue = (TextView) findViewById(R.id.dateFinValue);
    }
    
    /**
     * Init listener
     */
    private void initListener() {
    	dateDebutBouton.setOnClickListener(this);
    	dateFinBouton.setOnClickListener(this);
    	periodeBouton.setOnClickListener(this);
    	estPeriodeBouton.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		if(v == dateDebutBouton) {
			CustomDatePickerDialog customDatePickerDialog = new CustomDatePickerDialog(this, dateDebutCalendar) {
				@Override
				public void getValue() {
					dateDebutCalendar = getCalendar();
					updateDateDebut();
					dismiss();
				}
			};
			customDatePickerDialog.show();
		}else if(v == dateFinBouton){
			CustomDatePickerDialog customDatePickerDialog = new CustomDatePickerDialog(this, dateFinCalendar) {
				@Override
				public void getValue() {
					dateFinCalendar = getCalendar();
					updateDateFin();
					dismiss();
				}
			};
			customDatePickerDialog.show();
			
		}else if(v == periodeBouton){
			CustomPeriodePickerDialog customPeriodePickerDialog = new CustomPeriodePickerDialog(this, periode) {
				@Override
				public void getValue() {
					periode = getPeriode();
					updatePeriode();
					dismiss();
				}
			};
			customPeriodePickerDialog.show();
		}else if(v == estPeriodeBouton){
			
		}
		
	}
	
	DatePickerDialog.OnDateSetListener dateDebutListener = new DatePickerDialog.OnDateSetListener() {

	    @Override
	    public void onDateSet(DatePicker view, int year, int monthOfYear,
	            int dayOfMonth) {
	        // TODO Auto-generated method stub
	    	dateDebutCalendar.set(Calendar.YEAR, year);
	    	dateDebutCalendar.set(Calendar.MONTH, monthOfYear);
	    	dateDebutCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
	    }

	};
	
	private void initValues(){
		
		Garde garde = (Garde) FilesUtils.loadFromSdCard(Constantes.PATH_DATA);
		dateDebutCalendar = Calendar.getInstance();
		dateFinCalendar = Calendar.getInstance();
		if(garde == null){
			garde = new Garde();
		}else{
			dateDebutCalendar.setTime(DateUtils.parseDate( garde.getDateDebut()));
			dateFinCalendar.setTime(DateUtils.parseDate( garde.getDateFin()));
			periode = garde.getPeriode();
		}
		
		
		
	}
	
	private void updateValues(){
		updateDateDebut();
		updateDateFin();
		updatePeriode();
 
	}
	
	private void updateDateDebut(){
		 Date dateDebut = dateDebutCalendar.getTime();
		 String dateDebutString = DateUtils.formatDate(dateDebut);
		 dateDebutValue.setText(dateDebutString);
	}
	
	private void updateDateFin(){
		Date dateFin = dateFinCalendar.getTime();
		 String dateFinString = DateUtils.formatDate(dateFin);
		 dateFinValue.setText(dateFinString);
	}
	
	private void updatePeriode(){
		String typePeriode = "";
		switch(periode.getTypePeriode()){
		case WEEK:
			typePeriode = "1 semaine sur";
			break;
		case MONTH:
			typePeriode = "1 mois sur";
			break;
		case TIMES:
			typePeriode = "1 fois sur";
			break;
		}
		String title = typePeriode + " " + String.valueOf(periode.getNumberPeriode()) ;
		periodeValue.setText(title);
	}

}
