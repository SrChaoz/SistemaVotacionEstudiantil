package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import modelo.Estudiante;
import modelo.Voto;
import modelo.Candidato;

public class FormularioCedulaEstudiante extends JInternalFrame {

    private JTextField txtCedula;
    private JButton btnVerificar;
    private List<Estudiante> estudiantes;
    private List<Candidato> candidatos;
    private List<Voto> votos;

    public FormularioCedulaEstudiante(Frame parent, List<Estudiante> estudiantes, List<Candidato> candidatos) {
        super("Verificación de Cédula", true, true, false, false);
        this.estudiantes = estudiantes;
        this.candidatos = candidatos;
        initialize();
    }

    private void initialize() {
        setSize(400, 200);
        getContentPane().setLayout(new GridLayout(3, 1));

        txtCedula = new JTextField();
        btnVerificar = new JButton("Verificar");

        btnVerificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verificarCedula();
            }
        });

        getContentPane().add(new JLabel("Ingrese su cédula:"));
        getContentPane().add(txtCedula);
        getContentPane().add(btnVerificar);
        
        System.out.println("la lista de estudiantess es" + estudiantes);
    }

    private void verificarCedula() {
        String cedula = txtCedula.getText().trim();
        System.out.println("Actualizando JComboBox con cursos: " + estudiantes);
        boolean encontrado = false;

        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCedula().equals(cedula)) {
                encontrado = true;
                if (estudiante.getEstado()) { // Verificar si el estudiante ya votó
                    JOptionPane.showMessageDialog(this, "Ya has votado. No puedes votar nuevamente.");
                } else {
                    abrirFormularioVotacion(estudiante);
                }
                break;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(this, "Estudiante no encontrado.");
        }
    }

    private void abrirFormularioVotacion(Estudiante estudiante) {
    	 FormularioVotacion formularioVotacion = new FormularioVotacion(candidatos, votos, estudiante);
    	 formularioVotacion.setVisible(true);
        getParent().add(formularioVotacion);
        dispose(); 
    }
}
