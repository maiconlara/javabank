/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package javabank.model;

import javabank.model.ContaI;

/**
 *
 * @author maico
 */
public class Conta implements ContaI {
    protected double depositoInicial;
    protected double saldo;
    protected Cliente cliente;
    protected long idConta;

    public long getIdConta() {
        return idConta;
    }

    public void setIdConta(long idConta) {
        this.idConta = idConta;
    }
    protected String tipo;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public void setNumConta(Long num){
        idConta = num;
    }
    
    public double getDepositoInicial() {
        return depositoInicial;
    }

    public void setDepositoInicial(double depositoInicial) {
        this.depositoInicial = depositoInicial;
    }
    
    @Override
    public Cliente getDono() {
        return cliente;
    }
    
    @Override
    public long getNumero() {
       return idConta;
    }
    
    @Override
    public double getSaldo() {
        return saldo;
    }
    
    public void setSaldo(double saldo){
        this.saldo = saldo;
    }
    
    public void remunera() {
    }
    
    public boolean deposita(double valor) {
        return valor > 0;
    }
    
    public boolean saca(double valor) {
        return valor > 0;
    }
}
