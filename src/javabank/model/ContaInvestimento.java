/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javabank.model;

import javabank.model.ContaI;

/**
 *
 * @author maico
 */
public class ContaInvestimento extends Conta implements ContaI {
    
    private double depositoMinimo;

    public double getDepositoMinimo() {
        return depositoMinimo;
    }

    public void setDepositoMinimo(double depositoMinimo) {
        this.depositoMinimo = depositoMinimo;
    }

    public double getMontanteMinimo() {
        return montanteMinimo;
    }

    public void setMontanteMinimo(double montanteMinimo) {
        this.montanteMinimo = montanteMinimo;
    }
    private double montanteMinimo;
    
    
    @Override
    public boolean saca(double valor) {
        if(valor>0 && (this.saldo-valor >= montanteMinimo )){
            super.saca(valor);
            return true;
        }else{
            return false;        
        }      
    }
    
    
    @Override
    public boolean deposita(double valor) {
        if(valor>0 && valor>=this.depositoMinimo){
            super.deposita(valor);
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    public void remunera() {
       this.saldo += this.saldo*0.02;
    }
}   
    
    
