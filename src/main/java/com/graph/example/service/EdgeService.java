package com.graph.example.service;

import com.graph.example.entity.Edge;

import java.util.List;

public interface EdgeService<N> {

    Edge<N> addEdge(Edge<N> edge);

    List<Edge<N>> getEdges();
}
