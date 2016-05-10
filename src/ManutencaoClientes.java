
import java.sql.SQLException;

public class ManutencaoClientes {
	MeuPreparedStatement bd;
	Cliente c;
	
	public ManutencaoClientes(MeuPreparedStatement bd) throws Exception{
		
		if (bd == null)
            throw new Exception ("Acesso ao BD não fornecido.");
		else
        this.bd = bd;						
	}
	
	
	public void incluir(Cliente cliente) throws Exception
    {
        if (cliente == null)
            throw new Exception ("Cliente não fornecido.");

        try
        {
            String sql;

            sql = "insert into tdiCliente values(?,?,?)";

            bd.prepareStatement(sql);

            bd.setString (1, cliente.getNome());
            bd.setString (2, cliente.getRg());
            bd.setString (3, cliente.getCpf());

            bd.executeUpdate (); // faz o insert
            bd.commit();           
          
            
        }
        
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao inserir cliente.");
        }
        
    }
	
	public void excluir(int idExclusao) throws Exception
    {
        try
        {
            String sql;

            sql = "delete from tdiCliente where idCliente = ?";

            bd.prepareStatement (sql);

            bd.setInt (1, idExclusao);

            bd.executeUpdate (); 
            bd.commit();          
          
            
        }
        
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao excluir cliente.");
        }
        
    }
	
	public MeuResultSet listar() throws Exception
	{
		try
        {
            String sql;

            sql = "select * from tdiCliente";

            bd.prepareStatement (sql);

            return bd.executeQuery();
        }
		
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao listar no MeuResultSet os dados do cliente.");
        }
		
	}
	
	public MeuResultSet pesquisar(int idCliente) throws Exception
	{
		try
        {
            String sql;

            sql = "select * from tdiCliente where idCliente=" + idCliente;

            bd.prepareStatement (sql);

            return bd.executeQuery();
        }
		
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao Pesquisar no MeuResultSet os dados do cliente.");
        }
		
	}
	
	public void alterar(Cliente cliente) throws Exception
	{
		try
        {
            String sql;

            sql = "update tdiCliente set nome=?, cpf=?, rg=? where idCliente=?";

            bd.prepareStatement(sql);

            bd.setString (1, cliente.getNome());
            bd.setString (2, cliente.getRg());
            bd.setString (3, cliente.getCpf());
            bd.setInt    (4, cliente.getIdCliente());

            bd.executeUpdate (); // faz o update
            bd.commit();    
        }
		
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao Pesquisar no MeuResultSet os dados do cliente.");
        }
		
	}
	
	public void comprar(Cliente clienteComprador, int idLivroComprado) throws Exception
	{
		if(clienteComprador == null){
			throw new Exception("Clinte não fornecido");
			
		}
		try
        {
			String sql;

            sql = "insert into tdiCompra values(?,?)";

            bd.prepareStatement(sql);

            bd.setInt (1, clienteComprador.getIdCliente());
            bd.setInt (2, idLivroComprado);

            bd.executeUpdate (); // faz o insert
            bd.commit();   
        }
		
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao comprar");
        }
		
	}
	
	
}
