package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import modelo.Mesa;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdministracionMesasFrame extends JInternalFrame {
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtPresidente;
    private JTextField txtSecretario;
    private JTable tableMesas;
    private DefaultTableModel tableModel;
    private List<Mesa> mesas;

    public AdministracionMesasFrame(List<Mesa> mesas) {
        super("Administración de Mesas", true, true, true, true);
        setSize(600, 400);
        setLayout(new BorderLayout());

        this.mesas = mesas;

        JPanel panelFormulario = new JPanel(new GridLayout(5, 2));
        panelFormulario.add(new JLabel("ID:"));
        txtId = new JTextField();
        panelFormulario.add(txtId);

        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Presidente:"));
        txtPresidente = new JTextField();
        panelFormulario.add(txtPresidente);

        panelFormulario.add(new JLabel("Secretario:"));
        txtSecretario = new JTextField();
        panelFormulario.add(txtSecretario);

        JButton btnGuardar = new JButton("Guardar");
        panelFormulario.add(btnGuardar);

        add(panelFormulario, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Presidente", "Secretario"}, 0);
        tableMesas = new JTable(tableModel);
        add(new JScrollPane(tableMesas), BorderLayout.CENTER);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarMesa();
            }
        });
    }

    private void guardarMesa() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String nombre = txtNombre.getText();
            String presidente = txtPresidente.getText();
            String secretario = txtSecretario.getText();

            if (nombre.isEmpty() || presidente.isEmpty() || secretario.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Mesa mesa = new Mesa(id, nombre, presidente, secretario);
            mesas.add(mesa);
            tableModel.addRow(new Object[]{id, nombre, presidente, secretario});

            // Limpiar campos
            txtId.setText("");
            txtNombre.setText("");
            txtPresidente.setText("");
            txtSecretario.setText("");

            // Actualizar combo box en AdministracionCursosFrame si está abierto
            for (JInternalFrame frame : getDesktopPane().getAllFrames()) {
                if (frame instanceof AdministracionCursosFrame) {
                    ((AdministracionCursosFrame) frame).actualizarComboBoxMesas();
                }
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar la mesa", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
