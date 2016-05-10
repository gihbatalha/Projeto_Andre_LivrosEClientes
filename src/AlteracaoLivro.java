

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AlteracaoLivro
 */
@WebServlet("/AlteracaoLivro")
public class AlteracaoLivro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlteracaoLivro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try
		{
			String idLivro  = request.getParameter("idLivro");			
			int id = Integer.parseInt(idLivro);			
				
			String titulo = request.getParameter("tituloLivro");
			String autor  = request.getParameter("autorLivro");
			
			float preco   = Float.parseFloat(request.getParameter("precoLivro"));
			
			
			MeuPreparedStatement comando = (MeuPreparedStatement)request.getSession().getAttribute("conexao");
			
			if (comando == null)
			{
				comando = new MeuPreparedStatement("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://regulus:1433;databasename=BDu14169",
						"BDu14169", "bancodedados");
				request.getSession().setAttribute("conexao", comando);
			}
			
			
			ManutencaoLivros livros = new ManutencaoLivros(comando);			
			Livro livro = new Livro(id, titulo, autor , preco);			
			livros.alterar(livro);
			response.getWriter().println("Usuário alterado com sucesso.");
			
		}
		catch (Exception e)
		{
			response.getWriter().println(e.getMessage());
			//Fazer as exceções
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
