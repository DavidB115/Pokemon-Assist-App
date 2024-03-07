package com.pokemonapp.dao;

import com.pokemonapp.model.RegisterUserDto;
import com.pokemonapp.model.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    User getUserById(int id);

    User getUserByUsername(String username);

    User createUser(RegisterUserDto user);
}
