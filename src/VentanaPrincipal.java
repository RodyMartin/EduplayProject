import javax.swing.*;

public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal() {
        setTitle("Eduplay");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        JButton btnIngresar = new JButton("Ingresar");
        JButton btnRegistrarse = new JButton("Registrarse");
        JButton btnSoyDocente = new JButton("Soy Docente");


        JPanel panel = new JPanel();
        panel.add(btnIngresar);
        panel.add(btnRegistrarse);
        panel.add(btnSoyDocente);

        // Añadir el panel a la ventana
        add(panel);

        // Métodos para manejar los eventos de los botones
        btnIngresar.addActionListener(e -> abrirVentanaIngresoAlumno());
        btnRegistrarse.addActionListener(e -> abrirVentanaRegistro());
        btnSoyDocente.addActionListener(e -> abrirVentanaIngresoDocente());
    }

    private void abrirVentanaIngresoAlumno() {
        VentanaIngresoAlumno ventanaIngresoAlumno = new VentanaIngresoAlumno();
        ventanaIngresoAlumno.setVisible(true);
    }

    private void abrirVentanaRegistro() {
        VentanaRegistro ventanaRegistro = new VentanaRegistro();
        ventanaRegistro.setVisible(true);
    }

    private void abrirVentanaIngresoDocente() {
        VentanaIngresoDocente ventanaIngresoDocente = new VentanaIngresoDocente();
        ventanaIngresoDocente.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }
}
