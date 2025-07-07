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

/**
 * Classe de base de personagem — contém todas as funcionalidades necessárias para:
 * 
 * <ul>
 *   <li> Acomodar poses (em formato de Folha De Sprite). </li>
 *   <li> Adicionar poses dinâmicas (animações extras). </li>
 * </ul>
 * 
 * @author Marco Antônio Pereira Júnior (fundador da Brutu's Game Development Team).
 */
public class BaseCharacter extends Component
{
    // Campos privados.
    
    /**
     * Verificador de personagem jogável.
     */
    private boolean playable = false;
    
    /**
     * Nome de exibição do personagem.
     */
    private String displayName = "Base Character";
    
    /**
     * Nome de identificação única do personagem.
     * 
     * <p>
     * Deve seguir as convenções de nomenclatura de <b>Unix</b>.
     * </p>
     */
    private final String characterID;
    
    // Construtores públicos.

    /**
     * Crie uma nova instância de personagem básico com campos definidos {@code this.characterID = "base-character"; this.setDisplayName("Base Character");}.
     */
    public BaseCharacter()
    {
        this("base-character", "Base Character");
    }

    /**
     * Crie uma nova instância composta por <b>ID</b> de personagem e <b>nome de exibição</b>.
     * 
     * @param characterID O ID de personagem solicitado para a criação.
     * @param characterID O nome de exibição do personagem a ser criado.
     * 
     * @throws IllegalArgumentException caso haja um nome de exibição ou ID de personagem inválidos.
     */
    public BaseCharacter(String characterID, String displayName)
    {
        if(characterID == null) throw(new IllegalArgumentException("O ID de personagem não deve ser inicializado como nulo."));
        if(characterID.trim().isEmpty()) throw(new IllegalArgumentException("O ID de personagem não deve ser inicializado como vazío."));
        
        this.characterID = characterID;
        this.setDisplayName(displayName);
    }
    
    // Métodos públicos (não sobrepõem nenhum método de superclasse).
    
    /**
     * Obtém o nome de exibição do personagem.
     * 
     * @return O nome de exibição referente ao personagem.
     */
    public String getDisplayName()
    {
        return(this.displayName);
    }
    
    /**
     * Altere o nome de exibição do personagem.
     * 
     * @param value O novo valor solicitado à substituir.
     */
    public void setDisplayName(String value)
    {
        if(value == null) throw(new IllegalArgumentException("O valor à substituir o nome de exibição não pode em hipótese alguma ser nulo."));
        if(value.trim().isEmpty()) throw(new IllegalArgumentException("O valor à substituir o nome de exibição não pode ser vazío."));
        
        this.displayName = value;
    }
    
    /**
     * Obtém o identificador de personagem.
     * 
     * @return O identificador referente ao personagem.
     */
    public String getCharacterID()
    {
        return(this.characterID);
    }
    
    /**
     * Imprima um registro no <b>console</b> via {@code Console.log(exit);} junto do nome de personagem.
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
        
        sb.append("Stage Character Log (").append(this.characterID).append(") > \"").append(String.valueOf(message)).append("\";");
        
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
        this.log("Personagem carregado!");
    }
    
    /**
     * Compare a igualdade entre este e outro objeto.
     * 
     * @param obj O objeto a ser comparado no processo.
     * 
     * @return Um valor binário entre {@code true} e {@code false} caso haja igualdade.
     */
    public boolean equals(Object obj)
    {
        if(obj == this) return(true);
        if(!(obj instanceof BaseCharacter)) return(false);
        
        BaseCharacter other = (BaseCharacter)(obj);
        
        return(
            other.playable == this.playable &&
            (this.displayName == null ?
            other.displayName == null :
            other.displayName.equals(this.displayName)) &&
            (this.characterID == null ?
            other.characterID == null :
            other.characterID.equals(this.characterID))
        );
    }
    
    /**
     * Obtém o código de identificação do personagem.
     * 
     * @return O código cujo servirá para identificar o personagem nas tabelas do estágio.
     */
    public int hashCode()
    {
        int code = 17;
        
        code = 31 * code + (this.playable ? 1 : 0);
        code = 31 * code + (this.displayName != null ? this.displayName.hashCode() : 0);
        code = 31 * code + (this.characterID != null ? this.characterID.hashCode() : 0);
        
        return(code);
    }
}
