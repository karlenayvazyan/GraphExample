package com.graph.example.service.impl;

import com.graph.example.entity.Edge;
import com.graph.example.exception.EntityNotFoundException;
import com.graph.example.repository.EdgeRepository;
import com.graph.example.service.EdgeService;
import com.graph.example.service.NodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class EdgeServiceImplTest {

    @TestConfiguration
    static class Configuration {

        @MockBean
        private NodeService<Integer> nodeService;

        @MockBean
        private EdgeRepository<Integer> edgeRepository;

        @Bean
        public EdgeService<Integer> mandateService() {
            return new EdgeServiceImpl<>(nodeService, edgeRepository);
        }
    }

    @Autowired
    private EdgeRepository<Integer> edgeRepository;

    @Autowired
    private EdgeService<Integer> edgeService;

    @Autowired
    private NodeService<Integer> nodeService;

    @Test(expected = IllegalArgumentException.class)
    public void addEdgeWhenEdgeIsNull() {
        Edge<Integer> edge = null;
        edgeService.addEdge(edge);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addEdgeWhenEdgeIdIsNull() {
        Edge<Integer> edge = new Edge<>();
        edgeService.addEdge(edge);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addEdgeWhenEdgeDestinationNodeIdIsNull() {
        Edge<Integer> edge = new Edge<>();
        edge.setSourceNode(1);
        edgeService.addEdge(edge);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addEdgeWhenEdgeWeightNull() {
        Edge<Integer> edge = new Edge<>();
        edge.setSourceNode(2);
        edge.setDestinationNode(5);
        edgeService.addEdge(edge);
    }

    @Test(expected = EntityNotFoundException.class)
    public void addEdgeWhenSourceNodeIdIsInvalid() {
        Edge<Integer> edge = new Edge<>();
        edge.setSourceNode(2);
        edge.setDestinationNode(5);
        edge.setWeight(13);
        when(nodeService.contains(edge.getSourceNode())).thenReturn(Boolean.FALSE);
        edgeService.addEdge(edge);
    }

    @Test(expected = EntityNotFoundException.class)
    public void addEdgeWhenDestinationNodeIdIsInvalid() {
        Edge<Integer> edge = new Edge<>();
        edge.setSourceNode(2);
        edge.setDestinationNode(5);
        edge.setWeight(13);
        when(nodeService.contains(edge.getSourceNode())).thenReturn(Boolean.TRUE);
        when(nodeService.contains(edge.getDestinationNode())).thenReturn(Boolean.FALSE);
        edgeService.addEdge(edge);
    }

    @Test
    public void addEdge() {
        Edge<Integer> edge = new Edge<>();
        edge.setSourceNode(2);
        edge.setDestinationNode(5);
        edge.setWeight(13);
        when(nodeService.contains(edge.getSourceNode())).thenReturn(Boolean.TRUE);
        when(nodeService.contains(edge.getDestinationNode())).thenReturn(Boolean.TRUE);
        Edge<Integer> addedEdge = new Edge<>();
        when(edgeRepository.add(edge)).thenReturn(addedEdge);
        Edge edgeResult = edgeService.addEdge(edge);
        assertNotNull(edgeResult);
    }

    @Test
    public void getEdges() {
        Edge<Integer> edge = new Edge<>();
        edge.setSourceNode(2);
        edge.setDestinationNode(5);
        edge.setWeight(13);
        when(nodeService.contains(edge.getSourceNode())).thenReturn(Boolean.TRUE);
        when(nodeService.contains(edge.getDestinationNode())).thenReturn(Boolean.TRUE);
        when(edgeRepository.getEdges()).thenReturn(Collections.singletonList(edge));
        List<Edge<Integer>> edgesResult = edgeService.getEdges();
        assertNotNull(edgesResult);
    }
}