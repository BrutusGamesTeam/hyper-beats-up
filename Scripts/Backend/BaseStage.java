/**
 * Pacote de localização padrão dos módulos da ITsMagic Engine.
 * 
 * <p>
 * - Infelizmente não há possibilidade nenhuma de utilizar recursos da <b>JDK</b> moderna.
 * - O compilador integrado deste ambiente requer uso da <b>JDK</b> na versão 7.0, devido problemas de integração.
 * - Lucas Leandro confirmou que os compiladores modernos para utilizar requerem o sistema Gradle juntamente.
 * - Porém, o sistema Gradle é <b>impossível</b> de fazer funcionar em um motor destinado para utilizar diretamente em celulares.
 * </p>
 *
 * <p>
 * Créditos ao criador do motor: Lucas Leandro.
 * </p>
 */
package JAVARuntime;

// Importações de dependências.

import java.util.Set;
import java.util.HashSet;

/**
 * Classe de base de estágio — contém todas as funcionalidades necessárias para:
 * 
 * <ul>
 *   <li> Acomodar personagens (em formato de Folha De Sprite). </li>
 *   <li> Adicionar mecânicas (utilizando ficheiros de código). </li>
 *   <li> Enfeitar com visuais (em formato de Folha De Sprite). </li>
 * </ul>
 * 
 * @author Marco Antônio Pereira Júnior (fundador da Brutu's Game Development Team).
 */
public class BaseStage extends Component
{
    // Constantes públicas.
    
    /**
     * Número máximo de personagens no mesmo estágio.
     * 
     * <p>
     * Por razões de desempenho, este número é limitado para quatro (4) personagens simultâneos.
     * </p>
     */
    public static final int MAX_CHARACTERS = 4;
    
    // Campos privados.
    
    /**
     * Tabéla de identificação de personagens simultâneos.
     */
    private Set<BaseCharacter> characters = new HashSet<BaseCharacter>(MAX_CHARACTERS);
    
    /**
     * Nome de identificador do estágio.
     */
    private String stageID = "base-stage";
    
    // Métodos públicos (não sobrepõem nenhum método de superclasse).
    
    /**
     * Adicione um personagem à tabela.
     * 
     * @param character O personagem a ser adicionado.
     */
    public void addCharacter(BaseCharacter character)
    {
        if(this.characters.size() > MAX_CHARACTERS) throw(new RuntimeException("O personagem a ser adicionado ultrapassa do limite de personagens simultâneos."));
        if(character == null) throw(new IllegalArgumentException("O personagem a ser adicionado não pode em hipótese alguma ser nulo."));
        if(this.characters.contains(character)) return;
        
        this.characters.add(character);
        
        this.log("Personagem adicionado! (" + character.getCharacterID() + ")");
        
        SpatialObject charObject = new SpatialObject(character.getDisplayName());
        
        charObject.addComponent(character);
    }
    
    /**
     * Imprima um registro no <b>console</b> via {@code Console.log(exit);} junto do nome de estágio.
     * 
     * <p>
     * Este método serve apenas para facilitar o uso do console.
     * </p>
     * 
     * @param message A mensagem de saída do registro.
     */
    public void log(Object message)
    {
        if(message == null) throw(new IllegalArgumentException("A mensagem de registro não pode ser inválida."));
        
        Log exit = new Log();
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("Game Stage Log (").append(this.stageID).append(") > \"").append(String.valueOf(message)).append("\";");
        
        exit.setMessage(sb.toString());
        Console.log(exit);
    }
    
    // Métodos públicos (sobrepõem os da superclasse).
    
    /**
     * Execute as tarefas uma única vez após o primeiro quadro.
     * 
     * Aviso: este método é automáticamente convocado, não chame-o manualmente.
     */
    @Override
    public void start()
    {
        this.log("Estágio carregado!");
        
        this.addCharacter(new BaseCharacter("mark", "Mark"));
    }
}
