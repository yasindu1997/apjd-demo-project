import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.sql.*;

public class AddFormController {
    @FXML
    private JFXTextField txtCusId;

    @FXML
    private JFXTextField txtCusName;

    @FXML
    private JFXTextField txtCusAddress;

    @FXML
    private JFXTextField txtCusSalary;

    @FXML
    void save(ActionEvent event) {
        String cusId = txtCusId.getText();
        String name = txtCusName.getText();
        String address = txtCusAddress.getText();
        double salary = Double.parseDouble(txtCusSalary.getText()); //"25000.00" ---> 25000.00

        try {
            //java app + mysql connect karana connector load
            Class.forName("com.mysql.cj.jdbc.Driver");

            //create a connection with database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo",
                    "root","yasindu@ijse");

            PreparedStatement stm = connection.prepareStatement("insert into customer values(?,?,?,?)");

            stm.setObject(1,cusId);
            stm.setObject(2,name);
            stm.setObject(3,address);
            stm.setObject(4,salary);

            int result = stm.executeUpdate();
            System.out.println(result);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
