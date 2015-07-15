package cl.inacap.unidad1.activity;

import java.util.ArrayList;

import cl.inacap.unidad1.SqlLite.SQLite;
import cl.inacap.unidad1.clases.Usuario;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class Fragment_Mapa extends Fragment {
	private MapView mMapView;
	private GoogleMap googleMap;
	double latitude = -33.469119900000000000; 
    double longitude = -70.641997000000000000; 
    double zoom;
    String usuario;
    Button marcador;
    int valor = 0;



@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
    // TODO Auto-generated method stub
    View view = inflater
                .inflate(R.layout.fragment_fragment_mapa, container, false);
        
	mMapView = (MapView)view.findViewById(R.id.map);
	marcador = (Button)view.findViewById(R.id.Btn_marcador);
	mMapView.onCreate(savedInstanceState);
	mMapView.onResume();
	  try { 
          MapsInitializer.initialize(getActivity().getApplicationContext()); 
      } catch (Exception e) { 
          e.printStackTrace(); 
      }
	  //googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
	   googleMap = mMapView.getMap(); 
       // latitude and longitude 
       
       actualizarPosicion();
       // create marker 
       MarkerOptions marker = new MarkerOptions().position( 
               new LatLng(latitude, longitude)).title("Posicion actual"); 

       // Changing marker icon 
       marker.icon(BitmapDescriptorFactory 
               .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)); 

       // adding marker 
       googleMap.addMarker(marker); 
       CameraPosition cameraPosition = new CameraPosition.Builder() 
               .target(new LatLng(latitude,longitude)).zoom(15).build(); 
       googleMap.animateCamera(CameraUpdateFactory 
               .newCameraPosition(cameraPosition)); 
       posicionMap();
       marcador.setOnClickListener(new OnClickListener(){
             
		@Override
		public void onClick(View v) {
			Usuario s = new Usuario();
			
			final String[] v1 = new String[2];
			ArrayList<Usuario> usuarios = s.listaUsuarios();
			//v1final String[] items = {"Español", "Inglés", "Francés"};
			int largo = usuarios.size();
			for(int i = 0;i < largo;i++)
			{
				v1[i] = usuarios.get(i).login_usuario;
			}
			final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle("Seleccione usuario....").setSingleChoiceItems(v1,0, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				/*	*/
					if(which != -1)
					{
					valor = which;
					}
				}
				
			
			} ).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub	
					Toast.makeText(
                            getActivity(),
                            "Seleccionaste:" +v1[valor],
                            Toast.LENGTH_SHORT)
                            .show();
					usuario = v1[valor];
					
					if(usuario != null)
					{
					SQLite ser = new SQLite(getActivity());
					ser.abrir();
					ser.insertarMapa(longitude,latitude,new Double(zoom).toString(),usuario);
					ser.cerrar();
					Toast.makeText(getActivity(), "Marker is added to the Map", Toast.LENGTH_SHORT).show();	
					posicionMap();
					}
					else
					{
						Toast.makeText(
	                            getActivity(),
	                            "Seleccionaste: debes seleccionar una opcion para marcar",
	                            Toast.LENGTH_SHORT)
	                            .show();
					}
					
				}
			}).setNegativeButton(android.R.string.cancel,new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
			
			builder.show();
			
				
		} 	   
       }
       );
       
       googleMap.setOnMapClickListener(new OnMapClickListener(){

   		@Override
   		public void onMapClick(LatLng point) {
   			// TODO Auto-generated method stub
   			drawMarker(point,"juan");
   			latitude = point.latitude;
   			longitude = point.longitude;
   			zoom = googleMap.getCameraPosition().zoom;
   			
   			
   		}
   		
   	});
   
       posicionMap();
  
  return view;
 }
public void posicionMap()
{
	SQLite s = new SQLite(getActivity());
	s.abrir();
	Cursor cursor = s.getMaps();
	int contador = cursor.getCount();
	cursor.moveToFirst();
	for(int i = 0;i<contador; i++)
	{
		longitude = cursor.getDouble(1);
		latitude = cursor.getDouble(2);
		zoom = cursor.getDouble(3);
		usuario = cursor.getString(4);
		LatLng location = new LatLng(latitude,longitude);
		drawMarker(location,usuario);
		cursor.moveToNext();
		googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(latitude,longitude)));
		
	}
	
	
	
}
private void drawMarker(LatLng point,String usuario){
    // Creating an instance of MarkerOptions
    MarkerOptions markerOptions = new MarkerOptions().title(usuario);

    // Setting latitude and longitude for the marker
    markerOptions.position(point);
   // Toast.makeText(getActivity(),usuario, Toast.LENGTH_SHORT).show();	
    if(usuario.equals("test"))
    {
    	markerOptions.icon(BitmapDescriptorFactory 
                .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)); 
    }
    else
    {
    	markerOptions.icon(BitmapDescriptorFactory 
                .defaultMarker(BitmapDescriptorFactory.HUE_BLUE)); 
    }

    // Adding marker on the Google Map
    googleMap.addMarker(markerOptions);
}
private void muestraPosicion(Location loc) {
    if(loc != null)
    {
    	latitude = Double.parseDouble(String.valueOf(loc.getLatitude()));
    	longitude = Double.parseDouble(String.valueOf(loc.getLongitude()));
    }
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
              //  lblEstado.setText("GPS DESACTIVADO");
            }
            public void onProviderEnabled(String provider){
             //   lblEstado.setText("GPS ACTIVADO");
            }
            public void onStatusChanged(String provider, int status, Bundle extras){
                Log.i("LocAndroid", "Provider Status: " + status);
             //   lblEstado.setText("Provider Status: " + status);
            }
        };
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,180000, 0, locationListener);
    }
   /* else
    {
        lblLatitud.setText("Latitud: (sin_datos)");
        lblLongitud.setText("Longitud: (sin_datos)");
        lblPrecision.setText("Precision: (sin_datos)");
    }*/
   

}




