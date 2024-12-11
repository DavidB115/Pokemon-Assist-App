package com.pokemonapp.controller;

import com.pokemonapp.exception.DaoException;
import com.pokemonapp.dao.EffectivenessDao;
import com.pokemonapp.model.EffectivenessDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/effectiveness")
public class EffectivenessController {
}
