package com.logintest;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginServlet() {
        super();
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		LoginBean loginBean = new LoginBean();
		
		loginBean.setUsername(username);
		loginBean.setPassword(password);
		
		LoginDao loginDao = new LoginDao();
		
		try {
			
			String userValidate = loginDao.AuthenticateUser(loginBean);
			
			if(userValidate.equals("Admin_role")) {
				
				System.out.println("Admin's page");
				
				HttpSession session = request.getSession();
				session.setAttribute("Admin",username);
				request.setAttribute("Username", username);
				
				request.getRequestDispatcher("/Admin.jsp").forward(request, response);
				
			}else if(userValidate.equals("Student_role")) {
				
				System.out.println("Student's page");
				
				HttpSession session = request.getSession();
				session.setAttribute("Admin",username);
				request.setAttribute("Username", username);
				
				request.getRequestDispatcher("/Student.jsp").forward(request, response);
				
			}else{
				
				System.out.println("Incorrect username or password");
				String msg = "Incorrect Username or password";
				request.setAttribute("error_message", msg);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/LoginSession.jsp");
				
				dispatcher.forward(request, response);
				//response.sendRedirect("LoginSession.jsp");
				
			}
			
		}catch (IOException e) {
			e.printStackTrace();
		}catch(Exception e1) {
			e1.printStackTrace(); 
		}
		
	}

}
