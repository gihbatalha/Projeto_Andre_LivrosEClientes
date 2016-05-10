

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class abrirClientes
 */
@WebServlet("/abrirClientes")
public class abrirClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public abrirClientes() {
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
						
			MeuPreparedStatement comando = (MeuPreparedStatement)request.getSession().getAttribute("conexao");
			
			if (comando == null)
			{
				comando = new MeuPreparedStatement("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://regulus:1433;databasename=BDu14169",
						"BDu14169", "bancodedados");
				request.getSession().setAttribute("conexao", comando);
			}
			
			ManutencaoClientes clis = new ManutencaoClientes(comando);			
			MeuResultSet ResultSetCli = clis.listar();
						
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset='ISO-8859-1'>");
			out.println("<link rel='shortcut icon' href='icon_livro.png'>");
			out.println("<link rel='stylesheet' href='estilo.css' type='text/css'>");
			out.println("<meta charset='utf-8'>");
			out.println("<script type='text/javascript' src='javascript.js'></script>");
			out.println("<title> Clientes </title>");
			out.println("</head>");	
			
			out.println("<body>");
			
			out.println("<div class='menu'>");
			out.println("<label class='titulo'> Cadastro </label>");
			out.println("<label class='subtitulo'> Clientes e Livros </label>");
			out.println("<a href='abrirParaCompra' class='item1'> Compra </a>");
			out.println("<a href='abrirClientes' class='item2' > Clientes </a>");
			out.println("<a href='abrirLivros' class='item3' > Livros </a>");
			out.println("</div>");
			
			
			out.println("<div class='formulario'>");
					
			out.println("<label class='tituloForm'> Clientes Cadastrados: </label>");
			
			out.println("<br>");
			
			//Exibe os cliente
			out.println("<table border='1'>");
			
			out.println("<tr>");
			out.println("<td>"+ "Id"    +"</td>");
			out.println("<td>"+ "Nome " +"</td>");
			out.println("<td>"+ "RG"    +"</td>");
			out.println("<td>"+ "CPF"   +"</td>");			
			out.println("</tr>");
			
			while(ResultSetCli.next()){
				
			String idTabela, nomeTabela, rgTabela, cpfTabela;
				
			idTabela   = ResultSetCli.getString("idCliente");
			nomeTabela = ResultSetCli.getString("Nome");
			rgTabela   = ResultSetCli.getString("rg");
			cpfTabela  = ResultSetCli.getString("cpf");
			
			out.println("<tr>");
			out.println("<td>"+ idTabela   +"</td>");
			out.println("<td>"+ nomeTabela +"</td>");
			out.println("<td>"+ rgTabela   +"</td>");
			out.println("<td>"+ cpfTabela  +"</td>");			
			out.println("</tr>");
			}
			
			out.println("</table>");
			
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
			
			out.println("<label class='tituloForm'> Cadastro Clientes </label>");
			out.println("<form action='Inclusao'>");
			out.println("<p> Id    <input type='text' size='10' name='idCliente'   id='idCliente'> <br><br>");
			out.println("<p> Nome  <input type='text' size='80' name='nomeCliente' > <br><br>");
			out.println("<p> RG    <input type='text' size='80' name='rgCliente' > <br><br>");
			out.println("<p> CPF   <input type='text' size='80' name='cpfCliente' > <br><br>");
			
			out.println("<button type='submit' 	onClick='document.forms[0].action =\" Inclusao\"; return true; '> Inclusão </button> ");
			out.println("<button type='submit'  onClick='document.forms[0].action =\"Exclusao\"; return true; '> Exclusão </button>");
			out.println("<br><br>");
			out.println("<button type='submit'  onClick='document.forms[0].action =\"Alteracao\"; return true;'> Alteração </button>");
			out.println("<button type='submit'  onClick='document.forms[0].action =\"Pesquisar\"; return true; '> Pesquisar </button>");
			out.println("<br><br>");
			out.println("<button type='reset'> Limpar </button>");
			out.println("</form>");
			
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
			//response.getWriter().println("Usuário cadastrado com sucesso.");
			
		
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
