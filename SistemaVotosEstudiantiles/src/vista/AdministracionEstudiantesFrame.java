package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import modelo.Curso;
import modelo.Estudiante;

public class AdministracionEstudiantesFrame extends JInternalFrame {
    private JTextField txtCedula;
    private JTextField txtNombre;
    private JComboBox<Curso> comboBoxCursos;
    private JTable tableEstudiantes;
    private DefaultTableModel tableModel;
    private List<Estudiante> estudiantes;
    private List<Curso> cursos;

    public AdministracionEstudiantesFrame(List<Curso> cursos, List<Estudiante> estudiantes) {
        super("Administración de Estudiantes", true, true, false, false);
        setSize(600, 400);
        getContentPane().setLayout(new BorderLayout());

        this.cursos = new ArrayList<>(cursos); 
        this.estudiantes = estudiantes != null ? estudiantes : new ArrayList<>(); 

        JPanel panelFormulario = new JPanel(new GridLayout(4, 2));
        panelFormulario.add(new JLabel("Cédula:"));
        txtCedula = new JTextField();
        panelFormulario.add(txtCedula);

        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Curso:"));
        comboBoxCursos = new JComboBox<>();
        panelFormulario.add(comboBoxCursos);

        JButton btnGuardar = new JButton("Guardar");
        panelFormulario.add(btnGuardar);

        getContentPane().add(panelFormulario, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Cédula", "Nombre", "Curso"}, 0);
        tableEstudiantes = new JTable(tableModel);
        getContentPane().add(new JScrollPane(tableEstudiantes), BorderLayout.CENTER);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarEstudiante();
            }
        });

        actualizarComboBoxCursos(cursos);
        cargarEstudiantesExistentes();
    }

    public void actualizarComboBoxCursos(List<Curso> cursos) {
        comboBoxCursos.removeAllItems();
        System.out.println("Actualizando JComboBox con cursos: " + cursos);
        if (cursos != null) {
            for (Curso curso : cursos) {
                comboBoxCursos.addItem(curso);
            }
        }
        comboBoxCursos.setSelectedIndex(-1); 
    }

    private void guardarEstudiante() {
        try {
            String cedula = txtCedula.getText();
            String nombre = txtNombre.getText();
            Curso cursoSeleccionado = (Curso) comboBoxCursos.getSelectedItem();

            if (cedula.isEmpty() || nombre.isEmpty() || cursoSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idEstudiante = estudiantes.size() + 1;
            Estudiante estudiante = new Estudiante(idEstudiante, cedula, nombre, cursoSeleccionado, false);
            estudiantes.add(estudiante);
            cursoSeleccionado.agregarEstudiante(estudiante); // Asegúrate de actualizar la lista de estudiantes del curso

            // Actualiza la tabla de estudiantes
            tableModel.addRow(new Object[]{idEstudiante, cedula, nombre, cursoSeleccionado.getNombre()});

            // Limpia los campos del formulario
            txtCedula.setText("");
            txtNombre.setText("");
            comboBoxCursos.setSelectedIndex(-1);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar el estudiante", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    private void cargarEstudiantesExistentes() {
        for (Estudiante estudiante : estudiantes) {
            tableModel.addRow(new Object[]{estudiante.getId(), estudiante.getCedula(), estudiante.getNombre(), estudiante.getCurso().getNombre()});
        }
    }
}
