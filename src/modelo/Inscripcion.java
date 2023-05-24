
package modelo;

public class Inscripcion {

    private int idInscripcion;
    private double nota;
    private Alumno alumno;
    private Materia materia;

    public Inscripcion(int idInscripcion, double nota, Alumno alumno, Materia materia) {
        this.idInscripcion = idInscripcion;
        this.nota = nota;
        this.alumno = alumno;
        this.materia = materia;
    }

    public Inscripcion(double nota, Alumno alumno, Materia materia) {
        this.nota = nota;
        this.alumno = alumno;
        this.materia = materia;
    }

    public Inscripcion() {
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getIdAlumno() {
        return alumno.getIdAlumno();
    }

    public void setIdAlumno(int idAlumno) {
        this.alumno.setIdAlumno(idAlumno);
    }

    public int getIdMateria() {
        return materia.getIdMateria();
    }

    public void setIdMateria(int idMateria) {
        this.materia.setIdMateria(idMateria);
    }

    @Override
    public String toString() {
        return "Inscripcion{" + "idInscripcion=" + idInscripcion + ", nota=" + nota + ", alumno=" + alumno + ", materia=" + materia + '}';
    }
    
    
}
