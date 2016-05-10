

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PesquisarLivro
 */
@WebServlet("/PesquisarLivro")
public class PesquisarLivro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PesquisarLivro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType( "text/html" );
		PrintWriter out = response.getWriter();
		
		try
		{
			String id = request.getParameter("idLivro");
			String tituloRecebido, autorRecebido, precoRecebido, idRecebido;
			
			MeuPreparedStatement comando = (MeuPreparedStatement)request.getSession().getAttribute("conexao");
			
			if (comando == null)
			{
				comando = new MeuPreparedStatement("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://regulus:1433;databasename=BDu14169",
						"BDu14169", "bancodedados");
				request.getSession().setAttribute("conexao", comando);
			}
			
			ManutencaoLivros livros = new ManutencaoLivros(comando);
			MeuResultSet rs        = livros.listar();
			MeuResultSet ResultSet = livros.pesquisar(Integer.parseInt(id));
			
			ResultSet.first();
			
			if(ResultSet == null)
				response.getWriter().println("Livro não existe");
			
			else{	
				
				idRecebido     = "" + ResultSet.getInt("idLivro");
				tituloRecebido = ResultSet.getString("titulo");
				autorRecebido  = ResultSet.getString("autor");
				precoRecebido  = "" + ResultSet.getString("preco");
											
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<meta charset='ISO-8859-1'>");
				out.println("<link rel='shortcut icon' href='icon_livro.png'>");
				out.println("<link rel='stylesheet' href='estilo.css' type='text/css'>");
				out.println("<meta charset='utf-8'>");
				out.println("<script type='text/javascript' src='javascript.js'></script>");
				out.println("<title> Pesquisar </title>");
				out.println("</head>");	
				
				out.println("<body>");
				out.println("<div class='menu'>");
				out.println("<label class='titulo'> Cadastro </label>");
				out.println("<label class='subtitulo'> Clientes e Livros </label>");
				out.println("<a href='index.html' class='item1'> Início </a>");
				out.println("<a href='clientes.html' class='item2' > Clientes </a>");
				out.println("<a href='livros.html' class='item3' > Livros </a>");
				out.println("</div>");
				
				
				out.println("<div class='formulario'>");
						
				out.println("<label class='tituloForm'> Livros Cadastrados: </label>");
				
				out.println("<br>");
				
				//Exibe os cliente
				out.println("<table border='1'>");
				
				out.println("<tr>");
				out.println("<td>"+ "Id"   +"</td>");
				out.println("<td>"+ "Titulo " +"</td>");
				out.println("<td>"+ "Autor"   +"</td>");
				out.println("<td>"+ "Preço"  +"</td>");			
				out.println("</tr>");
				
				while(rs.next()){
					
				String idTabela, tituloTabela, autorTabela, precoTabela;
					
				idTabela     = rs.getString("idLivro");
				tituloTabela = rs.getString("titulo");
				autorTabela  = rs.getString("autor");
				precoTabela  = rs.getString("preco");		
				
				
				out.println("<tr>");
				out.println("<td>"+ idTabela   +"</td>");
				out.println("<td>"+ tituloTabela +"</td>");
				out.println("<td>"+ autorTabela   +"</td>");
				out.println("<td>"+ precoTabela  +"</td>");			
				out.println("</tr>");
				}
				
				out.println("</table>");
				
				out.println("<br>");
				out.println("<br>");
				out.println("<br>");
				
				out.println("<label class='tituloForm'> Cadastro Livros </label>");
				out.println("<form action='InclusaoLivro'>");
				out.println("<p> Id     <input type='text' size='10' name='idLivro'     id='idLivro'     value='" + idRecebido+"'> <br><br>");
				out.println("<p> Título <input type='text' size='80' name='tituloLivro' id='tituloLivro' value='" + tituloRecebido+"'> <br><br>");
				out.println("<p> Autor  <input type='text' size='80' name='autorLivro'  id='autorLivro'  value='" + autorRecebido+"'> <br><br>");
				out.println("<p> Preço  <input type='text' size='30' name='precoLivro'  id='precoLivro'  value='" + precoRecebido+"'> <br><br>");
				
				out.println("<button type='submit' 	onClick='document.forms[0].action =\" InclusaoLivro\"; return true; '> Inclusão </button> ");
				out.println("<button type='submit'  onClick='document.forms[0].action =\"ExclusaoLivro\"; return true; '> Exclusão </button>");
				out.println("<br><br>");
				out.println("<button type='submit'  onClick='document.forms[0].action =\"AlteracaoLivro\"; return true;'> Alteração </button>");
				out.println("<button type='submit'  onClick='document.forms[0].action =\"PesquisarLivro\"; return true; '> Pesquisar </button>");
				out.println("<br><br>");
				out.println("<button type='reset'> Limpar </button>");
				
				out.println("</div>");
				out.println("</body>");
				out.println("</html>");
			}
			
													
			
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
