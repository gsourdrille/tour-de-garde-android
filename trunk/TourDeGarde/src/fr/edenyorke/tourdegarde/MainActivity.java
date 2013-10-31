package fr.edenyorke.tourdegarde;

import java.util.Calendar;
import java.util.List;

import fr.edenyorke.tourdegarde.bean.Constantes;
import fr.edenyorke.tourdegarde.bean.Garde;
import fr.edenyorke.tourdegarde.utils.CollectionUtils;
import fr.edenyorke.tourdegarde.utils.FilesUtils;
import fr.edenyorke.tourdegarde.utils.GardeUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnDateChangedListener, OnValueChangeListener{
	
	
	private DatePicker datePicker;
	private NumberPicker inDayPicker;
	private ImageView image;
	
	private TextView nameGarde;
	
	private int year;
	private int month;
	private int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setCurrentDateOnView();
        
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    	case android.R.id.home:
			onBackPressed();
			return true;
    	case R.id.menu_settings:
    		Intent myIntent = new Intent(MainActivity.this, ListeGardeActivity.class);
    		MainActivity.this.startActivity(myIntent);
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    
    private void initView(){
    	
    	datePicker = (DatePicker) findViewById(R.id.datePicker);
    	datePicker.setSpinnersShown(true);
 		datePicker.setCalendarViewShown(false);
 		
 		inDayPicker = (NumberPicker) findViewById(R.id.inDayPicker);
 		inDayPicker.setMinValue(0);
 		inDayPicker.setMaxValue(1);
 		inDayPicker.setDisplayedValues( new String[] { "AM", "PM" } );
 		inDayPicker.setOnValueChangedListener(this);
 		
 		image = (ImageView) findViewById(R.id.resultImageView);
 		
 		nameGarde = (TextView) findViewById(R.id.nameGarde);
    }
    
 // display current date
 	public void setCurrentDateOnView() {
  
 		final Calendar c = Calendar.getInstance();
 		year = c.get(Calendar.YEAR);
 		month = c.get(Calendar.MONTH);
 		day = c.get(Calendar.DAY_OF_MONTH);
 		// set current date into datepicker
 		datePicker.init(year, month, day, this);
 		
 		//max Date
 		c.add(Calendar.YEAR, 5);
 		datePicker.setMaxDate(c.getTimeInMillis());
 		
 
  
 	}


	@Override
	public void onDateChanged(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		calculerDate();
		
	}
	@Override
	public void onValueChange (NumberPicker np1, int oldVal, int newVal){
		calculerDate();
	}

	private void calculerDate() {
		Calendar dateSelected = Calendar.getInstance();
		dateSelected.set(Calendar.YEAR, datePicker.getYear());
		dateSelected.set(Calendar.MONTH, datePicker.getMonth());
		dateSelected.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
		
		if(inDayPicker.getValue() == 0){
			dateSelected.set(Calendar.HOUR_OF_DAY, 8);
		}else{
			dateSelected.set(Calendar.HOUR_OF_DAY, 18);
		}
		dateSelected.set(Calendar.MINUTE,0);
		dateSelected.set(Calendar.SECOND,0);
		dateSelected.set(Calendar.MILLISECOND,0);
		List<Garde> listeGarde = (List<Garde>) FilesUtils.loadFromSdCard(Constantes.PATH_DATA);
		if(CollectionUtils.isEmpty(listeGarde)){
			//affichage message d'erreur
		}else{
			Garde gardeInDate = null;
			
			//On commence par chercher les garde sans periode
			List<Garde> listeGardeSansPeriode = GardeUtils.getGardeSansPeriode(listeGarde);
			if(!CollectionUtils.isEmpty(listeGardeSansPeriode)){
				gardeInDate = GardeUtils.isDateBeetween(listeGardeSansPeriode, dateSelected);
				if(gardeInDate != null){
					image.setVisibility(View.VISIBLE);
					nameGarde.setVisibility(View.VISIBLE);
					nameGarde.setText(getResources().getString(R.string.name_garde, gardeInDate.getName()));
					if(gardeInDate.isEstPeriodeDeGarde()){
						image.setImageResource(R.drawable.istourdegarde);
					}else{
						image.setImageResource(R.drawable.isnottourdegarde);
					}
					
				}
				
			}
			
			if(gardeInDate == null){
				List<Garde> listeGardeAvecPeriode = GardeUtils.getGardeAvecPeriode(listeGarde);
				if(!CollectionUtils.isEmpty(listeGardeAvecPeriode)){
					gardeInDate = GardeUtils.isDateBeetweenAvecPeriode(listeGardeAvecPeriode, dateSelected);
					if(gardeInDate != null){
						image.setVisibility(View.VISIBLE);
						nameGarde.setVisibility(View.VISIBLE);
						nameGarde.setText(getResources().getString(R.string.name_garde, gardeInDate.getName()));
						if(gardeInDate.isEstPeriodeDeGarde()){
							image.setImageResource(R.drawable.istourdegarde);
						}else{
							image.setImageResource(R.drawable.isnottourdegarde);
						}
						
					}else{
						gardeInDate = GardeUtils.isNotDateBeetweenAvecPeriode(listeGardeAvecPeriode, dateSelected);
						if(gardeInDate != null){
							image.setVisibility(View.VISIBLE);
							nameGarde.setVisibility(View.VISIBLE);
							nameGarde.setText(getResources().getString(R.string.name_garde, gardeInDate.getName()));
							if(gardeInDate.isEstPeriodeDeGarde()){
								image.setImageResource(R.drawable.isnottourdegarde);
							}else{
								image.setImageResource(R.drawable.istourdegarde);
							}
							
						}
					}
				}
			}
			
		}
	}
    
}