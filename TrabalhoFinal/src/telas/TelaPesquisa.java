package telas;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import basededados.BancoDados;
import logica.PessoaBusinnes;
import modelo.Pessoa;

import static javafx.scene.layout.Priority.ALWAYS;

public class TelaPesquisa extends Application {

    private ObservableList<Pessoa> dadosGrid = FXCollections.observableArrayList();


    private BorderPane borderPane = new BorderPane();

    private TableView<Pessoa> gridPessoas;

    private Stage stage;

    @Override
    public void start(Stage palco) throws Exception {


        dadosGrid.addAll(BancoDados.getInstance().tbPessoa);

        palco.setTitle("Pesquisa");

        borderPane.paddingProperty().setValue(new Insets(10));
        borderPane.setPrefSize(900, 700);

        HBox boxCampoDePesquisa = montaPainelDePesquisa();

        borderPane.setTop(boxCampoDePesquisa);

        gridPessoas = montaGridPessoas();

        borderPane.setCenter(gridPessoas);


        ToolBar toolBar = montaBarraDeFerramentas();

        borderPane.setBottom(toolBar);

        palco.setScene(new Scene(borderPane, 800, 600));
        palco.show();

        this.stage = palco;
    }

    private ToolBar montaBarraDeFerramentas() {

        ToolBar toolBar = new ToolBar();

        Button btnNovo = new Button("NOVO");
        Button btnAlterar = new Button("ALTERAR");
        Button btnExcluir = new Button("EXCLUIR");

        toolBar.getItems().addAll(btnNovo, btnAlterar, btnExcluir);


        btnNovo.setOnAction(click -> {

            invocarTelaDeCadastro(null);

        });

        btnExcluir.setOnAction(click -> {


            if (gridPessoas.getSelectionModel().getSelectedItem() != null) {
                Pessoa pessoa = gridPessoas.getSelectionModel().getSelectedItems().get(0);
                PessoaBusinnes businnes = new PessoaBusinnes();
                businnes.excluirPessoa(pessoa);
                //atualizar a tela
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        dadosGrid = FXCollections.observableArrayList();
                        dadosGrid.addAll(BancoDeDadosEmMemoria.getInstance().tbPessoas);
                        gridPessoas.setItems(null);
                        gridPessoas.setItems(dadosGrid);
                    }
                });


            }
        });


        btnAlterar.setOnAction(click -> {
            if (gridPessoas.getSelectionModel().getSelectedItem() != null) {
                Pessoa pessoa = gridPessoas.getSelectionModel().getSelectedItems().get(0);

         

                invocarTelaDeCadastro(pessoa);





            }
        });
        


        return toolBar;
    }

    private void invocarTelaDeCadastro(Pessoa pessoa) {
        TelaCadastro cadastro = new TelaCadastro(stage);
        stage.setScene(new Scene(cadastro.getTela(), 800, 600));

        if (pessoa == null) {
            cadastro.iniciaInsercao();
        } else {
            cadastro.iniciaAlteracao(pessoa);
        }

    }

    private HBox montaPainelDePesquisa() {

        HBox boxCampoDePesquisa = new HBox();
        boxCampoDePesquisa.paddingProperty().setValue(new Insets(10));
        boxCampoDePesquisa.setHgrow(borderPane, ALWAYS);
     
        TextField ct = new TextField();
        ct.setPrefColumnCount(60);

        ct.promptTextProperty().setValue("Digite aqui para Fazer sua Pesquisa");

        
        Button btnPesquisa = new Button("Busca");
        boxCampoDePesquisa.getChildren().add(ct);
        boxCampoDePesquisa.getChildren().add(btnPesquisa);

        btnPesquisa.setOnAction(action -> {
            String chave = ct.textProperty().get();
            PessoaBusinnes businnes = new PessoaBusinnes();
            try {
                Pessoa pessoa = businnes.pesquisaPorCodigo(chave);
                if (pessoa != null) {
                    dadosGrid.clear();
                    dadosGrid.add(pessoa);
                } else {
                    System.out.println(" Pessoa Não Encontrada");
                }
            } catch (Exception e) {
                e.printStackTrace();
                /*Popup erro = new Popup();
                erro.centerOnScreen();
                erro.getContent().addAll( new Label(e.getMessage()));
                erro.show(palco);*/

            }

        });

      
        return boxCampoDePesquisa;
    }

    private TableView<Pessoa> montaGridPessoas() {

       
        TableColumn colunaCodigo = new TableColumn();
        colunaCodigo.minWidthProperty().setValue(100);
        
        colunaCodigo.setText("CÓDIGO");
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("CODIGO"));

        TableColumn colunaNome = new TableColumn();
        colunaNome.minWidthProperty().setValue(100);
        colunaNome.setText("NOME");
        colunaNome.setCellValueFactory(new PropertyValueFactory("NOME"));

        TableColumn colunaEmail = new TableColumn();
        colunaEmail.minWidthProperty().setValue(100);
        colunaEmail.setText("EMAIL");
        colunaEmail.setMinWidth(200);
        colunaEmail.setCellValueFactory(new PropertyValueFactory("EMAIL"));

        TableColumn colunaTelefone = new TableColumn();
        colunaTelefone.minWidthProperty().setValue(100);
        colunaTelefone.setText("TELEFONE");
        colunaTelefone.setMinWidth(200);
        colunaTelefone.setCellValueFactory(new PropertyValueFactory("TELEFONE"));

        TableView<Pessoa> gridPessoas = new TableView();

        gridPessoas.getColumns().addAll(colunaCodigo, colunaNome, colunaEmail, colunaTelefone);
        gridPessoas.setItems(dadosGrid);


        return gridPessoas;
    }


    public void atualizarGrid() {
       
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                dadosGrid = FXCollections.observableArrayList();
                dadosGrid.addAll(BancoDados.getInstance().tbPessoas);
                gridPessoas.setItems(null);
                gridPessoas.setItems(dadosGrid);
            }
        });
    }
}