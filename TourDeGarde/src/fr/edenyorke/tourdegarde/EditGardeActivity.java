package fr.edenyorke.tourdegarde;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import fr.edenyorke.tourdegarde.bean.Constantes;
import fr.edenyorke.tourdegarde.bean.Garde;
import fr.edenyorke.tourdegarde.bean.Periode;
import fr.edenyorke.tourdegarde.dialog.CustomDatePickerDialog;
import fr.edenyorke.tourdegarde.dialog.CustomPeriodePickerDialog;
import fr.edenyorke.tourdegarde.utils.CollectionUtils;
import fr.edenyorke.tourdegarde.utils.DateUtils;
import fr.edenyorke.tourdegarde.utils.FilesUtils;
import fr.edenyorke.tourdegarde.utils.StringUtils;

public class EditGardeActivity extends Activity implements OnClickListener,OnKeyListener, OnCheckedChangeListener{
	
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
		buttonSupprimer.setVisibility(View.INVISIBLE);
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
    	boutonAfficherPeriode.setOnCheckedChangeListener(this);
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
			if(save()){
				onBackPressed();
			}
		}else if(v == buttonSupprimer){
			delete();
		}
		
	}
	
	DatePickerDialog.OnDateSetListener dateDebutListener = new DatePickerDialog.OnDateSetListener() {

	    @Override
	    public void onDateSet(DatePicker view, int year, int monthOfYear,
	            int dayOfMonth) {
	    	dateDebutCalendar.set(Calendar.YEAR, year);
	    	dateDebutCalendar.set(Calendar.MONTH, monthOfYear);
	    	dateDebutCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
	    }

	};
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if(isChecked){
			periodeBouton.setVisibility(View.VISIBLE);
			afficherPeriode = true;
		}else{
			periodeBouton.setVisibility(View.INVISIBLE);
			afficherPeriode = false;
		}
	}
	
	private void initValues(){
		Intent i = getIntent();
		Garde garde = (Garde)i.getSerializableExtra("garde");
		if(garde != null){
			dateDebutCalendar = Calendar.getInstance();
			dateFinCalendar = Calendar.getInstance();
			dateDebutCalendar.setTime(garde.getDateDebut());
			dateFinCalendar.setTime(garde.getDateFin());
			periode = garde.getPeriode();
			
			isPeriode = garde.isEstPeriodeDeGarde();
			name = garde.getName();
			currentId = garde.getId();
			updateValues();
			buttonSupprimer.setVisibility(View.VISIBLE);
		}else{
			dateDebutCalendar = null;
			dateFinCalendar = null;
			isPeriode = true;
			dateDebutValue.setText(R.string.nondefinie);
			dateFinValue.setText(R.string.nondefinie);
			periodeValue.setText(R.string.nondefinie);
			updateEstPeriode();
		}
		if(periode != null){
			periodeBouton.setVisibility(View.VISIBLE);
			afficherPeriode = true;
			boutonAfficherPeriode.setChecked(true);
		}else{
			periodeBouton.setVisibility(View.INVISIBLE);
			afficherPeriode = false;
			boutonAfficherPeriode.setChecked(false);
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
			 estPeriodeString = getResources().getString(R.string.oui);
		 }else{
			 estPeriodeString = getResources().getString(R.string.non);;
		 }
		 estPeriodeValue.setText(estPeriodeString);
	}
	
	private void updateName(){
		 nameValue.setText(name);
	}
	
	private void updatePeriode(){
		if(periode != null){
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
	
	private boolean save(){
		List<String> erreurs = validateGarde();
		if(!CollectionUtils.isEmpty(erreurs)){
			String erreurMessage = "";
			for(String erreur : erreurs){
				erreurMessage += erreur +"\n";
			}
			
			AlertDialog alertDialog = new AlertDialog.Builder(EditGardeActivity.this).create();
			alertDialog.setTitle("Erreurs");
			alertDialog.setMessage(erreurMessage);
			alertDialog.show();
			return false;
	
		}else{
			List<Garde> listeGarde = (List<Garde>) FilesUtils.loadFromSdCard(Constantes.PATH_DATA);
			if(listeGarde==null){
				listeGarde = new ArrayList<Garde>();
			}
			if(currentId != null){
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
				Toast.makeText(this, getResources().getString(R.string.edit_garde_ok, gardeEdit.getName()), Toast.LENGTH_SHORT).show();
			}else{
				Garde garde = new Garde();
				garde.setId(UUID.randomUUID().toString());
				populateGarde(garde);
				listeGarde.add(garde);
				FilesUtils.saveOnSdCard(listeGarde, Constantes.PATH_DATA);
				Toast.makeText(this, getResources().getString(R.string.ajout_garde_ok, garde.getName()), Toast.LENGTH_SHORT).show();
			}
		}
		return true;
	}
	
	
	private void delete(){
			
		new AlertDialog.Builder(this)
        .setIcon(android.R.drawable.ic_dialog_alert)
        .setTitle(R.string.confirme_title)
        .setMessage(R.string.confirme_message)
        .setPositiveButton(R.string.oui, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            	processDelete();
            }
        })
        .setNegativeButton(R.string.non, null)
        .show();
	}
	
	
	private void processDelete() {
		List<Garde> listeGarde = (List<Garde>) FilesUtils.loadFromSdCard(Constantes.PATH_DATA);
		Garde gardeToDelete = null;
		int index = -1;
		while (index < listeGarde.size() && gardeToDelete == null) {
			index++;
			Garde garde = listeGarde.get(index);
			if (garde.getId().equals(currentId)){
				gardeToDelete = garde;
			}
			
		}
		if(gardeToDelete != null){
			listeGarde.remove(index);
		}
		FilesUtils.saveOnSdCard(listeGarde, Constantes.PATH_DATA);
		Toast.makeText(this, getResources().getString(R.string.delete_garde_ok, gardeToDelete.getName()), Toast.LENGTH_SHORT).show();
		onBackPressed();
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
			erreurs.add(getResources().getString(R.string.erreur_nom_vide));
		}
		if(dateDebutCalendar == null){
			erreurs.add(getResources().getString(R.string.erreur_datedebut_vide));
		}
		if(dateFinCalendar == null){
			erreurs.add(getResources().getString(R.string.erreur_datefin_vide));
		}
		if(dateDebutCalendar != null && dateFinCalendar != null && dateDebutCalendar.after(dateFinCalendar)){
			erreurs.add(getResources().getString(R.string.erreur_datefin_before));
		}
		if(afficherPeriode && periode == null){
			erreurs.add(getResources().getString(R.string.erreur_periode_vide));
		}
		
		return erreurs;
	}

	

}
