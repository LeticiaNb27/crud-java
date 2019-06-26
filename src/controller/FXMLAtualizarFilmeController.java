package controller;
import dao.FilmesDAO;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Filmes;

public class FXMLAtualizarFilmeController implements Initializable {
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
    private Stage stage;
    private boolean btnAtualizar = false;
    Filmes filme = new Filmes();
    int id = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public boolean isBtnSalvar() {
        return btnAtualizar;
    }

    public void setBtnSalvar(boolean btnSalvar) {
        this.btnAtualizar = btnSalvar;
    }
    @FXML
    void salvar() {
        filme.setId(id);
        filme.setNome(txtNome.getText());
        filme.setDescricao(txtDescricao.getText());
        filme.setLancamento(String.valueOf(dateLancamento.getValue()));
        btnAtualizar = true;
        stage.close();
    }

    @FXML
    void cancelar() {
        stage.close();
    }
    
    
    public void setFilme(Filmes filme){
        this.filme = filme;
        id = filme.getId();
        txtNome.setText(filme.getNome());
        txtDescricao.setText(filme.getDescricao());
        dateLancamento.setValue(LocalDate.parse(filme.getLancamento()));
        
    }
}
