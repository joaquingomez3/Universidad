
package Control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
    
    public Alumno buscarAlumnoPorDni(int dni){
        Alumno alu= null;
       String query = "SELECT nombre, apellido, fechaNacimiento, estado FROM alumno WHERE dni=?";
        PreparedStatement ps;
        try{
            ps=con.prepareStatement (query);
            ps.setInt(1, dni);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                alu=new Alumno();                
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
    
    public void eliminarAlumno(int idAlumno){
        Alumno alu= null;
        try {
            String sql= "UPDATE alumno SET estado=? WHERE id_alumno=? ";
            
            PreparedStatement ps= con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setBoolean(1, alu.isEstado());
            ps.setInt(2, alu.getIdAlumno());
            ps.executeUpdate();
            
            ps.close();
            
            } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    
    public List<Alumno> listarAlumnos(){
          List<Alumno> alumnos = new ArrayList<>();    
            
        try {
            String query = "SELECT * FROM alumno";
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            
            while(rs.next()){
                
                Alumno alu = new Alumno();
                alu.setIdAlumno(rs.getInt("id_alumno"));
                alu.setDni(rs.getInt("dni"));
                alu.setApellido(rs.getString("apellido"));
                alu.setNombre(rs.getString("nombre"));
                alu.setFechaNac(LocalDate.MIN);
                alu.setEstado(true);
                alumnos.add(alu);
                
            }      
            ps.close();
        }catch (SQLException ex) {
            JOptionPane.showInternalMessageDialog(null, "Error Alumno "+ex.getMessage());
        }
        return alumnos;
        
    }
        
    


}

  
    
    