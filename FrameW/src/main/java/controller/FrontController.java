package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IService;
import service.Parser;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public FrontController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	
	public void FFunction(String path,String cpath, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		PrintWriter out=response.getWriter();
		
		String name=cpath;
		Parser.setPath(path);
		
		if(Parser.P(name))
		{
			IService i=(IService)getObject(Parser.getClassName());
			i.service(request, response, Parser.getView());
		}
		else
		{
			out.println("url doesnot exist");
		}
		
	}
	
	
	public Object getObject(String className)
	{
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Object ob = null;
		try {
			ob = clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ob;
	}

}
