package cl.inacap.unidad1.activity;

import cl.inacap.unidad1.clases.Usuario;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		Button btn_ingresar = (Button)findViewById(R.id.btn_salir);
		
		btn_ingresar.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				validarLoginUsuario();
				
			}});
		
	}

	public void validarLoginUsuario()//funcion que valida usuario y pasa parametro al activity(ClientesActivity) para filtrar 
	{
		EditText txt_login = (EditText)findViewById(R.id.txt_login);
		EditText txt_contrasena = (EditText)findViewById(R.id.txt_contrasena);
		Usuario usuario = new Usuario();
		if(usuario.validarLogin(txt_login.getText().toString(),txt_contrasena.getText().toString()))
		{
			//Toast.makeText(LoginActivity.this,"Usuario Correcto",Toast.LENGTH_SHORT).show();
            finish();
			Intent intent = new Intent(LoginActivity.this,Principal.class);
			//intent.putExtra("parametro",txt_login.getText().toString());
			LoginActivity.this.startActivity(intent);	
			txt_login.setText("");
			txt_contrasena.setText("");
		}
		else
		{
			//Toast.makeText(LoginActivity.this,"Usuario o contrase�a incorrecto",Toast.LENGTH_SHORT).show();
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
