package com.graph.example.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class NodeRepository<N> {

    private final List<N> nodes;

    public NodeRepository() {
        this.nodes = new ArrayList<>();
    }

    public N add(N node) {
        nodes.add(node);
        return node;
    }

    public List<N> getNodes() {
        return Collections.unmodifiableList(nodes);
    }

    public boolean contains(N node) {
        return nodes.contains(node);
    }

    public Optional<N> get(N node) {
        return nodes.stream()
                .filter(node::equals)
                .findFirst();
    }
}
