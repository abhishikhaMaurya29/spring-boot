package com.abhishikha.repository;

import com.abhishikha.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
}

//psql
//create table users (id integer primary key, username text, password text);
//select * from users;
//insert into users values (1, 'navin', 'n@123'), (2, 'sushil', 's@123');
