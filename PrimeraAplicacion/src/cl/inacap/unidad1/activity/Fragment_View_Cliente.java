package cl.inacap.unidad1.activity;
import java.util.ArrayList;

import cl.inacap.unidad1.SqlLite.SQLite;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Fragment_View_Cliente extends Fragment  {
	EditText nombre ;
    EditText direccion ;
    EditText fono;
    String id;
 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancedState){
     View rootView = inflater.inflate(R.layout.fragment_view_cliente, container, false);
     id = getArguments().getString("Key");
     String press = getArguments().getString("press");
     nombre = (EditText)rootView.findViewById(R.id.edit_nombre);
	 direccion = (EditText)rootView.findViewById(R.id.edit_direccion);
	 fono = (EditText)rootView.findViewById(R.id.edit_telefono);
     Button btn_eliminar = (Button)rootView.findViewById(R.id.btn_delete_cliente);
	 Button btn_modificar = (Button)rootView.findViewById(R.id.btn_update_cliente);
	 btn_eliminar.setVisibility(View.INVISIBLE);
	 btn_modificar.setVisibility(View.INVISIBLE);
	 if(press.equals("2"))
		{
			btn_eliminar.setVisibility(View.VISIBLE);
			traerCliente();
			nombre.setEnabled(false);
			direccion.setEnabled(false);
			fono.setEnabled(false);
			}
		else
		if(press.equals("1"))
		{
			//String nombre = ""; 
			btn_modificar.setVisibility(View.VISIBLE);
			traerCliente();				
		}
	 btn_modificar.setOnClickListener(new OnClickListener(){// Boton modifica clientes

			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
				dialog.setTitle("Modificacion");
				dialog.setMessage("Seguro que desea modificar los datos de cliente ??");
				dialog.setCancelable(false);
				dialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
				 
				  @Override
			    public void onClick(DialogInterface dialog, int which) {
			    SQLite s = new SQLite(getActivity());
				s.abrir();
			    s.update(nombre.getText().toString(),direccion.getText().toString(),Integer.parseInt(fono.getText().toString()),Integer.parseInt(id));
				s.cerrar();
				Fragment_Home d = new Fragment_Home ();   
    			FragmentManager fragmentManager = getFragmentManager();
    			fragmentManager
    					.beginTransaction()
    					.replace(R.id.container,d)
    					.commit();
				  }
				});
				dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
				 
				   @Override
				   public void onClick(DialogInterface dialog, int which) {
				      dialog.cancel();
				      Fragment_Home d = new Fragment_Home ();   
		    			FragmentManager fragmentManager = getFragmentManager();
		    			fragmentManager
		    					.beginTransaction()
		    					.replace(R.id.container,d)
		    					.commit();
				   }
				});
				dialog.show();			
			}});
	 btn_eliminar.setOnClickListener(new OnClickListener(){// Boton elimina clientes

			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
				dialog.setTitle("Eliminar");
				dialog.setMessage("Seguro que desea eliminar los datos de cliente ??");
				dialog.setCancelable(false);
				dialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
				 
				  @Override
			    public void onClick(DialogInterface dialog, int which) {
					  
			    SQLite s = new SQLite(getActivity());
				s.abrir();
				s.deleteContact(Integer.parseInt(id));	
				s.cerrar();
				  Fragment_Home d = new Fragment_Home ();   
	    			FragmentManager fragmentManager = getFragmentManager();
	    			fragmentManager
	    					.beginTransaction()
	    					.replace(R.id.container,d)
	    					.commit();
				
				  }
				});
				dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
				 
				   @Override
				   public void onClick(DialogInterface dialog, int which) {
				      dialog.cancel();
				      Fragment_Home d = new Fragment_Home ();   
		    			FragmentManager fragmentManager = getFragmentManager();
		    			fragmentManager
		    					.beginTransaction()
		    					.replace(R.id.container,d)
		    					.commit();
				    
				   }
				});
				dialog.show();
				
				
			}});
			
	
	 
     
     return rootView;
 }
 public void traerCliente()
 {
 	SQLite s = new SQLite(getActivity());
		s.abrir();
	    Cursor c = s.getRegistro(Integer.parseInt(id));
	   // resultado.setText("");
	   
		if(c.moveToFirst())
		{
			do
			{
			nombre.setText(c.getString(1));
			direccion.setText(c.getString(2));
			fono.setText(c.getString(3));
			}
			while(c.moveToNext());
			
		}		
		 s.cerrar();	
 }
/** IMPORTANTE: Sobrecargar el método de BaseFragment **/
 public void onCreateOptionsMenu(Menu menu, ActionBar actionBar, MenuInflater inflater){
     actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE |    ActionBar.DISPLAY_HOME_AS_UP);
     inflater.inflate(R.menu.fragment_view_resumen, menu);
 }
 
 @Override
 public boolean onOptionsItemSelected(MenuItem item) {
     //Sobreescribimos la acción al pulsar sobre los iconos del menú del fragment
     return super.onOptionsItemSelected(item);
 }


	
}


