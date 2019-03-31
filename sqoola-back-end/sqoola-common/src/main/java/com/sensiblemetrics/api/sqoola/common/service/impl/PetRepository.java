package com.sensiblemetrics.api.sqoola.common.service.impl;

public class PetRepository extends MapBackedRepository<Long, Pet> {
    List<Pet> findPetByStatus(String status) {
      return where(input -> Objects.equals(input.getStatus(), status));
    }

    List<Pet> findPetByTags(String tags) {
      return where(input -> input.getTags().stream()
          .anyMatch(input1 -> Objects.equals(input1.getName(), tags)));
    }
}
