import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class registroP extends JFrame {
    private JPanel registrop;
    private JTextField ced;
    private JTextField his;
    private JTextField nom;
    private JTextField ape;
    private JTextField tlf;
    private JTextField ed;
    private JTextField desc;
    private JButton registrarButton;
    private JButton consultarPacienteButton;

    public registroP() {
        super("Registro");
        setSize(400, 574);
        setContentPane(registrop);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //CIERRA LA VENTANA

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = ced.getText();
                String historial = his.getText();
                String nombre = nom.getText();
                String apellido = ape.getText();
                String edad = ed.getText();
                String telefono = tlf.getText();
                String enfermedad = desc.getText();


                if (cedula.isEmpty() || historial.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || edad.isEmpty() ||
                        telefono.isEmpty() || enfermedad.isEmpty()) {
                    anadir(cedula,historial,nombre,apellido,telefono,edad,enfermedad);
                    JOptionPane.showMessageDialog(null, "Registro exitoso");
                }
            }
        });}


    private void anadir(String cedula, String nombre, String historial, String apellido, String telefono, String edad, String enfermedad) {
        String url = "jdbc:mysql://localhost:3306/sistema_hospitalario";
        String username = "root";
        String password = "123456";

        try {
            Connection conexion = DriverManager.getConnection(url, username, password);
            String sql = "insert into PACIENTE (cedula,historial,nombre,apellido,telefono,edad,enfermedad) values (?,?,?,?,?,?,?)";
            PreparedStatement pst = conexion.prepareStatement(sql);

            pst.setString(1, cedula);
            pst.setInt(2, Integer.parseInt(historial));
            pst.setString(3, nombre);
            pst.setString(4, apellido);
            pst.setString(5, telefono);
            pst.setInt(6, Integer.parseInt(edad));
            pst.setString(7, enfermedad);

            int rowsIns = pst.executeUpdate();
            if (rowsIns > 0) {
                JOptionPane.showMessageDialog(null, "Paciente registrado");
                pst.close();
                conexion.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }


    }
}


