package com.gugawag.rpc.banco;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BancoServiceIF extends Remote {
    void cadastrarConta(String numeroConta)throws RemoteException;
    Conta pesquisarConta(String numeroConta) throws RemoteException;
    void creditarSaldo(String numeroConta, double valor) throws RemoteException;
    double saldo(String numeroConta) throws RemoteException;
    void removerConta(String numeroConta) throws  RemoteException;
    int quantidadeContas() throws RemoteException;
}
