import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class getfeedback implements Initializable{

    @FXML
    private TableView<Table> feedback;

    @FXML
    private TableColumn<Table, String> dates;

    @FXML
    private TableColumn<Table, String> messsage;

    private final ObservableList<Table> dta= FXCollections.observableArrayList();
//    @FXML
//    void initialize() throws IOException {
//    	System.out.println("df");
//	// TODO Auto-generated method stub
//	   
//		
//}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Connection con;
		try {
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			Statement stmt=(Statement) con.createStatement();
			ResultSet rs=stmt.executeQuery("select date,feedback from feedback_table order by feed_id DESC");
			while(rs.next()) {
				Table t=new Table(rs.getString(1),""+rs.getString(2));
				dta.add(t);
			}
			dates.setCellValueFactory(new PropertyValueFactory<>("date"));
			messsage.setCellValueFactory(new PropertyValueFactory<>("message"));
			feedback.setItems(dta);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}  
}