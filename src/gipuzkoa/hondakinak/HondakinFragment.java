package gipuzkoa.hondakinak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class HondakinFragment extends ListFragment {
	ArrayList<Hondakina> h = null;
	TextView txtpays;
	public static DatabaseHandler db;
	public static EditText et;
	private static String testua="";
	private static final String ARGUMENT_TEXT = "text";
	
	public static HondakinFragment newInstance(String text) {
		HondakinFragment fragment = new HondakinFragment();
		 // arguments
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_TEXT, text);
        fragment.setArguments(arguments);
        testua=text;
		return fragment;
	}	
	public static HondakinFragment newInstance() {
		HondakinFragment fragment = new HondakinFragment();
		return fragment;
	}

	public HondakinFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.bilatzailea, container, false);
		
		Resources res = getResources();
		Configuration conf = res.getConfiguration();
		Locale myLocale = new Locale("ge");
		db = new DatabaseHandler(getActivity());
		///////////////////////////////////////////////
		try {
			db.createDataBase();
			db.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("gaizki db");
		}

		if (conf.locale.equals(myLocale)) {
			MainActivity.lang = "ES";
		} else {
			MainActivity.lang = "EU";
		}
//		System.out.println("create lang= " + MainActivity.lang + " locale: "
//				+ getResources().getConfiguration().locale);
		et = (EditText) rootView.findViewById(R.id.bilatu);
		if (testua!=""){
			et.setText(testua);
		}

		/**
		 * EditText- bilatu
		 */
		et.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				listaLortu();
			}

		
		
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				listaLortu();
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				listaLortu();
			}
		});
		return rootView;
	
	}
	
	@Override
	public void onResume(){
		super.onResume();
		if (testua!=""){
			et.setText(testua);
			listaLortu();
		}
	}
		public void listaLortu() {
			// TODO Auto-generated method stub
			String st = null;
			if (et.length() > 0) {
				st = et.getText().toString();
				MainActivity.et1=et.getText().toString();
			} else {
				st = "";
				MainActivity.et1="";
			}
			db.close();
			et.setTextColor(Color.BLACK);
			ArrayList<String> hiz = null;
			if (db.checkDataBase() == false) {
				try {
					db.createDataBase();
					db.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (MainActivity.lang == "ES") {
				System.out.println("ES "
						+ getResources().getConfiguration().locale);
				try {
					h = db.getResiduos(st,"izena");
					hiz = db.getNombresResiduos(st,"izena");
				} catch (SQLiteException e) {
					// TODO Auto-generated catch block
					db.dropDB();
					try {
						db.createDataBase();
						h = db.getResiduos(st,"izena");
						hiz = db.getNombresResiduos(st,"izena");
					} catch (SQLiteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("EU locale: "
						+ getResources().getConfiguration().locale);
				try {
					try {
						h = db.getHondakinak(st,"izena");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						hiz = db.getHondakinIzenak(st,"izena");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (SQLiteException e) {
					// TODO Auto-generated catch block
					db.dropDB();
					try {
						try {
							db.createDataBase();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							h = db.getHondakinak(st,"izena");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						hiz = db.getHondakinIzenak(st,"izena");
					} catch (SQLiteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					getActivity(), android.R.layout.simple_list_item_1,
					hiz);

			setListAdapter(adapter);

			ListView lv = getListView();
			// listening to single list item on click
			lv.setOnItemClickListener(new OnItemClickListener() {
				@SuppressLint("NewApi")
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Intent i = new Intent(getActivity().getApplicationContext(),
							HondakinHautatua.class);
					Hondakina hon = (Hondakina) h.get(position);
					i.putExtra("izen", (String) hon.getName());
					i.putExtra("non", (String) hon.getNon());
					i.putExtra("info", (String) hon.getInfo());
					startActivity(i);
//					HondakinActivity.this.overridePendingTransition(
//							R.anim.animazioa, R.anim.animazioa2);
				}
			});
			}

}
