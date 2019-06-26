
package controller;


import dao.FilmesDAO;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Filmes;
import view.Palco;

public class FXMLVisualizarController implements Initializable {
    FilmesDAO dao = new FilmesDAO();
    
   
    @FXML
    private TableColumn<Filmes, String> colNome;

    @FXML
    private TableView<Filmes> tabela;

    @FXML
    private TableColumn<Filmes, String> colLancamento;

    @FXML
    private TableColumn<Filmes, String> colDescricao;

    @FXML
    private TableColumn<Filmes, Integer> colId;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        mostrarTabela();
    }    

    
        
    @FXML
    public void btExcluir(){        
        Filmes filme = new Filmes();
        filme = tabela.getSelectionModel().getSelectedItem();        
        if(filme != null){            
            if(dao.deletar(filme.getId())){
                Alert alert = new Alert(Alert.AlertType.WARNING);            
                alert.setTitle("Exclusão com sucesso");
                alert.setHeaderText("Exclusão");
                alert.setContentText("Dados excluidos com sucesso");
                alert.showAndWait();
                mostrarTabela();
            }else{
                System.out.println("Erro ao excluir");
            }                             
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);            
            alert.setTitle("Exclusão");
            alert.setHeaderText("ALERTA!!!");
            alert.setContentText("Você deve selecionar um filme para excluir");
            alert.show();
        }        
    }
    
    @FXML public void btVoltar(){
        Palco.telaPrincipal();
    }

    public void mostrarTabela(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colLancamento.setCellValueFactory(new PropertyValueFactory<>("lancamento"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tabela.setItems(listaFilmes());
    }
    public ObservableList<Filmes> listaFilmes(){
        List<Filmes> lista =  dao.pesquisarAll();
        return FXCollections.observableArrayList(lista);        
    }
    
    @FXML
    public void btAlterar() throws IOException{
        Filmes filme = tabela.getSelectionModel().getSelectedItem();
        if(filme != null){
            boolean buttonConfirmaClick = showTela(filme);
            if(buttonConfirmaClick){                
                dao.atualizar(filme);
                mostrarTabela();
            }            
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);            
            alert.setTitle("Alterar");
            alert.setHeaderText("ALERTAR");
            alert.setContentText("Você deve selecionar um filme para alterar");
            alert.show();
        }           
    }

    private boolean showTela(Filmes filme)throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAtualizarFilmeController.class.getResource("/view/FXMLAtualizarFilme.fxml"));
        AnchorPane pagina = (AnchorPane) loader.load();        
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Atualizar Filme");
        Scene scener = new Scene(pagina);
        dialogStage.setScene(scener);
        
        FXMLAtualizarFilmeController alterarController = loader.getController();
        alterarController.setStage(dialogStage);
        alterarController.setFilme(filme);
        dialogStage.showAndWait();
        return alterarController.isBtnSalvar();
    }
}
