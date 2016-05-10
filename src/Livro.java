
public class Livro {
	int idLivro;
	String titulo, autor;
	float preco;
	
	public Livro(int id, String titulo, String autor, float preco){
		this.idLivro = id;
		this.titulo  = titulo;
		this.autor   = autor;
		this.preco   = preco;
		
	}
	
	public int getIdLivro() {
		return idLivro;
	}	
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		if(preco>=0.00)
		this.preco = preco;
	}
}
