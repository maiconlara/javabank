/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javabank.controller;

import java.util.List;
import javabank.model.Conta;
import javabank.model.ContaCorrente;
import javabank.model.ContaInvestimento;
import javabank.model.dao.ContaDao;
import javabank.view.ViewBanco;

/**
 *
 * @author maico
 */
public class ContaController {
    private ContaDao dao_conta;
    private ViewBanco view;
    
    public ContaController() {
        
    }
    
    public ContaController(ViewBanco view,ContaDao dao_conta){
        this.view = view;
        this.dao_conta = dao_conta;
        initController();
    }
    
    public void initController(){
        this.view.setControllerConta(this);
    }
    
    public void listarContas(){
        try{
            List<Conta> contas = this.dao_conta.getLista();
            view.mostrarContas(contas);
        }catch( Exception ex){
            System.out.print("asdas");
        }
    }
    
    
    public void inserirContaCorrente(ContaCorrente cc){
        try{
            this.dao_conta.insertContaCorrente(cc);
            view.adicionarConta(cc);
        } catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Erro ao listar contatos.");
        }
    }
    
    public void inserirContaInvestimento(ContaInvestimento ci){
        try{
            //System.out.println("Erro ao listar contatos.");
            this.dao_conta.inserirContaInvestimento(ci);
            view.adicionarConta(ci);
        } catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Erro ao inserir conta investimento.");
        }
    }
    
    public long buscaNumConta(long id_cliente){
        try{
            return this.dao_conta.buscaNumConta(id_cliente);
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Erro ao buscar num conta.");
            throw new RuntimeException(ex); 
        }
    }
    
    public void Remunerar(Conta conta) {
        conta.remunera();
        AtualizarSaldo(conta);
    }
    
    public boolean Sacar(Conta conta, double valor) {
        if (!conta.saca(valor)) 
            return false;
        
        conta.setSaldo(conta.getSaldo() - valor);
        AtualizarSaldo(conta);
        return true;
        
    }

    public boolean Depositar(Conta conta, double valor) {
        if (!conta.deposita(valor))
            return false;
        conta.setSaldo(conta.getSaldo() + valor);
        AtualizarSaldo(conta);
        return true;
    }
    
    public void AtualizarSaldo(Conta conta) {
        try {
            this.dao_conta.AtualizarSaldo(conta);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    
    
}
