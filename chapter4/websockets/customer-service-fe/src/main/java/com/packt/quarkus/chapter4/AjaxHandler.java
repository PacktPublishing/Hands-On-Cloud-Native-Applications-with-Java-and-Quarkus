package com.packt.quarkus.chapter4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;



@WebServlet("/AjaxHandler")
public class AjaxHandler extends HttpServlet {

    public AjaxHandler() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String endpoint = System.getenv("CUSTOMER_SERVICE") != null ? System.getenv("CUSTOMER_SERVICE") : "ws://localhost:8080/customers";

        PrintWriter out = response.getWriter();
        out.println(endpoint);
        out.flush();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}