package com.mypayroll.app;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class Login extends HttpServlet {




    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        //Login Methode Created//
        try {


            String customerName = req.getParameter("E_NAME");
            String password = req.getParameter("E_CODE");



            Model m = new Model();
            m.setE_NAME(customerName);
            System.out.println(customerName);
            System.out.println(customerName);
            m.setE_CODE( password);
            System.out.println( password);

            boolean status = m.cheak();


            if (status)
            {



                HttpSession session = req.getSession(true);
                session.setAttribute("ECODE_Session", password);


                resp.sendRedirect("CTC.html");


            } else
                {

                resp.sendRedirect("resolve.html");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        //CTC .java calculation//


    }



}












































































































































