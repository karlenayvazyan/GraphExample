package com.graph.example.service;

import java.util.List;

public interface NodeService<N> {

    N addNode(N node);

    List<N> getNodes();

    boolean contains(N node);

    N get(N node);
}
