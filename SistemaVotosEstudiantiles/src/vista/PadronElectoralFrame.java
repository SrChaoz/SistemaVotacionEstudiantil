package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import modelo.Curso;
import modelo.Estudiante;

public class PadronElectoralFrame extends JInternalFrame {
    private JTable tableCursosEstudiantes;
    private DefaultTableModel tableModel;
    private List<Curso> cursos;

    public PadronElectoralFrame(List<Curso> cursos) {
        super("Padrón Electoral", true, true, true, true);
        setSize(600, 400);
        setLayout(new BorderLayout());

        this.cursos = cursos;

        tableModel = new DefaultTableModel(new Object[]{"Curso", "Estudiantes"}, 0);
        tableCursosEstudiantes = new JTable(tableModel);
        add(new JScrollPane(tableCursosEstudiantes), BorderLayout.CENTER);

        actualizarTabla();
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0); // Limpiar la tabla antes de actualizar

        for (Curso curso : cursos) {
            StringBuilder estudiantesStr = new StringBuilder();
            for (Estudiante estudiante : curso.getEstudiantes()) {
                estudiantesStr.append(estudiante.getNombre()).append(", ");
            }

            if (estudiantesStr.length() > 0) {
                estudiantesStr.setLength(estudiantesStr.length() - 2); // Eliminar la última coma y espacio
            }

            tableModel.addRow(new Object[]{curso.getNombre(), estudiantesStr.toString()});
        }
    }
}
