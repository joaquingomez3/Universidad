
package modelo;

import java.time.LocalDate;

public class Alumno {
    private int id_alumno;
    private int dni;
    private String nombre;
    private String apellido;
    private LocalDate fechaNac;
    private boolean estado;
    private String toString;

    public Alumno(int id_alumno, int dni, String nombre, String apellido, LocalDate fechaNac, boolean estado){
        this.id_alumno = id_alumno;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
        this.estado = estado;
    }

    public Alumno(int dni, String nombre, String apellido, LocalDate fechaNac, boolean estado) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
        this.estado = estado;
    }

    public Alumno() {
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String setNombre(String nombre) {
        this.nombre=nombre;
        return null;
    }

    public String setApellido(String apellido) {
        this.apellido=apellido;
        return null;
    }

    public int getIdAlumno() {
        return id_alumno;
    }

    public void setIdAlumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
         

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return  id_alumno + " , " + nombre + " , " + apellido ;
    }
    
    
}

