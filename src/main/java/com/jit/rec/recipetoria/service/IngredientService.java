package com.jit.rec.recipetoria.service;

import com.jit.rec.recipetoria.dto.IngredientDTO;
import com.jit.rec.recipetoria.entity.Ingredient;
import com.jit.rec.recipetoria.exception.ResourceNotFoundException;
import com.jit.rec.recipetoria.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientDTO createIngredient(IngredientDTO newIngredientInfo) {
        Ingredient ingredient = new Ingredient();

        ingredient.setName(newIngredientInfo.name());
        ingredient.setAmount(newIngredientInfo.amount());
        ingredient.setMeasurementUnit(newIngredientInfo.measurementUnit());

        ingredientRepository.save(ingredient);

        return IngredientDTO.convertToDTO(ingredient);
    }

    public IngredientDTO getIngredientById(Long ingredientId) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient with ID: " + ingredientId + " not found!"));

        return IngredientDTO.convertToDTO(ingredient);
    }

    public IngredientDTO updateIngredientById(Long ingredientId, IngredientDTO updatedIngredientInfo) {
        Ingredient ingredientToBeUpdated = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient with ID: " + ingredientId + " not found!"));

        ingredientToBeUpdated.setName(updatedIngredientInfo.name());
        ingredientToBeUpdated.setAmount(updatedIngredientInfo.amount());
        ingredientToBeUpdated.setMeasurementUnit(updatedIngredientInfo.measurementUnit());

        ingredientRepository.save(ingredientToBeUpdated);

        return IngredientDTO.convertToDTO(ingredientToBeUpdated);
    }

    public void deleteIngredientById(Long ingredientId) {
        if (!ingredientRepository.existsById(ingredientId)) {
            throw new ResourceNotFoundException("Ingredient with ID: " + ingredientId + " not found!");
        }

        ingredientRepository.deleteById(ingredientId);
    }
}
