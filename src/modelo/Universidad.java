
package modelo;

import Control.AlumnoData;
import java.time.LocalDate;


public class Universidad {
    
        public static void main(String[] args) {
        
        Alumno alu = new Alumno(8543221, "Juan Bautista","Alberdi",LocalDate.of(1810, 8, 29),true);
        AlumnoData ad = new AlumnoData();
        ad.guardarAlumno(alu);
        
//        Alumno alu = new Alumno(14, "Bernarda","Quiroga", LocalDate.of(2000, 02, 23),true);
//        AlumnoData ad = new AlumnoData();
//        ad.actualizarAlumno(alu);
    }
}
    
