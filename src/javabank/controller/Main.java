/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javabank.controller;
import javabank.controller.ClienteController;
import javabank.model.dao.ClienteDao;
import javabank.model.dao.ContaDao;
import javabank.view.ViewBanco;
import javabank.controller.ContaController;
/**
 *
 * @author maico
 */
public class Main {
        public static void main(String args[]){
            
        
        ViewBanco view = new ViewBanco();
        ClienteDao dao = new ClienteDao();
        ContaDao dao_conta = new ContaDao();
        ClienteController controller = new ClienteController(view,dao);
        ContaController controller_conta = new ContaController(view, dao_conta);
    } 
}
