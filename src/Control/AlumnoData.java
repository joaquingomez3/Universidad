
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
            String sql= "INSERT INTO alumno (dni, apellido, nombre, fechaNacimiento, estado) values(?,?,?,?,?)";
            
            PreparedStatement ps= con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,alumno.getDni());            
            ps.setString(3, alumno.getApellido());
            ps.setString(2, alumno.getNombre());
            ps.setDate(4, Date.valueOf( alumno.getFechaNac()));
            ps.setBoolean(5, alumno.isEstado());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                alumno.setIdAlumno(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Alumno guardado.");
            }else
                System.out.println("El alumno no se pudo guardar");
                
             ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "El alumno no se pudo guardar");
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void actualizarAlumno(Alumno alumno){
        try {
            String sql= "UPDATE alumno SET  dni=?, apellido=?, nombre=?, fechaNacimiento=?, estado=?  WHERE alumno.id_alumno=?";
            
            PreparedStatement ps= con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());            
            ps.setDate(4, Date.valueOf( alumno.getFechaNac()));
            ps.setBoolean(5, alumno.isEstado());
            ps.setInt(6, alumno.getIdAlumno());
            ps.executeUpdate();
            
            ps.close();
            
            } catch (SQLException ex) {
            Logger.getLogger(AlumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    
     public Alumno buscarAlumno(int id_alumno) {
        Alumno alumno = null;
        String sql = "SELECT  dni, apellido, nombre, fechaNacimiento FROM alumno WHERE id_alumno = ? AND estado = 1";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,id_alumno );
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                alumno=new Alumno();
                alumno.setIdAlumno(id_alumno);
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNac(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(true);
               

            } else {
                JOptionPane.showMessageDialog(null, "No existe el alumno");
            }
            JOptionPane.showMessageDialog(null, "Alumno encontrado");
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno "+ex.getMessage());
        }

        return alumno;
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
    
    public void eliminarAlumno(int id_alumno) {
    try {
        String sql = "UPDATE alumno SET estado = 0 WHERE id_alumno = ?;";
        
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, id_alumno);
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
                    alu.setFechaNac(rs.getDate("fechaNacimiento").toLocalDate());
                    alu.setEstado(rs.getBoolean("estado"));
                    alumnos.add(alu);

                }      
                ps.close();
            }catch (SQLException ex) {
                JOptionPane.showInternalMessageDialog(null, "Error Alumno "+ex.getMessage());
            }
            return alumnos;

        }      
    


}

  
    
    