package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	String charset = null;
	HashMap<String, Controller> list = null;
	
	private final String prefix = "/view/";
	private final String postfix = ".jsp";
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		charset = config.getInitParameter("charset");
		list = new HashMap<String, Controller>();
		
		list.put("/", new MainController());
		list.put("/memberInsert", new MemberInsertController());
		list.put("/memberList", new MemberListController());
		list.put("/memberMoneyList", new MemberMoneyListController());
		list.put("/memberUpdate", new MemberUpdateController());
		
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding(charset);
		
		String url = req.getRequestURI();
		String contextPath = req.getContextPath();
		String path = url.substring(contextPath.length());
		
		Controller subController = list.get(path);
		
		if(subController == null) {
			resp.sendRedirect("/");
			return;
		}
	
		String result = null;
		try {
			result = subController.execute(req, resp);
		} catch (SerialException | IOException e) {
			e.printStackTrace();
		}
		
		if(result.indexOf("redirect::") != 0) {
			RequestDispatcher dispatcher = req.getRequestDispatcher(prefix + result + postfix);
			dispatcher.forward(req, resp);
		}else {
			String reUrl = result.substring("redirect::".length());
			resp.sendRedirect(reUrl);
		}
	}
	
	
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
