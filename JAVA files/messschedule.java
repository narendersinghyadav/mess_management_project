import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class messschedule {

    @FXML
    private TextField a1;

    @FXML
    private TextField a2;

    @FXML
    private TextField a3;

    @FXML
    private TextField a4;

    @FXML
    private TextField a5;

    @FXML
    private TextField a6;

    @FXML
    private TextField a7;

    @FXML
    private TextField a8;

    @FXML
    private TextField a9;

    @FXML
    private TextField a10;

    @FXML
    private TextField a11;

    @FXML
    private TextField a12;

    @FXML
    private TextField a13;

    @FXML
    private TextField a14;

    @FXML
    private TextField a15;

    @FXML
    private TextField a22;

    @FXML
    private TextField a16;

    @FXML
    private TextField a23;

    @FXML
    private TextField a17;

    @FXML
    private TextField a24;

    @FXML
    private TextField a18;

    @FXML
    private TextField a25;

    @FXML
    private TextField a19;

    @FXML
    private TextField a26;

    @FXML
    private TextField a20;

    @FXML
    private TextField a27;

    @FXML
    private TextField a21;

    @FXML
    private TextField a28;
    @FXML
    private Button applychanges;
    @SuppressWarnings("resource")
	@FXML
   void initialize() throws IOException {
	  if(login.foo==false) {
	  		a1.setEditable(false);
		  	a2.setEditable(false);
		  	a3.setEditable(false);
		  	a4.setEditable(false);
		  	a5.setEditable(false);
		  	a6.setEditable(false);
		  	a18.setEditable(false);
		  	a7.setEditable(false);
		  	a8.setEditable(false);
		  	a19.setEditable(false);
		  	a9.setEditable(false);
		  	a10.setEditable(false);
		  	a11.setEditable(false);
		  	a20.setEditable(false);
		  	a12.setEditable(false);
		  	a13.setEditable(false);
		  	a14.setEditable(false);
		  	a15.setEditable(false);
		  	a21.setEditable(false);
		  	a16.setEditable(false);
		  	a17.setEditable(false);
		  	a22.setEditable(false);
		  	a23.setEditable(false);
		  	a24.setEditable(false);
		  	a25.setEditable(false);
		  	a26.setEditable(false);
		  	a27.setEditable(false);
		  	a28.setEditable(false);
		  applychanges.setDisable(true);
	  }
	  try {
		BufferedReader reader=new BufferedReader(new FileReader("mess.txt"));
		a1.setText(reader.readLine().trim());
		  a2.setText(reader.readLine().trim());
		  a3.setText(reader.readLine().trim());
		  a4.setText(reader.readLine().trim());
		  a5.setText(reader.readLine().trim());
		  a6.setText(reader.readLine().trim());
		  a7.setText(reader.readLine().trim());
		  a8.setText(reader.readLine().trim());
		  a9.setText(reader.readLine().trim());
		  a10.setText(reader.readLine().trim());
		  a11.setText(reader.readLine().trim());
		  a12.setText(reader.readLine().trim());
		  a13.setText(reader.readLine().trim());
		  a14.setText(reader.readLine().trim());
		  a15.setText(reader.readLine().trim());
		  a16.setText(reader.readLine().trim());  
		  a17.setText(reader.readLine().trim());
		  a18.setText(reader.readLine().trim());
		  a19.setText(reader.readLine().trim());
		  a20.setText(reader.readLine().trim());
		  a21.setText(reader.readLine().trim());
		  a22.setText(reader.readLine().trim());
		  a23.setText(reader.readLine().trim());
		  a24.setText(reader.readLine().trim());
		  a25.setText(reader.readLine().trim());
		  a26.setText(reader.readLine().trim());
		  a27.setText(reader.readLine().trim());
		  a28.setText(reader.readLine().trim());
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

    @FXML
    void applychange(ActionEvent event) {
    	  
		  try {
			  File file =new File("mess.txt");
			  file.createNewFile();
			FileWriter fp=new FileWriter("mess.txt");
			fp.append(a1.getText().toString()+"\r\n");
			fp.append(a2.getText().toString()+"\r\n");
			fp.append(a3.getText().toString()+"\r\n");
			fp.append(a4.getText().toString()+"\r\n");
			fp.append(a5.getText().toString()+"\r\n");
			fp.append(a6.getText().toString()+"\r\n");
			fp.append(a7.getText().toString()+"\r\n");
			fp.append(a8.getText().toString()+"\r\n");
			
			fp.append(a9.getText().toString()+"\r\n");
			fp.append(a10.getText().toString()+"\r\n");
			
			fp.append(a11.getText().toString()+"\r\n");
			fp.append(a12.getText().toString()+"\r\n");
			fp.append(a13.getText().toString()+"\r\n");
			fp.append(a14.getText().toString()+"\r\n");
			fp.append(a15.getText().toString()+"\r\n");
			fp.append(a16.getText().toString()+"\r\n");
			fp.append(a17.getText().toString()+"\r\n");
			fp.append(a18.getText().toString()+"\r\n");
			fp.append(a19.getText().toString()+"\r\n");
			fp.append(a20.getText().toString()+"\r\n");
			fp.append(a21.getText().toString()+"\r\n");
			fp.append(a22.getText().toString()+"\r\n");
			fp.append(a23.getText().toString()+"\r\n");
			fp.append(a24.getText().toString()+"\r\n");
			fp.append(a25.getText().toString()+"\r\n");
			fp.append(a26.getText().toString()+"\r\n");
			fp.append(a27.getText().toString()+"\r\n");
			fp.append(a28.getText().toString());
			fp.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
