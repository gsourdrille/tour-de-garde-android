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
import fr.edenyorke.tourdegarde.dialog.CustomDatePickerDialog;
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
			 new DatePickerDialog(SettingsActivity.this, dateDebutListener, dateDebutCalendar
	                    .get(Calendar.YEAR), dateDebutCalendar.get(Calendar.MONTH),
	                    dateDebutCalendar.get(Calendar.DAY_OF_MONTH)).show();
		}else if(v == dateFinBouton){
			CustomDatePickerDialog dialog = new CustomDatePickerDialog(this) {
				@Override
				public void onClick(View v) {
					 int day = getDatePicker().getDayOfMonth();
					 int month = getDatePicker().getMonth() ;
					 int year = getDatePicker().getYear();
					 dateDebutCalendar.set(Calendar.DAY_OF_MONTH, day);
					 dateDebutCalendar.set(Calendar.MONTH, month);
					 dateDebutCalendar.set(Calendar.YEAR, year);
					 Date result = dateDebutCalendar.getTime();
					 String resultString = DateUtils.formatDate(result);
					 dateFinValue.setText(resultString);
					 dismiss();
				}
			};
			dialog.show();
			
		}else if(v == periodeBouton){
			
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
		}
		
		
		
	}

}
