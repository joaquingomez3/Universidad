
package Control;

import java.awt.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Inscripcion;



public class InscripcionData {
    
    public List<Inscripcion> obtenerInscripciones(){
        List<Inscripcion> cursadas = new ArrayList<>();
          
        try {
            String sql = "SELECT * FROM inscripcion;";
            PreparedStatement ps = con.prepareStatement(sql);
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
