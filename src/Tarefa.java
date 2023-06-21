import java.util.Date;

public class Tarefa {

    private String descricao;
    private Date dataPrevisao;
    private boolean concluida;

    public Tarefa() {
        // Construtor padrão vazio
    }

    public Tarefa(String descricao, Date dataPrevisao) {
        if (descricao == null || dataPrevisao == null) {
            throw new IllegalArgumentException("Tarefa e data devem ser não nulos");
        }
        this.descricao = descricao;
        this.dataPrevisao = dataPrevisao;
        this.concluida = false;
    }

    // Getters e setters

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataPrevisao() {
        return dataPrevisao;
    }

    public void setDataPrevisao(Date dataPrevisao) {
        this.dataPrevisao = dataPrevisao;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }
}
