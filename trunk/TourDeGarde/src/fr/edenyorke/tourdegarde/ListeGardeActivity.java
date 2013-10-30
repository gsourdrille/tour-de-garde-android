package fr.edenyorke.tourdegarde;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import fr.edenyorke.tourdegarde.adapter.ListeGardeAdapter;
import fr.edenyorke.tourdegarde.bean.Constantes;
import fr.edenyorke.tourdegarde.bean.Garde;
import fr.edenyorke.tourdegarde.utils.CollectionUtils;
import fr.edenyorke.tourdegarde.utils.FilesUtils;

public class ListeGardeActivity extends Activity implements OnItemClickListener{
	
	private ListView listeGardeView;
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.liste_garde_activity);
	        initView();
	        setRedirection();
	        
	    }
	 
	 @Override
	 protected void onResume(){
		 super.onResume();
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
		 listeGardeView.setOnItemClickListener(this);
		 
	 }
	 
 private void initData(){
		 
	 List<Garde> listegarde = (List<Garde>) FilesUtils.loadFromSdCard(Constantes.PATH_DATA);
	 if(CollectionUtils.isEmpty(listegarde)){
		 listegarde = new ArrayList<Garde>();
	 }
		ListeGardeAdapter adapter = new ListeGardeAdapter(this, R.layout.liste_garde_view, listegarde);
		listeGardeView.setAdapter(adapter);
	 }
 
 private void setRedirection(){
	 
	 List<Garde> listegarde = (List<Garde>) FilesUtils.loadFromSdCard(Constantes.PATH_DATA);
		if(CollectionUtils.isEmpty(listegarde)){
			Intent myIntent = new Intent(ListeGardeActivity.this, EditGardeActivity.class);
			ListeGardeActivity.this.startActivity(myIntent);
		}
	 }

@Override
public void onItemClick(AdapterView<?> a, View v, int position, long id) {
	List<Garde> listegarde = (List<Garde>) FilesUtils.loadFromSdCard(Constantes.PATH_DATA);
	Garde garde = listegarde.get(position);
	Intent myIntent = new Intent(ListeGardeActivity.this, EditGardeActivity.class);
	myIntent.putExtra("garde", garde);
	ListeGardeActivity.this.startActivity(myIntent);
}
	    

}
