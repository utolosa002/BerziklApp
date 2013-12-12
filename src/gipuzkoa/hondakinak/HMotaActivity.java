package gipuzkoa.hondakinak;

import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HMotaActivity extends Activity {
	private LinearLayout slidingPanel;
	private boolean isExpanded;
	private DisplayMetrics metrics;
	private ListView listView;
	private RelativeLayout headerPanel;
	private RelativeLayout menuPanel;
	private int panelWidth;
	private ImageView menuViewButton;
	Button bilaketa;
	Button hizkuntza;
	Button motak;
	Button astea;
	TextView txtpays;
	FrameLayout.LayoutParams menuPanelParameters;
	FrameLayout.LayoutParams slidingPanelParameters;
	LinearLayout.LayoutParams headerPanelParameters;
	LinearLayout.LayoutParams listViewParameters;

    private ListView listView1;

    @SuppressLint("CutPasteId")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_mota_list);
        metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		panelWidth = (int) ((metrics.widthPixels) * 0.75);

		headerPanel = (RelativeLayout) findViewById(R.id.header);
		headerPanelParameters = (LinearLayout.LayoutParams) headerPanel
				.getLayoutParams();
		headerPanelParameters.width = metrics.widthPixels;
		headerPanel.setLayoutParams(headerPanelParameters);

		menuPanel = (RelativeLayout) findViewById(R.id.menuPanel);
		menuPanelParameters = (FrameLayout.LayoutParams) menuPanel
				.getLayoutParams();
		menuPanelParameters.width = panelWidth;
		menuPanel.setLayoutParams(menuPanelParameters);

		slidingPanel = (LinearLayout) findViewById(R.id.slidingPanel);
		slidingPanelParameters = (FrameLayout.LayoutParams) slidingPanel
				.getLayoutParams();
		slidingPanelParameters.width = metrics.widthPixels;
		slidingPanel.setLayoutParams(slidingPanelParameters);

		listView = (ListView) findViewById(R.id.hondakinLista);
		listViewParameters = (LinearLayout.LayoutParams) listView
				.getLayoutParams();
		listViewParameters.width = metrics.widthPixels;
		listView.setLayoutParams(listViewParameters);

		// Slide the Panel

		menuViewButton = (ImageView) findViewById(R.id.menuViewButton);
		bilaketa = (Button) findViewById(R.id.menu_bilaketa);
		motak = (Button) findViewById(R.id.menu_motak);
		astea = (Button) findViewById(R.id.menu_astea);
		hizkuntza = (Button) findViewById(R.id.menu_hizkuntza);
		
		final hMota[] hMota_data;
		if(HondakinActivity.lang=="EU"){
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
        
       
		hMotaAdapter adapter = new hMotaAdapter(this, 
                R.layout.h_mota_lerroa, hMota_data);
        
        
        listView1 = (ListView)findViewById(R.id.hondakinLista);
             
        View header = (View)getLayoutInflater().inflate(R.layout.h_mota_burukoa, null);
        listView1.addHeaderView(header);
        
        listView1.setAdapter(adapter);

	
	astea.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(getApplicationContext(),
					AsteaActivity.class);
			startActivity(i);
		}
	});
	motak.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			isExpanded = false;
			// Collapse
			new CollapseAnimation(slidingPanel, panelWidth,
					TranslateAnimation.RELATIVE_TO_SELF, 0.75f,
					TranslateAnimation.RELATIVE_TO_SELF, 0.0f, 0, 0.0f, 0,
					0.0f);
		}
	});
	bilaketa.setOnClickListener(new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(getApplicationContext(),
					HondakinActivity.class);
			startActivity(i);
		}
	});

	hizkuntza.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			System.out.println("clik lang= " + HondakinActivity.lang + " locale: "
					+ getResources().getConfiguration().locale);
			if (HondakinActivity.lang == "EU") {
				HondakinActivity.lang = "ES";
				Toast.makeText(HMotaActivity.this, "Idioma: Español",
						Toast.LENGTH_SHORT).show();
				setLocale("ge");

			} else {
				HondakinActivity.lang = "EU";
				Toast.makeText(HMotaActivity.this, "Hizkuntza: Euskara",
						Toast.LENGTH_SHORT).show();
				setLocale("");
			}		
	}
	});

	menuViewButton.setOnClickListener(new OnClickListener() {
		public void onClick(View v) {
			if (!isExpanded) {
				isExpanded = true;
				menuViewButton.setImageResource(R.drawable.fletxaalderantziz);
				// Expand
				new ExpandAnimation(slidingPanel, panelWidth,
						Animation.RELATIVE_TO_SELF, 0.0f,
						Animation.RELATIVE_TO_SELF, 0.75f, 0, 0.0f, 0, 0.0f);
			} else {
				isExpanded = false;
				menuViewButton.setImageResource(R.drawable.fletxa);
				// Collapse
				new CollapseAnimation(slidingPanel, panelWidth,
						TranslateAnimation.RELATIVE_TO_SELF, 0.75f,
						TranslateAnimation.RELATIVE_TO_SELF, 0.0f, 0, 0.0f,
						0, 0.0f);
			}
		}
	});
}
	public void setLocale(String lang) {
		Locale myLocale = new Locale(lang);
		Resources res = getResources();
		DisplayMetrics dm = res.getDisplayMetrics();
		Configuration conf = res.getConfiguration();
		conf.locale = myLocale;
		res.updateConfiguration(conf, dm);
		Intent refresh = new Intent(this, HMotaActivity.class);
		startActivity(refresh);
		finish();
	}
}