
public class Cliente {
	String nome, rg, cpf;
	int idCliente;
	
	
	public Cliente(int idCliente, String nome, String rg, String cpf){
		this.idCliente = idCliente; 
		this.nome = nome;
		this.rg   = rg;
		this.cpf  = cpf;		
	}
	
	public Cliente(String nome, String rg, String cpf){
		this.nome = nome;
		this.rg   = rg;
		this.cpf  = cpf;		
	}
	
	public int getIdCliente() {
		return idCliente;
	}
	
	protected void setIdCiente(int Id){
		this.idCliente = Id;		
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getRg() {
		return rg;
	}

	public String getCpf() {
		return cpf;
	}
}
