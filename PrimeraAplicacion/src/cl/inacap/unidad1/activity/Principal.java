package cl.inacap.unidad1.activity;

import java.util.Locale;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Principal extends FragmentActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		Fragment objFragment = null;
		Locale defaultLocale = Locale.getDefault();
		switch(position){
		case 0:
			objFragment = new Fragment_Home();
			mTitle = "Home";
			break;
		case 1:
			objFragment = new Fragment_Cliente();
			if(defaultLocale.getISO3Language().equals("spa")){
			mTitle = "Clientes";
			break;		
			}
			else
			{
			mTitle = "Customer";
			break;
			}
		case 2:
			objFragment = new Fragment_Pedido();
			if(defaultLocale.getISO3Language().equals("spa")){
			mTitle = "Pedidos";
			break;		
			}
			else
			{
			mTitle = "Order";
			break;
			}
		case 3:
			objFragment = new Fragment_Resumen();
			if(defaultLocale.getISO3Language().equals("spa")){
			mTitle = "Resumen";
			break;		
			}
			else
			{
			mTitle = "Sumary";
			break;
			}
		
	 	case 4:
	 		objFragment = new Fragment_Mapa();
			if(defaultLocale.getISO3Language().equals("spa")){
			mTitle = "Mapas";
			break;		
			}
			else
			{
			mTitle = "Maps";
			break;
			}
			
	/*   case 3:
		      objFragment = new Fragment_Pedido();
		      mTitle = "Pedidos";
			  break;*/
	   
		
		}
		
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager
				.beginTransaction()
				.replace(R.id.container,objFragment)
				.commit();
	}

	public void onSectionAttached(int number) {
	/*	switch (number) {
		
		case 0:
			mTitle = "Home";
			Intent intent = new Intent(Principal.this,MenuClientesActivity.class);		
			Principal.this.startActivity(intent);
		case 1:
			mTitle = "Clientes";
			break;
		case 2:
			mTitle = "Productos";
			break;
		case 3:
			mTitle = "Pedidos";
			break;
		}*/
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.principal, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_principal,
					container, false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((Principal) activity).onSectionAttached(getArguments().getInt(
					ARG_SECTION_NUMBER));
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		switch(keyCode){
			case KeyEvent.KEYCODE_BACK:
				AlertDialog.Builder dialog = new AlertDialog.Builder(this);
Locale defaultLocale = Locale.getDefault();
				
				if(defaultLocale.getISO3Language().equals("spa")){
					dialog.setTitle("¿Seguro desea salir?");
					dialog.setMessage("se puden perder sus datos");
					dialog.setCancelable(false);
						
				}
				else
				{
					dialog.setTitle("¿Sure you want to quit?");
					dialog.setMessage(" You could lose your data");
					dialog.setCancelable(false);
				}
				dialog.setPositiveButton("Si/YES", new DialogInterface.OnClickListener() {
				 
				  @Override
			    public void onClick(DialogInterface dialog, int which) {
				   finish();  
				   System.runFinalization();
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
			
		    return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
