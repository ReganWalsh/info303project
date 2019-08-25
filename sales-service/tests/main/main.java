/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

//import api.SalesPerCustomerApi;
import domain.Sale;
import java.io.IOException;
import java.util.List;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author walre888
 */
public class main {
    
    public static void main (String [] args) throws IOException{
        
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        
        //SalesPerCustomerApi perCustomerApi = retrofit.create(SalesPerCustomerApi.class);
        
        //List<Sale> sales = perCustomerApi.apiSalesCustomerIdGet("S1").execute().body();
        
        //System.out.println(sales);
    }
    
}
