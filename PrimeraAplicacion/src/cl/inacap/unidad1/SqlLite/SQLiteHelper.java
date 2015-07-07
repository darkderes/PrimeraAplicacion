package cl.inacap.unidad1.SqlLite;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.content.Context;


public class SQLiteHelper extends SQLiteOpenHelper{

	 //nombre de la base de datos
	 private static final String database = "bdd_almacen";
	
	 public final String id = "id"; 
	 public final String nombre = "nombre";
	 public final String direccion = "direccion";
	 public final String telefono = "telefono";
	 
	 public final String id_pedido = "id_pedido";
	 public final String cliente = "cliente";
	 public final String producto = "producto";
	 public final String cantidad = "cantidad";
	 public final String precio = "precio";
	 public final String total = "total";
	 public final String fecha = "fecha";
	 //versión de la base de datos
	 private static final int version = 1;
	 
	 public final String tabla = "clientes";
	 public final String pedido = "pedidos";
	 //Instrucción SQL para crear las tablas
	 private String sql = "CREATE TABLE clientes ( " +id+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+nombre+" TEXT,"+direccion+" TEXT,"+telefono+" INTEGER )";
	 private String tabla_pedidos = "CREATE TABLE pedidos (" +id_pedido+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"+cliente+" TEXT,"+producto+" TEXT, "+cantidad+" INTEGER,"+precio+" INTEGER,"+total+" INTEGER,"+fecha+" TEXT )";
	
	public SQLiteHelper(Context context) {
		super(context,database,null,version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(sql);
		db.execSQL(tabla_pedidos);
		Log.i("SQLite","Se crea BD "+ database + " version "+ version);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		 Log.i("SQLite", "Control de versiones: Old Version=" + oldVersion + " New Version= " + newVersion  );
		  if ( newVersion > oldVersion )
		  {
		   //elimina tabla
		   db.execSQL( "DROP TABLE IF EXISTS "+ tabla);
		   db.execSQL("DROP TABLE IF EXIST "+ pedido);
		   //y luego creamos la nueva tabla
		   db.execSQL( sql );   
		   db.execSQL(tabla_pedidos);
		   Log.i("SQLite", "Se actualiza versión de la base de datos, New version= " + newVersion  );
		  }
		
	}

}
