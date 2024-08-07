package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Curso;
import modelo.Mesa;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdministracionMesasFrame extends JInternalFrame {
    private JTextField txtNombre;
    private JTextField txtPresidente;
    private JTextField txtSecretario;
    private JTable tableMesas;
    private DefaultTableModel tableModel;
    private List<Mesa> mesas;

    public AdministracionMesasFrame(List<Mesa> mesas) {
        super("Administración de Mesas", true, true, false, false);
        setSize(600, 400);
        getContentPane().setLayout(new BorderLayout());

        this.mesas = mesas;

        JPanel panelFormulario = new JPanel(new GridLayout(4, 2));
        
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

        getContentPane().add(panelFormulario, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Presidente", "Secretario"}, 0);
        tableMesas = new JTable(tableModel);
        getContentPane().add(new JScrollPane(tableMesas), BorderLayout.CENTER);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarMesa();
            }
        });
        
        cargarMesasExistentes();
    }

    private void guardarMesa() {
        try {
            String nombre = txtNombre.getText();
            String presidente = txtPresidente.getText();
            String secretario = txtSecretario.getText();

            if (nombre.isEmpty() || presidente.isEmpty() || secretario.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int id = generarIdUnico();
            Mesa mesa = new Mesa(id, nombre, presidente, secretario);
            mesas.add(mesa);
            tableModel.addRow(new Object[]{id, nombre, presidente, secretario});

           
            txtNombre.setText("");
            txtPresidente.setText("");
            txtSecretario.setText("");

           
            for (JInternalFrame frame : getDesktopPane().getAllFrames()) {
                if (frame instanceof AdministracionCursosFrame) {
                    ((AdministracionCursosFrame) frame).actualizarComboBoxMesas();
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar la mesa", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private int generarIdUnico() {
        return mesas.size() + 1;
    }

    private void cargarMesasExistentes() {
        for (Mesa mesa : mesas) {
            tableModel.addRow(new Object[]{mesa.getId(), mesa.getNombre(), mesa.getPresidente(), mesa.getSecretario()});
        }
    }
}
