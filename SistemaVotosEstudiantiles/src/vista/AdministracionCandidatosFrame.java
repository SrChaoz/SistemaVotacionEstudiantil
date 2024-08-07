package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import modelo.Candidato;
import modelo.Curso;

public class AdministracionCandidatosFrame extends JInternalFrame {
    private JTextField txtNombre;
    private JTextField txtNombrePartido;
    private JTextField txtFoto;
    private JComboBox<Curso> comboBoxCursos;
    private JTable tableCandidatos;
    private DefaultTableModel tableModel;
    private List<Candidato> candidatos;
    private List<Curso> cursos;

    public AdministracionCandidatosFrame(List<Curso> cursos, List<Candidato> candidatos) {
        super("Administración de Candidatos", true, true, false, false);
        setSize(600, 400);
        getContentPane().setLayout(new BorderLayout());

        this.cursos = cursos;
        this.candidatos = candidatos;

        // Panel para el formulario
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2));
        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Nombre del Partido:"));
        txtNombrePartido = new JTextField();
        panelFormulario.add(txtNombrePartido);

        panelFormulario.add(new JLabel("Foto:"));
        txtFoto = new JTextField();
        panelFormulario.add(txtFoto);

        panelFormulario.add(new JLabel("Curso:"));
        comboBoxCursos = new JComboBox<>();
        actualizarComboBoxCursos(cursos);
        panelFormulario.add(comboBoxCursos);

        JButton btnGuardar = new JButton("Guardar");
        panelFormulario.add(btnGuardar);

        getContentPane().add(panelFormulario, BorderLayout.NORTH);

        
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Partido", "Curso"}, 0);
        tableCandidatos = new JTable(tableModel);
        getContentPane().add(new JScrollPane(tableCandidatos), BorderLayout.CENTER);

       
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCandidato();
            }
        });

        cargarCandidatosExistentes();
    }

    private void guardarCandidato() {
        try {
            String nombre = txtNombre.getText();
            String nombrePartido = txtNombrePartido.getText();
            String foto = txtFoto.getText();
            Curso cursoSeleccionado = (Curso) comboBoxCursos.getSelectedItem();

            if (nombre.isEmpty() || nombrePartido.isEmpty() || foto.isEmpty() || cursoSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idCandidato = candidatos.size() + 1;
            Candidato candidato = new Candidato(idCandidato, nombre, cursoSeleccionado, foto, nombrePartido);
            candidatos.add(candidato);
            tableModel.addRow(new Object[]{idCandidato, nombre, nombrePartido, cursoSeleccionado.getNombre(), foto});

            // Limpiar campos
            txtNombre.setText("");
            txtNombrePartido.setText("");
            txtFoto.setText("");
            comboBoxCursos.setSelectedIndex(-1);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar el candidato", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizarComboBoxCursos(List<Curso> cursos) {
        comboBoxCursos.removeAllItems();
        for (Curso curso : cursos) {
            comboBoxCursos.addItem(curso);
        }
        comboBoxCursos.setSelectedIndex(-1); // Clear selection
    }

    private void cargarCandidatosExistentes() {
        for (Candidato candidato : candidatos) {
            tableModel.addRow(new Object[]{candidato.getId(), candidato.getNombre(), candidato.getNombrePartido(), candidato.getCurso().getNombre(), candidato.getFoto()});
        }
    }
}
