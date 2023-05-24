
package Control;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Alumno;
import modelo.Inscripcion;
import modelo.Materia;


public class InscripcionData {
    private Connection con;
    private MateriaData matData= new MateriaData();
    private AlumnoData aluData = new AlumnoData();
    
    public InscripcionData(){
        con= Conexion.getConexion();
    }
    
    public void guardarInscripcion(Inscripcion insc) {
        String sql = "INSERT INTO inscripcion(idAlumno, idMateria, nota) VALUES (?,?,?)";
                try{
                    PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, insc.getIdAlumno());
                    ps.setInt(2, insc.getIdMateria());
                    ps.setDouble(3, insc.getNota());
                    
                    ps.executeUpdate();
                    ResultSet rs = ps.getGeneratedKeys();
                    
                    if(rs.next()){
                         insc.setIdInscripcion(rs.getInt(1));
                         JOptionPane.showMessageDialog(null, "Inscripcion Agregada.");
                    }          
                }
                catch(SQLException ex){
                        JOptionPane.showMessageDialog(null, "Error, no se pudo acceder.");

                }
    }
    
    public List<Inscripcion> obtenerInscripciones(){
        
        List<Inscripcion> cursadas = new ArrayList<>();    
            
        try {
            String query = "SELECT * FROM inscripcion;";
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            Inscripcion insc;
            
            while(rs.next()){
                insc = new Inscripcion();
                insc.setIdInscripcion(rs.getInt("idInscripto"));
                
                Alumno a = aluData.buscarAlumno(rs.getInt("idAlumno"));
                insc.setIdAlumno(a.getIdAlumno());
                
                Materia m = matData.buscarMateria(rs.getInt("idMateria"));
                insc.setIdMateria(m.getIdMateria());
                insc.setNota(rs.getDouble("nota"));

                cursadas.add(insc);
            }      
            ps.close();
        }catch (SQLException ex) {
            JOptionPane.showInternalMessageDialog(null, "Error Inscripcion "+ex.getMessage());
        }
        return cursadas;
    }
    
    public List<Inscripcion> obtenerInscripcionesPorAlumno(int idAlumno){
        return null;
        
    }
    
    public void borrarInscripcionMateriaAlumno(int id_alumno,int id_materia){
        
        String sql = "DELETE FROM inscripcion WHERE id_alumno = ? AND id_materia = ? ";

        try {
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, id_alumno);
        ps.setInt(2, id_materia);


        ps.executeUpdate();

        JOptionPane.showMessageDialog(null, "Se elimino correctamente");


        } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al eliminar");
        }
        }
    
     public void actualizarNota(int id_alumno,int id_materia,double nota){
            String sql = "UPDATE inscripcion SET nota = ? WHERE id_alumno = ? AND id_materia=? ";

            try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, nota);
            ps.setInt(2, id_alumno);
            ps.setInt(3, id_materia);


            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Se actualizo correctamente");


            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar");
            }
            }
     
     public List<Materia> obtenerMateriasNOCursadas(int id_materia){
        List<Materia> materias = new ArrayList<Materia>();
        
        try {
            String sql = "SELECT FROM materia WHERE estado = 1 AND idMateria not in" +
                    "(SELECT idMateria FROM inscripcion WHERE idAlumno= ?);";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_materia);
            ResultSet resultSet = ps.executeQuery();
            Materia materia;
            
            while(resultSet.next()){
                materia = new Materia();
                materia.setIdMateria(resultSet.getInt("idMateria"));
                materia.setNombre(resultSet.getString("nombre"));
                materias.add(materia);                               
        }
            ps.close();            
            
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }     
        return null;
       
  }
     
      public List<Materia> obtenerMateriasCursadas(int id_materia){
        return null;
      //// 1hora 1min
      }
      
      public List<Alumno> obtenerAlumnosXMateria(int id_materia){
        return null;
          
      }
     
}