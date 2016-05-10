
import java.sql.SQLException;

public class ManutencaoLivros {
	MeuPreparedStatement bd;
	Livro l;
	
	public ManutencaoLivros (MeuPreparedStatement bd) throws Exception
    {
        if (bd == null)
            throw new Exception ("Acesso ao BD não fornecido.");
        else
        this.bd = bd;
    }
	
	
	//incluir retorna a chave gerada
	//implementar isso
	public void incluir(Livro livro) throws Exception
    {
        if (livro == null)
            throw new Exception ("Livro não fornecido.");

        try
        {
            String sql;

            sql = "insert into tdiLivro values(?,?,?)";

            bd.prepareStatement (sql);

            bd.setString (1, livro.getTitulo());
            bd.setString (2, livro.getAutor());
            bd.setFloat  (3, livro.getPreco());

            bd.executeUpdate ();
            bd.commit();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao inserir livro.");
        }
    }
	
	
	public void excluir(int idExclusao) throws Exception
    {
        try
        {
            String sql;

            sql = "delete from tdiLivro where idLivro = ?";

            bd.prepareStatement (sql);

            bd.setInt (1, idExclusao);

            bd.executeUpdate (); 
            bd.commit();          
          
            
        }
        
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao excluir livro.");
        }
        
    }
	
	public MeuResultSet listar() throws Exception
	{
		try
        {
            String sql;

            sql = "select * from tdiLivro";

            bd.prepareStatement (sql);

            return bd.executeQuery();
        }
		
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao listar no MeuResultSet os dados do livro.");
        }
		
	}
	
	public MeuResultSet pesquisar(int idLivro) throws Exception
	{
		try
        {
            String sql;

            sql = "select * from tdiLivro where idLivro=" + idLivro;

            bd.prepareStatement (sql);

            return bd.executeQuery();
        }
		
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao Pesquisar no MeuResultSet os dados do livro.");
        }
		
	}
	
	public void alterar(Livro livro) throws Exception
	{
		try
        {
            String sql;

            sql = "update tdiLivro set titulo=?, autor=?, preco=? where idLivro=?";

            bd.prepareStatement(sql);
            
            bd.setString (1, livro.getTitulo());
            bd.setString (2, livro.getAutor());
            bd.setFloat  (3, livro.getPreco());
            bd.setInt    (4, livro.getIdLivro());

            bd.executeUpdate (); // faz o update
            bd.commit();    
        }
		
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao Pesquisar no MeuResultSet os dados do livro.");
        }
		
	}

}
