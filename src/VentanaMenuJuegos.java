import javax.swing.*;

public class VentanaMenuJuegos extends JFrame {
    private String usuario;

    public VentanaMenuJuegos(String usuario) {
        this.usuario = usuario;

        setTitle("Eduplay - Menú de Juegos");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el botón de Matemáticas
        JButton btnMatematicas = new JButton("Matemáticas");

        // Añadir el botón al panel
        JPanel panel = new JPanel();
        panel.add(btnMatematicas);

        // Añadir el panel a la ventana
        add(panel);

        // Acción para el botón Matemáticas
        btnMatematicas.addActionListener(e -> abrirVentanaMatematicas());
    }

    private void abrirVentanaMatematicas() {
        VentanaMatematicas ventanaMatematicas = new VentanaMatematicas(usuario);
        ventanaMatematicas.setVisible(true);
    }
}
