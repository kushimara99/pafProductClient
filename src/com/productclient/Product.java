package com.productclient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Product {

	private Connection connect()
    {
        Connection con = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

 

            //Provide the correct details: DBServer/DBName, username, password
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/product", "root", "root");
        }
        catch (Exception e)
        {e.printStackTrace();}
        return con;
    } 

 

    //Read Research
    public String getProduct() {
    	
    	
    	String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			// Prepare the HTML table to be displayed
			output = "<table  class='table table-dark table-striped'><tr>"
					+ "<th>ID</th>"
					+"<th>Product ID</th>"
					+ "<th>Product Code</th>"
					+ "<th>Product Name</th>"
					+ "<th>Product Price</th>"
					+ "<th>Quantity</th>"
					+ "<th>Researcher ID</th>"
					+ "<th>Created On</th>"
					+ "<th>Update</th>"
					+ "<th>Delete</th></tr>";


            // create a prepared statement
            String query = " select * from product";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();
            
       
            while(rs.next()) {
          
                int id = rs.getInt("id") ;
                int product_id = rs.getInt("product_id") ;
                String product_code = rs.getString("product_code") ;
                String product_name =  rs.getString("product_name") ;
                double product_price = rs.getDouble("product_price") ;
                int quantity = rs.getInt("quantity") ;
                int researcher_id = rs.getInt("researcher_id") ;
                String timestamp = rs.getString("timestamp") ;
                           
                
             // Add a row into the HTML table
            	output += "<tr><td>" + id + "</td>";
				output += "<td>" + product_id + "</td>";
				output += "<td>" + product_code + "</td>";
				output += "<td>" + product_name + "</td>"; 
				output += "<td>" + product_price + "</td>";
				output += "<td>" + quantity + "</td>";
				output += "<td>" + researcher_id + "</td>";
				output += "<td>" + timestamp + "</td>";
			
				

				// buttons
				output += "<td><input name='btnUpdate' id='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-id='" + id + "'></td>"
						+"<td><input name='btnRemove' id='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-id='" + id + "'></td></tr>";
			}

			con.close();

			// Complete the HTML table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the products.";
			System.err.println(e.getMessage());
		}
		return output;
	}
    

    //insert product
    public JsonObject insertProduct( int researcher_id,int product_id , String name, String code,int qnty,double price,String timestamp)
    {
        JsonObject result = null;
        try
        {
            Connection con = connect();

 

            if (con == null)
            {
            	
                result = new JsonObject();
                result.addProperty("status", "error");
                result.addProperty("ERROR", "Error while connecting to the database for inserting.");
                return result;
            }

 


            // create a prepared statement
            String query = " insert into product"+
                    "(`researcher_id`,`product_id`,`product_name`,`product_code`,`quantity`,`product_price`,`timestamp`)"
                    + " values (?, ?, ?, ?, ?,?,?)";

 


            PreparedStatement preparedStmt = con.prepareStatement(query);

 


            // binding values
            preparedStmt.setInt(1, researcher_id);
            preparedStmt.setInt(2, product_id);
            preparedStmt.setString(3, name);
            preparedStmt.setString(4, code);
            preparedStmt.setInt(5, qnty);
            preparedStmt.setDouble(6,price);
            preparedStmt.setString(7,timestamp);
        
            // execute the statement

 

            preparedStmt.execute();
            con.close();
            
            result = new JsonObject();
          
            result = new JsonObject();
                    result.addProperty("status", "successfull");
                    result.addProperty("data", getProduct());

      
        }
        catch (Exception e)
        {
            result = new JsonObject();
            
            result.addProperty("status", "exception");
                    result.addProperty("data", "exception occured while inserting data.");

            System.err.println(e.getMessage());
        }
        return result;
    } 
    
  //update research
  	public JsonObject updateProduct( int id,int product_id, int researcher_id , String name, String code,int qnty,double price,String timestamp)
  	{
  		JsonObject result = null;
  		try
  		{
  			Connection con = connect();

  			if (con == null)
  			{
  				result = new JsonObject();
  				result.addProperty("status", "error");
                result.addProperty("data", "Error while connecting to the database for updating.");
                return result;
  			}


  			// create a prepared statement
  			String query = "UPDATE product SET product_id=?,product_code=?,product_name=?,product_price=?,quantity=?,researcher_id=?,timestamp=? WHERE id=?";


  			PreparedStatement preparedStmt = con.prepareStatement(query);


  			// binding values
  			
  		
  		 preparedStmt.setInt(1, product_id);
  		 preparedStmt.setString(2, code);
  		 preparedStmt.setString(3, name);
  	   preparedStmt.setDouble(4,price);
  	    preparedStmt.setInt(5, qnty);
  		  preparedStmt.setInt(6, researcher_id);
  		   preparedStmt.setString(7, timestamp);
  preparedStmt.setInt(8, id);
  		
  			
  			// execute the statement
  			int status = preparedStmt.executeUpdate();
            con.close();
           
            result = new JsonObject();
            if(status > 0) {
            	
                result.addProperty("status", "successfull");
                            result.addProperty("data", getProduct());

               
            }else {
            
                result.addProperty("status", "failed");
                            result.addProperty("data", getProduct());

            }
  		
  		}
  		catch (Exception e)
  		{
  		
  		    result = new JsonObject();
  		            result.addProperty("status", "exception");
  		            result.addProperty("data", "exception occured while updating product.");
  		            System.err.println(e.getMessage());

  		}
  		return result;
  	} 
  	
  	//delete research
  	public JsonObject deleteProduct( int id )
  	{
  		JsonObject result = null;
  		try
  		{
  			Connection con = connect();

  			if (con == null)
  			{
  			
  			    result = new JsonObject();
  			                result.addProperty("status", "error");
  			                result.addProperty("data", "Error while connecting to the database for deleting.");
  			                return result;

  			}


  			// create a prepared statement
  			String query = "delete from product where id=?";
  					 

  			PreparedStatement preparedStmt = con.prepareStatement(query);


  			// binding values
  			preparedStmt.setInt(1, id);
//  			preparedStmt.setString(2, name);
//  			preparedStmt.setString(3, description);
//  			preparedStmt.setString(4, category);
//  			preparedStmt.setDouble(5,budget);
//  			preparedStmt.setInt(6, research_id);
  			
  			// execute the statement
  			int status = preparedStmt.executeUpdate();
            con.close();
           
            result = new JsonObject();
            if(status > 0) {
            	
                result.addProperty("status", "successfull");
                            result.addProperty("data", getProduct());

               
            }else {
            	
                result.addProperty("status", "failed");
                            result.addProperty("data", getProduct());

            }
  			
  		}
  		catch (Exception e)
  		{
  			
  		    result = new JsonObject();
  		            result.addProperty("status", "exception");
  		            result.addProperty("data", "exception occured while deleting product.");
  		            System.err.println(e.getMessage());

  		}
  		return result;
  	}
 


}