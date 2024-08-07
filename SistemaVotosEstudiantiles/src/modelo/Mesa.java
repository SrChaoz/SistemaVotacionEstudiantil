package modelo;

import java.util.ArrayList;
import java.util.List;

public class Mesa {
    private int id;
    private String nombre;
    private String presidente;
    private String secretario;
    private List<Voto> votos;

    // Constructor, getters y setters
    public Mesa(int id, String nombre, String presidente, String secretario) {
        this.id = id;
        this.nombre = nombre;
        this.presidente = presidente;
        this.secretario = secretario;
        this.votos = new ArrayList<>();
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

    public String getPresidente() {
        return presidente;
    }

    public void setPresidente(String presidente) {
        this.presidente = presidente;
    }

    public String getSecretario() {
        return secretario;
    }

    public void setSecretario(String secretario) {
        this.secretario = secretario;
    }

    public List<Voto> getVotos() {
        return votos;
    }

    public void agregarVoto(Voto voto) {
        this.votos.add(voto);
    }

    public int contarVotos() {
        return this.votos.size();
    }

    public List<Voto> obtenerVotos() {
        return new ArrayList<>(this.votos);
    }
    
    @Override
    public String toString() {
        return nombre;
    }
}