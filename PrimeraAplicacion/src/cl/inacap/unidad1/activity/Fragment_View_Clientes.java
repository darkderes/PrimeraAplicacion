package cl.inacap.unidad1.activity;
import java.util.ArrayList;

import cl.inacap.unidad1.SqlLite.SQLite;
import android.app.ActionBar;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Fragment_View_Clientes extends Fragment  {
	private ArrayAdapter<String> adapter;
	static String press;
     ListView lv_pedido;
 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancedState){
     View rootView = inflater.inflate(R.layout.fragment_fragment_view_resumen, container, false);
     lv_pedido = (ListView)rootView.findViewById(R.id.lv_pedido) ;
		
		SQLite s = new SQLite(getActivity());
		s.abrir();
		Cursor cursor = s.getRegistros();
		ArrayList<String> listData = s.getFormatListUniv(cursor);
		adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1  , listData );	
		lv_pedido.setAdapter(adapter);
		press = getArguments().getString("Key");
		
		adapter.notifyDataSetChanged();
		if( listData.size()== 0 )
		{
			Toast.makeText(getActivity(), "No existen registros"  ,Toast.LENGTH_SHORT).show();
		}
		s.cerrar();
		lv_pedido.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                // TODO Auto-generated method stub
            	if(press.toString().equals("1")||press.toString().equals("2"))
            	{
            	Object object = lv_pedido.getItemAtPosition( position );
            	//Se extrae el ID = [X] 
            	//String datos = object.toString(); 
            	int posicionInicial = object.toString().indexOf("[") + 1; 
            	int posicionFinal = object.toString().indexOf("]",posicionInicial); 
            	String id_position =  object.toString().substring(posicionInicial,posicionFinal);
            	Fragment_View_Cliente s = new Fragment_View_Cliente();   
            	Bundle parametro = new Bundle();
            	parametro.putString("Key",id_position);
            	parametro.putString("press",press);
            	s.setArguments(parametro);
    			FragmentManager fragmentManager = getFragmentManager();
    			fragmentManager
    					.beginTransaction()
    					.replace(R.id.container,s)
    					.commit();
            	}

            }
        });
		
		
     return rootView;
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

