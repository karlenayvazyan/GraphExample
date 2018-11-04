package com.graph.example.service.impl;

import com.graph.example.entity.Edge;
import com.graph.example.exception.EntityNotFoundException;
import com.graph.example.repository.EdgeRepository;
import com.graph.example.service.EdgeService;
import com.graph.example.service.NodeService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class EdgeServiceImpl<N> implements EdgeService<N> {

    private final NodeService<N> nodeService;

    private final EdgeRepository<N> edgeRepository;

    public EdgeServiceImpl(NodeService<N> nodeService, EdgeRepository<N> edgeRepository) {
        this.nodeService = nodeService;
        this.edgeRepository = edgeRepository;
    }

    @Override
    public Edge<N> addEdge(Edge<N> edge) {
        Assert.notNull(edge, "Edge should not be null");
        N sourceNodeId = edge.getSourceNode();
        Assert.notNull(sourceNodeId, "Edge sourceNodeId should not be null");
        N destinationNodeId = edge.getDestinationNode();
        Assert.notNull(destinationNodeId, "Edge destinationNodeId should not be null");
        Assert.notNull(edge.getWeight(), "Edge weight should not be null");
        if (!nodeService.contains(sourceNodeId)) {
            throw new EntityNotFoundException();
        }
        if (!nodeService.contains(destinationNodeId)) {
            throw new EntityNotFoundException();
        }
        return edgeRepository.add(edge);
    }

    @Override
    public List<Edge<N>> getEdges() {
        return edgeRepository.getEdges();
    }
}
