import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class VentanaMatematicas extends JFrame {
    private String usuario;
    private JTextField campoRespuesta;
    private JLabel etiquetaPregunta;
    private JLabel etiquetaResultado;
    private int puntaje = 0;
    private int num1;
    private int num2;

    public VentanaMatematicas(String usuario) {
        this.usuario = usuario;

        setTitle("Juego de Matemáticas");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear los componentes
        etiquetaPregunta = new JLabel();
        campoRespuesta = new JTextField(5);
        etiquetaResultado = new JLabel();
        JButton btnComprobar = new JButton("Comprobar");

        // Añadir los componentes al panel
        JPanel panel = new JPanel();
        panel.add(etiquetaPregunta);
        panel.add(campoRespuesta);
        panel.add(btnComprobar);
        panel.add(etiquetaResultado);

        // Añadir el panel a la ventana
        add(panel);

        // Generar la primera pregunta
        generarPregunta();

        // Acción para el botón Comprobar
        btnComprobar.addActionListener(e -> comprobarRespuesta());
    }

    private void generarPregunta() {
        Random random = new Random();
        num1 = random.nextInt(10) + 1;
        num2 = random.nextInt(10) + 1;
        etiquetaPregunta.setText(num1 + " + " + num2 + " = ");
    }

    private void comprobarRespuesta() {
        try {
            int respuesta = Integer.parseInt(campoRespuesta.getText());
            if (respuesta == num1 + num2) {
                etiquetaResultado.setText("CORRECTO");
                puntaje++;
                actualizarPuntaje();
            } else {
                etiquetaResultado.setText("INCORRECTO");
            }
            generarPregunta(); // Genera una nueva pregunta
            campoRespuesta.setText(""); // Limpia el campo de respuesta
        } catch (NumberFormatException e) {
            etiquetaResultado.setText("Ingrese un número válido");
        }
    }

    private void actualizarPuntaje() {
        try (Connection conexion = ConexionBD.conectar()) {
            String sql = "UPDATE alumnos SET puntaje_matematicas = ? WHERE usuario = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, puntaje);
            statement.setString(2, usuario);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar el puntaje");
        }
    }
}
