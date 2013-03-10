package gipuzkoa.hondakinak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class HondakinActivity extends ListActivity {
	EditText et;
	String lang = "EU";
	ToggleButton h;
	final DatabaseHandler db = new DatabaseHandler(this);

	@Override
	protected void onSaveInstanceState(Bundle outState) {
	 // TODO Auto-generated method stub
	 super.onSaveInstanceState(outState);
	 outState.putString("TEXT", et.getText().toString());
	 outState.putString("LANG", lang);

	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
	 // TODO Auto-generated method stub
	 super.onRestoreInstanceState(savedInstanceState);
	 et.setText(savedInstanceState.getString("TEXT"));
	 lang=(savedInstanceState.getString("LANG"));
	    try {
					db.createDataBase();
					db.close();
		}catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("gaizki db");
		}
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hondakin);
		Resources res = getResources();
	    Configuration conf = res.getConfiguration();
	    Locale myLocale = new Locale("ge");	
	    try {
					db.createDataBase();
					db.close();
		}catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("gaizki db");
		}
	    h = (ToggleButton) findViewById(R.id.hizkuntza); 
	   		
	    if(conf.locale.equals(myLocale)){
	    	 lang = "ES";
	    	 h.setChecked(false);
	    }else{
	    	 lang = "EU";
	    	 h.setChecked(true);
	    }
		System.out.println("create lang= "+lang+" locale: "+getResources().getConfiguration().locale);
		et = (EditText) findViewById(R.id.bilatu);
		
		/**
		 * CRUD Operations
		 * */
		// Inserting Contacts
		h.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				System.out.println("clik lang= "+lang+" locale: "+getResources().getConfiguration().locale);
				if (lang == "EU") {
					db.close();
					lang = "es";
					Toast.makeText(HondakinActivity.this,
	                            "Idioma: Español", Toast.LENGTH_SHORT)
	                            .show();
	                setLocale("ge");
	        		
	        		
				} else {
					db.close();
					lang = "EU";
					Toast.makeText(HondakinActivity.this,
	                            "Hizkuntza: Euskara", Toast.LENGTH_SHORT)
	                            .show();
					setLocale("");
				}
			}

		});
		/**
		 * EditText- bilatu
		 */

		et.setOnKeyListener(new View.OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				db.close();
				if (et.length() > 0) {
					String s = et.getText().toString();
					final ArrayList<String> hiz;
					final ArrayList<Hondakina> h;
					if(db.checkDataBase()==false){
						try {
							db.createDataBase();
							db.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (lang == "ES") {
						System.out.println("ES "+getResources().getConfiguration().locale);
						h = db.getResiduos(s);
						hiz = db.getNombresResiduos(s);
					} else {
						System.out.println("EU locale: "+getResources().getConfiguration().locale);
						h = db.getHondakinak(s);
						hiz = db.getHondakinIzenak(s);
					}
				
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(
							getBaseContext(),
							android.R.layout.simple_list_item_1, hiz);

					setListAdapter(adapter);

					
				
					ListView lv = getListView();
					// listening to single list item on click
					lv.setOnItemClickListener(new OnItemClickListener() {
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							Intent i = new Intent(getApplicationContext(),
									HondakinHautatua.class);
							Hondakina hon = (Hondakina) h.get(position);
							i.putExtra("izen", (String) hon.getName());
							i.putExtra("non", (String) hon.getNon());
							i.putExtra("info", (String) hon.getInfo());
							startActivity(i);
						}
					});
				} else {
					String[] hiz = { "" };
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(
							getBaseContext(),
							android.R.layout.simple_list_item_1, hiz);

					setListAdapter(adapter);

					ListView lv = getListView();
					lv.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
						}
					});
				}
				return false;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(1, 1, 0, "Iturburua").setIcon(android.R.drawable.ic_menu_info_details);
		menu.add(2, 2, 1, "Data").setIcon(android.R.drawable.ic_menu_info_details);
		menu.add(3, 3, 2, "Ezabatu DatuBasea").setIcon(android.R.drawable.ic_menu_delete);
		menu.add(4, 4, 3, "dev.").setIcon(android.R.drawable.ic_menu_day);
		return true;
	}
 
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
 
		case 3:

			db.dropDB();
			Toast.makeText(this, "Datu-Basea ezabatua!", Toast.LENGTH_LONG).show();
			 
			break;	
		case 4:
				 
				Toast.makeText(this, "Developed by Unai Tolosa", Toast.LENGTH_LONG).show();
	 
				break;
	case 2:
		
		Toast.makeText(this, "v1.2 -> 2013-01", Toast.LENGTH_LONG).show();

		break;
	case 1:
		Toast.makeText(this,"Informazio iturburuak: \n" +
			    		"www.gipuzkoazz.com\n" +
			    		"www.usurbil.net/hondakinak/hiztegia.html\n"+
			    		"www.hernaniatezate.net/zer-non/\n" +
			    		"lezozerozabor.wordpress.com/\n"
				, Toast.LENGTH_LONG).show();
		break;
	}
 
		return super.onOptionsItemSelected(item);
	}

    public void setLocale(String lang) {
    	Locale myLocale = new Locale(lang);
    	Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, HondakinActivity.class);
        startActivity(refresh);
        this.finish();
    }
    }