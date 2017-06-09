package app.bot.dao;

import app.bot.item.ItemNaoalcool;
import app.bot.item.ItemNaoalcoolRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;

public class NaoalcoolDAO {
    
    private final ApplicationContext context;
    private ItemNaoalcoolRepository itemNaoalcoolRepository;

    public NaoalcoolDAO(ApplicationContext context) {
        this.context = context;
    }

    public void cadastraNaoalcool(){
        
        itemNaoalcoolRepository = context.getBean(ItemNaoalcoolRepository.class);
        
        if("[]".equals(itemNaoalcoolRepository.findAll().toString())){
            
            ItemNaoalcool na1 = new ItemNaoalcool();
            na1.setNome("Coca-cola");
            na1.setValor("3.0");        
            itemNaoalcoolRepository.save(na1);
                
            ItemNaoalcool na2 = new ItemNaoalcool();
            na2.setNome("Guaraná Antártica");
            na2.setValor("3.0");        
            itemNaoalcoolRepository.save(na2);
        
            ItemNaoalcool na3 = new ItemNaoalcool();
            na3.setNome("Suco de Laranja");
            na3.setValor("4.0");        
            itemNaoalcoolRepository.save(na3);
            
            ItemNaoalcool na4 = new ItemNaoalcool();
            na4.setNome("Limonada Suiça");
            na4.setValor("4.0");        
            itemNaoalcoolRepository.save(na4);
            
            ItemNaoalcool na5 = new ItemNaoalcool();
            na5.setNome("Água");
            na5.setValor("2.0");        
            itemNaoalcoolRepository.save(na5);
        }
        
    }
    
    public List<String> recuperaOpcoesNaoalcool() {
        
        itemNaoalcoolRepository = context.getBean(ItemNaoalcoolRepository.class);        
        Iterable<ItemNaoalcool> naoalcools = itemNaoalcoolRepository.findAll();    
        List<String> result = new ArrayList<>();
        
        for(ItemNaoalcool naoalcool : naoalcools){
            result.add(naoalcool.getNome());
        }
    
        return result;         
    }

    public List<String> recuperaValoresNaoalcool() {
        
        itemNaoalcoolRepository = context.getBean(ItemNaoalcoolRepository.class);        
        Iterable<ItemNaoalcool> naoalcools = itemNaoalcoolRepository.findAll();    
        List<String> result = new ArrayList<>();
        
        for(ItemNaoalcool naoalcool : naoalcools){
            result.add(naoalcool.getValor());
        }
        
        return result;
    }
    
}
