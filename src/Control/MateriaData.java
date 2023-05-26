
package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
                JOptionPane.showMessageDialog(null, "Materia guardada.");
            }else
                System.out.println("La materia no se pudo guardar");
        
             ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "La materia no se pudo guardar.");
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
     
     public Materia buscarMateria(int id_materia) {
        Materia materia = null;
        String sql = "SELECT  nombre, año FROM materia WHERE id_materia = ? AND estado = 1";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,id_materia );
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                materia=new Materia();
                materia.setIdMateria(id_materia);
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("año"));
                materia.setEstado(true);
               

            } else {
                JOptionPane.showMessageDialog(null, "No existe la materia.");
            }
            JOptionPane.showMessageDialog(null, "Materia encontrada: " + materia.getNombre() + ", ID de materia: " + materia.getIdMateria());
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Materia. "+ex.getMessage());
        }

        return materia;
    } 
     
     public void eliminarMateria(int id_materia){
        try {
        String sql = "UPDATE materia SET estado = 0 WHERE id_materia = ?;";
        
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        ps.setInt(1, id_materia);
        ps.executeUpdate();        
        
        ps.close();
        JOptionPane.showMessageDialog(null, "Materia eliminada.");
    } catch (SQLException ex) {
        Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex);
        
    }


    }     
     
     public List<Materia> listarMaterias(){
         List<Materia> materias = new ArrayList<>();    
            
        try {
            String query = "SELECT * FROM materia";
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            
            while(rs.next()){
                
                Materia mat = new Materia();
                mat.setIdMateria(rs.getInt("id_materia"));               
                mat.setNombre(rs.getString("nombre"));
                mat.setAnio(rs.getInt("anio"));
                mat.setEstado(rs.getBoolean("estado"));
                materias.add(mat);
                
                
                
            }      
            ps.close();
        }catch (SQLException ex) {
            JOptionPane.showInternalMessageDialog(null, "Error Materias "+ex.getMessage());
        }
        return materias;
        
    }
     
     
}    