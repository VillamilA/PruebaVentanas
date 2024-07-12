import com.mysql.cj.x.protobuf.MysqlxPrepare;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class login extends JFrame {
    private JTextField usu;
    private JTextField contra;
    private JButton ingresarButton;
    private JPanel logeo;

    public login() {
        super("Login");
        setSize(400,500);
        setContentPane(logeo);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //CIERRA LA VENTANA

        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = usu.getText();
                String contrasena = contra.getText();

                if (validarlogin(usuario, contrasena)) {
                    JOptionPane.showMessageDialog(null, "Iniciaste Sesión de manera exitosa :D ");
                    new registroP().setVisible(true);
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "INGRESA LOS DATOS CORRECTAMENTE O NO ESTAS EN LA BASE :D");
                }
            }
        });
    }
    public boolean validarlogin(String usuario, String contrasena) {
        boolean validar = false;
        String url = "jdbc:mysql://localhost:3306/sistema_hospitalario";
        String username = "root";
        String password = "123456";
        try {
            Connection conexion = DriverManager.getConnection(url, username, password);
            String sql = "select * from USUARIO where username =? and password =?";
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1, usuario);
            pst.setString(2, contrasena);
            ResultSet resultset = pst.executeQuery();
            if (resultset.next()) {
                validar = true;
            }
            resultset.close();
            pst.close();
            conexion.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return validar;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new login().setVisible(true);
            }
        });
    }

}
