package fr.edenyorke.tourdegarde;

import java.util.Calendar;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnDateChangedListener{
	
	
	private DatePicker datePicker;
	private ImageView image;
	
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
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
    	case R.id.menu_settings:
    		Intent myIntent = new Intent(MainActivity.this, SettingsActivity.class);
    		MainActivity.this.startActivity(myIntent);
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }
    
    private void initView(){
    	datePicker = (DatePicker) findViewById(R.id.datePicker1);
    	datePicker.setSpinnersShown(true);
 		datePicker.setCalendarViewShown(false);
 		
 		image = (ImageView) findViewById(R.id.imageView1);
    }
    
 // display current date
 	public void setCurrentDateOnView() {
  
 		final Calendar c = Calendar.getInstance();
 		year = c.get(Calendar.YEAR);
 		month = c.get(Calendar.MONTH);
 		day = c.get(Calendar.DAY_OF_MONTH);
 		// set current date into datepicker
 		datePicker.init(year, month, day, this);
 		
 
  
 	}


	@Override
	public void onDateChanged(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		
		//TODO Caluler date
		boolean isTourdeGarde = false;
		if(isTourdeGarde){
			image.setImageResource(R.drawable.istourdegarde);
		}else{
			image.setImageResource(R.drawable.isnottourdegarde);
		}
	}
    
}