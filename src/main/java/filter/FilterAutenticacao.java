package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/principal/*" }) /* Intercepta todas as requisi��es que vierem do projeto ou mapeamento */

public class FilterAutenticacao implements Filter {

	private static Connection connection;

	public FilterAutenticacao() {

	}

	public void destroy() {

		/*
		 * Encerra os processos quando o servidor � parado Mataria os processos de
		 * conex�o com o banco
		 */

		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * Intecepta as requisi��es e as respostas no sistema tudo que fizer no sistema
	 * vai passar por aqui valida��o de autentia��o Dar commit e rolbak de
	 * transa��es do banco Validar e fazer redirecionamento de paginas
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {

			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();

			String usuarioLogado = (String) session.getAttribute("usuario");

			String urlParaAutenticar = req.getServletPath();// Url que est� sendo acessada

			/* Validar se est� logado, sen�o redireciona para a tela de Login */

			if (usuarioLogado == null
					&& !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {/* n�o est� logado */

				RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
				request.setAttribute("msg", "Por favor realize o login!");
				redireciona.forward(request, response);
				return;/* Para a execu��o e redireciona para o login */

			} else {
				chain.doFilter(request, response);
			}

			connection.commit();/* deu tudo certo ent�o comita as altera��es no BD */

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}
	}

	// Inicia os processos ou recursos quando o servidor sobe o projeto
	public void init(FilterConfig fConfig) throws ServletException {

		connection = SingleConnectionBanco.getConnection();

	}

}
