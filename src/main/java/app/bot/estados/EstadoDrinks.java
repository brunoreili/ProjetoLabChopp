package app.bot.estados;

import app.bot.cliente.Cliente;
import app.bot.comanda.Comanda;
import app.bot.dao.DrinkDAO;
import java.util.List;
import org.springframework.context.ApplicationContext;

public class EstadoDrinks extends Estado {
    
    private final DrinkDAO drinkDAO = new DrinkDAO(context);
    private final Cliente cliente;
    private final Comanda comanda;
    private final String escolha;
    int item;

    public EstadoDrinks(ApplicationContext context, Cliente cliente, Comanda comanda, String escolha) {
        super(context);
        this.cliente = cliente;
        this.comanda = comanda;
        this.escolha = escolha;
    }

    @Override
    public void processaTexto(String texto) {
        
        List<String> cerveja = drinkDAO.recuperaOpcoesDrinks();
        
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
