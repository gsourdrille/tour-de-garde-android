package fr.edenyorke.tourdegarde;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import fr.edenyorke.tourdegarde.bean.Constantes;
import fr.edenyorke.tourdegarde.bean.Garde;
import fr.edenyorke.tourdegarde.bean.Periode;
import fr.edenyorke.tourdegarde.dialog.CustomDatePickerDialog;
import fr.edenyorke.tourdegarde.dialog.CustomPeriodePickerDialog;
import fr.edenyorke.tourdegarde.utils.DateUtils;
import fr.edenyorke.tourdegarde.utils.FilesUtils;
import fr.edenyorke.tourdegarde.utils.StringUtils;

public class EditGardeActivity extends Activity implements OnClickListener,OnKeyListener{
	
	private LinearLayout dateDebutBouton;
	private LinearLayout dateFinBouton;
	private LinearLayout periodeBouton;
	private LinearLayout estPeriodeBouton;
	private LinearLayout nameBouton;
	private TextView dateDebutValue;
	private TextView dateFinValue;
	private TextView periodeValue;
	private TextView estPeriodeValue;
	private Button buttonAnnuler;
	private Button buttonSauver;
	private Button buttonSupprimer;
	private CheckBox boutonAfficherPeriode;
	private TextView nameValue;
	private EditText nameEditValue;
	private Calendar dateDebutCalendar;
	private Calendar dateFinCalendar;
	private Periode periode;
	private boolean isPeriode;
	private boolean afficherPeriode;
	private String name;
	private String currentId;
	
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.edit_garde_layout);

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
    	nameBouton = (LinearLayout)findViewById(R.id.nameBouton);
    	
    	dateDebutValue = (TextView) findViewById(R.id.dateDebutValue);
    	dateFinValue = (TextView) findViewById(R.id.dateFinValue);
    	periodeValue = (TextView) findViewById(R.id.periodeValue);
    	estPeriodeValue = (TextView) findViewById(R.id.estPeriodeValue);
    	nameValue = (TextView) findViewById(R.id.nameValue);
    	nameEditValue = (EditText) findViewById(R.id.nameEditValue);
    	buttonAnnuler = (Button) findViewById(R.id.buttonCancel);
    	buttonSauver = (Button) findViewById(R.id.buttonSave);
    	buttonSupprimer = (Button) findViewById(R.id.buttonDelete);
    	nameBouton.removeView(nameEditValue);
    	
    	boutonAfficherPeriode = (CheckBox) findViewById(R.id.boutonAfficherPeriode);

    }
    
    /**
     * Init listener
     */
    private void initListener() {
    	dateDebutBouton.setOnClickListener(this);
    	dateFinBouton.setOnClickListener(this);
    	periodeBouton.setOnClickListener(this);
    	estPeriodeBouton.setOnClickListener(this);
    	nameBouton.setOnClickListener(this);
    	buttonAnnuler.setOnClickListener(this);
    	buttonSauver.setOnClickListener(this);
    	buttonSupprimer.setOnClickListener(this);
    	nameEditValue.setOnKeyListener(this);
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
			isPeriode = !isPeriode;
			updateEstPeriode();
		}else if(v == nameBouton){
				nameBouton.removeView(nameValue);
				nameEditValue.setText(name);
				nameEditValue.setFocusable(true);
				nameEditValue.requestFocus();
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
	            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
				nameBouton.addView(nameEditValue);
				updateEstPeriode();
		}else if(v == buttonAnnuler){
			onBackPressed();
		}else if(v== buttonSauver){
			save();
			onBackPressed();
		}else if(v == buttonSupprimer){
			//TODO supprimer
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
		
		Garde garde = null;
		List<Garde> listegarde = (List<Garde>) FilesUtils.loadFromSdCard(Constantes.PATH_DATA);
		if(listegarde != null){
			garde = listegarde.get(0);
		}
		dateDebutCalendar = Calendar.getInstance();
		dateFinCalendar = Calendar.getInstance();
		periode = new Periode();
		isPeriode = true;
		dateDebutValue.setText(R.string.nondefinie);
		dateFinValue.setText(R.string.nondefinie);
		periodeValue.setText(R.string.nondefinie);
		updateEstPeriode();
		if(garde != null){
			dateDebutCalendar.setTime(garde.getDateDebut());
			dateFinCalendar.setTime(garde.getDateFin());
			periode = garde.getPeriode();
			isPeriode = garde.isEstPeriodeDeGarde();
			name = garde.getName();
			currentId = garde.getId();
			updateValues();
		}
	}
	
	private void updateValues(){
		updateDateDebut();
		updateDateFin();
		updatePeriode();
		updateEstPeriode();
		updateName();
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
	
	private void updateEstPeriode(){
		 String estPeriodeString = "";
		 if(isPeriode){
			 estPeriodeString = "Oui";
		 }else{
			 estPeriodeString = "Non";
		 }
		 estPeriodeValue.setText(estPeriodeString);
	}
	
	private void updateName(){
		 nameValue.setText(name);
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
	
	private void save(){
		List<Garde> listeGarde = (List<Garde>) FilesUtils.loadFromSdCard(Constantes.PATH_DATA);
		if(listeGarde==null){
			listeGarde = new ArrayList<Garde>();
		}
		if(currentId != null){
			//TODO modification
			Garde gardeEdit = null;
			int index = -1;
			while (index < listeGarde.size() && gardeEdit == null) {
				index++;
				Garde garde = listeGarde.get(index);
				if (garde.getId().equals(currentId)){
					gardeEdit = garde;
				}
				
			}
			populateGarde(gardeEdit);
			listeGarde.remove(index);
			listeGarde.add(index, gardeEdit);
			FilesUtils.saveOnSdCard(listeGarde, Constantes.PATH_DATA);
		}else{
			Garde garde = new Garde();
			garde.setId(UUID.randomUUID().toString());
			populateGarde(garde);
			listeGarde.add(garde);
			FilesUtils.saveOnSdCard(listeGarde, Constantes.PATH_DATA);
		}
		
	}
	private void populateGarde(Garde garde) {
		garde.setDateDebut(dateDebutCalendar.getTime());
		garde.setDateFin(dateFinCalendar.getTime());
		garde.setPeriode(periode);
		garde.setEstPeriodeDeGarde(isPeriode);
		garde.setName(name);
	}

	@Override
	public boolean onKey(View arg0, int keyCode, KeyEvent event) {
		if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
		        (keyCode == KeyEvent.KEYCODE_ENTER))
		    {
			nameBouton.removeView(nameEditValue);
			nameBouton.removeView(nameValue);
			nameBouton.addView(nameValue);
			name = nameEditValue.getText().toString();
			nameValue.setText(name);
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
		    }
		    // Returning false allows other listeners to react to the press.
		    return false;
	}
	
	private List<String> validateGarde(){
		List<String> erreurs = new ArrayList<String>();
		if(StringUtils.isEmpty(name)){
			//erreurs.add(object)
		}
		
		return erreurs;
	}

}
