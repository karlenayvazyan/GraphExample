package com.graph.example.facade.impl;

import com.graph.example.entity.Edge;
import com.graph.example.facade.EdgeFacade;
import com.graph.example.service.EdgeService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EdgeFacadeImpl<N> implements EdgeFacade<N> {

    private final EdgeService<N> edgeService;

    public EdgeFacadeImpl(EdgeService<N> edgeService) {
        this.edgeService = edgeService;
    }

    @Override
    public Edge<N> addEdge(Edge<N> edge) {
        return edgeService.addEdge(edge);
    }

    @Override
    public List<Edge<N>> getEdges() {
        return edgeService.getEdges();
    }
}
