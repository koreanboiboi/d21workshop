package workshop1.workshop21.repositories;


//When we want to implement jdbcTemplate.query we have to static import the query here first.
import static workshop1.workshop21.repositories.Queries.SQL_GET_ALL_CUSTOMERS;
import static workshop1.workshop21.repositories.Queries.SQL_SELECT_CUSTOMER_BY_ID;
import static workshop1.workshop21.repositories.Queries.SQL_SELECT_ALL_CUSTOMERS_ORDER;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import workshop1.workshop21.models.Customer;
import workshop1.workshop21.models.CustomerRowMapper;
import workshop1.workshop21.models.Order;
@Repository
public class CustomerRepository {

    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Customer> getAllCustomer(Integer offset, Integer limit){

        List<Customer> custs = new LinkedList<>();

        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_GET_ALL_CUSTOMERS, limit,offset);

        while(rs.next()){
            custs.add(Customer.create(rs));
        }

        return custs;
        
    }

    public Customer findCustomerById(Integer id) {

        List<Customer> custs = jdbcTemplate.query(SQL_SELECT_CUSTOMER_BY_ID, new CustomerRowMapper(), new Object[] {id});

        return (Customer) custs.get(0);
    }

    public List<Order> getCustomerOrder(Integer id) {

        final List<Order> orders = new LinkedList<>();

        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_SELECT_ALL_CUSTOMERS_ORDER, id);

        while(rs.next()){
            orders.add(Order.create(rs));
        }

        return orders;
    }

}
