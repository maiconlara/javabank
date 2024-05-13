/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javabank.controller;

import java.util.List;
import javax.swing.JOptionPane;
import javabank.model.Cliente;
import javabank.view.ViewBanco;
import javabank.model.dao.ClienteDao;
import javabank.model.Conta;


//import javabank.model.Conta;

import javabank.model.ContaCorrente;
import javabank.model.ContaInvestimento;
import javabank.model.dao.ContaDao;


/**
 *
 * @author maico
 */


public class ClienteController {
    private ViewBanco view;
    private ClienteDao dao;
    
    
    public ClienteController(ViewBanco view, ClienteDao dao){
        this.view = view;
        this.dao = dao;
        initController();
    }

    public ClienteController() {
        
    }
    
    private void initController(){
       this.view.setController(this);
       this.view.initView();
    }
    
    public Cliente buscaClienteCpf(String cpf){
        try{
            return this.dao.buscaClienteCpf(cpf);
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Erro ao buscar  cliente.");
            throw new RuntimeException(ex); 
        }
    } 
    
    
    
    
    public void listarCliente(){
        try{
            List<Cliente> clientes = this.dao.getLista();
            view.mostrarListaClientes(clientes);
        }catch( Exception ex){
            System.out.print("");
        }
    }
    
    public void listarClienteSemConta(){
        try{
             List<Cliente> clientes = this.dao.listarClienteSemConta();
             view.mostrarListaClientes(clientes);
        }catch( Exception ex){
            System.out.print("");
        }
    }
    
   
    public void inserirCliente(Cliente cliente){
        try{
            this.dao.inserir(cliente);
            //this.listarCliente();
            view.adicionarCliente(cliente);
        } catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Erro ao listar contatos.");
        }
    }
    
    public void deleteCliente(Cliente cliente){
        try{
            ClienteDao dao_aux = new ClienteDao();
            dao_aux.deleteCliente(cliente);
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Erro excluir cliente");
        }
    }
    
    
    public void updateCliente(Cliente cliente){
        try{
            ClienteDao dao_aux = new ClienteDao();
            dao_aux.updateCliente(cliente);
            view.mostarMensagemInfo("Cliente Atualizado com sucesso!");
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Erro editar cliente");
        }
    }
    

    
}
