package com.graph.example.facade;

import com.graph.example.entity.Path;

import java.util.List;

public interface PathFacade<N> {

    List<N> getPath(Path<N> path);
}
