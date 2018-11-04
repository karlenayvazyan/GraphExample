package com.graph.example.util;

import com.graph.example.entity.Edge;
import com.graph.example.service.EdgeService;
import com.graph.example.service.PathService;
import com.graph.example.service.impl.DijkstraAlgorithmServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class DijkstraAlgorithmServiceImplTest {

    @TestConfiguration
    static class Configuration {

        @MockBean
        private EdgeService<Integer> edgeService;

        @Bean
        public PathService mandateService() {
            return new DijkstraAlgorithmServiceImpl<>(edgeService);
        }
    }

    @Autowired
    private DijkstraAlgorithmServiceImpl<Integer> dijkstra;

    @Autowired
    private EdgeService<Integer> edgeService;

    @Test(expected = IllegalArgumentException.class)
    public void getPathWhenSourceIsNull() {
        Integer source = null;
        Integer target = null;
        dijkstra.getPath(source, target);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPathWhenTargetIsNull() {
        Integer source = 1;
        Integer target = null;
        dijkstra.getPath(source, target);
    }

    @Test
    public void getPath() {
        List<Integer> nodes = new ArrayList<>();
        List<Edge<Integer>> edges = new ArrayList<>();
        for (int i = 1; i < 12; i++) {
            nodes.add(i);
        }

        addLane(0L, 0, 1, 85, nodes, edges);
        addLane(1L, 0, 2, 217, nodes, edges);
        addLane(2L, 0, 4, 173, nodes, edges);

        addLane(3L, 2, 6, 186, nodes, edges);
        addLane(4L, 2, 7, 103, nodes, edges);
        addLane(5L, 3, 7, 183, nodes, edges);

        addLane(6L, 5, 8, 250, nodes, edges);
        addLane(7L, 8, 9, 84, nodes, edges);
        addLane(8L, 7, 9, 167, nodes, edges);

        addLane(9L, 4, 9, 502, nodes, edges);
        addLane(10L, 9, 10, 40, nodes, edges);
        addLane(11L, 1, 10, 600, nodes, edges);

        when(edgeService.getEdges()).thenReturn(edges);
        List<Integer> path = dijkstra.getPath(nodes.get(0), nodes.get(7));

        assertNotNull(path);
        assertThat(path, hasSize(3));
        assertThat(path, contains(nodes.get(0), nodes.get(2), nodes.get(7)));
    }

    private void addLane(Long laneId, int sourceLocNo, int destLocNo, int duration, List<Integer> nodes, List<Edge<Integer>> edges) {
        Edge<Integer> lane = new Edge<>(nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
        edges.add(lane);
    }
}