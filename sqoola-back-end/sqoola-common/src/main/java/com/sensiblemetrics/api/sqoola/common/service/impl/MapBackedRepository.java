package com.sensiblemetrics.api.sqoola.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;

public class MapBackedRepository<K, V extends Identifiable<K>> {
  private Map<K, V> service = new HashMap<>();

  public void delete(K key) {
    service.remove(key);
  }

  public boolean exists(K key) {
    return service.containsKey(key);
  }

  public void add(V model) {
    service.put(model.getIdentifier(), model);
  }

  public V get(K key) {
    return service.get(key);
  }

  public V first() {
    return service.values().stream().findFirst().orElse(null);
  }

  public List<V> where(Predicate<V> criteria) {
    return service.values().stream()
            .filter(criteria).collect(toList());
  }
}
