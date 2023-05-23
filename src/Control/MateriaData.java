
package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Materia;

public class MateriaData {
    private Connection con;
    
    public MateriaData(){
        con= Conexion.getConexion();
    }
    
     public void cargarMateria(Materia materia){
        try {
            String sql= "INSERT INTO materia (nombre, año, estado) values(?,?,?)";
            
            PreparedStatement ps= con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,materia.getNombre());
            ps.setInt(2, materia.getAnio());
            ps.setBoolean(3, materia.isEstado());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                materia.setIdMateria(rs.getInt(1));
            }else
                System.out.println("El alumno no se pudo guardar");
        
             ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void actualizarMateria(Materia materia){
        try {
            String sql= "UPDATE materia SET id_materia=?, nombre=?, año=?, estado=? WHERE 1";
            
            PreparedStatement ps= con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, materia.getIdMateria());
            ps.setString(2, materia.getNombre());
            ps.setInt(3, materia.getAnio());
            ps.setBoolean(4, materia.isEstado());          
            ps.executeUpdate();
            
            ps.close();
            
    } catch (SQLException ex) {
            Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
     
     public Materia buscarMateria(int idMateria){
        Materia mat= null;
       String query = "SELECT id_materia, nombre, año, estado FROM materia WHERE id_materia=?";
        PreparedStatement ps;
        try{
            ps=con.prepareStatement (query);
            ps.setInt(1, idMateria);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                mat=new Materia();                
                mat.setIdMateria( idMateria );               
                mat.setNombre(rs.getString("nombre"));
                mat.setAnio(rs.getInt("anio"));               
                mat.setEstado(rs.getBoolean("Activo"));
                
                }else
                    System.out.println("Materia inexistente");
                    
        } catch (SQLException ex) {
            Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        return null;
    }  
}    