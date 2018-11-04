package com.graph.example.facade.impl;

import com.graph.example.facade.NodeFacade;
import com.graph.example.service.NodeService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NodeFacadeImpl<N> implements NodeFacade<N> {

    private final NodeService<N> nodeService;

    public NodeFacadeImpl(NodeService<N> nodeService) {
        this.nodeService = nodeService;
    }

    @Override
    public N addNode(N node) {
        return nodeService.addNode(node);
    }

    @Override
    public N get(N node) {
        return nodeService.get(node);
    }

    @Override
    public List<N> getNodes() {
        return nodeService.getNodes();
    }
}
