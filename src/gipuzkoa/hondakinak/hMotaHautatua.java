package gipuzkoa.hondakinak;

<<<<<<< HEAD
import java.io.IOException;
=======
>>>>>>> c4383c9500fb96e972bb52fb01c57a4ffa142ac6
import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class hMotaHautatua extends ListActivity {

	private ArrayList<String> hiz;
	private ArrayList<Hondakina> h;

	public final DatabaseHandler db = new DatabaseHandler(this);

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.hmota1);
		LinearLayout llall = (LinearLayout) findViewById(R.id.llall);
		TextView txtIzen = (TextView) findViewById(R.id.izen);
		TextView tit = (TextView) findViewById(R.id.tv0);
		TextView txtEguna = (TextView) findViewById(R.id.eguna);
		TextView txtOrdu = (TextView) findViewById(R.id.ordu);
		ListView lv = (ListView) findViewById(R.id.list);
		ImageView im = (ImageView) findViewById(R.id.izkina);
		ImageView motairudi = (ImageView) findViewById(R.id.hmota);
		Intent i = getIntent();
		// getting attached intent data
		String mota;
		String izen = i.getStringExtra("izena");
		String eguna = i.getStringExtra("eguna");
		String ordua = i.getStringExtra("ordua");
		int irudia = 0;
		irudia = i.getIntExtra("irudia", irudia);
		im.setImageResource(irudia);
		// displaying selected product name
		tit.setText(izen);
		txtIzen.setText(izen);
		txtIzen.setFocusable(false);
		txtEguna.setText(eguna);
		txtEguna.setFocusable(false);
		txtOrdu.setText(ordua);
		txtOrdu.setFocusable(false);
		motairudi.setImageResource(irudia);
		mota = txtIzen.getText().toString();
		if (txtIzen.getText().toString().contains("Ontzi arina")
				|| txtIzen.getText().toString().contains("Envase ligero")) {
			llall.setBackgroundColor(Color.parseColor("#FFD700"));
			if (txtIzen.getText().toString().contains("Ontzi arina")) {
				im.setImageResource(R.drawable.ontziakizk);
			} else {
				im.setImageResource(R.drawable.envasesizk);
				mota = "Envas";
			}
			motairudi.setImageResource(irudia);
			tit.setTextColor(Color.parseColor("#000000"));
			txtEguna.setTextColor(Color.parseColor("#000000"));
			txtIzen.setTextColor(Color.parseColor("#000000"));
			txtOrdu.setTextColor(Color.parseColor("#000000"));
			lv.setBackgroundColor(Color.parseColor("#000000"));
		} else if (txtIzen.getText().toString().contains("Organikoa")
				|| txtIzen.getText().toString().contains("Orgánico")) {
			llall.setBackgroundColor(Color.parseColor("#663300"));
			if (txtIzen.getText().toString().contains("Organikoa")) {
				im.setImageResource(R.drawable.organikoaizk);
			} else {
				im.setImageResource(R.drawable.organicoizk);
				mota = "Org";
			}
		} else if (txtIzen.getText().toString().contains("Papera/kartoia")
				|| txtIzen.getText().toString().contains("Papel/Cartón")) {
			llall.setBackgroundColor(Color.parseColor("#0000FF"));
			if (txtIzen.getText().toString().contains("Papera/kartoia")) {
				im.setImageResource(R.drawable.paperaizk);
				mota = "Paper";
			} else {
				im.setImageResource(R.drawable.papelizk);
				mota = "Papel";
			}
		} else if (txtIzen.getText().toString().contains("Iglu berdera")
				|| txtIzen.getText().toString().contains("Al iglú verde")) {
			llall.setBackgroundColor(Color.parseColor("#00FF00"));
			if (txtIzen.getText().toString().contains("Iglu berdera")) {
				im.setImageResource(R.drawable.beiraizk);

			} else {
				mota = "Al igl";
				im.setImageResource(R.drawable.vidrioizk);
			}
		} else if (txtIzen.getText().toString().contains("Errefusa")
				|| txtIzen.getText().toString().contains("Rechazo")) {
			llall.setBackgroundColor(Color.parseColor("#808080"));
			if (txtIzen.getText().toString().contains("Errefusa")) {
				im.setImageResource(R.drawable.errefusaizk);
			} else {
				im.setImageResource(R.drawable.rechazoizk);
			}
		} else if (txtIzen
				.getText()
				.toString()
				.contains(
						"Konpresak eta" + Html.fromHtml("<br />") + " pardelak")
				|| txtIzen
						.getText()
						.toString()
						.contains(
								"Compresas y" + Html.fromHtml("<br />")
										+ " Pañales")) {
			llall.setBackgroundColor(Color.parseColor("#808080"));
			if (txtIzen
					.getText()
					.toString()
					.contains(
							"Konpresak eta" + Html.fromHtml("<br />")
									+ " pardelak")) {
				im.setImageResource(R.drawable.errefusaizk);
			} else {
				im.setImageResource(R.drawable.rechazoizk);
			}
		} else if (txtIzen.getText().toString().contains("Garbigunera")
				|| txtIzen.getText().toString().contains("Al garbigune")) {
			llall.setBackgroundColor(Color.parseColor("#7392B5"));
			im.setImageResource(irudia);
		} else if (txtIzen.getText().toString().contains("Pilak")
				|| txtIzen.getText().toString().contains("Pilas")) {
			llall.setBackgroundColor(Color.parseColor("#FFFF00"));
			im.setImageResource(irudia);
		} else if (txtIzen.getText().toString().contains("Arropa")
				|| txtIzen.getText().toString().contains("Ropa")) {
			llall.setBackgroundColor(Color.parseColor("#FF00FF"));
			if (txtIzen.getText().toString().contains("Arropa")) {
				mota = "Oihalen edukiontzira";
			} else {
				mota = "Al contenedor";
			}
			im.setImageResource(irudia);
		} else {
			llall.setBackgroundColor(Color.parseColor("#FFFFFF"));
			im.setImageResource(irudia);
		}
		if (HondakinActivity.lang == "ES") {
<<<<<<< HEAD
			try {
				hiz = db.getNombresResiduos(mota, "non");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				h = db.getResiduos(mota, "non");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				hiz = db.getHondakinIzenak(mota, "non");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				h = db.getHondakinak(mota, "non");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
=======
			hiz = db.getNombresResiduos(mota, "non");
			h = db.getResiduos(mota, "non");
		} else {
			hiz = db.getHondakinIzenak(mota, "non");
			h = db.getHondakinak(mota, "non");
>>>>>>> c4383c9500fb96e972bb52fb01c57a4ffa142ac6
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getBaseContext(), android.R.layout.simple_list_item_1, hiz);
		setListAdapter(adapter);
		lv = this.getListView();
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(getApplicationContext(),
						HondakinHautatua.class);
				Hondakina hon = (Hondakina) h.get(position);
				i.putExtra("izen", (String) hon.getName());
				i.putExtra("non", (String) hon.getNon());
				i.putExtra("info", (String) hon.getInfo());
				startActivity(i);
				hMotaHautatua.this.overridePendingTransition(R.anim.animazioa,
						R.anim.animazioa2);
			}
		});
		im.setOnClickListener(new View.OnClickListener() {
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				finish();
				hMotaHautatua.this.overridePendingTransition(R.anim.animazioa3,
						R.anim.animazioa4);
			}
		});
	}
}