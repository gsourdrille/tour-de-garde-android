package fr.edenyorke.tourdegarde;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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