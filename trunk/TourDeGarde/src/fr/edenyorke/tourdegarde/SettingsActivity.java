package fr.edenyorke.tourdegarde;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SettingsActivity extends Activity implements OnClickListener{
	
	private LinearLayout dateDebutBouton;
	private LinearLayout dateFinBouton;
	private LinearLayout periodeBouton;
	private LinearLayout estPeriodeBouton;
	private TextView dateDebutValue;
	private TextView dateFinValue;
	private TextView periodeValue;
	private TextView estPeriodeValue;
	
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.settings);

        // Get IHM Components
        initScreenComponent();
        
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
			
		}else if(v == dateFinBouton){
			
		}else if(v == periodeBouton){
			
		}else if(v == estPeriodeBouton){
			
		}
		
	}

}
