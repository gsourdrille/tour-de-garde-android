package fr.edenyorke.tourdegarde;

import java.util.Calendar;
import java.util.Locale;

import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.NumericWheelAdapter;
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
import fr.edenyorke.tourdegarde.adapter.DayArrayAdapter;

public class MainActivity extends Activity implements OnDateChangedListener{
	
	
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
 		
 		image = (ImageView) findViewById(R.id.imageView1);
 		
 		
 		 final WheelView hours = (WheelView) findViewById(R.id.hour);
         NumericWheelAdapter hourAdapter = new NumericWheelAdapter(this, 0, 23,"%02d");
         hourAdapter.setItemResource(R.layout.wheel_text_item);
         hourAdapter.setItemTextResource(R.id.text);
         hours.setViewAdapter(hourAdapter);
         hours.setCyclic(true);
     
         final WheelView mins = (WheelView) findViewById(R.id.mins);
         NumericWheelAdapter minAdapter = new NumericWheelAdapter(this, 0, 59, "%02d");
         minAdapter.setItemResource(R.layout.wheel_text_item);
         minAdapter.setItemTextResource(R.id.text);
         mins.setViewAdapter(minAdapter);
         mins.setCyclic(true);
         
         // set current time
         Calendar calendar = Calendar.getInstance(Locale.FRANCE);
         hours.setCurrentItem(calendar.get(Calendar.HOUR));
         mins.setCurrentItem(calendar.get(Calendar.MINUTE));
         
         final WheelView day = (WheelView) findViewById(R.id.day);
         day.setViewAdapter(new DayArrayAdapter(this, calendar));        
 		
    }
    
 // display current date
 	public void setCurrentDateOnView() {
  
 		final Calendar c = Calendar.getInstance();
 		year = c.get(Calendar.YEAR);
 		month = c.get(Calendar.MONTH);
 		day = c.get(Calendar.DAY_OF_MONTH);
 		// set current date into datepicker
 		
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