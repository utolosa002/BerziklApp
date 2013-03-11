package gipuzkoa.hondakinak;

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

public class HondakinHautatua extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.hondakin1);
		LinearLayout ll = (LinearLayout) findViewById(R.id.lla);
		TextView txtIzen = (TextView) findViewById(R.id.izen);
		TextView tit = (TextView) findViewById(R.id.tv0);
		TextView txtNon = (TextView) findViewById(R.id.non);
		TextView txtInfo = (TextView) findViewById(R.id.info);
		TextView txtordu = (TextView) findViewById(R.id.ordu);
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
			}
		});
		if (txtNon.getText().toString().contains("Ontzi arina")||txtNon.getText().toString().contains("Envase ligero")) {
			im.setImageResource(R.drawable.horia);
			ll.setBackgroundColor(Color.parseColor("#FFFF00"));
			tit.setTextColor(Color.parseColor("#000000"));
			txtInfo.setTextColor(Color.parseColor("#000000"));
			txtNon.setTextColor(Color.parseColor("#000000"));
			txtIzen.setTextColor(Color.parseColor("#000000"));
			if(txtNon.getText().toString().contains("Ontzi arina")){
				txtordu.setText("Astearte eta ostiraletan" + Html.fromHtml("<br />") +" 22:00-07:00");
			}else{
				txtordu.setText("Martes y viernes " + Html.fromHtml("<br />") +" 22:00-07:00");

			}
		} else if (txtNon.getText().toString().contains("Organikoa")||txtNon.getText().toString().contains("Orgánico")) {
			im.setImageResource(R.drawable.marroia);
			ll.setBackgroundColor(Color.parseColor("#663300"));
			if(txtNon.getText().toString().contains("Organikoa")){
				txtordu.setText("Astelehen, ostegun eta Larunbatetan" + Html.fromHtml("<br />") +" 22:00-07:00");
			}else{
				txtordu.setText("Lunes, Jueves y Sábado" + Html.fromHtml("<br />") +" 22:00-07:00");
			}
		} else if (txtNon.getText().toString().contains("Papera/kartoia")||txtNon.getText().toString().contains("Papel/Cartón")) {
			im.setImageResource(R.drawable.urdina);
			ll.setBackgroundColor(Color.parseColor("#0000FF"));
			if(txtNon.getText().toString().contains("Papera/kartoia")){
			txtordu.setText("Asteazkenean" + Html.fromHtml("<br />") +" 22:00-07:00");
			}else{
				txtordu.setText("Míercoles " + Html.fromHtml("<br />") +" 22:00-07:00");
			}
		} else if (txtNon.getText().toString().contains("Iglu berdera")||txtNon.getText().toString().contains("Al iglú verde")) {
			im.setImageResource(R.drawable.berdea);
			ll.setBackgroundColor(Color.parseColor("#00FF00"));
			if(txtNon.getText().toString().contains("Iglu berdera")){
				txtordu.setText("Nahi duzunean");
			}else{
				txtordu.setText("Cuando quieras");
			}
		} else if (txtNon.getText().toString().contains("Errefusa")||txtNon.getText().toString().contains("Rechazo")) {
			im.setImageResource(R.drawable.errefusa);
			ll.setBackgroundColor(Color.parseColor("#808080"));
			if(txtNon.getText().toString().contains("Errefusa")){
				txtordu.setText("Igandean" + Html.fromHtml("<br />") +" 22:00-07:00");
			}else{
				txtordu.setText("Domingo" + Html.fromHtml("<br />") +" 22:00-07:00");
			}
		} else if (txtNon.getText().toString().contains("Garbigunera")||txtNon.getText().toString().contains("Al garbigune")) {
			im.setImageResource(R.drawable.garbi);
			ll.setBackgroundColor(Color.parseColor("#7392B5"));
			if(txtNon.getText().toString().contains("Garbigunera")){
				txtordu.setText("Oiartzungo Garbigunea" + Html.fromHtml("<br />") +"NON: Talaia industriagunea." + Html.fromHtml("<br />") +"ORDUTEGIA: Astelehenetik larunbatera 10:00 – 13:00" + Html.fromHtml("<br />") +"Astelehenetan, asteazkenetan eta ostiraletan 17:00 – 20:00");
			}else{
				txtordu.setText("Al Garbigune de Oiartzun " + Html.fromHtml("<br />") +"Dónde: Talaia industriagunea." + Html.fromHtml("<br />") +"HORARIOS: De Lunes a Sábado 10:00 – 13:00" + Html.fromHtml("<br />") +"Lunes, Míercoles y viernes 17:00 – 20:00");
			}
		} else {
			im.setImageResource(R.drawable.morea);
			ll.setBackgroundColor(Color.parseColor("#FF00FF"));
			if(txtNon.getText().toString().contains("ropa")){
				txtordu.setText("Cuando quieras");
			}else{
			txtordu.setText("Nahi duzunean");
			}
		}
	}
}