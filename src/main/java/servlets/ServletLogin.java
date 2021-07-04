package servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

/**
 * Servlet implementation class ServletLogin Também chamadas de controllers ou
 * ServletLoginController
 */
@WebServlet(urlPatterns = { "/principal/ServletLogin", "/ServletLogin"}) /* Mapeamento ou rota da página */
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletLogin() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) Recebe os dados pela URL em parâmetros
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) Recebe os dados enviados por um formulário
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");

		if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {

			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);

			if (modelLogin.getLogin().equalsIgnoreCase("admin") && modelLogin.getSenha().equalsIgnoreCase("admin")) {

				request.getSession().setAttribute("usuario", modelLogin.getLogin());

				if (url == null || url.equals("null")) {

					url = "/principal/principal.jsp";

				}
					RequestDispatcher redirecionar = request.getRequestDispatcher(url);
					redirecionar.forward(request, response);

			} else {

				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
				request.setAttribute("msg", "Informe o login e a senha corretamente");
				redirecionar.forward(request, response);

			}

		} else {

			RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
			request.setAttribute("msg", "Informe o login e a senha corretamente");
			redirecionar.forward(request, response);
		}

	}

}
