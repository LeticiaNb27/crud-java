package view;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Palco extends Application {
   static Stage palco; 
   
   public static Scene sceneCadFilme;
   public static Scene sceneVisualizarFilme ;
   public static Scene scener;

   
    
    @Override
    public void start(Stage stage) throws IOException{
        palco = stage;
        //Carregar os FXMLs
        Parent root = FXMLLoader.load(getClass().getResource("FXMLPrincipal.fxml"));

        
        //Crio as scenas com os fxmls
         scener = new Scene(root, 600, 600);
         
        
        
         stage.setTitle("Tela Principal");
         stage.setScene(scener);
         stage.setResizable(false);
         stage.show();
    }    


    public static void main(String[] args) {
        launch(args);
    }
    
    public void cadastroFilme() throws IOException{
        Parent cadFilme = FXMLLoader.load(getClass().getResource("FXMLCadastroFilme.fxml"));
        sceneCadFilme = new Scene(cadFilme, 600, 600);
        palco.setTitle("Cadastro de Filme");
        palco.setScene(sceneCadFilme);        
    }
    
            
       
    
    public void mostrarFilme() throws IOException{
        Parent visualizarFilme = FXMLLoader.load(getClass().getResource("FXMLVisualizar.fxml"));
        sceneVisualizarFilme = new Scene(visualizarFilme, 600, 600);
        palco.setTitle("Visualizar Filme");
        palco.setScene(sceneVisualizarFilme);
    }
    
    public static void telaPrincipal(){
        palco.setTitle("Tela Principal");
        palco.setScene(scener);
    }
    
    
    
    
}
