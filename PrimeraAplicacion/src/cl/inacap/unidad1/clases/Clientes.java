package cl.inacap.unidad1.clases;

import java.util.ArrayList;

public class Clientes {
	
	int id_cliente;
	String nombre ;
	String direccion;
	String telefono;

	
	public ArrayList<Clientes> listaClientes(String Usuario)
	{
	int id_usuario = 0;
	if(Usuario.equals("juan"))
	{
		id_usuario = 1;
	}
	else
	{
		id_usuario = 2;
	}
    ArrayList<Clientes> lista = new ArrayList<Clientes>();
    Clientes cliente = new Clientes();
    
    if(id_usuario == 1)
    	
    {
    cliente.id_cliente = 1;
    cliente.nombre ="Juan";
    cliente.direccion = "huerfanos 1400";
    cliente.telefono = "13245678"; 
    
    lista.add(cliente);
    
    cliente = new Clientes();
    cliente.id_cliente = 2;
    cliente.nombre ="Jorge";
    cliente.direccion = "Amunategui 1400";
    cliente.telefono = "13245678"; 
   
    lista.add(cliente);
    }
    else
    {
    cliente = new Clientes();
    cliente.id_cliente = 3;
    cliente.nombre ="Julio";
    cliente.direccion = "Moneda 2222";
    cliente.telefono = "13245678"; 
    lista.add(cliente);
    }
    
    return lista;
	}
	public String toString()
	{
		return String.valueOf(this.nombre) + " : " + this.direccion+ " ( " + this.telefono + ")";
	}

}
