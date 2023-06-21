import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


public class Lista extends Tarefa {
    private static List<Tarefa> tarefas = new ArrayList<>();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

//=====================================================================================================<Main>================================================================================//
    public static void main(String[] args) {
        Locale locale = new Locale.Builder().setLanguage("pt").setRegion("BR").build();
        Locale.setDefault(locale);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            exibirMenu();
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    ordenarTarefas();
                    adicionarTarefa (scanner);
                    break;
                case 2:
                    ordenarTarefas();
                    removerTarefa (scanner);
                    break;
                case 3:
                    ordenarTarefas();
                    editarTarefa (scanner);
                    break;
               case 4:
                    ordenarTarefas();
                    alterarStatus(scanner);
                    break;
                case 5:
                    ordenarTarefas();
                    break;
                case 6:
                    System.out.println("Encerrando o programa...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opcao invalida, tente novamente!");
                    break;
            }
        }
    }

//=============================================================================================<Exibir Menu>================================================================================//

    private static void exibirMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Adicionar tarefa");
        System.out.println("2. Remover tarefa");
        System.out.println("3. Editar tarefa");
        System.out.println("4. Alterar status da tarefa");
        System.out.println("5. Exibir tarefas por data de previsao");
        System.out.println("6. Sair");
        System.out.println("\nEscolha uma opcao: ");
    }

//============================================================================================<Adicionar Tarefa>============================================================================//

    private static void adicionarTarefa(Scanner scanner) {
    System.out.println("Descricao da tarefa: ");
    scanner.nextLine();
    String descricao = scanner.nextLine();

    boolean dataValida = false;
    while (!dataValida) {
        System.out.println("\nData de previsao da tarefa (DD/MM/AAAA): ");
        String dataString = scanner.next();

        try {
            Date dataPrevisao = dateFormat.parse(dataString);
            tarefas.add(new Tarefa(descricao, dataPrevisao));
            System.out.println("A tarefa foi adicionada com sucesso!");
            dataValida = true;
        } catch (ParseException e) {
            System.out.println("Data invalida. Tente novamente.");
        }
    }
}


//============================================================================================<Remover Tarefa>==============================================================================//

    private static void removerTarefa(Scanner scanner) {
    System.out.println("Digite o indice da tarefa que deseja remover: ");
    int numero = scanner.nextInt();

    int indice = numero - 1; // Ajuste do índice

    if (indice >= 0 && indice < tarefas.size()) {
        tarefas.remove(indice);
        System.out.println("A tarefa foi removida com sucesso!");
        ordenarTarefas();
    } else {
        System.out.println("Indice invalido, tente novamente!");
        ordenarTarefas();
    }
}


//============================================================================================<Editar Tarefa>===============================================================================//

    private static void editarTarefa(Scanner scanner) {
        System.out.println("Digite o índice da tarefa que deseja editar: ");
        int indice = scanner.nextInt();

        if (indice >= 1 && indice <= tarefas.size()) {
            scanner.nextLine();

            System.out.println("Digite a nova descrição da tarefa: ");
            String novaDescricao = scanner.nextLine();

            tarefas.get(indice - 1).setDescricao(novaDescricao);
            System.out.println("A tarefa foi editada com sucesso!");

            System.out.println("Digite a nova data de previsão da tarefa (dd/MM/yyyy): ");
            String novaDataStr = scanner.nextLine();

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date novaData = dateFormat.parse(novaDataStr);

                tarefas.get(indice - 1).setDataPrevisao(novaData);
                System.out.println("A data de previsão foi editada com sucesso!");
                ordenarTarefas();
            } catch (ParseException e) {
                System.out.println("Formato de data inválido. A edição da data falhou!");
            }
        } else {
            System.out.println("Índice inválido, tente novamente!");
            ordenarTarefas();
        }
    }

//============================================================================================<Alterar Status>==============================================================================//

    private static void alterarStatus(Scanner scanner) {
    System.out.println("Digite o indice da tarefa que deseja alterar o status: ");
    int indice = scanner.nextInt();

    if (indice >= 1 && indice <= tarefas.size()) {
        Tarefa tarefa = tarefas.get(indice - 1);
        tarefa.setConcluida(!tarefa.isConcluida());

        String status = tarefa.isConcluida() ? "concluida" : "pendente";
        System.out.println("Status da tarefa alterado para " + status + "\n");
        ordenarTarefas();

    } else {
        System.out.println("Indice invalido. Tente novamente.");
        ordenarTarefas();
    }
}

//============================================================================================<Ordenar Tarefas>==============================================================================//  

   private static void ordenarTarefas() {
    if (tarefas.isEmpty()) {
        System.out.println("Nenhuma tarefa adicionada");
    } else {
        Collections.sort(tarefas, Comparator.comparing(Tarefa::getDataPrevisao));

        System.out.println("Lista de Tarefas:");
        for (int i = 0; i < tarefas.size(); i++) {
            Tarefa tarefa = tarefas.get(i);
            String concluida = tarefa.isConcluida() ? "Concluida" : "Pendente";
            System.out.println("---" + (i + 1) + ". Descricao: " + tarefa.getDescricao() +
                    " | Data: " + dateFormat.format(tarefa.getDataPrevisao()) +
                    " | Status: " + concluida + "---");
        }
    }
}

}


        


