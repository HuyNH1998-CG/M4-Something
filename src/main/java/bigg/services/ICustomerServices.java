package bigg.services;

import bigg.model.Customer;

import java.util.Optional;

public interface ICustomerServices {
    Iterable<Customer> findAll();

    Optional<Customer> findById(Long id);

    void save(Customer customer);

    void delete(Long id);

    Iterable<Customer> findByName(String name);


}
