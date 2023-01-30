import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.*;

public class SearchFormController {
    @FXML
    private JFXTextField txtSearch;

    @FXML
    private Label lblId;

    @FXML
    private Label lblName;

    @FXML
    private Label lblTown;

    @FXML
    private Label lblSalary;

    @FXML
    void search(ActionEvent event) {
        String cusId = txtSearch.getText();

        try {
            //java app + mysql connect karana connector load
            Class.forName("com.mysql.cj.jdbc.Driver");

            //create a connection with database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo",
                    "root","yasindu@ijse");

            PreparedStatement stm = connection.prepareStatement("select * from customer where cid=?");

            stm.setObject(1,cusId);

            ResultSet result = stm.executeQuery();

            //executeUpdate() ---> add/update/delete ---> int ---> success ---> 1 unsuccessful ---> -1
            //executeQuary() ---> search/getAll ---> ResultSet [{customer1},{customer2}]
            //jdbc transaction

            if(result.next()){
                lblId.setText(result.getString(1));
                lblName.setText(result.getString(2));
                lblTown.setText(result.getString(3));
                lblSalary.setText(result.getString(4));
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
