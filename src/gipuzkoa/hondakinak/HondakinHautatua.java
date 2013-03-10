package gipuzkoa.hondakinak;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HondakinHautatua extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.hondakin1);
		TextView txtIzen = (TextView) findViewById(R.id.izen);
		TextView tit = (TextView) findViewById(R.id.tv0);
		TextView txtNon = (TextView) findViewById(R.id.non);
		TextView txtInfo = (TextView) findViewById(R.id.info);
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
		} else if (txtNon.getText().toString().contains("Organikoa")||txtNon.getText().toString().contains("Orgánico")) {
			im.setImageResource(R.drawable.marroia);
		} else if (txtNon.getText().toString().contains("Papera/kartoia")||txtNon.getText().toString().contains("Papel/Cartón")) {
			im.setImageResource(R.drawable.urdina);
		} else if (txtNon.getText().toString().contains("Iglu berdera")||txtNon.getText().toString().contains("Al iglú verde")) {
			im.setImageResource(R.drawable.berdea);
		} else if (txtNon.getText().toString().contains("Errefusa")||txtNon.getText().toString().contains("Rechazo")) {
			im.setImageResource(R.drawable.errefusa);
		} else if (txtNon.getText().toString().contains("Garbigunera")||txtNon.getText().toString().contains("Al garbigune")) {
			im.setImageResource(R.drawable.garbi);
		} else {
			im.setImageResource(R.drawable.morea);
		}
	}
}