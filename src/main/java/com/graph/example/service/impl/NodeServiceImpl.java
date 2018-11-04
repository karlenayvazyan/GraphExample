package com.graph.example.service.impl;

import com.graph.example.exception.EntityNotFoundException;
import com.graph.example.repository.NodeRepository;
import com.graph.example.service.NodeService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class NodeServiceImpl<N> implements NodeService<N> {

    private final NodeRepository<N> nodeRepository;

    public NodeServiceImpl(NodeRepository<N> nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    @Override
    public N addNode(N node) {
        Assert.notNull(node, "Node should not be null");
        return nodeRepository.add(node);
    }

    @Override
    public List<N> getNodes() {
        return nodeRepository.getNodes();
    }

    @Override
    public boolean contains(N node) {
        Assert.notNull(node, "node should not be null");
        return nodeRepository.contains(node);
    }

    @Override
    public N get(N node) {
        Assert.notNull(node, "Id should not be null");
        return nodeRepository.get(node)
                .orElseThrow(() -> new EntityNotFoundException("Entity by id not found"));
    }
}
