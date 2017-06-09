package app.bot.estados;

import app.bot.cliente.Cliente;
import app.bot.comanda.Comanda;
import app.bot.dao.NaoalcoolDAO;
import java.util.List;
import org.springframework.context.ApplicationContext;

public class EstadoNaoalcool extends Estado {
    
    private final NaoalcoolDAO naoalcoolDAO = new NaoalcoolDAO(context);
    private final Cliente cliente;
    private final Comanda comanda;
    private final String escolha;
    int item;

    public EstadoNaoalcool(ApplicationContext context, Cliente cliente, Comanda comanda, String escolha) {
        super(context);
        this.cliente = cliente;
        this.comanda = comanda;
        this.escolha = escolha;
    }

    @Override
    public void processaTexto(String texto) {
        
        List<String> cerveja = naoalcoolDAO.recuperaOpcoesNaoalcool();
        
        try{
            item = Integer.parseInt(texto) - 1;
            mensagemResposta = "Você escolheu a cerveja " + cerveja.get(item) + System.lineSeparator() +
                                "Quantas deseja?";
            proximoEstado = new EstadoQuantidade(context, cliente, comanda, escolha, item);
        }
        catch (Exception e){
            mensagemResposta = "Por favor, escolha uma opção válida!";
            proximoEstado = this;
        }
        
    }

}
