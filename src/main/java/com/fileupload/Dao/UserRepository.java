package com.fileupload.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fileupload.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
