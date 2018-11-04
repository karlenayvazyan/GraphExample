package com.graph.example.service.impl;

import com.graph.example.entity.Edge;
import com.graph.example.service.EdgeService;
import com.graph.example.service.PathService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

@Service("dijkstraAlgorithmServiceImpl")
public class DijkstraAlgorithmServiceImpl<N> implements PathService<N> {
    private List<Edge<N>> edges;
    private Set<N> settledNodes;
    private Map<N, Integer> distance;

    private final EdgeService<N> edgeService;

    public DijkstraAlgorithmServiceImpl(EdgeService<N> edgeService) {
        this.edgeService = edgeService;
    }

    @Override
    public List<N> getPath(N source, N target) {
        Assert.notNull(source, "source should not be null when getting shortest path");
        Assert.notNull(target, "target should not be null when getting shortest path");
        this.edges = edgeService.getEdges();
        this.settledNodes = new HashSet<>();
        this.distance = new HashMap<>();
        distance.put(source, 0);
        Set<N> unSettledNodes = new HashSet<>();
        unSettledNodes.add(source);
        Map<N, N> predecessors = new HashMap<>();
        while (unSettledNodes.size() > 0) {
            N node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            List<N> adjacentNodes = getNeighbors(node);
            for (N adjacentNodesTarget : adjacentNodes) {
                if (getShortestDistance(adjacentNodesTarget) > getShortestDistance(node)
                        + getDistance(node, adjacentNodesTarget)) {
                    distance.put(adjacentNodesTarget, getShortestDistance(node)
                            + getDistance(node, adjacentNodesTarget));
                    predecessors.put(adjacentNodesTarget, node);
                    unSettledNodes.add(adjacentNodesTarget);
                }
            }
        }

        List<N> path = new LinkedList<>();
        N step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

    private List<N> getNeighbors(N node) {
        return edges.stream()
                .filter(edge -> isSourceEqualsToNodeAndIsNotSettled(node, edge))
                .map(Edge::getDestinationNode)
                .collect(Collectors.toList());
    }

    private boolean isSourceEqualsToNodeAndIsNotSettled(N node, Edge edge) {
        return edge.getSourceNode().equals(node)
                && settledNodes.stream()
                .noneMatch(id -> id.equals(edge.getDestinationNode()));
    }

    private int getShortestDistance(N destination) {
        Integer destinationDistance = distance.get(destination);
        return Optional.ofNullable(destinationDistance)
                .orElse(Integer.MAX_VALUE);
    }

    private int getDistance(N node, N target) {
        return edges.stream()
                .filter(edge -> isNodeSourceAndDistanceEquals(node, target, edge))
                .findFirst()
                .map(Edge::getWeight)
                .orElseThrow(() -> new RuntimeException("Should not happen"));
    }

    private boolean isNodeSourceAndDistanceEquals(N node, N target, Edge edge) {
        return edge.getSourceNode().equals(node) && edge.getDestinationNode().equals(target);
    }

    private N getMinimum(Set<N> nodes) {
        N minimum = null;
        for (N node : nodes) {
            if (minimum == null) {
                minimum = node;
            } else {
                if (getShortestDistance(node) < getShortestDistance(minimum)) {
                    minimum = node;
                }
            }
        }
        return minimum;
    }
}