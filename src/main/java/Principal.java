
import javax.swing.JOptionPane;

/**
 * Programa principal do cadastro de cliete..
 */
public class Principal {

    //Quantidade clientes
    static final int TAMANHO = 100;

    // Vetores que armazenam os dados dos clientes
    static int[] ids = new int[TAMANHO];       // Armazena os IDs
    static String[] nomes = new String[TAMANHO]; // Armazena os nomes
    static String[] cpfs = new String[TAMANHO];  // Armazena os CPFs

    // Variável que controla quantos clientes estão cadastrados
    static int total = 0;

    public static void main(String[] args) {

        // variável que guarda a escolha do usuário
        int opcao;

        // Loop principal do sistema (menu)
        do {
            // Mostra o menu e lê a opção escolhida
            opcao = Integer.parseInt(JOptionPane.showInputDialog("\n=== CADASTRO DE CLIENTES ==="
                    + "\n1 - Incluir cliente"
                    + "\n2 - Excluir cliente"
                    + "\n3 - Alterar cliente"
                    + "\n4 - Consultar cliente"
                    + "\n5 - Listar todos"
                    + "\n9 - Sair"
                    + "\n Escolha: "));

            // Estrutura que decide qual método executar
            switch (opcao) {
                case 1:
                    incluir(); // chama método incluir
                    break;
                case 2:
                    excluir(); // chama método excluir
                    break;
                case 3:
                    alterar(); // chama método alterar
                    break;
                case 4:
                    consultar(); // chama método consultar
                    break;
                case 5:
                    listar(); // chama método listar
                    break;
                case 9:
                    System.out.println("Saindo do sistema");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 9); // repete até o usuário escolher sair
    }

    /**
     * Inclui um novo cliente no final dos vetores.
     */
    static void incluir() {
        // Verifica se ainda há espaço no vetor
        if (total >= ids.length) {
            System.out.println("Cadastro cheio!");
        } else {
            // Solicita o ID do cliente       
            int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o Id: "));

            // Verifica se o ID já existe
            if (buscarIndicePorId(id) != -1) {
                System.out.println("Id já cadastrado!");
            } else {

                // Armazena o ID na final do vetor,
                ids[total] = id;
                // Solicita o nome        
                nomes[total] = JOptionPane.showInputDialog("Informe o nome: ");
                // Solicita o CPF
                cpfs[total] = JOptionPane.showInputDialog("Informe o CPF: ");

                // Incrementa o total de clientes
                total = total + 1;

                System.out.println("Cliente cadastrado com sucesso!");
            }
        }
    }

    /**
     * Exclui um cliente dos vetores.
     */
    static void excluir() {

        // Solicita o ID do cliente a ser removido        
        int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o Id a ser excluído: "));

        // Busca a posição do cliente
        int indice = buscarIndicePorId(id);

        // Se não encontrou
        if (indice == -1) {
            System.out.println("Cliente não encontrado!");
        } else {

            // Desloca os elementos para "fechar o buraco"
            for (int i = indice; i < total - 1; i++) {
                ids[i] = ids[i + 1];
                nomes[i] = nomes[i + 1];
                cpfs[i] = cpfs[i + 1];
            }

            // Diminui o total de clientes
            total = total - 1;

            System.out.println("Cliente excluído com sucesso!");
        }
    }

    /**
     * Altera os dados de um cliente. O Id não é alterado.
     */
    static void alterar() {

        // Solicita o ID
        int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o Id: "));

        // Busca o cliente
        int indice = buscarIndicePorId(id);

        // Se não encontrou
        if (indice == -1) {
            System.out.println("Cliente não encontrado!");
        } else {
            // Atualiza nome        
            nomes[indice] = JOptionPane.showInputDialog("Novo nome: ");
            // Atualiza CPF        
            cpfs[indice] = JOptionPane.showInputDialog("Novo CPF: ");

            System.out.println("Cliente alterado com sucesso!");
        }
    }

    /**
     * Consultar por id e nome do cliente.
     */
    static void consultar() {
        // variável que guarda a escolha do usuário
        int opcao;

        // Loop da consulta de cliente (submenu)
        do {
            // Permite escolher tipo de busca
            opcao = Integer.parseInt(JOptionPane.showInputDialog("\n=== CONSULTAR CLIENTES POR ==="
                    + "\n1 - Id"
                    + "\n2 - Nome"
                    + "\n9 - Sair"
                    + "\n Escolha: "));

            // Estrutura que decide qual método executar
            switch (opcao) {
                case 1: // Busca por Id                      
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o Id: "));

                    //Procura a posição do cliente nos vetores
                    int indice = buscarIndicePorId(id);

                    // Se não encontrou
                    if (indice == -1) {
                        System.out.println("Cliente não encontrado!");
                    } else {

                        // Exibe dados
                        System.out.println("\nId: " + ids[indice]);
                        System.out.println("Nome: " + nomes[indice]);
                        System.out.println("CPF: " + cpfs[indice]);
                    }

                    break;
                case 2: // Busca por Nome
                    String nomeBusca = JOptionPane.showInputDialog("Informe o nome: ");

                    boolean encontrou = false;

                    // Percorre todos os clientes
                    for (int i = 0; i < total; i++) {

                        // equalsIgnoreCase ignora maiúsculas/minúsculas
                        if (nomes[i].equalsIgnoreCase(nomeBusca)) {

                            System.out.println("\nId: " + ids[i]);
                            System.out.println("Nome: " + nomes[i]);
                            System.out.println("CPF: " + cpfs[i]);
                            encontrou = true;
                        }
                    }

                    // Caso não encontre nenhum
                    if (!encontrou) {
                        System.out.println("Cliente não encontrado!");
                    }
                    break;
                case 9:
                    System.out.println("Voltando ao menu principal");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (opcao != 9); //Fim do do while
    }

    /**
     * Lista os dados do clientes
     */
    static void listar() {

        // Verifica se há clientes
        if (total == 0) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            System.out.println("\n=== CLIENTES CADASTRADOS ===");

            // Percorre todos os clientes
            for (int i = 0; i < total; i++) {

                System.out.println("Id: " + ids[i]
                        + " | Nome: " + nomes[i]
                        + " | CPF: " + cpfs[i]);
            }
        }
    }

    /**
     * Busca a posição de um cliente nos vetores pelo id.
     *
     * @param id Identificador do cliente
     * @return Um inteiro com a posição. Retorna -1 senão encontrar.
     */
    static int buscarIndicePorId(int id) {

        // Percorre o vetor procurando o ID
        for (int i = 0; i < total; i++) {

            if (ids[i] == id) {
                return i; // retorna a posição encontrada
            }
        }

        return -1; // não encontrou
    }
}
