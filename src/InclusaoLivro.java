

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InclusaoLivro
 */
@WebServlet("/InclusaoLivro")
public class InclusaoLivro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InclusaoLivro() {
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
			//String idLivro  = request.getParameter("idLivro");
			
			//int id = Integer.parseInt(idLivro);
			//int id        = Integer.parseInt(request.getParameter("idLivro"));			
				
			String titulo = request.getParameter("tituloLivro");
			String autor  = request.getParameter("autorLivro");
			
			float preco   = Float.parseFloat(request.getParameter("precoLivro"));
			
			MeuPreparedStatement comando = (MeuPreparedStatement)request.getSession().getAttribute("conexao");
						
			if (comando == null)
			{
				response.getWriter().println("passou4");
				comando = new MeuPreparedStatement("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://regulus:1433;databasename=BDu14169",
						"BDu14169", "bancodedados");
				request.getSession().setAttribute("conexao", comando);
			}
			
			ManutencaoLivros livros = new ManutencaoLivros(comando);			
			Livro livro = new Livro(0, titulo, autor, preco);			
			livros.incluir(livro);
			response.getWriter().println("Livro cadastrado com sucesso!");
			
		}
		catch (Exception e)
		{
			response.getWriter().println("Erro" + e.getMessage());
			
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
