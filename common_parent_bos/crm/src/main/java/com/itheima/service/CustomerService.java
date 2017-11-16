package com.itheima.service;

import com.itheima.domain.Customer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/10 21:05 <br/>
 * Author zzff
 */
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public interface CustomerService {

    @PUT
    @Path("/customer")
    public boolean updateCustomer(Customer customer);

    @DELETE
    @Path("/customer")
    public void deleteCustomerById(@QueryParam("id")Long id);

    @GET
    @Path("/customer")
    public Customer findCustomerById(@QueryParam("id")Long id);

    @GET
    @Path("/customers")
    public List<Customer> findCustomer();

    @POST
    @Path("/customer")
    public void insertCustomer(Customer customer);

    @GET
    @Path("/findFixedAreaIdByAddress")
    public Long findFixedAreaId(@QueryParam("address")String address);

}
