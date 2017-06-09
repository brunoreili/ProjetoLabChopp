package app.bot.estados;

import app.bot.dao.NaoalcoolDAO;
import app.bot.dao.DrinkDAO;
import app.bot.cliente.Cliente;
import app.bot.comanda.Comanda;
import app.bot.dao.CervejaDAO;
import java.util.List;
import org.springframework.context.ApplicationContext;

public class EstadoBebidas extends Estado{

    private final CervejaDAO cervejaDAO = new CervejaDAO(context);
    private final DrinkDAO drinkDAO = new DrinkDAO(context);
    private final NaoalcoolDAO naoalcooDAO = new NaoalcoolDAO(context);
    private final Cliente cliente;
    private final Comanda comanda;
    String escolha;
    List<String> opcoes;
    List<String> precos;
    
    public EstadoBebidas(ApplicationContext context, Cliente cliente, Comanda comanda) {
        super(context);
        this.cliente = cliente;
        this.comanda = comanda;
    }
    
    @Override
    public void processaTexto(String texto) {
       
        switch (texto.trim()) {
            case "1":
                escolha = "Cerveja";
                
                opcoes = cervejaDAO.recuperaOpcoesCervejas();
                precos = cervejaDAO.recuperaValoresCervejas();                
                
                mensagemResposta = "Legal, temos as cervejas:";                
                for(int i=0; i < opcoes.size() ; i++){
                    mensagemResposta += System.lineSeparator() + (i+1) + " - " + opcoes.get(i) + "..................R$ " + precos.get(i);
                }                
                proximoEstado = new EstadoCervejas(context, cliente, comanda, escolha); 
                
                break;
            case "2":
                escolha = "Drinks";
                
                opcoes = drinkDAO.recuperaOpcoesDrinks();
                precos = drinkDAO.recuperaValoresDrinks();
                        
                mensagemResposta = "Legal, temos os drinks:";                
                for(int i=0; i < opcoes.size() ; i++){
                    mensagemResposta += System.lineSeparator() + (i+1) + " - " + opcoes.get(i) + "..................R$ " + precos.get(i);
                }                
                proximoEstado = new EstadoDrinks(context, cliente, comanda, escolha); 
                
                break;
            case "3":
                escolha = "Não Alcoólicos";
                
                opcoes = naoalcooDAO.recuperaOpcoesNaoalcool();
                precos = naoalcooDAO.recuperaValoresNaoalcool();
                        
                mensagemResposta = "Legal, temos:";                
                for(int i=0; i < opcoes.size() ; i++){
                    mensagemResposta += System.lineSeparator() + (i+1) + " - " + opcoes.get(i) + "..................R$ " + precos.get(i);
                }                
                proximoEstado = new EstadoNaoalcool(context, cliente, comanda, escolha); 
                
                break;
            default:
                mensagemResposta = "Por favor, escolha uma opção válida!";
                proximoEstado = this;
                break;    
        }
    }

}