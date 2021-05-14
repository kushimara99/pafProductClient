package com.productclient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

/**
 * Servlet implementation class ProductClientAPI
 */
@WebServlet("/ProductClientAPI")
public class ProductClientAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Product product = new Product();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductClientAPI() {
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
		JsonObject output = product.insertProduct(
				 Integer.parseInt(request.getParameter("researcher_id")),
                Integer.parseInt(request.getParameter("product_id")),
                request.getParameter("product_name"),
                request.getParameter("product_code"),
                Integer.parseInt(request.getParameter("quantity")),
                Double.parseDouble(request.getParameter("product_price")),
                request.getParameter("timestamp"));
               
               
        response.getWriter().write(output.toString());
	
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> paras = getParasMap(request);
		JsonObject output = product.updateProduct(
				Integer.parseInt(paras.get("hiddenProductIDSave").toString()),
				 Integer.parseInt(paras.get("product_id")),
				 Integer.parseInt(paras.get("researcher_id")),
				 paras.get("product_name"),
				 paras.get("product_code"),
				    Integer.parseInt(paras.get("quantity")),
				    Double.parseDouble(paras.get("product_price")),
				    paras.get("timestamp"));
				   
				  
				
		response.getWriter().write(output.toString());
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String, String> paras = getParasMap(request);
		JsonObject output = product.deleteProduct(Integer.parseInt(paras.get("ID").toString()));
		response.getWriter().write(output.toString());
	}
	
	
	// Convert request parameters to a Map
		private static Map<String,String> getParasMap(HttpServletRequest request)
		{
			Map<String, String> map = new HashMap<String, String>();
			try
			{
				Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
				String queryString = scanner.hasNext() ?
						scanner.useDelimiter("\\A").next() : "";
				scanner.close();
				String[] params = queryString.split("&");
				for (String param : params)
				{
					String[] p = param.split("=");
					map.put(p[0], java.net.URLDecoder.decode(p[1], StandardCharsets.UTF_8.name()));
				}
			}
			catch (Exception e)
			{
			}
			return map;
		}

}
