package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import modelo.Candidato;
import modelo.Estudiante;
import modelo.Voto;

public class FormularioVotacion extends JInternalFrame {
    private JComboBox<Candidato> comboBoxCandidatos;
    private JButton btnVotar;
    private List<Candidato> candidatos;
    private List<Voto> votos;
    private Estudiante estudiante;

    public FormularioVotacion(List<Candidato> candidatos, List<Voto> votos, Estudiante estudiante) {
        super("Votación", true, true, false, false);
        setRootPaneCheckingEnabled(false);
        setEnabled(false);
        this.estudiante = estudiante;
        this.candidatos = candidatos;
        this.votos = votos != null ? votos : new ArrayList<>(); 
        initialize();
    }

    private void initialize() {
        setSize(400, 300);
        getContentPane().setLayout(new GridLayout(3, 1));

        comboBoxCandidatos = new JComboBox<>();
        System.out.println("Lista de candidatos: " + candidatos);
        for (Candidato candidato : candidatos) {
            comboBoxCandidatos.addItem(candidato);
        }

        btnVotar = new JButton("Votar");
        btnVotar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarVoto();
            }
        });

        getContentPane().add(new JLabel("Seleccione un candidato:"));
        getContentPane().add(comboBoxCandidatos);
        getContentPane().add(btnVotar);
    }
    
    private void registrarVoto() {
        Candidato candidatoSeleccionado = (Candidato) comboBoxCandidatos.getSelectedItem();
        if (candidatoSeleccionado != null) {
            // Registrar el voto
            registrarVotos(estudiante, candidatoSeleccionado);

            // Cambiar el estado del estudiante a 'votado'
            estudiante.setEstado(true); 

            JOptionPane.showMessageDialog(this, "Gracias por su voto.");
            dispose(); // Cerrar el formulario de votación
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un candidato.");
        }
    }


    private void registrarVotos(Estudiante estudiante, Candidato candidato) {
        Voto voto = new Voto(estudiante, candidato);
        votos.add(voto);
        candidato.incrementarVotos(); 
    }
}
