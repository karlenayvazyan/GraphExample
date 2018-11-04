package com.graph.example.repository;

import com.graph.example.entity.Edge;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class EdgeRepository<N> {

    private final List<Edge<N>> edges;

    public EdgeRepository() {
        this.edges = new ArrayList<>();
    }

    public Edge<N> add(Edge<N> edge) {
        edges.add(edge);
        return edge;
    }

    public List<Edge<N>> getEdges() {
        return Collections.unmodifiableList(edges);
    }
}
