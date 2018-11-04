package com.graph.example.facade;

import java.util.List;

public interface NodeFacade<N> {

    N addNode(N nodeModel);

    N get(N node);

    List<N> getNodes();
}
