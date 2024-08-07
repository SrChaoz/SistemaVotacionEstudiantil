package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import modelo.Candidato;
import modelo.Voto;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ReporteVotosFrame extends JInternalFrame {
    private JTable tableCandidatos;
    private DefaultTableModel tableModel;

    public ReporteVotosFrame(List<Candidato> candidatos, List<Voto> votos) {
        super("Reporte de Votos", false, true, false, false);
        setRootPaneCheckingEnabled(false);
        setSize(600, 400);
        getContentPane().setLayout(new BorderLayout());


        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Curso", "Partido", "Votos"}, 0);
        tableCandidatos = new JTable(tableModel);
        getContentPane().add(new JScrollPane(tableCandidatos), BorderLayout.CENTER);

        Map<Candidato, Integer> conteoVotos = new HashMap<>();
        for (Candidato candidato : candidatos) {
            conteoVotos.put(candidato, 0);
        }
        
        for (Voto voto : votos) {
            Candidato candidato = voto.getCandidato();
            conteoVotos.put(candidato, conteoVotos.get(candidato) + 1);
        }

        for (Candidato candidato : candidatos) {  
            tableModel.addRow(new Object[]{
                candidato.getId(),
                candidato.getNombre(),
                candidato.getCurso().getNombre(),
                candidato.getNombrePartido(),
                candidato.getVotos(),
               
            });
        }
    }
}
