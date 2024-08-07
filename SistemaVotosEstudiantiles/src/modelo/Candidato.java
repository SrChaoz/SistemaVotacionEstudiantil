package modelo;

public class Candidato extends Persona {
    private String foto;
    private Curso curso;
    private String nombrePartido;
    private int votos; // Agrega este atributo

    // Constructor, getters y setters
    public Candidato(int id, String nombre, Curso curso, String foto, String nombrePartido) {
        super(id, nombre);
        this.curso = curso;
        this.foto = foto;
        this.nombrePartido = nombrePartido;
        this.votos = 0; // Inicializa los votos en 0
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getNombrePartido() {
        return nombrePartido;
    }

    public void setNombrePartido(String nombrePartido) {
        this.nombrePartido = nombrePartido;
    }
    
    public void incrementarVotos() {
        this.votos++;
    }
}