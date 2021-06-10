package AppKeepCode;

//import DAO.UsuariosDAO;
import ProcessoMaq.GetAllProcess;
import ProcessosIDE.ProcessIDE;
import Usuario.UsuarioDatas;
import java.io.IOException;

public class KeepCodeAPI {
    
    public static void main(String[] args) throws IOException {
        UsuarioDatas usuario = new UsuarioDatas();
        GetAllProcess getAllDatesProcess = new GetAllProcess();
        ProcessIDE processosIDE = new ProcessIDE();

        getAllDatesProcess.dadosGeraisDoComputador();
        getAllDatesProcess.inserirDadosMaquina(usuario);
        getAllDatesProcess.buscandoMaquina(usuario);

        processosIDE.getFkIdMaquina(usuario);
        processosIDE.putAllNameIde();
        processosIDE.buscarDadosDaIDE();
        processosIDE.getFkIdMaquina(usuario);
        processosIDE.inserirDadosDeIde();

        processosIDE.mostrarTodosDadosRecebidos();
    }
}
