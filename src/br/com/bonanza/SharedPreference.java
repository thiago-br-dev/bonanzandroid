package br.com.bonanza;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {

	private Context context;

	// -------------------------------------------------------------------------------
	public SharedPreference(Context context) {

		this.context = context;
	}

	// -------------------------------------------------------------------------------
	public void registrar(boolean chave) {

		// aqui salvar o sharedpreferences
		@SuppressWarnings("static-access")
		SharedPreferences prefs = context.getSharedPreferences("SETTINGS",
				context.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putBoolean("chave", chave);

		editor.commit();

	}

	// -------------------------------------------------------------------------------
	public boolean recuperar() {
		@SuppressWarnings("static-access")
		SharedPreferences prefs = context.getSharedPreferences("SETTINGS",
				context.MODE_PRIVATE);

		boolean chave = prefs.getBoolean("chave", false);

		return chave;
	}

	// -------------------------------------------------------------------------------
}
