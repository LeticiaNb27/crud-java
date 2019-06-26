
package controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import view.Palco;

public class FXMLPrincipalController implements Initializable {

    Palco palco = new Palco();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
      @FXML
    void cadatrar(ActionEvent event) throws IOException  {
          palco.cadastroFilme();
    }
    
     @FXML
    void pesquisar(ActionEvent event) throws IOException {
        palco.mostrarFilme();

    }

}
