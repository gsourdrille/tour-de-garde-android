package fr.edenyorke.tourdegarde.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import fr.edenyorke.tourdegarde.R;

public abstract class CustomDatePickerDialog extends Dialog implements android.view.View.OnClickListener{
	
	private DatePicker datePicker;
	private NumberPicker inDayPicker;
	private Button cancelButton;
	private Button saveButton;

	public CustomDatePickerDialog(Context context) {
		super(context);
		setContentView(R.layout.custom_datepicker_dialog);
		setCancelable(true);
		
		datePicker = (DatePicker) findViewById(R.id.customDatePicker);
    	datePicker.setSpinnersShown(true);
 		datePicker.setCalendarViewShown(false);
 		
 		inDayPicker = (NumberPicker) findViewById(R.id.customInDayPicker);
 		inDayPicker.setMinValue(0);
 		inDayPicker.setMaxValue(1);
 		inDayPicker.setDisplayedValues( new String[] { "AM", "PM" } );
 		
 		cancelButton = (Button) findViewById(R.id.buttonCancel);
 		saveButton = (Button) findViewById(R.id.buttonSave);
 		saveButton.setOnClickListener(this);
 		
	}

	@Override
	public abstract void onClick(View v) ;

	public DatePicker getDatePicker() {
		return datePicker;
	}

	public void setDatePicker(DatePicker datePicker) {
		this.datePicker = datePicker;
	}

	public NumberPicker getInDayPicker() {
		return inDayPicker;
	}

	public void setInDayPicker(NumberPicker inDayPicker) {
		this.inDayPicker = inDayPicker;
	}
	
	
	

}
