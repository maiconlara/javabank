/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javabank.model.dao;

import javabank.model.dao.ClienteDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javabank.model.Conta;
import javabank.model.ContaCorrente;
import javabank.model.ContaInvestimento;

/**
 *
 * @author maico
 */
public class ContaDao {
    private final String insert = "insert into conta (id_cliente, tipo, saldo, montante_minimo, deposito_minimo, limite) values (?,?,?,?,?,?)";
    private final String inset_cc = "insert into conta (id_cliente, tipo, saldo, limite) values (?,?,?,?)";
    private final String insert_ci = "insert into conta (id_cliente, tipo, saldo, montante_minimo, deposito_minimo) values (?,?,?,?,?)";
    private final String select = "select * from conta";
    private final String select_numConta = "select id from conta where id_cliente= ?";
    private final String update = "update cliente set nome=?, sobrenome=?, cpf=?, rg=?, salario=?, endereco=? WHERE id=?";
    private final String update_saldo = "update conta set saldo=? where id=?";
    private final String delete = "delete from cliente WHERE id=?";
    
    public ContaDao() {

    }
    
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/lpooii", "root", "Paodeforma1/");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void inserirContaInvestimento(ContaInvestimento ci) throws SQLException{
        try ( 
              Connection connection = this.getConnection();
              PreparedStatement stmtAdiciona = connection.prepareStatement(insert_ci, Statement.RETURN_GENERATED_KEYS);)
        {
            stmtAdiciona.setLong(1, ci.getCliente().getId());
            stmtAdiciona.setString(2, "I");
            stmtAdiciona.setDouble(3, ci.getSaldo());
            stmtAdiciona.setDouble(4, ci.getMontanteMinimo());
            stmtAdiciona.setDouble(5, ci.getDepositoMinimo());
            stmtAdiciona.execute();
            
            ResultSet generatedKeys = stmtAdiciona.getGeneratedKeys();
            generatedKeys.next();
            long id = generatedKeys.getLong(1);
            ci.setIdConta(id);
            
        }catch (SQLException e) {
            throw new RuntimeException(e);
        } 
    }
    
    public void insertContaCorrente(ContaCorrente cc) throws SQLException{
        try ( 
              Connection connection = this.getConnection();
              PreparedStatement stmtAdiciona = connection.prepareStatement(inset_cc, Statement.RETURN_GENERATED_KEYS);)
        {
            stmtAdiciona.setLong(1, cc.getCliente().getId());
            stmtAdiciona.setString(2, "C");
            stmtAdiciona.setDouble(3, cc.getSaldo());
            stmtAdiciona.setDouble(4, cc.getLimite());
            stmtAdiciona.execute();
           
            ResultSet generatedKeys = stmtAdiciona.getGeneratedKeys();
            generatedKeys.next();
            long id = generatedKeys.getLong(1);
            cc.setIdConta(id);
            
            
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } 
    }
    
    public long buscaNumConta(long id_cliente){
        long id = 0;
        try ( 
              Connection connection = this.getConnection();
              PreparedStatement stmtBuscaNumConta = connection.prepareStatement(select_numConta);)
        {
            stmtBuscaNumConta.setLong(1,id_cliente);
            
            ResultSet rs = stmtBuscaNumConta.executeQuery();
            
            while(rs.next()){
                id = rs.getInt("id");
            }
            
            return id;  
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }    
        
    }
    
    public List<Conta> getLista(){
        try ( 
              Connection connection = this.getConnection();
              PreparedStatement stmtBuscaContas = connection.prepareStatement(select);)
        {
            System.out.print("Entrou aqui no getList");
            ResultSet rs = stmtBuscaContas.executeQuery();
            List<Conta> contas = new ArrayList();
            while (rs.next()) {

                
                long id = rs.getLong("id");
                long id_cliente  = rs.getLong("id_cliente");
                String tipo = rs.getString("tipo");
                double saldo = rs.getDouble("saldo");
                double montante_minimo = rs.getDouble("montante_minimo");
                double deposito_minimo = rs.getDouble("deposito_minimo");
                double limite = rs.getDouble("limite");

                if(tipo.equals("C")){
                    
                    ContaCorrente conta = new ContaCorrente();
                    conta.setSaldo(saldo);
                    conta.setLimite(limite);
                    conta.setIdConta(id);
                    
                    ClienteDao c = new ClienteDao();
                    conta.setCliente(c.getCliente(id_cliente));
                    
                    contas.add(conta);
                }else if(tipo.equals("I")){
                    
                    ContaInvestimento conta = new ContaInvestimento();
                    conta.setMontanteMinimo(montante_minimo);
                    conta.setDepositoMinimo(deposito_minimo);
                    conta.setSaldo(saldo);
                    conta.setIdConta(id);
                    
                    ClienteDao c = new ClienteDao();
                    conta.setCliente(c.getCliente(id_cliente));
                    
                    contas.add(conta);
                }
                
                
            }

            return contas;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
         
    }
    
    public void AtualizarSaldo(Conta conta) {
    try ( 
              Connection connection = this.getConnection();
              PreparedStatement stmtAtualizarSaldo = connection.prepareStatement(update_saldo);)
        {
            System.out.println("SALDO CONTA ATTSALDO:" + conta.getSaldo());
            stmtAtualizarSaldo.setDouble(1, conta.getSaldo());
            stmtAtualizarSaldo.setLong(2, conta.getIdConta());
            stmtAtualizarSaldo.execute();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    
    }
    
}
