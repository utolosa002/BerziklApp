package gipuzkoa.hondakinak;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class HMotaFragment extends Fragment {
	TextView txtpays;

	private ListView listView1;

	public static HMotaFragment newInstance() {
		HMotaFragment fragment = new HMotaFragment();
		return fragment;
	}

	public HMotaFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.h_mota_list, container, false);
		final hMota[] hMota_data;
		if(MainActivity.lang=="EU"){
		      hMota_data = new hMota[]
		        {
		        		  new hMota( "Ontzi arina"," Astearte eta ostiraletan "," 20:00-07:00", R.drawable.plastikoalezo),
		                  new hMota( "Organikoa"," Astelehen, ostegun eta Larunbatetan ","20:00-07:00",R.drawable.organikoalezo),
		                  new hMota( "Papera/kartoia"," Asteazkena "," 20:00-07:00",R.drawable.paperalezo),
		                  new hMota( "Errefusa","Igandean","20:00-07:00",R.drawable.errefusalezo),
		                  new hMota( "Garbigunera","NON: Talaia" + Html.fromHtml("<br />") +" industriagunea, Oiartzun","ORDUTEGIA: Astelehenetik larunbatera" + Html.fromHtml("<br />") +" 10:00 – 13:00" + Html.fromHtml("<br />") +"Astelehenetan, asteazkenetan eta ostiraletan 17:00 – 20:00",R.drawable.garbi),
		                  new hMota( "Iglu berdera","Beira","Nahi duzunean",R.drawable.berdea),
		                  new hMota( "Arropa","Arropa kontainerrera","Nahi duzunean",R.drawable.morea),
		                  new hMota( "Pilak ","Pilen kontainerra","Nahi duzunean",R.drawable.horia),
		                  new hMota( "Konpresak eta" + Html.fromHtml("<br />") +" pardelak","egunero","20:00-07:00",R.drawable.errefusa),
		        };
		        }else{

		            hMota_data = new hMota[]{ 
		        		  new hMota( "Envase ligero"," Martes y viernes "," 20:00-07:00", R.drawable.plastikoalezo),
		                  new hMota( "Orgánico","Lunes, Jueves y Sábado"," 20:00-07:00",R.drawable.organikoalezo),
		                  new hMota( "Papel/Cartón"," Míercoles ","20:00-07:00",R.drawable.paperalezo),
		                  new hMota( "Rechazo"," Domingo ","20:00-07:00",R.drawable.errefusalezo),
		                  new hMota( "Al garbigune","Dónde: Talaia industriagunea, Oiartzun" ,"HORARIOS: De Lunes a Sábado 10:00 – 13:00" + Html.fromHtml("<br />") +"Lunes, Míercoles y viernes 17:00 – 20:00",R.drawable.garbi),
		                  new hMota( "Al iglú verde","Vidrio","Cuando quieras",R.drawable.berdea),
		                  new hMota( "Ropa ","Al contenedor de la ropa","Cuando quieras",R.drawable.morea),
		                  new hMota( "Pilas ","Al contenedor de pilas","Cuando quieras",R.drawable.horia),
		                  new hMota( "Compresas y" + Html.fromHtml("<br />") +" Pañales","Cualquier día","20:00-07:00",R.drawable.errefusa)
		            };
		            }
		
		hMotaAdapter adapter = new hMotaAdapter(getActivity(),
				R.layout.h_mota_lerroa, hMota_data);

		listView1 = (ListView) rootView.findViewById(R.id.hondakinLista);

		View header = (View) inflater.inflate(
				R.layout.h_mota_burukoa, null);
		listView1.addHeaderView(header);
		listView1.setAdapter(adapter);
		return rootView;
	}

}