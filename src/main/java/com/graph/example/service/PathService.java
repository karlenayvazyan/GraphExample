package com.graph.example.service;

import java.util.List;

public interface PathService<N> {

    List<N> getPath(N source, N target);
}
