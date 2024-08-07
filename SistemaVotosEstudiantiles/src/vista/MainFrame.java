package vista;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import modelo.Curso;
import modelo.Mesa;
import modelo.Voto;
import modelo.Estudiante;
import modelo.Candidato;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

    private JDesktopPane desktopPane;
    private List<Mesa> mesas;
    private List<Curso> cursos;
    private List<Estudiante> estudiantes;
    private List<Candidato> candidatos;
    private List<Voto> votos;


    public MainFrame() {
        setTitle("Sistema de Votación");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        desktopPane = new JDesktopPane();
        setContentPane(desktopPane);

        mesas = new ArrayList<>();
        cursos = new ArrayList<>();
        estudiantes = new ArrayList<>();
        candidatos = new ArrayList<>();
        votos = new ArrayList<>();
        


        JMenuBar menuBar = new JMenuBar();

        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menuArchivo.add(itemSalir);

        JMenu menuAdministracion = new JMenu("Administración");
        JMenuItem itemMesas = new JMenuItem("Mesas");
        JMenuItem itemCursos = new JMenuItem("Cursos");
        JMenuItem itemEstudiantes = new JMenuItem("Estudiantes");
        JMenuItem itemCandidatos = new JMenuItem("Candidatos");
        
        itemMesas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirAdministracionMesasFrame();
            }
        });
        itemCursos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirAdministracionCursosFrame();
            }
        });
        itemEstudiantes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirAdministracionEstudiantesFrame();
            }
        });
        
        itemCandidatos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	abrirAdministracionCandidatosFrame();
            }
        });

        menuAdministracion.add(itemMesas);
        menuAdministracion.add(itemCursos);
        menuAdministracion.add(itemEstudiantes);
        menuAdministracion.add(itemCandidatos);

        // Menú Proceso
        JMenu menuProceso = new JMenu("Proceso");
        JMenuItem itemSufragar = new JMenuItem("Sufragar");
        itemSufragar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirProcesoSufragio();
            }
        });
        menuProceso.add(itemSufragar);

        // Menú Reportes
        JMenu menuReportes = new JMenu("Reportes");
        JMenuItem itemPadronElectoral = new JMenuItem("Padrón Electoral");
        JMenuItem itemResultadosGenerales = new JMenuItem("Resultados Generales");
        JMenuItem itemResultadoGrafico = new JMenuItem("Resultados Graficos");

        itemPadronElectoral.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarPadronElectoral();
            }
        });
        itemResultadosGenerales.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 abrirReporteVotosFrame();
            }
        });
        
        itemResultadoGrafico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarResultados();
            }
        });

        menuReportes.add(itemPadronElectoral);
        menuReportes.add(itemResultadosGenerales);
        menuReportes.add(itemResultadoGrafico);

        menuBar.add(menuArchivo);
        menuBar.add(menuAdministracion);
        menuBar.add(menuProceso);
        menuBar.add(menuReportes);

        setJMenuBar(menuBar);
    }
    
    private void abrirAdministracionCandidatosFrame() {
        if (!FrameAbierto("Administración de Candidatos")) {
            AdministracionCandidatosFrame frame = new AdministracionCandidatosFrame(cursos, candidatos);
            desktopPane.add(frame);
            frame.setVisible(true);
        }
    }

    private void abrirAdministracionMesasFrame() {
        if (!FrameAbierto("Administración de Mesas")) {
            AdministracionMesasFrame frame = new AdministracionMesasFrame(mesas);
            desktopPane.add(frame);
            frame.setVisible(true);
        }
    }

    private void abrirAdministracionCursosFrame() {
        if (!FrameAbierto("Administración de Cursos")) {
            AdministracionCursosFrame frame = new AdministracionCursosFrame(mesas, cursos, desktopPane);
            desktopPane.add(frame);
            frame.setVisible(true);
            frame.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosed(InternalFrameEvent e) {
                    cursos = frame.getCursos(); 
                }
            });
        }
    }


    private void abrirAdministracionEstudiantesFrame() {
        if (!FrameAbierto("Administración de Estudiantes")) {
            AdministracionEstudiantesFrame frame = new AdministracionEstudiantesFrame(cursos, estudiantes);
            desktopPane.add(frame);
            frame.setVisible(true);
            frame.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosed(InternalFrameEvent e) {
                    estudiantes = frame.getEstudiantes(); 
                }
            });
        }
    }

    private void abrirProcesoSufragio() {
        if (!FrameAbierto("Verificación de Cédula")) {
            FormularioCedulaEstudiante form = new FormularioCedulaEstudiante(this, estudiantes, candidatos);
            desktopPane.add(form);
            form.setVisible(true);
        }
    }

    private void abrirReporteVotosFrame() {
        if (!FrameAbierto("Reporte de Votos")) {
            ReporteVotosFrame frame = new ReporteVotosFrame(candidatos, votos);
            desktopPane.add(frame);
            frame.setVisible(true);
        }
    }

    private void mostrarResultados() {
        if (!FrameAbierto("Resultado en Barras")) {
            frameBarra reporte = new frameBarra(desktopPane, candidatos);
            reporte.crearResultadosEnBarras();
        }
    }

    private void mostrarPadronElectoral() {
        if (!FrameAbierto("Padrón Electoral")) {
            PadronElectoralFrame padronElectoralFrame = new PadronElectoralFrame(cursos);
            desktopPane.add(padronElectoralFrame);
            padronElectoralFrame.setVisible(true);
        }
    }

    private boolean FrameAbierto(String frameName) {
        for (JInternalFrame frame : desktopPane.getAllFrames()) {
            if (frame.getTitle().equals(frameName)) {
                return true;
            }
        }
        return false;
    }

    
    private void registrarVoto(Estudiante estudiante, Candidato candidato) {
        Voto voto = new Voto(estudiante, candidato);
        votos.add(voto);
        candidato.setVotos(candidato.getVotos() + 1); 
    }
    
    
    private void actualizarComboBoxCursos() {
        for (JInternalFrame frame : desktopPane.getAllFrames()) {
            if (frame instanceof AdministracionEstudiantesFrame) {
                ((AdministracionEstudiantesFrame) frame).actualizarComboBoxCursos(cursos);
            }
        }
    }
    
    
    
    public void actualizarComboBoxCursos(List<Curso> cursos) {
        for (JInternalFrame frame : desktopPane.getAllFrames()) {
            if (frame instanceof AdministracionEstudiantesFrame) {
                ((AdministracionEstudiantesFrame) frame).actualizarComboBoxCursos(cursos);
            }
        }
    }
    
    
    private void actualizarEstudiantesFrame() {
        for (JInternalFrame frame : desktopPane.getAllFrames()) {
            if (frame instanceof AdministracionEstudiantesFrame) {
                ((AdministracionEstudiantesFrame) frame).actualizarComboBoxCursos(cursos);
            }
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            }
        });
    }
}
