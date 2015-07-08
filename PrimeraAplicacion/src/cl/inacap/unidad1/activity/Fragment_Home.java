package cl.inacap.unidad1.activity;


import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;



public class Fragment_Home extends Fragment {
	
	TextView lblEstado;
	TextView lblPrecision;
	TextView lblLongitud;
	TextView lblLatitud;
 
 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancedState){
     View rootView = inflater.inflate(R.layout.fragment_fragment_home, container, false);
     lblEstado = (TextView)rootView.findViewById(R.id.TxtEstado);
     lblPrecision = (TextView)rootView.findViewById(R.id.Txt_precision);
     lblLongitud = (TextView)rootView.findViewById(R.id.Txt_longitud);
     lblLatitud = (TextView)rootView.findViewById(R.id.Txt_latitud);
     Button salir = (Button)rootView.findViewById(R.id.btn_salir);
     //actualizarPosicion();
     
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
     actualizarPosicion();
     return rootView;
 }
 private void actualizarPosicion()
 {
     //Obtenemos una referencia al LocationManager
	 LocationManager locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
     
     //Obtenemos la última posición conocida
     Location location =
         locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
  
     //Mostramos la última posición conocida
     muestraPosicion(location);
  
     //Nos registramos para recibir actualizaciones de la posición
     LocationListener locationListener = new LocationListener() {
         public void onLocationChanged(Location location) {
             muestraPosicion(location);
         }
         public void onProviderDisabled(String provider){
             lblEstado.setText("GPS DESACTIVADO");
         }
         public void onProviderEnabled(String provider){
             lblEstado.setText("GPS ACTIVADO");
         }
         public void onStatusChanged(String provider, int status, Bundle extras){
             Log.i("LocAndroid", "Provider Status: " + status);
             lblEstado.setText("Provider Status: " + status);
         }
     };
  
     locationManager.requestLocationUpdates(
             LocationManager.GPS_PROVIDER,180000, 0, locationListener);
 }
  
 private void muestraPosicion(Location loc) {
     if(loc != null)
     {
         lblLatitud.setText("Latitud: " + String.valueOf(loc.getLatitude()));
         lblLongitud.setText("Longitud: " + String.valueOf(loc.getLongitude()));
         lblPrecision.setText("Precision: " + String.valueOf(loc.getAccuracy()));
         Log.i("LocAndroid", String.valueOf(
             loc.getLatitude() + " - " + String.valueOf(loc.getLongitude())));
     }
     else
     {
         lblLatitud.setText("Latitud: (sin_datos)");
         lblLongitud.setText("Longitud: (sin_datos)");
         lblPrecision.setText("Precision: (sin_datos)");
     }
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

