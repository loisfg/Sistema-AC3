package ProcessoMaq;

import Usuario.UsuarioDatas;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscosGroup;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.Conexao;
import jdbc.ConexaoSql;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class GetAllProcess {

    MaquinaDatas maquina = new MaquinaDatas();
    Looca looca = new Looca();
    Processador processador = looca.getProcessador();
    DiscosGroup discosGroup = new DiscosGroup();
    Memoria memoria = new Memoria();
    Conexao con = new Conexao();
    ConexaoSql conn = new ConexaoSql();
    JdbcTemplate template = new JdbcTemplate(con.getBanco()); 
    JdbcTemplate templateSql = new JdbcTemplate(conn.getBanco());


    public void dadosGeraisDoComputador() {
            /**
             * Atribuindo dados de tamanho de disco do computador (o dado é
             * somado se vc tiver mais de um!!)
             */
            maquina.setUs_disco_total(discosGroup.getTamanhoTotal());

            /**
             * Atribuindo dados do nome do processador (precisa melhorar isso)
             */
            maquina.setUs_cpu_nome(processador.getNome());

            /**
             * Atribuindo valores ao total de memória RAM do ser abençoado de deos
             */
            maquina.setUs_ram_total(memoria.getTotal());

        try {
            /**
             * Buscando dados do nome do computador do individuo
             */
            maquina.setUs_name_pc(InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException ex) {
            Logger.getLogger(GetAllProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void usoMemoriaAtual() {
        /**
         * Busca dados de memória RAM sendo utilizada no momento do computador
         * do individuo (não é usado para mandar dados para o banco)
         */
        memoria.getEmUso();
    }

    public void memoriaDisponivel() {
        /**
         * Busca dados de memória RAM total disponivel do computador do
         * individuo (não é usado para mandar dados para o banco)
         */
        memoria.getTotal();
    }

    public void inserirDadosMaquina(UsuarioDatas usuario) {
        /**
         * Inserindo dados de maquina a partir do método abaixo
         */
        maquinaProcess(maquina, usuario);

    }

    public void buscandoMaquina(UsuarioDatas user) {
        selectDaMaquina(maquina, user);
    }

    public void maquinaProcess(MaquinaDatas maquinaDatas, UsuarioDatas usuarioDatas) {
        Integer cont = 0;
        /**
         * Os dados do computador não serão inseridos novamente caso já haja um
         * com o mesmo cpf no banco
         */

        String insertProcessValues = " IF NOT EXISTS (SELECT * from tb_us_maquina where fk_id_funcionario = ?)\n"
                + "INSERT INTO tb_us_maquina VALUES (?, ?, ?, ?, ?)";

        template.update(insertProcessValues,
                usuarioDatas.getId_cpf(),
                maquinaDatas.getUs_name_pc(),
                maquinaDatas.getUs_ram_total(),
                maquinaDatas.getUs_disco_total(),
                maquinaDatas.getUs_cpu_nome(),
                usuarioDatas.getId_cpf());
        
        templateSql.update(insertProcessValues,
                usuarioDatas.getId_cpf(),
                maquinaDatas.getUs_name_pc(),
                maquinaDatas.getUs_ram_total(),
                maquinaDatas.getUs_disco_total(),
                maquinaDatas.getUs_cpu_nome(),
                usuarioDatas.getId_cpf());

        if (cont.equals(0)) {
            System.out.println("Tentando inserir dados do seu computador no banco de dados:" + maquina.getUs_cpu_nome());
        }
        cont++;
    }

    public MaquinaDatas selectDaMaquina(MaquinaDatas maquinaUsuario, UsuarioDatas usuario) {

        List<MaquinaDatas> pegandoFkMaq = template.query("SELECT * FROM tb_us_maquina WHERE fk_id_funcionario = ?",
                new BeanPropertyRowMapper<>(MaquinaDatas.class), usuario.getId_cpf());

        for (MaquinaDatas maquinaDatas : pegandoFkMaq) {
            /**
             * Separa de onde busca os dados, alguns estão aqui e outros estão
             * no maquinaDatas já (tem outro método parecido, favor não apagar)
             */
            maquinaDatas.setUs_ram_total(maquina.getUs_ram_total());
            maquinaDatas.setUs_disco_total(maquina.getUs_disco_total());
            maquinaDatas.setUs_cpu_nome(maquina.getUs_cpu_nome());

            maquinaDatas.setId_maquina(maquinaDatas.getId_maquina());
            maquinaDatas.setFk_id_funcionario(maquinaDatas.getFk_id_funcionario());
        }
        
        List<MaquinaDatas> pegandoFkMaqSql = template.query("SELECT * FROM tb_us_maquina WHERE fk_id_funcionario = ?",
                new BeanPropertyRowMapper<>(MaquinaDatas.class), usuario.getId_cpf());

        for (MaquinaDatas maquinaDatas : pegandoFkMaqSql) {
            /**
             * Separa de onde busca os dados, alguns estão aqui e outros estão
             * no maquinaDatas já (tem outro método parecido, favor não apagar)
             */
            maquinaDatas.setUs_ram_total(maquina.getUs_ram_total());
            maquinaDatas.setUs_disco_total(maquina.getUs_disco_total());
            maquinaDatas.setUs_cpu_nome(maquina.getUs_cpu_nome());

            maquinaDatas.setId_maquina(maquinaDatas.getId_maquina());
            maquinaDatas.setFk_id_funcionario(maquinaDatas.getFk_id_funcionario());
        }
        return maquinaUsuario;
    }
}
