

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;


/**
 * Servlet implementation class AbrirParaExclusao
 */
@WebServlet("/AbrirParaExclusao")
public class AbrirParaExclusao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AbrirParaExclusao() {
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
			MeuResultSet ResultSet = clis.listar();
			
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset='ISO-8859-1'>");
			out.println("<link rel='shortcut icon' href='icon_livro.png'>");
			out.println("<link rel='stylesheet' href='estilo.css' type='text/css'>");
			out.println("<meta charset='utf-8'>");
			out.println("<script type='text/javascript' src='javascript.js'></script>");
			out.println("<title> Exclusão </title>");
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
					
			out.println("<label class='tituloForm'> Clientes Cadastrados: </label>");
			
			out.println("<br>");
			out.println("<table>");
			
			out.println("<tr>");
			out.println("<td>"+ "Id"   +"</td>");
			out.println("<td>"+ "Nome " +"</td>");
			out.println("<td>"+ "RG"   +"</td>");
			out.println("<td>"+ "CPF"  +"</td>");			
			out.println("</tr>");
			
			while(ResultSet.next()){
				
			String idTabela, nomeTabela, rgTabela, cpfTabela;
				
			idTabela   = ResultSet.getString("idCliente");
			nomeTabela = ResultSet.getString("Nome");
			rgTabela   = ResultSet.getString("rg");
			cpfTabela  = ResultSet.getString("cpf");
			
			out.println("<tr>");
			out.println("<td>"+ idTabela   +"</td>");
			out.println("<td>"+ nomeTabela +"</td>");
			out.println("<td>"+ rgTabela   +"</td>");
			out.println("<td>"+ cpfTabela  +"</td>");			
			out.println("</tr>");
			}
			
			out.println("</table>");
			out.println("</body>");
			out.println("</html>");
			//response.getWriter().println("Usuário cadastrado com sucesso.");
			

			out.println("<br>");
			out.println("<form action='Exclusao'>");
			out.println("<p> Id Para Exclusão: <input type='text' size='20' name='idCliente' > <br><br>");
			out.println("<input type='submit' value='Exclui o Cliente'>");
			out.println("</form>");			
			
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
