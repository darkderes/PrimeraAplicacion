package cl.inacap.unidad1.activity;
import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
     Button btn_view = (Button)rootView.findViewById(R.id.btn_view_cliente);
     Button btn_modificar = (Button)rootView.findViewById(R.id.Btn_Modificar);
     Button btn_eliminar = (Button)rootView.findViewById(R.id.Btn_Eliminar);
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
			}});
		btn_view.setOnClickListener(new OnClickListener(){// Boton ingresa clientes a base de datos.

			@Override
			public void onClick(View v) {
				llamar("0");
			}});
		btn_modificar.setOnClickListener(new OnClickListener(){// Boton ingresa clientes a base de datos.

			@Override
			public void onClick(View v) {
				llamar("1");
			}});
		btn_eliminar.setOnClickListener(new OnClickListener(){// Boton ingresa clientes a base de datos.

			@Override
			public void onClick(View v) {
				llamar("2");
			}});
     return rootView;
 }
 public void llamar(String x)
 {
	 Fragment_View_Clientes s = new Fragment_View_Clientes();
	         Bundle parametro = new Bundle();
	         parametro.putString("Key",x);
	         s.setArguments(parametro);
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager
						.beginTransaction()
						.replace(R.id.container,s)
						.commit();
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

