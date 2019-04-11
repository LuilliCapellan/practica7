
package com.example.practica7;


public class Estudiante {

    private Integer matricula;
    private String nombre;
    private String correo;
    private int carrera;

    public Estudiante(Integer matricula, String nombre, String correo, int carrera) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.correo = correo;
        this.carrera = carrera;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo =  correo;
    }

    public int getCarrera() {
        return carrera;
    }

    public void setCarrera(int carrera) {
        this.carrera =  carrera;
    }

}