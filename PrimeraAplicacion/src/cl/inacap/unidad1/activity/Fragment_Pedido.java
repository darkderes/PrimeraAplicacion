package cl.inacap.unidad1.activity;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cl.inacap.unidad1.SqlLite.SQLite;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Fragment_Pedido extends Fragment {
	Spinner producto;
	Spinner cliente;
	TextView pedido;
 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancedState){
     View rootView = inflater.inflate(R.layout.fragment_fragment__pedidos, container, false);
     producto = (Spinner)rootView.findViewById(R.id.sp_producto);
     cliente = (Spinner)rootView.findViewById(R.id.sp_cliente);
     pedido = (TextView)rootView.findViewById(R.id.txt_pedido);
     final EditText cantidad = (EditText)rootView.findViewById(R.id.txt_cantidad);
	 final EditText precio = (EditText)rootView.findViewById(R.id.txt_precio);
	 Button boton = (Button)rootView.findViewById(R.id.btn_add_pedido);
     cargaProducto();
     cargaCliente();
     NumeroPedido();
     boton.setOnClickListener(new OnClickListener(){// Boton abre memu de clientes

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				
				SQLite s = new SQLite(getActivity());
				s.abrir();
				int cant = Integer.parseInt(cantidad.getText().toString());
				int prec = Integer.parseInt(precio.getText().toString());
				int total = cant * prec;
				Date now = new Date();
				DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT);
				String s1 = df1.format(now);			
				s.insertarPedido(cliente.getSelectedItem().toString(),producto.getSelectedItem().toString(),cant,prec,total,s1);
				s.cerrar();
				AlertDialog.Builder builder =
		                new AlertDialog.Builder(getActivity());
		 
		        builder.setMessage("Pedido ingresado correctamente")
		               .setTitle("Informacion")
		               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		                   public void onClick(DialogInterface dialog, int id) {
		                       dialog.cancel();
		                       
		                   }
		               });
		        
		        builder.show();
				
			}});
	
     return rootView;
 }
 public void NumeroPedido()//funcion trae el ultimo numero de pedido ingresado 
 {
 	int num = 0;
 	SQLite s = new SQLite(getActivity());
 	s.abrir();
 	num = s.getUltimoID()+1;
 	pedido.setText("PO :"+Integer.toString(num));
 	s.cerrar();
 }
/** IMPORTANTE: Sobrecargar el método de BaseFragment **/
 public void onCreateOptionsMenu(Menu menu, ActionBar actionBar, MenuInflater inflater){
     actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE |    ActionBar.DISPLAY_HOME_AS_UP);
     inflater.inflate(R.menu.fragment_pedido, menu);
 }
 
 @Override
 public boolean onOptionsItemSelected(MenuItem item) {
     //Sobreescribimos la acción al pulsar sobre los iconos del menú del fragment
     return super.onOptionsItemSelected(item);
 }
	public void cargaProducto()// carga spinner producto
	{
		ArrayAdapter <CharSequence> adaptador = ArrayAdapter.createFromResource(getActivity(),R.array.producto,android.R.layout.simple_spinner_dropdown_item);
		producto.setAdapter(adaptador);
	}
	  private void cargaCliente() { // carga spinner cliente
	        // database handler
	        
	 
	        // Spinner Drop down elements
	        List<String> lables = getAllLabels();
	 
	        // Creating adapter for spinner
	        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
	                android.R.layout.simple_spinner_item, lables);
	 
	        // Drop down layout style - list view with radio button
	        dataAdapter
	                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	 
	        // attaching data adapter to spinner
	        cliente.setAdapter(dataAdapter);
	    }
		public List<String> getAllLabels(){
	        List<String> labels = new ArrayList<String>();
	         
	        // Select All Query
	        SQLite s = new SQLite(getActivity());
	        s.abrir();
	        Cursor cursor = s.getRegistros();
	      
	        // looping through all rows and adding to list
	        if (cursor.moveToFirst()) {
	            do {
	                labels.add(cursor.getString(1));
	            } while (cursor.moveToNext());
	        }
	         
	        // closing connection
	    
	        s.cerrar();
	         
	        // returning lables
	        return labels; 
	       
	    }
}

