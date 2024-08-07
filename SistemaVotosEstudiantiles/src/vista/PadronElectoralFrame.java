package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Curso;
import modelo.Estudiante;

import java.awt.*;
import java.util.List;

public class PadronElectoralFrame extends JInternalFrame {
    private JTable tablePadron;
    private DefaultTableModel tableModel;
    private List<Curso> cursos;

    public PadronElectoralFrame(List<Curso> cursos) {
        super("Padrón Electoral", true, true, false, false);
        setSize(600, 400);
        getContentPane().setLayout(new BorderLayout());

        this.cursos = cursos;

        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre del Curso", "Mesa", "Estudiantes"}, 0);
        tablePadron = new JTable(tableModel);
        getContentPane().add(new JScrollPane(tablePadron), BorderLayout.CENTER);

        cargarDatosPadron();
    }

    private void cargarDatosPadron() {
        for (Curso curso : cursos) {
            String nombreMesa = curso.getMesa() != null ? curso.getMesa().getNombre() : "Sin mesa";
            StringBuilder estudiantesStr = new StringBuilder();
            for (Estudiante estudiante : curso.getEstudiantes()) {
                estudiantesStr.append(estudiante.getNombre()).append(", ");
            }
            if (estudiantesStr.length() > 0) {
                estudiantesStr.setLength(estudiantesStr.length() - 2); 
            }
            tableModel.addRow(new Object[]{curso.getId(), curso.getNombre(), nombreMesa, estudiantesStr.toString()});
        }
    }
}
