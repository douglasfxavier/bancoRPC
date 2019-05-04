package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

//    private Map<String, Double> saldoContas;
    Map<String,Conta> contas;

    public BancoServiceServer() throws RemoteException {
        this.contas = new HashMap<String,Conta>();
//        saldoContas = new HashMap<>();
//        saldoContas.put("1", 100.0);
//        saldoContas.put("2", 156.0);
//        saldoContas.put("3", 950.0);
    }

    @Override
    public void cadastrarConta(String numeroConta) {
        contas.put(numeroConta,new Conta(numeroConta));
    }

    @Override
    public Conta pesquisarConta(String numeroConta) throws RemoteException {
        return contas.get(numeroConta);
    }

    @Override
    public void creditarSaldo(String numeroconta, double valor) throws RemoteException {
        Conta conta = contas.get(numeroconta);
        conta.setSaldo(conta.getSaldo() + valor);
    }

    @Override
    public double saldo(String numeroConta) throws RemoteException {
        Conta conta = contas.get(numeroConta);
        return conta.getSaldo();
    }

    @Override
    public void removerConta(String numeroConta) throws RemoteException {
        contas.remove(numeroConta);
    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return contas.size();
    }

}
