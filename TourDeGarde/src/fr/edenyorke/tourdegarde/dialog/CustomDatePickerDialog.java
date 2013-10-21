package fr.edenyorke.tourdegarde.dialog;

import java.util.Calendar;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import fr.edenyorke.tourdegarde.R;
import fr.edenyorke.tourdegarde.utils.DateUtils;

public abstract class CustomDatePickerDialog extends Dialog implements android.view.View.OnClickListener, OnDateChangedListener, OnValueChangeListener{
	
	private DatePicker datePicker;
	private NumberPicker inDayPicker;
	private Button cancelButton;
	private Button saveButton;
	private Calendar calendar;

	public CustomDatePickerDialog(Context context, Calendar currentDate) {
		super(context);
		setContentView(R.layout.custom_datepicker_dialog);
		setCancelable(true);
		
		calendar = currentDate;
		
		datePicker = (DatePicker) findViewById(R.id.customDatePicker);
    	datePicker.setSpinnersShown(true);
 		datePicker.setCalendarViewShown(false);
 		datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), this);
 		
 		inDayPicker = (NumberPicker) findViewById(R.id.customInDayPicker);
 		inDayPicker.setMinValue(0);
 		inDayPicker.setMaxValue(1);
 		inDayPicker.setDisplayedValues( new String[] { "AM", "PM" } );
 		if(calendar.get(Calendar.HOUR_OF_DAY) <= 12){
 			inDayPicker.setValue(0);
 		}else{
 			inDayPicker.setValue(1);
 		}
 		inDayPicker.setOnValueChangedListener(this);
 		
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

	private void getDatePickerValue() {
		int day = getDatePicker().getDayOfMonth();
		 int month = getDatePicker().getMonth() ;
		 int year = getDatePicker().getYear();
		 calendar = Calendar.getInstance();
		 calendar.set(Calendar.DAY_OF_MONTH, day);
		 calendar.set(Calendar.MONTH, month);
		 calendar.set(Calendar.YEAR, year);
		 
		 if(inDayPicker.getValue()==0){
			 calendar.set(Calendar.HOUR_OF_DAY, 8); 
		 }else{
			 calendar.set(Calendar.HOUR_OF_DAY,18);
		 }
	}
	
	public abstract void getValue();
	
	@Override
	public void onDateChanged(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		updateTitle();
		
	}

	private void updateTitle() {
		getDatePickerValue();
		String currentDate = DateUtils.formatDate(calendar.getTime(), DateUtils.FORMAT_DATE_LONG);
		setTitle(currentDate);
	}
	@Override
	public void onValueChange (NumberPicker np1, int oldVal, int newVal){
		updateTitle();
	}
	
	
	
	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}
	
	public Calendar getCalendar() {
		return calendar;
	}

	

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
