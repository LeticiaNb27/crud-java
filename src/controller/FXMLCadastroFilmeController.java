
package controller;

import dao.FilmesDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Filmes;
import view.Palco;

public class FXMLCadastroFilmeController implements Initializable {

        
    @FXML
    private Button btnSalvar;

    @FXML
    private DatePicker dateLancamento;

    @FXML
    private TextField txtNome;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextArea txtDescricao;
            
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    

    @FXML
    void salvar(ActionEvent event) {
        Filmes filme = new Filmes();
        FilmesDAO dao = new FilmesDAO();
        
        filme.setNome(txtNome.getText());
        filme.setDescricao(txtDescricao.getText());
        filme.setLancamento(String.valueOf(dateLancamento.getValue()));
        Alert cadastro = new Alert(Alert.AlertType.INFORMATION);
        cadastro.setHeaderText("Cadastro de Filmes");
        cadastro.setContentText(dao.salvar(filme));
        cadastro.showAndWait();
        Palco.telaPrincipal();
        
       
    }

    @FXML
    void cancelar(ActionEvent event) {
        Palco.telaPrincipal();
    }
    
    public void limpaCampos(){
        txtDescricao.setText("");
        txtNome.setText("");
    }
}
