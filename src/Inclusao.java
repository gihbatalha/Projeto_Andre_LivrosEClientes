
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Inclusao
 */
@WebServlet("/Inclusao")
public class Inclusao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inclusao() {
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
			String nome = request.getParameter("nomeCliente");
			String cpf = request.getParameter("cpfCliente");
			String rg = request.getParameter("rgCliente");
			
			//String titulo = request.getParameter("titulo");
			//String genero = request.getParameter("cbxGenero");
			//int estoque = Integer.parseInt(request.getParameter("estoque"));
			//String preco = request.getParameter("preco");
			
			//Se tivesse um float preco = Float.parseFloat(request.getParameter("preco"))
			
			MeuPreparedStatement comando = (MeuPreparedStatement)request.getSession().getAttribute("conexao");
			
			if (comando == null)
			{
				comando = new MeuPreparedStatement("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://regulus:1433;databasename=BDu14169",
						"BDu14169", "bancodedados");
				request.getSession().setAttribute("conexao", comando);
			}
			
			ManutencaoClientes clis = new ManutencaoClientes(comando);			
			Cliente cli = new Cliente(nome, rg, cpf);			
			clis.incluir(cli);
			response.getWriter().println("Usuário cadastrado com sucesso.");
			
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
