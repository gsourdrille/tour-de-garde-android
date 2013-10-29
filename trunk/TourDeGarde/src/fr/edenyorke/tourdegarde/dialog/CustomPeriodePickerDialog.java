package fr.edenyorke.tourdegarde.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import fr.edenyorke.tourdegarde.R;
import fr.edenyorke.tourdegarde.bean.Periode;
import fr.edenyorke.tourdegarde.bean.TypePeriode;

public abstract class CustomPeriodePickerDialog extends Dialog implements android.view.View.OnClickListener, OnValueChangeListener{
	
	private NumberPicker typePeriodePicker;
	private NumberPicker numberPeriodePicker;
	private Button cancelButton;
	private Button saveButton;
	private Periode periode;
	private String[] typePeriodeArray = new String[] { "1 semaine sur", "1 mois sur", "1 fois sur" };

	public CustomPeriodePickerDialog(Context context, Periode currentPeriode) {
		super(context);
		setContentView(R.layout.custom_periodepicker_dialog);
		setCancelable(true);
		
		if(currentPeriode == null){
			periode = new Periode();
		}else{
			periode = currentPeriode;
		}
		typePeriodePicker = (NumberPicker) findViewById(R.id.customTypePeriode);
		typePeriodePicker.setMinValue(0);
		typePeriodePicker.setMaxValue(2);
		typePeriodePicker.setDisplayedValues( typePeriodeArray);
		typePeriodePicker.setOnValueChangedListener(this);
		switch (periode.getTypePeriode()) {
		case WEEK:
			typePeriodePicker.setValue(0);
			break;
		case MONTH:
			typePeriodePicker.setValue(1);
			break;
		case TIMES:
			typePeriodePicker.setValue(2);
			break;
		default:
			break;
		}
		
 		
		numberPeriodePicker = (NumberPicker) findViewById(R.id.customNumberPeriode);
		numberPeriodePicker.setMinValue(2);
		numberPeriodePicker.setMaxValue(4);
		numberPeriodePicker.setValue(periode.getNumberPeriode());
		numberPeriodePicker.setOnValueChangedListener(this);
 		
 		cancelButton = (Button) findViewById(R.id.buttonCancel);
 		cancelButton.setOnClickListener(this);
 		saveButton = (Button) findViewById(R.id.buttonSave);
 		saveButton.setOnClickListener(this);
 		
 		updateTitle();
	}

	@Override
	public void onClick(View v) {
		
		if(v == saveButton) {
			 getValue();
		}else{
			this.dismiss();
		}
	}

	private void getPickerValue() {
		
		
		switch (typePeriodePicker.getValue()) {
		case 0:
			periode.setTypePeriode(TypePeriode.WEEK);
			break;
		case 1:
			periode.setTypePeriode(TypePeriode.MONTH);
			break;
		case 2:
			periode.setTypePeriode(TypePeriode.TIMES);
			break;
		default:
			break;
		}
		
		periode.setNumberPeriode(getNumberPeriodePicker().getValue());
		
	}
	
	public abstract void getValue();
	
	

	private void updateTitle() {
		getPickerValue();
		String title = typePeriodeArray[ getTypePeriodePicker().getValue() ] + " " + String.valueOf(getNumberPeriodePicker().getValue()) ;
		setTitle(title);
	}
	@Override
	public void onValueChange (NumberPicker np1, int oldVal, int newVal){
		updateTitle();
	}

	public NumberPicker getTypePeriodePicker() {
		return typePeriodePicker;
	}

	public void setTypePeriodePicker(NumberPicker typePeriodePicker) {
		this.typePeriodePicker = typePeriodePicker;
	}

	public NumberPicker getNumberPeriodePicker() {
		return numberPeriodePicker;
	}

	public void setNumberPeriodePicker(NumberPicker numberPeriodePicker) {
		this.numberPeriodePicker = numberPeriodePicker;
	}

	public Periode getPeriode() {
		return periode;
	}

	public void setPeriode(Periode periode) {
		this.periode = periode;
	}
	
	
	
	
	
	
	

}
