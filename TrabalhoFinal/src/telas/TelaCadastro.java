package telas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logica.PessoaBusinnes;
import modelo.Pessoa;

public class TelaCadastro {

    private Stage Palco;
    private VBox janela;
    private EstadoTela estadoTela = EstadoTela.INSERINDO;
    private Pessoa entidade;
    public  TextField edtCodigo = new TextField();
    public  TextField edtNome = new TextField();
    public  TextField edtEmail = new TextField();
    public TextField edtTelefone = new TextField();


    public TelaCadastro(Stage palco) {
        this.palco = palco;

        janela = new VBox();

        Label lblCodigo = new Label("Codigo:");


        edtCodigo.promptTextProperty().setValue("informe o código");
        janela.getChildren().addAll(lblCodigo, edtCodigo);

        Label lblNome = new Label();
        lblNome.setText("Nome: ");

        janela.getChildren().add(lblNome);
        janela.getChildren().add(edtNome);

        Label lblEmail = new Label("Email:");

        edtEmail.promptTextProperty().setValue("Digite aqui o email");
        janela.getChildren().addAll(lblEmail, edtEmail);

        Label lblTelefone = new Label("Telefone:");
        edtTelefone.promptTextProperty().setValue("informe o Telefone");
        janela.getChildren().addAll(lblTelefone, edtTelefone);

        // controles
        Button btnConfirmar = new Button("Confirmar");


        btnConfirmar.setOnAction(event -> {
            PessoaBusinnes businnes = new PessoaBusinnes();

            if (estadoTela.equals(EstadoTela.INSERINDO)) {
                businnes.addNovaPessoa(edtCodigo.getText(), edtNome.getText(), edtEmail.getText(),edtTelefone.getText());
                edtCodigo.textProperty().setValue(null);
                edtNome.textProperty().setValue(null);
                edtEmail.textProperty().setValue(null);
                edtTelefone.textProperty().setValue(null);
            } else if (estadoTela.equals(EstadoTela.EDITANDO)) {
                businnes.alterarPessoa(this.entidade, edtCodigo.getText(), edtNome.getText(), edtEmail.getText(),edtTelefone.getText());
                try {
                    abrirTelaDePesquisa(palco);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Button btnJanelaPesquisa = new Button("Pesquisa");

        ToolBar toolBar = new ToolBar();
        toolBar.getItems().addAll(btnConfirmar, btnJanelaPesquisa);

        janela.getChildren().add(toolBar);


        btnJanelaPesquisa.setOnAction(action -> {
            //comandos a executar quanto clicarmos no botão pesquisa
            try {


                abrirTelaDePesquisa(palco);

            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    private void abrirTelaDePesquisa(Stage palco) throws Exception {
        TelaPesquisa pesquisa = new TelaPesquisa();
        pesquisa.start(palco);
        pesquisa.atualizarGrid();
    }

    public VBox getTela() {
        return this.janela;
    }

    public void iniciaInsercao() {
        this.estadoTela = EstadoTela.INSERINDO;
    }

    public void iniciaAlteracao(Pessoa pessoa) {
        this.entidade = pessoa;
        this.estadoTela = EstadoTela.EDITANDO;
        edtCodigo.textProperty().setValue(pessoa.getCodigo());
        edtNome.textProperty().setValue(pessoa.getNome());
        edtEmail.textProperty().setValue(pessoa.getEmail());
        edtTelefone.textProperty().setValue(pessoa.getTelefone());

    }
}
