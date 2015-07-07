package cl.inacap.unidad1.activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment_Home extends Fragment {
 
 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancedState){
     View rootView = inflater.inflate(R.layout.fragment_fragment_home, container, false);
     Button salir = (Button)rootView.findViewById(R.id.btn_salir);
     
     salir.setOnClickListener(new OnClickListener(){// Boton ingresa clientes a base de datos.

			@Override
			public void onClick(View v) {
				AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
				dialog.setTitle("¿Seguro desea salir?");
				dialog.setMessage(" se perderan sus datos ingresados");
				dialog.setCancelable(false);
				dialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
				 
				  @Override
			    public void onClick(DialogInterface dialog, int which) {
				   
				   System.exit(0);
				  }
				});
				dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
				 
				   @Override
				   public void onClick(DialogInterface dialog, int which) {
				      dialog.cancel();
				   }
				});
				dialog.show();
			}});
     return rootView;
 }
 
/** IMPORTANTE: Sobrecargar el método de BaseFragment **/
 public void onCreateOptionsMenu(Menu menu, ActionBar actionBar, MenuInflater inflater){
     actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE |    ActionBar.DISPLAY_HOME_AS_UP);
     inflater.inflate(R.menu.fragment_home, menu);
 }
 
 @Override
 public boolean onOptionsItemSelected(MenuItem item) {
     //Sobreescribimos la acción al pulsar sobre los iconos del menú del fragment
     return super.onOptionsItemSelected(item);
 }
}

