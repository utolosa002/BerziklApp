package gipuzkoa.hondakinak;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("NewApi")
public class HondakinHautatua extends Activity {
	// getting attached intent data
	/*String izen,info,non="";
	Intent i;
	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		i = new Intent(getApplicationContext(),
				this.getClass());
		i.putExtra("izen", izen.toString());
		i.putExtra("non", non.toString());
		i.putExtra("info", info.toString());
		savedInstanceState.putString("LANG", HondakinActivity.lang);
		savedInstanceState.putAll(savedInstanceState);
		super.onSaveInstanceState(savedInstanceState);

	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		HondakinActivity.lang = (savedInstanceState.getString("LANG"));
		if (HondakinActivity.lang=="ES"){
			setLocale("ge");
		}else{
			setLocale("");
		}

		i = getIntent();
		// getting attached intent data
		izen = i.getStringExtra("izen");
		info = i.getStringExtra("info");
		non = i.getStringExtra("non");
		super.onRestoreInstanceState(savedInstanceState);
	}
*/
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.hondakin1);
		LinearLayout ll = (LinearLayout) findViewById(R.id.lla);
		TextView txtIzen = (TextView) findViewById(R.id.izen);
		TextView tit = (TextView) findViewById(R.id.tv0);
		TextView txtNon = (TextView) findViewById(R.id.non);
		TextView txtInfo = (TextView) findViewById(R.id.info);
		TextView txtOrdu = (TextView) findViewById(R.id.ordu);
		ImageView im = (ImageView) findViewById(R.id.imageView1);
		Button b = (Button) findViewById(R.id.botoi);

		Intent i = getIntent();
		// getting attached intent data
		String izen = i.getStringExtra("izen");
		String info = i.getStringExtra("info");
		String non = i.getStringExtra("non");
		// displaying selected product name
		tit.setText(izen);
		txtIzen.setText(izen);
		txtIzen.setFocusable(false);
		txtInfo.setText(info);
		txtNon.setText(non);
		txtInfo.setFocusable(false);
		txtNon.setFocusable(false);
		
		b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
				HondakinHautatua.this.overridePendingTransition(R.anim.animazioa3,
	                    R.anim.animazioa4);
			}
		});
		if (txtNon.getText().toString().contains("Ontzi arina")||txtNon.getText().toString().contains("Envase ligero")) {
			im.setImageResource(R.drawable.plastikoa);
			ll.setBackgroundColor(Color.parseColor("#FFFF00"));
			tit.setTextColor(Color.parseColor("#000000"));
			txtInfo.setTextColor(Color.parseColor("#000000"));
			txtNon.setTextColor(Color.parseColor("#000000"));
			txtIzen.setTextColor(Color.parseColor("#000000"));
			if(txtNon.getText().toString().contains("Ontzi arina")){
				txtOrdu.setText("Astearte eta ostiraletan jaso." + Html.fromHtml("<br />") +" Aurreko eguneko 20:00-07:00");
			}else{
				txtOrdu.setText("Recogida: Martes y viernes." + Html.fromHtml("<br />") +" 20:00 del día anterior - hasta las 07:00");
			}
			txtOrdu.setTextColor(Color.parseColor("#000000"));
		} else if (txtNon.getText().toString().contains("Organikoa")||txtNon.getText().toString().contains("Orgánico")) {
			im.setImageResource(R.drawable.organikoa);
			ll.setBackgroundColor(Color.parseColor("#663300"));
			if(txtNon.getText().toString().contains("Organikoa")){
				txtOrdu.setText("Astelehen, ostegun eta Larunbatetan jaso." + Html.fromHtml("<br />") +" Aurreko eguneko 20:00-07:00etara");
			}else{
				txtOrdu.setText("Recogida: Lunes, Jueves y Sábado." + Html.fromHtml("<br />") +" 20:00 del día anterior - hasta las 07:00");
			}
		} else if (txtNon.getText().toString().contains("Papera/kartoia")||txtNon.getText().toString().contains("Papel/Cartón")) {
			im.setImageResource(R.drawable.papera);
			ll.setBackgroundColor(Color.parseColor("#0000FF"));
			if(txtNon.getText().toString().contains("Papera/kartoia")){
			txtOrdu.setText("Asteazkenean jaso." + Html.fromHtml("<br />") +"Aurreko eguneko 20:00-07:00");
			}else{
				txtOrdu.setText("Recogida: Míercoles." + Html.fromHtml("<br />") +" 20:00 del día anterios - hasta las 07:00");
			}
		} else if (txtNon.getText().toString().contains("Iglu berdera")||txtNon.getText().toString().contains("Al iglú verde")) {
			im.setImageResource(R.drawable.beira);
			ll.setBackgroundColor(Color.parseColor("#00FF00"));
			if(txtNon.getText().toString().contains("Iglu berdera")){
				txtOrdu.setText("Nahi duzunean");
			}else{
				txtOrdu.setText("Cuando quieras");
			}
		} else if (txtNon.getText().toString().contains("Errefusa")||txtNon.getText().toString().contains("Rechazo")) {
			
			im.setImageResource(R.drawable.errefusa);
			ll.setBackgroundColor(Color.parseColor("#808080"));
			if(txtNon.getText().toString().contains("Errefusa")){
				if (tit.getText().toString().contains("Konpresa")||tit.getText().toString().contains("Pixoihala")||tit.getText().toString().contains("pañala")) {
					txtOrdu.setText("Egunero." + Html.fromHtml("<br />") +" Aurreko eguneko 20:00-07:00");
				}else{
					txtOrdu.setText("Igandean jaso." + Html.fromHtml("<br />") +" Larunbateko 20:00tatik-Igandeko 07:00etara");
				}
			}else{
				if (tit.getText().toString().contains("Compresa")||tit.getText().toString().contains("Pañal")) {
					txtOrdu.setText("Cualquier día." + Html.fromHtml("<br />") +" 20:00 del día anterior - hasta las 07:00");
				}else{
				txtOrdu.setText("Recogida: Domingo." + Html.fromHtml("<br />") +" 20:00 del día anterior - hasta las 07:00");
				}
			}
		} else if (txtNon.getText().toString().contains("Garbigunera")||txtNon.getText().toString().contains("Al garbigune")) {
			im.setImageResource(R.drawable.garbi);
			ll.setBackgroundColor(Color.parseColor("#7392B5"));
			if(txtNon.getText().toString().contains("Garbigunera")){
				txtOrdu.setText("Oiartzungo Garbigunea" + Html.fromHtml("<br />") +"NON: Talaia industriagunea." + Html.fromHtml("<br />") +"ORDUTEGIA: Astelehenetik larunbatera 10:00 – 13:00" + Html.fromHtml("<br />") +"Astelehenetan, asteazkenetan eta ostiraletan 17:00 – 20:00");
			}else{
				txtOrdu.setText("Al Garbigune de Oiartzun " + Html.fromHtml("<br />") +"Dónde: Talaia industriagunea." + Html.fromHtml("<br />") +"Horario: De Lunes a Sábado 10:00 – 13:00" + Html.fromHtml("<br />") +"Lunes, Míercoles y viernes 17:00 – 20:00");
			}
		} else {
			im.setImageResource(R.drawable.morea);
			ll.setBackgroundColor(Color.parseColor("#FF00FF"));
			if(txtNon.getText().toString().contains("ropa")){
				txtOrdu.setText("Cuando quieras");
			}else{
				txtOrdu.setText("Nahi duzunean");
			}
		}
	}
}