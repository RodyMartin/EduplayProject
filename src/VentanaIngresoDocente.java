import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaIngresoDocente extends JFrame {
    private JTextField campoUsuario;
    private JPasswordField campoContrasena;

    public VentanaIngresoDocente() {
        setTitle("Ingreso de Docente");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear los campos y etiquetas
        JLabel etiquetaUsuario = new JLabel("Usuario:");
        campoUsuario = new JTextField(20);
        JLabel etiquetaContrasena = new JLabel("Contraseña:");
        campoContrasena = new JPasswordField(20);

        JButton btnIngresar = new JButton("Ingresar");

        // Añadir los componentes al panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(etiquetaUsuario);
        panel.add(campoUsuario);
        panel.add(etiquetaContrasena);
        panel.add(campoContrasena);
        panel.add(btnIngresar);

        // Añadir el panel a la ventana
        add(panel);

        // Acción para el botón Ingresar
        btnIngresar.addActionListener(e -> ingresarDocente());
    }

    private void ingresarDocente() {
        String usuario = campoUsuario.getText();
        String contrasena = new String(campoContrasena.getPassword());

        try (Connection conexion = ConexionBD.conectar()) {
            String sql = "SELECT * FROM docentes WHERE usuario = ? AND contrasena = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, usuario);
            statement.setString(2, contrasena);
            ResultSet resultado = statement.executeQuery();

            if (resultado.next()) {
                JOptionPane.showMessageDialog(this, "Ingreso exitoso");
                VentanaDocente ventanaDocente = new VentanaDocente();
                ventanaDocente.setVisible(true);
                dispose(); // Cierra la ventana después del ingreso
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al ingresar");
        }
    }
}
