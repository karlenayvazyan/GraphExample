package com.graph.example.facade;

import com.graph.example.entity.Edge;

import java.util.List;

public interface EdgeFacade<N> {

    Edge<N> addEdge(Edge<N> edgeModel);

    List<Edge<N>> getEdges();
}
