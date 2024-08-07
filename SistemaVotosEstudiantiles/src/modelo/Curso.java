package modelo;

import java.util.ArrayList;
import java.util.List;

public class Curso {
    private int id;
    private String nombre;
    private Mesa mesa;
    private List<Estudiante> estudiantes;

    public Curso(int id, String nombre, Mesa mesa) {
        this.id = id;
        this.nombre = nombre;
        this.mesa = mesa;
        this.estudiantes = new ArrayList<>();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void agregarEstudiante(Estudiante estudiante) {
        this.estudiantes.add(estudiante);
    }

    public int contarEstudiantes() {
        return this.estudiantes.size();
    }

    public List<Estudiante> obtenerEstudiantes() {
        return new ArrayList<>(this.estudiantes);
    }
    
    @Override
    public String toString() {
        return nombre; // o alguna otra representación adecuada
    }

}
