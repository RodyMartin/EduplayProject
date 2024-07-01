import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaDocente extends JFrame {
    private JTable tablaAlumnos;
    private DefaultTableModel modeloTabla;

    public VentanaDocente() {
        setTitle("Puntajes de Alumnos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        modeloTabla = new DefaultTableModel(new String[]{"Nombre", "Apellido", "Puntaje Matemáticas"}, 0);
        tablaAlumnos = new JTable(modeloTabla);

        // Llenar la tabla con los datos de los alumnos
        cargarDatosAlumnos();

        // Añadir la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(tablaAlumnos);

        // Añadir el JScrollPane a la ventana
        add(scrollPane);
    }

    private void cargarDatosAlumnos() {
        try (Connection conexion = ConexionBD.conectar()) {
            String sql = "SELECT nombre, apellido, puntaje_matematicas FROM alumnos";
            PreparedStatement statement = conexion.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();

            while (resultado.next()) {
                String nombre = resultado.getString("nombre");
                String apellido = resultado.getString("apellido");
                int puntajeMatematicas = resultado.getInt("puntaje_matematicas");
                modeloTabla.addRow(new Object[]{nombre, apellido, puntajeMatematicas});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los datos de los alumnos");
        }
    }
}
