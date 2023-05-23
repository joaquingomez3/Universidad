
package Control;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
