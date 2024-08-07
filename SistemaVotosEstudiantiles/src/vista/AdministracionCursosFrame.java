package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Curso;
import modelo.Mesa;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdministracionCursosFrame extends JInternalFrame {
    private JTextField txtNombreCurso;
    private JComboBox<Mesa> comboBoxMesas;
    private JTable tableCursos;
    private DefaultTableModel tableModel;
    private List<Curso> cursos;
    private List<Mesa> mesas;
    private JDesktopPane desktopPane; 

    public AdministracionCursosFrame(List<Mesa> mesas, List<Curso> cursos, JDesktopPane desktopPane) {
        super("Administración de Cursos", true, true, false, false);
        setSize(600, 400);
        getContentPane().setLayout(new BorderLayout());

        this.mesas = mesas;
        this.desktopPane = desktopPane; 
        this.cursos = cursos; // Usar la lista de cursos proporcionada

        JPanel panelFormulario = new JPanel(new GridLayout(3, 2));
        panelFormulario.add(new JLabel("Nombre del Curso:"));
        txtNombreCurso = new JTextField();
        panelFormulario.add(txtNombreCurso);

        panelFormulario.add(new JLabel("Mesa:"));
        comboBoxMesas = new JComboBox<>();
        actualizarComboBoxMesas();
        panelFormulario.add(comboBoxMesas);

        JButton btnGuardar = new JButton("Guardar");
        panelFormulario.add(btnGuardar);

        getContentPane().add(panelFormulario, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Mesa"}, 0);
        tableCursos = new JTable(tableModel);
        getContentPane().add(new JScrollPane(tableCursos), BorderLayout.CENTER);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCurso();
            }
        });

       
        cargarCursosExistentes();
    }

    private void guardarCurso() {
        try {
            String nombreCurso = txtNombreCurso.getText();
            Mesa mesaSeleccionada = (Mesa) comboBoxMesas.getSelectedItem();

            if (nombreCurso.isEmpty() || mesaSeleccionada == null) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idCurso = cursos.size() + 1;
            Curso curso = new Curso(idCurso, nombreCurso, mesaSeleccionada);
            cursos.add(curso); 
            tableModel.addRow(new Object[]{idCurso, nombreCurso, mesaSeleccionada.getNombre()});

            txtNombreCurso.setText("");
            comboBoxMesas.setSelectedIndex(-1);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar el curso", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarCursosExistentes() {
        for (Curso curso : cursos) {
            tableModel.addRow(new Object[]{curso.getId(), curso.getNombre(), curso.getMesa().getNombre()});
        }
    }

    public void actualizarComboBoxMesas() {
        comboBoxMesas.removeAllItems();
        for (Mesa mesa : mesas) {
            comboBoxMesas.addItem(mesa);
        }
        comboBoxMesas.setSelectedIndex(-1); 
    }

    public List<Curso> getCursos() {
        return cursos;
    }
}
