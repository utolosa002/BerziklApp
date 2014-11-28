package gipuzkoa.hondakinak;

import java.io.IOException;
import java.util.Locale;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	public static String lang = "EU";
	public static String et1 = "";
	public static int pos = 0;
	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		outState.putString("TEXT", et1);
		outState.putString("LANG", lang);
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		DatabaseHandler db = new DatabaseHandler(this);
		et1 = savedInstanceState.getString("TEXT");
		lang =savedInstanceState.getString("LANG");
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
		super.onRestoreInstanceState(savedInstanceState);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		  FragmentManager fragmentManager = getSupportFragmentManager();
		  if (position == 0) {
			  //Toast.makeText(this, "Nav pos0: "+position+et1, Toast.LENGTH_SHORT).show();
				this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
				fragmentManager.beginTransaction()
				.replace(R.id.container, HondakinFragment.newInstance(et1)).commit();
		  } else if (position == 1) {
				this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
				fragmentManager.beginTransaction()
				.replace(R.id.container, HMotaFragment.newInstance()).commit();
		  } else if (position == 2) {
				this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				fragmentManager.beginTransaction()
				.replace(R.id.container, AsteaFragment.newInstance()).commit();
		  }
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.bilaketa);
			break;
		case 2:
			mTitle = getString(R.string.motak);
			break;
		case 3:
			mTitle = getString(R.string.ordutegia);
			break;
		}
	}

	@SuppressWarnings("deprecation")
	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.global, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			
			if (lang == "EU") { 
				//db.close();
				lang = "ES";
				Toast.makeText(MainActivity.this, "Idioma: Español",
						Toast.LENGTH_SHORT).show();
				setLocale("ge");

			} else {
			//	db.close();
				lang = "EU";
				Toast.makeText(MainActivity.this, "Hizkuntza: Euskara",
						Toast.LENGTH_SHORT).show();
				setLocale("");
			}
			return true;
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
		Intent refresh = new Intent(this, MainActivity.class);
		startActivity(refresh);
		finish();
	}
}
