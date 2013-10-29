package fr.edenyorke.tourdegarde.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import fr.edenyorke.tourdegarde.R;
import fr.edenyorke.tourdegarde.bean.Garde;

public class ListeGardeAdapter extends ArrayAdapter<Garde>  {
	

		public ListeGardeAdapter(Context context, int textViewResourceId) {
		    super(context, textViewResourceId);
		}

		private List<Garde> gardes;

		public ListeGardeAdapter(Context context, int resource, List<Garde> gardes) {

		    super(context, resource, gardes);

		    this.gardes = gardes;

		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

		    View v = convertView;

		    if (v == null) {

		        LayoutInflater vi;
		        vi = LayoutInflater.from(getContext());
		        v = vi.inflate(R.layout.liste_garde_view, null);

		    }

		    Garde p = gardes.get(position);

		    if (p != null) {

		        ImageView image = (ImageView) v.findViewById(R.id.img);
		        TextView name = (TextView) v.findViewById(R.id.titre);
		        TextView periode = (TextView) v.findViewById(R.id.periode);

		        if(p.isEstPeriodeDeGarde()){
		        	image.setImageResource(R.drawable.istourdegarde);
		        }else{
		        	image.setImageResource(R.drawable.isnottourdegarde);
		        }
		        
		        name.setText(p.getName());
		        
		        if(p.getPeriode() != null){
		        	periode.setText(R.string.avec_periode);
		        }

		    }

		    return v;
		}
	}


