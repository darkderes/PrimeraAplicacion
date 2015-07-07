package cl.inacap.unidad1.activity;
import java.util.ArrayList;

import cl.inacap.unidad1.SqlLite.SQLite;
import android.app.ActionBar;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Fragment_Resumen extends Fragment {
	private ArrayAdapter<String> adapter;
 
 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancedState){
     View rootView = inflater.inflate(R.layout.fragment_fragment_resumen, container, false);
     ListView lv_pedido = (ListView)rootView.findViewById(R.id.lv_pedido) ;
		
		SQLite s = new SQLite(getActivity());
		s.abrir();
		Cursor cursor = s.getPedidos();	
		ArrayList<String> listData = s.getFormatListPedido(cursor);	
		adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1  , listData );	
		lv_pedido.setAdapter(adapter);
		
		//lvUsuarios.setOnItemClickListener( this );
	
		adapter.notifyDataSetChanged();
		if( listData.size()== 0 )
		{
			Toast.makeText(getActivity(), "No existen registros"  ,Toast.LENGTH_SHORT).show();
		}
		s.cerrar();
     return rootView;
 }
 
/** IMPORTANTE: Sobrecargar el método de BaseFragment **/
 public void onCreateOptionsMenu(Menu menu, ActionBar actionBar, MenuInflater inflater){
     actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE |    ActionBar.DISPLAY_HOME_AS_UP);
     inflater.inflate(R.menu.fragment_resumen, menu);
 }
 
 @Override
 public boolean onOptionsItemSelected(MenuItem item) {
     //Sobreescribimos la acción al pulsar sobre los iconos del menú del fragment
     return super.onOptionsItemSelected(item);
 }
}

