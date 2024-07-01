import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VentanaRegistro extends JFrame {
    private JTextField campoNombre;
    private JTextField campoApellido;
    private JTextField campoUsuario;
    private JPasswordField campoContrasena;

    public VentanaRegistro() {
        setTitle("Registro de Alumno");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear los campos y etiquetas
        JLabel etiquetaNombre = new JLabel("Nombre:");
        campoNombre = new JTextField(20);
        JLabel etiquetaApellido = new JLabel("Apellido:");
        campoApellido = new JTextField(20);
        JLabel etiquetaUsuario = new JLabel("Usuario:");
        campoUsuario = new JTextField(20);
        JLabel etiquetaContrasena = new JLabel("Contraseña:");
        campoContrasena = new JPasswordField(20);

        JButton btnRegistrar = new JButton("Registrar");

        // Añadir los componentes al panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(etiquetaNombre);
        panel.add(campoNombre);
        panel.add(etiquetaApellido);
        panel.add(campoApellido);
        panel.add(etiquetaUsuario);
        panel.add(campoUsuario);
        panel.add(etiquetaContrasena);
        panel.add(campoContrasena);
        panel.add(btnRegistrar);

        // Añadir el panel a la ventana
        add(panel);

        // Acción para el botón Registrar
        btnRegistrar.addActionListener(e -> registrarAlumno());
    }

    private void registrarAlumno() {
        String nombre = campoNombre.getText();
        String apellido = campoApellido.getText();
        String usuario = campoUsuario.getText();
        String contrasena = new String(campoContrasena.getPassword());

        try (Connection conexion = ConexionBD.conectar()) {
            String sql = "INSERT INTO alumnos (nombre, apellido, usuario, contrasena) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, nombre);
            statement.setString(2, apellido);
            statement.setString(3, usuario);
            statement.setString(4, contrasena);
            statement.executeUpdate();

            JOptionPane.showMessageDialog(this, "Registro exitoso");
            dispose(); // Cierra la ventana después del registro
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al registrar el alumno");
        }
    }
}
