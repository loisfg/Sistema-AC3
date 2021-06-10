/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author Loisfg
 */
public class ConexaoSql {
        private BasicDataSource banco;

    public ConexaoSql() {
        
        this.banco = new BasicDataSource();
        banco.setDriverClassName("com.mysql.jdbc.Driver");
        banco.setUrl("jdbc:mysql://localhost:3306/KeepCode");
        banco.setUsername("root");
        banco.setPassword("urubu100");
    }

    public BasicDataSource getBanco() {
        return banco;
    }
}
