package modelo;

public class Estudiante extends Persona {
    private String cedula;
    private Curso curso;
    private Boolean estado;

  
    public Estudiante(int id, String cedula, String nombre, Curso curso, Boolean estado) {
        super(id, nombre);
        this.cedula = cedula;
        this.curso = curso;
        this.estado = estado;
    }


	public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    

}