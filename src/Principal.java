import entidades.Acionista;
import entidades.Funcionario;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Principal extends Application {
    private List<Acionista> acionistas = new ArrayList<>();
    private List<Funcionario> funcionarios = new ArrayList<>();
    private ListView<Acionista> acionistasListView;
    private ListView<Funcionario> funcionariosListView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sistema de Clube");

        // Criação do painel principal
        HBox root = new HBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        // Criação dos botões para cada opção
        Label clubeDosPatetasLabel = new Label("Clube dos Patetas - Gerenciamento");
        clubeDosPatetasLabel.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        Button cadastrarAcionistaButton = new Button("Cadastrar Acionista");
        Button editarAcionistaButton = new Button("Editar Acionista");
        Button cadastrarFuncionarioButton = new Button("Cadastrar Funcionário");
        Button editarFuncionarioButton = new Button("Editar Funcionário");
        Button fecharProgramaButton = new Button("Fechar Programa");

        // Definição dos eventos de clique dos botões
        cadastrarAcionistaButton.setOnAction(e -> cadastrarAcionista());
        editarAcionistaButton.setOnAction(e -> editarAcionista());
        cadastrarFuncionarioButton.setOnAction(e -> cadastrarFuncionario());
        editarFuncionarioButton.setOnAction(e -> editarFuncionario());
        fecharProgramaButton.setOnAction(e -> primaryStage.close());

        // Criação dos ListViews para acionistas e funcionários
        Label acionistasLabelTitutloLabel = new Label("Acionistas");
        acionistasLabelTitutloLabel.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

        acionistasListView = new ListView<>();
        acionistasListView.setItems(FXCollections.observableArrayList(acionistas));
        acionistasListView.setPrefWidth(200);
        acionistasListView.setPrefHeight(300);
        Label funcionariosLabelTitutloLabel = new Label("Funcionários");
        funcionariosLabelTitutloLabel.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        funcionariosListView = new ListView<>();
        funcionariosListView.setItems(FXCollections.observableArrayList(funcionarios));
        funcionariosListView.setPrefWidth(200);
        funcionariosListView.setPrefHeight(300);

        // Adição dos botões e ListViews ao painel principal
        VBox buttonsBox = new VBox(10);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.getChildren().addAll(
                clubeDosPatetasLabel,
                cadastrarAcionistaButton,
                editarAcionistaButton,
                cadastrarFuncionarioButton,
                editarFuncionarioButton,
                fecharProgramaButton
        );

        HBox titulosBox = new HBox(40);
        titulosBox.setAlignment(Pos.CENTER);
        titulosBox.getChildren().addAll(
            acionistasLabelTitutloLabel,
            funcionariosLabelTitutloLabel
        );
        HBox listViewsBox = new HBox(20);
        listViewsBox.setAlignment(Pos.CENTER);
        listViewsBox.getChildren().addAll(
                acionistasListView,
                funcionariosListView
        );

        VBox titulosEViewBox = new VBox(20);
        titulosEViewBox.setAlignment(Pos.CENTER);
        titulosEViewBox.getChildren().addAll(
            titulosBox,
            listViewsBox
        );
        root.getChildren().addAll(
            buttonsBox,
            titulosEViewBox
        );

        // Criação da cena principal
        Scene scene = new Scene(root, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void cadastrarAcionista() {
        Stage stage = new Stage();
        stage.setTitle("Cadastrar Acionista");

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Label nomeLabel = new Label("Nome:");
        TextField nomeTextField = new TextField();

        Label enderecoLabel = new Label("Endereço:");
        TextField enderecoTextField = new TextField();

        Label cpfLabel = new Label("CPF:");
        TextField cpfTextField = new TextField();

        Label dataNascimentoLabel = new Label("Data de Nascimento:");
        DatePicker dataNascimentoPicker = new DatePicker();

        Label tipoAcaoLabel = new Label("Tipo de Ação (Familiar/Individual):");
        TextField tipoAcaoTextField = new TextField();

        Label dependentesLabel = new Label("Quantidade de Dependentes (Se for Individual será 0):");
        TextField dependentesTextField = new TextField();

        Button cadastrarButton = new Button("Cadastrar");
        cadastrarButton.setOnAction(e -> {
            String nome = nomeTextField.getText();
            String endereco = enderecoTextField.getText();
            String cpf = cpfTextField.getText();
            if (cpf.length() != 11 || nome.isEmpty() || endereco.isEmpty() || nome.substring(0).matches("[0-9]*") || endereco.substring(0).matches("[0-9]*")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Falha");
                alert.setHeaderText(null);
                alert.setContentText("Acionista não cadastrado!");
                alert.showAndWait();
            } else {
                String dataNascimento = dataNascimentoPicker.getValue().toString();
                String tipoAcao = tipoAcaoTextField.getText();
                if (tipoAcao.equals("Familiar") || tipoAcao.equals("Individual")) {
                    
                    int dependentes = Integer.parseInt(dependentesTextField.getText());
                    if (tipoAcao.equals("Individual")) {
                        if (dependentes > 0) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Falha");
                            alert.setHeaderText(null);
                            alert.setContentText("O número de dependentes foi alterado pra 0, pois a ação é individual!");
                            alert.showAndWait();
                        }
                        dependentes = 0;
                    }
                    Acionista acionista = new Acionista(nome, endereco, cpf, dataNascimento, tipoAcao, dependentes);
                    acionistas.add(acionista);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Sucesso");
                    alert.setHeaderText(null);
                    alert.setContentText("Acionista cadastrado com sucesso!");
                    alert.showAndWait();

                    acionistasListView.setItems(FXCollections.observableArrayList(acionistas)); // Atualiza a ListView de funcionários
           
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Falha");
                    alert.setHeaderText(null);
                    alert.setContentText("Acionista não cadastrado!");
                    alert.showAndWait();
                }
            }
            stage.close();
        });

        root.getChildren().addAll(
                nomeLabel,
                nomeTextField,
                enderecoLabel,
                enderecoTextField,
                cpfLabel,
                cpfTextField,
                dataNascimentoLabel,
                dataNascimentoPicker,
                tipoAcaoLabel,
                tipoAcaoTextField,
                dependentesLabel,
                dependentesTextField,
                cadastrarButton
        );

        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show();
    }

    private void editarAcionista() {
        Stage stage = new Stage();
        stage.setTitle("Editar Acionista");

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Label cpfLabel = new Label("CPF:");
        TextField cpfTextField = new TextField();

        Button editarButton = new Button("Procurar");
        editarButton.setOnAction(e -> {
            String cpf = cpfTextField.getText();

            Optional<Acionista> acionistaOptional = acionistas.stream()
                    .filter(a -> a.getCpf().equals(cpf))
                    .findFirst();
            
            if (acionistaOptional.isPresent()) {
                Acionista acionista = acionistaOptional.get();

                // Criação dos botões para as opções adicionais
                Label nomeAcionista = new Label("Nome: " + acionista.getNome() + "\nCPF: " + acionista.getCpf());
                Button fazerPagamentoButton = new Button("Fazer Pagamento");
                Button aumentarParcelasButton = new Button("Aumentar Parcelas em Atraso");
                Button cancelarAcaoButton = new Button("Cancelar Ação");

                // Definição dos eventos de clique dos botões
                fazerPagamentoButton.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informação");
                    alert.setHeaderText(null);
                    alert.setContentText("Quantidade de parcelas em atraso: " + acionista.getTempo_atraso());
                    acionistasListView.setItems(FXCollections.observableArrayList(acionistas)); // Atualiza a ListView de funcionários
                    alert.showAndWait();
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Fazer Pagamento");
                    dialog.setHeaderText(null);
                    dialog.setContentText("Digite a quantidade de parcelas a serem pagas:");

                    Optional<String> result = dialog.showAndWait();
                    result.ifPresent(quantidadeParcelas -> {
                        int quantidade = Integer.parseInt(quantidadeParcelas);
                        if (quantidade <= acionista.getTempo_atraso()) {
                            acionista.fazer_pagamento(quantidade);
                            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                            alert2.setTitle("Sucesso");
                            alert2.setHeaderText(null);
                            alert2.setContentText("Foi pago " + quantidade + " parcelas no total de R$" + (acionista.getPreco_acao()*quantidade));
                            alert2.showAndWait();
                            if (acionista.getTempo_atraso() == 0)  {
                                acionista.setEm_atraso(false);
                            }
                            funcionariosListView.setItems(FXCollections.observableArrayList(funcionarios));
                        }
                    });
                });
                aumentarParcelasButton.setOnAction(event -> {
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Aumentar Parcelas em Atraso");
                    dialog.setHeaderText(null);
                    dialog.setContentText("Digite a quantidade de parcelas em atraso a serem adicionadas:");

                    Optional<String> result = dialog.showAndWait();
                    result.ifPresent(parcelas -> {
                        int quantidade = Integer.parseInt(parcelas);
                        acionista.setTempo_atraso(acionista.getTempo_atraso() + quantidade);
                        acionista.setEm_atraso(true);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Sucesso");
                        alert.setHeaderText(null);
                        alert.setContentText("Parcelas em atraso aumentadas com sucesso!");
                        alert.showAndWait();
                        funcionariosListView.setItems(FXCollections.observableArrayList(funcionarios));
                    });
                });

                cancelarAcaoButton.setOnAction(event -> {
                    acionistas.remove(acionista);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Sucesso");
                    alert.setHeaderText(null);
                    alert.setContentText("Ação cancelada com sucesso!");
                    alert.showAndWait();
                    acionistasListView.setItems(FXCollections.observableArrayList(acionista));
                    stage.close();
                });

                root.getChildren().addAll(
                        nomeAcionista,
                        fazerPagamentoButton,
                        aumentarParcelasButton,
                        cancelarAcaoButton
                );
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Acionista não encontrado!");
                alert.showAndWait();
            }
        });

        root.getChildren().addAll(
                cpfLabel,
                cpfTextField,
                editarButton
        );

        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }


    private void cadastrarFuncionario() {
        Stage stage = new Stage();
        stage.setTitle("Cadastrar Funcionário");

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Label nomeLabel = new Label("Nome:");
        TextField nomeTextField = new TextField();

        Label enderecoLabel = new Label("Endereço:");
        TextField enderecoTextField = new TextField();

        Label cpfLabel = new Label("CPF:");
        TextField cpfTextField = new TextField();

        Label dataNascimentoLabel = new Label("Data de Nascimento:");
        DatePicker dataNascimentoPicker = new DatePicker();

        Label salarioLabel = new Label("Salário:");
        TextField salarioTextField = new TextField();

        Label funcaoLabel = new Label("Função:");
        TextField funcaoTextField = new TextField();

        Button cadastrarButton = new Button("Cadastrar");
        cadastrarButton.setOnAction(e -> {
            String nome = nomeTextField.getText();
            String endereco = enderecoTextField.getText();
            String cpf = cpfTextField.getText();
            if (cpf.length() != 11 || nome.isEmpty() || endereco.isEmpty() || nome.substring(0).matches("[0-9]*") || endereco.substring(0).matches("[0-9]*")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Falha");
                alert.setHeaderText(null);
                alert.setContentText("Funcionário não cadastrado");
                alert.showAndWait();
            } else {
                String dataNascimento = dataNascimentoPicker.getValue().toString();
                double salario = Double.parseDouble(salarioTextField.getText());
                String funcao = funcaoTextField.getText();

                Funcionario funcionario = new Funcionario(nome, endereco, cpf, dataNascimento, salario, funcao);
                funcionarios.add(funcionario);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText(null);
                alert.setContentText("Funcionário cadastrado com sucesso!");
                alert.showAndWait();
                funcionariosListView.setItems(FXCollections.observableArrayList(funcionarios));
            }
            stage.close();
        });

        root.getChildren().addAll(
                nomeLabel,
                nomeTextField,
                enderecoLabel,
                enderecoTextField,
                cpfLabel,
                cpfTextField,
                dataNascimentoLabel,
                dataNascimentoPicker,
                salarioLabel,
                salarioTextField,
                funcaoLabel,
                funcaoTextField,
                cadastrarButton
        );

        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show();
    }
    

    private void editarFuncionario() {
        Stage stage = new Stage();
        stage.setTitle("Editar Funcionário");

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Label cpfLabel = new Label("CPF:");
        TextField cpfTextField = new TextField();

        Button editarButton = new Button("Procurar");
        editarButton.setOnAction(e -> {
            String cpf = cpfTextField.getText();

            Optional<Funcionario> funcionarioOptional = funcionarios.stream()
                    .filter(f -> f.getCpf().equals(cpf))
                    .findFirst();

            if (funcionarioOptional.isPresent()) {
                Funcionario funcionario = funcionarioOptional.get();

                // Criação dos botões para as opções adicionais
                Label nomeFuncionario = new Label("Nome: " + funcionario.getNome() + "\nCPF: " + funcionario.getCpf());
                Button mudarFuncaoButton = new Button("Mudar Função");
                Button aumentarSalarioButton = new Button("Aumentar Salário");
                Button demitirFuncionarioButton = new Button("Demitir Funcionário");

                // Definição dos eventos de clique dos botões
                mudarFuncaoButton.setOnAction(event -> {
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Mudar Função");
                    dialog.setHeaderText(null);
                    dialog.setContentText("Digite a nova função do funcionário:");

                    Optional<String> result = dialog.showAndWait();
                    result.ifPresent(novaFuncao -> {
                        funcionario.setFuncao(novaFuncao);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Sucesso");
                        alert.setHeaderText(null);
                        alert.setContentText("Função alterada com sucesso!");
                        funcionariosListView.setItems(FXCollections.observableArrayList(funcionarios));
                        alert.showAndWait();
                    });
                });

                aumentarSalarioButton.setOnAction(event -> {
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Aumentar Salário");
                    dialog.setHeaderText(null);
                    dialog.setContentText("Digite o acréscimo do salário do funcionário em porcentagem:");

                    Optional<String> result = dialog.showAndWait();
                    result.ifPresent(acrescimo -> {
                        double aumentoPercentual = Double.parseDouble(acrescimo);
                        double novoSalario = funcionario.getSalario() * (1 + (aumentoPercentual / 100));
                        funcionario.setSalario(novoSalario);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Sucesso");
                        alert.setHeaderText(null);
                        alert.setContentText("Salário atualizado com sucesso!");
                        funcionariosListView.setItems(FXCollections.observableArrayList(funcionarios));
                        alert.showAndWait();
                    });
                });

                demitirFuncionarioButton.setOnAction(event -> {
                    funcionarios.remove(funcionario);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Sucesso");
                    alert.setHeaderText(null);
                    alert.setContentText("Funcionário demitido com sucesso!");
                    alert.showAndWait();
                    funcionariosListView.setItems(FXCollections.observableArrayList(funcionarios));
                    stage.close();
                });

                root.getChildren().addAll(
                        nomeFuncionario,
                        mudarFuncaoButton,
                        aumentarSalarioButton,
                        demitirFuncionarioButton
                );
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Funcionário não encontrado!");
                alert.showAndWait();
            }
        });

        root.getChildren().addAll(
                cpfLabel,
                cpfTextField,
                editarButton
        );

        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}