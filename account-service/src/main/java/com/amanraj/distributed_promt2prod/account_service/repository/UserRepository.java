package com.amanraj.distributed_promt2prod.account_service.repository;

import com.amanraj.distributed_promt2prod.account_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    User save(User user);
}
