package com.itheima.service.Impl;

import com.itheima.dao.CustomerRepository;
import com.itheima.domain.Customer;
import com.itheima.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/10 21:06 <br/>
 * Author zzff
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean updateCustomer(Customer customer) {
        Customer one = customerRepository.findOne(customer.getId());
        if(one!=null){
            customerRepository.save(customer);
            return true;
        }
        return false;
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.delete(id);
    }

    @Override
    public Customer findCustomerById(Long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public List<Customer> findCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public void insertCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Long findFixedAreaId(String address) {
        return customerRepository.findFixedAreaIdByAddress(address);
    }
}
