package com.example.Repositories;

import com.example.Entities.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    Optional<UserEntity> findByNombre(String nombre);

    @Query("select u from UserEntity u where u.nombre =?1")
    Optional<User> getNombre(String nombre);

}
