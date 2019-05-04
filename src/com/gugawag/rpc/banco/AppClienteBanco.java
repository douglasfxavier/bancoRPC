package com.gugawag.rpc.banco;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class AppClienteBanco {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        // procura o serviço no RMI Registry local. Perceba que o cliente não connhece a implementação do servidor,
        // apenas a interface
        Registry registry = LocateRegistry.getRegistry();
        BancoServiceIF banco = (BancoServiceIF) registry.lookup("BancoService");

        menu();
        Scanner entrada = new Scanner(System.in);
        int opcao = entrada.nextInt();

        while(opcao != 9) {
            switch (opcao) {
                case 0: {
                    //0 - Cadastrar conta
                    System.out.println("Digite o número da conta: ");
                    String conta = entrada.next();
                    banco.cadastrarConta(conta);
                    System.out.println("Cliente cadastrado com sucesso!");
                    break;
                }
                case 1: {
                    //1 - Consultar saldo da conta
                    System.out.println("Digite o número da conta:");
                    String numeroConta = entrada.next();
                    //chamada ao método remoto, como se fosse executar localmente
                    double saldo = banco.saldo(numeroConta);
                    System.out.println(String.format("SALDO da conta %s: %s",
                            numeroConta,saldo));
                    break;
                }
                case 2: {
                    //2 - Quantidade de contas
                    int quantidadeConta = banco.quantidadeContas();
                    System.out.println(
                            String.format("%s conta(s) registrada(s)",
                            quantidadeConta));
                    break;
                }
                case 3: {
                    //3 - Fazer depósito em conta
                    System.out.println("Número da conta: ");
                    String numeroConta = entrada.next();
                    System.out.println("Valor a depositar: ");
                    double valor = entrada.nextDouble();
                    banco.creditarSaldo(numeroConta,valor);
                    System.out.println(
                            String.format("Depósito de %s realizado na conta %s",
                                    valor,numeroConta));
                    break;
                }
                case 4: {
                    //4 - Pesquisar conta
                    System.out.println("Número da conta: ");
                    String numeroConta = entrada.next();
                    Conta conta = banco.pesquisarConta(numeroConta);
                    System.out.println(conta);
                    break;
                }
                case 5: {
                    //5 - Remover contar
                    System.out.println("Número da conta: ");
                    String numeroConta = entrada.next();
                    banco.removerConta(numeroConta);
                    System.out.println(
                            String.format("Conta %s removida com sucesso!",
                                    numeroConta));
                }
            }
            menu();
            opcao = entrada.nextInt();
        }
    }

    public static void menu() {
        System.out.println("\n=== BANCO RMI (ou FMI?!) ===");
        System.out.println("0 - Cadastrar conta");
        System.out.println("1 - Consultar saldo da conta");
        System.out.println("2 - Quantidade de contas");
        System.out.println("3 - Fazer depósito em conta");
        System.out.println("4 - Pesquisar conta");
        System.out.println("5 - Remover contar");
        System.out.println("9 - Sair");
    }

}
