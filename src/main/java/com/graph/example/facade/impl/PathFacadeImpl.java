package com.graph.example.facade.impl;

import com.graph.example.entity.Path;
import com.graph.example.facade.PathFacade;
import com.graph.example.service.PathService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PathFacadeImpl<N> implements PathFacade<N> {

    private final PathService<N> pathService;


    public PathFacadeImpl(@Qualifier("dijkstraAlgorithmServiceImpl") PathService<N> pathService) {
        this.pathService = pathService;
    }

    @Override
    public List<N> getPath(Path<N> path) {
        return pathService.getPath(path.getSourceNodeId(), path.getDestinationNodeId());
    }
}
