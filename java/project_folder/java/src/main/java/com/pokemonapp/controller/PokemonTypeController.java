package com.pokemonapp.controller;

import com.pokemonapp.exception.DaoException;
import com.pokemonapp.dao.PokemonTypeDao;
import com.pokemonapp.model.Type;
import com.pokemonapp.model.TypeNameDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping("/type")
public class PokemonTypeController {
}
