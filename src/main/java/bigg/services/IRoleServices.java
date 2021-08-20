package bigg.services;

import bigg.model.Role;

import java.util.Optional;

public interface IRoleServices {
    Iterable<Role> findAll();

    Optional<Role> findById(long id);

    void save(Role role);

    void remove(Long id);
}
