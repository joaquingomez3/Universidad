
package Control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Alumno;


public class AlumnoData {
    private Connection con;
    
    public AlumnoData(){
        con= Conexion.getConexion();
    }
    
    public void guardarAlumno(Alumno alumno){
        try {
            String sql= "INSERT INTO alumno (dni, nombre, apellido, fechaNacimiento, estado) values(?,?,?,?,?)";
            
            PreparedStatement ps= con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,alumno.getDni());
            ps.setString(2, alumno.getNombre());
            ps.setString(3, alumno.getApellido());
            ps.setDate(4, Date.valueOf( alumno.getFechaNac()));
            ps.setBoolean(5, alumno.isEstado());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                alumno.setIdAlumno(rs.getInt(1));
            }else
                System.out.println("El alumno no se pudo guardar");
        
             ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void actualizarAlumno(Alumno alumno){
        try {
            String sql= "UPDATE alumno SET nombre=?, apellido=?, fechaNacimiento=?, estado=? WHERE 1";
            
            PreparedStatement ps= con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setDate(3, Date.valueOf( alumno.getFechaNac()));
            ps.setBoolean(4, alumno.isEstado());
            ps.setInt(5, alumno.getIdAlumno());
            ps.executeUpdate();
            
            ps.close();
            
    } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    
    public Alumno buscarAlumno(int idAlumno){
        Alumno alu= null;
       String query = "SELECT dni, nombre, apellido, fechaNacimiento, estado FROM alumno WHERE id_alumno=?";
        PreparedStatement ps;
        try{
            ps=con.prepareStatement (query);
            ps.setInt(1, idAlumno);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                alu=new Alumno();                
                alu.setIdAlumno( idAlumno );
                alu.setDni(rs.getInt("dni"));
                alu.setNombre(rs.getString("nombre"));
                alu.setApellido(rs.getString("apellido"));
                alu.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                alu.setEstado(rs.getBoolean("Activo"));
                
                }else
                    System.out.println("Alumno inexistente");
                    
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        return null;
    }    


}

  
    
    