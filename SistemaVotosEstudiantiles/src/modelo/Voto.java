package modelo;

public class Voto {
    private Estudiante estudiante;
    private Candidato candidato;

    public Voto(Estudiante estudiante, Candidato candidato) {
        this.estudiante = estudiante;
        this.candidato = candidato;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }
}