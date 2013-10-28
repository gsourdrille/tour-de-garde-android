package fr.edenyorke.tourdegarde;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import fr.edenyorke.tourdegarde.bean.Constantes;
import fr.edenyorke.tourdegarde.bean.Garde;
import fr.edenyorke.tourdegarde.utils.FilesUtils;

public class ListeGardeActivity extends Activity{
	
	private ListView listeGardeView;
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.liste_garde_activity);
	        initView();
	        initData();
	        
	        
	    }
	 
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	    	switch (item.getItemId()) {
	    	case android.R.id.home:
				onBackPressed();
				return true;
	    	case R.id.add_garde:
	    		Intent myIntent = new Intent(ListeGardeActivity.this, EditGardeActivity.class);
	    		ListeGardeActivity.this.startActivity(myIntent);
	    		return true;
	    	default:
	    		return super.onOptionsItemSelected(item);
	    	}
	    }


	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.menu_liste_garde, menu);
	        return true;
	    }
	 
	 private void initView(){
		 
		 listeGardeView = (ListView) findViewById(R.id.listGardeView);
		 
	 }
	 
 private void initData(){
		 
	 List<Garde> listegarde = (List<Garde>) FilesUtils.loadFromSdCard(Constantes.PATH_DATA);
		if(listegarde == null){
			//TODO Afficher activite edit garde
		}else{
			//TODO Initialiser liste
		}
		 
	 }
	    

}
