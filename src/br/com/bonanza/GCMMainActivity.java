package br.com.bonanza;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ToggleButton;

import com.google.android.gcm.GCMRegistrar;

public class GCMMainActivity extends Activity {

	String TAG = "Bonanza Supermercados";
	private AlertDialog alerta;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		GCMRegistrar.checkDevice(this);
		GCMRegistrar.checkManifest(this);

		// Register Device Button
		Button regbtn = (Button) findViewById(R.id.register);

		regbtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(TAG, "Registering device");
				// Retrive the sender ID from GCMIntentService.java
				// Sender ID will be registered into GCMRegistrar
				GCMRegistrar.register(GCMMainActivity.this,
						GCMIntentService.SENDER_ID);
				
				String id = GCMRegistrar.getRegistrationId(GCMMainActivity.this);
				
				AlertDialog.Builder builder = new AlertDialog.Builder(
						GCMMainActivity.this);
				
				builder.setMessage(id);
				
				
				builder.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								try {

								} catch (ActivityNotFoundException e) {

									e.printStackTrace();
								}

							}
						});
				
				alerta = builder.create();// cria o alertDialog
				alerta.show();// exibe
			}
		});
		
		
		// ativa as notificações
		//-------------------------------------------------------------------------
		
		final ToggleButton tbutton = (ToggleButton) findViewById(R.id.toggleButton1);
		
		tbutton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				   if (tbutton.isChecked()) {
			            tbutton.setTextSize(13.0f);
			            SharedPreference sp = new SharedPreference(GCMMainActivity.this);
			            sp.registrar(true);
			            
			        } else {
			            SharedPreference sp = new SharedPreference(GCMMainActivity.this);
			            sp.registrar(false);
			        }
				
			}
		});
	}
}