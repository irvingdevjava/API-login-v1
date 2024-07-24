package com.irvza.login.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irvza.login.models.UserModel;

public interface UsersRepository extends JpaRepository<UserModel, Integer>{

}
