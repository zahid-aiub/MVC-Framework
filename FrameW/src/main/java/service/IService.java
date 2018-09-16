package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IService {

	
	public void service(HttpServletRequest request, HttpServletResponse response,String successUrl);
	
	
	
}
