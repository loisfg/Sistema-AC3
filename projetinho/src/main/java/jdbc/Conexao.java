package jdbc;

import org.apache.commons.dbcp2.BasicDataSource;

public class Conexao {

    private BasicDataSource banco;

    public Conexao() {
        
        this.banco = new BasicDataSource();
        banco.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        banco.setUrl("jdbc:sqlserver://ac3-marises.database.windows.net:1433;database=Ac3;");
        banco.setUsername("luis");
        banco.setPassword("1q@W3e4r5t");
    }

    public BasicDataSource getBanco() {
        return banco;
    }

}
