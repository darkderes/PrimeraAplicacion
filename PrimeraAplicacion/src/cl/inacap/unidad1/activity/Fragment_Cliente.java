package cl.inacap.unidad1.activity;
import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cl.inacap.unidad1.SqlLite.SQLite;

public class Fragment_Cliente extends Fragment {
 
 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancedState){
     View rootView = inflater.inflate(R.layout.fragment_fragment_cliente, container, false);
     Button btn_add = (Button)rootView.findViewById(R.id.btn_agregarCl);
		final EditText nombre = (EditText)rootView.findViewById(R.id.txt_nombre);
		final EditText direccion = (EditText)rootView.findViewById(R.id.txt_direccion);
		final EditText telefono = (EditText)rootView.findViewById(R.id.txt_telefono);
		
		btn_add.setOnClickListener(new OnClickListener(){// Boton ingresa clientes a base de datos.

			@Override
			public void onClick(View v) {
				
				SQLite s = new SQLite(getActivity());
				s.abrir();
				s.insertar(nombre.getText().toString(),direccion.getText().toString(),Integer.parseInt(telefono.getText().toString()));
				s.cerrar();
				Toast.makeText(getActivity(),"Cliente ingresado correctamente",Toast.LENGTH_SHORT).show();
				/*Intent intent = new Intent(Add_ClienteActivity.this,MenuClientesActivity.class);		
				Add_ClienteActivity.this.startActivity(intent);*/
			}});
     return rootView;
 }
 
/** IMPORTANTE: Sobrecargar el método de BaseFragment **/
 public void onCreateOptionsMenu(Menu menu, ActionBar actionBar, MenuInflater inflater){
     actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE |    ActionBar.DISPLAY_HOME_AS_UP);
     inflater.inflate(R.menu.fragment_cliente, menu);
 }
 
 @Override
 public boolean onOptionsItemSelected(MenuItem item) {
     //Sobreescribimos la acción al pulsar sobre los iconos del menú del fragment
     return super.onOptionsItemSelected(item);
 }
}

