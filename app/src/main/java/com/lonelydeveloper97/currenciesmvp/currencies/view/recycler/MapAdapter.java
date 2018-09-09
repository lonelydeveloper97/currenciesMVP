package com.lonelydeveloper97.currenciesmvp.currencies.view.recycler;


import android.support.v4.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class is used to stabilise id's for recyclerView if we have data in map format
 */
public class MapAdapter<K, V> {
    private Map<K, V> valuesMap;
    private final List<K> positions;

    public MapAdapter() {
        valuesMap = new HashMap<>();
        positions = new ArrayList<>();
    }

    public Pair<K, V> get(int position) {
        K k = positions.get(position);
        return new Pair<>(k, valuesMap.get(k));
    }

    public void add(K key, V value, int position) {
        valuesMap.put(key, value);
        addKey(key, position);
    }

    public void add(K key, V value) {
        valuesMap.put(key, value);
        addKey(key);
    }

    private void addKey(K key) {
        if (!positions.contains(key)) {
            positions.add(key);
        }
    }

    private void addKey(K key, int position) {
        if (!positions.contains(key)) {
            positions.add(position, key);
        }
    }

    public int size() {
        return positions.size();
    }

    public void remove(K o) {
        valuesMap.remove(o);
        positions.remove(o);
    }

    public void updateValuesMap(Map<K, V> valuesMap) {
        this.valuesMap = valuesMap;
        for (K key : valuesMap.keySet()) {
            addKey(key);
        }
        clearPositions(valuesMap.keySet());
    }

    private void clearPositions(Set<K> newKeySet) {
        for (K key : positions) {
            if (!newKeySet.contains(key)) {
                positions.remove(key);
            }
        }
    }

    public int indexOf(K key) {
        return positions.indexOf(key);
    }
}