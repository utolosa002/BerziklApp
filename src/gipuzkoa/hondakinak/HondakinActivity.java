package gipuzkoa.hondakinak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HondakinActivity extends ListActivity {
	public static EditText et;
	public static String et1 = "";
	public static String lang = "EU";
	public final DatabaseHandler db = new DatabaseHandler(this);
	private LinearLayout slidingPanel;
	private boolean isExpanded;
	private DisplayMetrics metrics;
	private ListView listView;
	private RelativeLayout headerPanel;
	private RelativeLayout menuPanel;
	private int panelWidth;
	private ImageView menuViewButton;
	ArrayList<Hondakina> h = null;
	Button bilaketa;
	Button hizkuntza;
	Button motak;
	Button astea;
	TextView txtpays;
	FrameLayout.LayoutParams menuPanelParameters;
	FrameLayout.LayoutParams slidingPanelParameters;
	LinearLayout.LayoutParams headerPanelParameters;
	LinearLayout.LayoutParams listViewParameters;

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		outState.putString("TEXT", et.getText().toString());
		outState.putString("LANG", lang);
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		et.setText(savedInstanceState.getString("TEXT"));
		lang = (savedInstanceState.getString("LANG"));
		super.onRestoreInstanceState(savedInstanceState);
		if (lang=="ES"){
			setLocale("ge");
		}else{
			setLocale("");
		}
		try {
			db.createDataBase();
			db.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("gaizki db");
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bilatzailea);
		Resources res = getResources();
		Configuration conf = res.getConfiguration();
		Locale myLocale = new Locale("ge");
		// //////////////////////////////////
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

		listView = (ListView) findViewById(R.id.list);
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

		// /////////////////////////////////////////////
		try {
			db.createDataBase();
			db.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("gaizki db");
		}

		if (conf.locale.equals(myLocale)) {
			lang = "ES";
		} else {
			lang = "EU";
		}
		System.out.println("create lang= " + lang + " locale: "
				+ getResources().getConfiguration().locale);
		et = (EditText) findViewById(R.id.bilatu);

		/**
		 * EditText- bilatu
		 */
		et.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				String st = null;
				if (et.length() > 0) {
					st = et.getText().toString();
					et1=et.getText().toString();
				} else {
					st = "";
					et1="";
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
				if (lang == "ES") {
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
						getBaseContext(), android.R.layout.simple_list_item_1,
						hiz);

				setListAdapter(adapter);

				ListView lv = getListView();
				// listening to single list item on click
				lv.setOnItemClickListener(new OnItemClickListener() {
					@SuppressLint("NewApi")
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						Intent i = new Intent(getApplicationContext(),
								HondakinHautatua.class);
						Hondakina hon = (Hondakina) h.get(position);
						i.putExtra("izen", (String) hon.getName());
						i.putExtra("non", (String) hon.getNon());
						i.putExtra("info", (String) hon.getInfo());
						startActivity(i);
						HondakinActivity.this.overridePendingTransition(
								R.anim.animazioa, R.anim.animazioa2);
					}
				});

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}
		});

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
				Intent i = new Intent(getApplicationContext(),
						HMotaActivity.class);
				startActivity(i);
			}
		});
		bilaketa.setOnClickListener(new OnClickListener() {
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
		hizkuntza.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("clik lang= " + lang + " locale: "
						+ getResources().getConfiguration().locale);
				if (lang == "EU") { 
					db.close();
					lang = "ES";
					Toast.makeText(HondakinActivity.this, "Idioma: Español",
							Toast.LENGTH_SHORT).show();
					setLocale("ge");

				} else {
					db.close();
					lang = "EU";
					Toast.makeText(HondakinActivity.this, "Hizkuntza: Euskara",
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
		Intent refresh = new Intent(this, HondakinActivity.class);
		startActivity(refresh);
		finish();
	}
}
