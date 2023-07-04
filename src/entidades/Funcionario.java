package entidades;

public class Funcionario extends Pessoa {

    private double salario;
    private String funcao;
    public Funcionario(String nome, String endereco, String cpf, String data_nascimento, double salario, String funcao) {
        super(nome, endereco, cpf, data_nascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        return "Nome = " + getNome() + "\nCPF = " + getCpf() + "\nFunção = " + getFuncao() + "\nSalário = R$" + getSalario();    
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
    
     
}