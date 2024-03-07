package com.learn.learn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.learn.Model.User;
import java.util.List;


@Repository
public interface UserRepo extends JpaRepository<User,Long>{
    
    User findByUsername(String username);

    List<User> findAll();

    User findByUsernameAndPassword(String username,String password);

    List<User> findByRoleId(String roleId);

    
}
