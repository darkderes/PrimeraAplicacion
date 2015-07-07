package cl.inacap.unidad1.SqlLite;
import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SQLite {
	SQLiteHelper sqliteHelper;
	SQLiteDatabase db;
	public SQLite(Context context) {
		// TODO Auto-generated constructor stub
		sqliteHelper = new SQLiteHelper(context);
		
	}
	
	public void abrir(){
		  Log.i("SQLite", "Se abre conexion a la base de datos " + sqliteHelper.getDatabaseName() );
		  db = sqliteHelper.getWritableDatabase(); // solo lectura 
		 }
	 
	public void cerrar()
	 {
	  Log.i("SQLite", "Se cierra conexion a la base de datos " + sqliteHelper.getDatabaseName() );
	  sqliteHelper.close();  
	 }
	 public long insertar(String Nombre,String Direccion,int Telefono)
	 {
		 Log.i("Sqlite","Insert :");
		 ContentValues contentValues = new ContentValues();
		 contentValues.put("nombre",Nombre);
		 contentValues.put("direccion",Direccion);
		 contentValues.put("telefono",Telefono);
		 
		 return db.insert("clientes", null,contentValues);
	 }
	 public long update(String Nombre,String Direccion,int Telefono,int id)
	 {
		 Log.i("Sqlite","Update :");
		 ContentValues contentValues = new ContentValues();
		 contentValues.put("nombre",Nombre);
		 contentValues.put("direccion",Direccion);
		 contentValues.put("telefono",Telefono);	 
		 return db.update("clientes",contentValues,"id = ?", new String[]{String.valueOf(id)});
	 }
		public Cursor getRegistros()
		{
			Log.i("SqlLite","Select :");
			return db.query("clientes" ,				
						new String[]{
					sqliteHelper.id,
					sqliteHelper.nombre,
					sqliteHelper.direccion,
					sqliteHelper.telefono
									  }, 
					null, null, null, null, null);
		}
		public Cursor getPedidos()
		{
			Log.i("SqlLite","Select :");
			return db.query("pedidos" ,				
						new String[]{
					sqliteHelper.id_pedido,
					sqliteHelper.cliente,
					sqliteHelper.producto,
					sqliteHelper.cantidad,
					sqliteHelper.precio,
					sqliteHelper.total,
					sqliteHelper.fecha
									  }, 
					null, null, null, null, null);
		}
		public Cursor getRegistro( int id )
		{
			return db.query( "clientes" ,				
						new String[]{
								sqliteHelper.id ,
								sqliteHelper.nombre,
								sqliteHelper.direccion,
								sqliteHelper.telefono
									  }, 
	                sqliteHelper.id + " = " + id , 
					null, null, null, null);
		}
		public int getUltimoID()
		{
			int id = 0;
			Cursor cursor = db.query( "pedidos" , 
					new String[]{ sqliteHelper.id_pedido },
					null, null, null,null,
					sqliteHelper.id_pedido + " DESC ", "1");
			if( cursor.moveToFirst() )
			{
				do
				{
					id = cursor.getInt(0);
				}   while ( cursor.moveToNext() );
			}
			return id;
		}
		
		public boolean deleteContact(int rowId) {
		    return db.delete("clientes", "id" + "=" + rowId, null) > 0;
		  }
		
		

		public ArrayList<String> getFormatListUniv( Cursor cursor )
		{
			ArrayList<String> listData = new ArrayList<String>();
			String item = "";
				if( cursor.moveToFirst() )
				{
					do
					{		
						item += "id: ["+ cursor.getInt(0) +"]\r\n";
						item += "nombre: " + cursor.getString(1) + "\r\n";
						item += "direccion: " + cursor.getString(2) + "\r\n";
						item += "telefono: " + cursor.getString(3) + "\r\n";
						listData.add( item );
						item="";            
					} while ( cursor.moveToNext() );
				}		
			return listData;		
		}
		public ArrayList<String> getFormatListPedido( Cursor cursor )
		{
			ArrayList<String> listData = new ArrayList<String>();
			String item = "";
				if( cursor.moveToFirst() )
				{
					do
					{		
						item += "Pedido: ["+ cursor.getInt(0) +"]\r\n";
						item += "Cliente: " + cursor.getString(1) + "\r\n";
						item += "Producto: " + cursor.getString(2) + "\r\n";
						item += "Cantidad: " + cursor.getInt(3) + "\r\n";
						item += "Precio: " + cursor.getInt(4) + "\r\n";
						item += "Total: " + cursor.getInt(5) + "\r\n";
						item += "Fecha Entrega: " +  cursor.getString(6) + "\r\n";
						listData.add( item );
						item="";            
					} while ( cursor.moveToNext() );
				}		
			return listData;		
		}
		public long insertarPedido(String cliente,String producto,int cantidad,int precio,int total,String fecha)
		 {
			 Log.i("Sqlite","Insert :");
			 ContentValues contentValues = new ContentValues();
			 contentValues.put("cliente",cliente);
			 contentValues.put("producto",producto);
			 contentValues.put("cantidad",cantidad);
			 contentValues.put("precio",precio);
			 contentValues.put("total", total);
			 contentValues.put("fecha",fecha);
			  
			 return db.insert("pedidos", null,contentValues);
		 }
	 

}
