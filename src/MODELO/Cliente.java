package MODELO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class Cliente extends Persona {
    private String nit;
    private int id_cliente;
    conexion cn;
    
    public Cliente(){}
    public Cliente(int id_cliente,String nit, String nombres, String apellidos, String direccion, String telefono, String nacimiento) {
        super( nombres, apellidos, direccion, telefono, nacimiento);
        this.id_cliente = id_cliente;
        this.nit = nit;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
    
    public int getId() {
        return id_cliente;
    }

    public void setId(int id_cliente) {
        this.id_cliente = id_cliente;
    }
    

    public void crear(){
        try {
            PreparedStatement parametro;
            cn = new conexion();
            cn.abrir_conexion();
            String query = "INSERT INTO clientes(nit, nombres, apellidos, direccion, telefono, fecha_nacimiento) VALUES(?,?,?,?,?,?);";
            parametro = (PreparedStatement) cn.conectar_db.prepareStatement(query);
            parametro.setString(1, getNit());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getNacimiento());
            // Faltan parámetros para sueldo, bonificación y total

            int executar = parametro.executeUpdate();
            System.out.println("Ingreso exitoso: " + executar);
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error en crear: " + ex.getMessage());
        }
    }
    
 
    public DefaultTableModel leer() {
    DefaultTableModel tabla = new DefaultTableModel();

    try {
        cn = new conexion();
        cn.abrir_conexion();
        String query = "SELECT * FROM clientes";
        ResultSet consulta = cn.conectar_db.createStatement().executeQuery(query);

        // Definir las columnas del modelo
        String encabezado[] = {"id_clientes", "nit", "nombres", "apellidos", "direccion", "telefono", "fecha_nacimiento"};
        tabla.setColumnIdentifiers(encabezado);

        // Crear un array de datos con el tamaño adecuado
        String datos[] = new String[7]; // Ajustado al número de columnas

        while (consulta.next()) {
            datos[0] = consulta.getString("id_clientes"); // Asegúrate de que este campo existe en tu base de datos
            datos[1] = consulta.getString("nit"); // Asegúrate de que este campo existe en tu base de datos
            datos[2] = consulta.getString("nombres");
            datos[3] = consulta.getString("apellidos");
            datos[4] = consulta.getString("direccion");
            datos[5] = consulta.getString("telefono");
            datos[6] = consulta.getString("fecha_nacimiento"); // Asegúrate de que este campo existe en tu base de datos
            tabla.addRow(datos);
        }
        cn.cerrar_conexion();
    } catch (SQLException ex) {
        cn.cerrar_conexion();
        System.out.println("Error en leer: " + ex.getMessage());
    }
    return tabla;  // Devolver el modelo de tabla
}
    @Override
    public void actualizar(){
        try {
            PreparedStatement parametro;
            cn = new conexion();
            cn.abrir_conexion();
            String query = "UPDATE clientes SET nit = ?, nombres = ?, apellidos = ?, direccion = ?, telefono = ?, fecha_nacimiento = ?  WHERE id_clientes = ?;";
            parametro = (PreparedStatement) cn.conectar_db.prepareStatement(query);
            parametro.setString(1, getNit());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getNacimiento());
            parametro.setInt(7, getId());
            // Faltan parámetros para sueldo, bonificación y total

            int executar = parametro.executeUpdate();
            System.out.println("Modificacion exitoso: " + executar);
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error en actualizar: " + ex.getMessage());
        }
    }
    @Override
        public void borrar(){
    try {
        PreparedStatement parametro;
        cn = new conexion();
        cn.abrir_conexion();
        String query = "DELETE FROM clientes WHERE id_clientes = ?;";
        
        // Inicializar 'parametro' con un PreparedStatement válido
        parametro = cn.conectar_db.prepareStatement(query); 
        
        // Establecer el valor para el parámetro 'id_clientes'
        parametro.setInt(1, getId());

        int executar = parametro.executeUpdate();
        System.out.println("Borrado exitoso: " + executar);
        
        // Cerrar la conexión y el PreparedStatement
        parametro.close();
        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println("Error en borrar: " + ex.getMessage());
    }
}
    }
