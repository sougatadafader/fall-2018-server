package com.example.whiteboardfall2018serverjava.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.whiteboardfall2018serverjava.models.User;

public interface UserRepository  extends CrudRepository<User,Integer>{

}
