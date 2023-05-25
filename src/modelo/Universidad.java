
package modelo;

import Control.AlumnoData;
import Control.InscripcionData;
import Control.MateriaData;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Universidad {
    
        public static void main(String[] args) {
        
        List<Alumno> al = new ArrayList<>();
        AlumnoData ad = new AlumnoData();
        al = ad.listarAlumnos();
        System.out.println(al);
        
        List<Inscripcion> ins = new ArrayList<>();
        InscripcionData insc = new InscripcionData();
        ins = insc.obtenerInscripciones();
        System.out.println(ins);
        
        List<Materia> ma = new ArrayList<>();
        MateriaData mate = new MateriaData();
        ma = mate.listarMaterias();
        System.out.println(ma);
        
       // Alumno alu = new Alumno(1, "Bernarda","Quiroga", LocalDate.of(2000, 02, 23),true);
       AlumnoData ads = new AlumnoData();
       ads.buscarAlumno(2);
       
       MateriaData matData = new MateriaData();
       matData.buscarMateria(1);
    }
}
    
