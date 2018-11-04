package com.graph.example.service.impl;

import com.graph.example.exception.EntityNotFoundException;
import com.graph.example.repository.NodeRepository;
import com.graph.example.service.NodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class NodeServiceImplTest {

    @TestConfiguration
    static class Configuration {

        @MockBean
        private NodeRepository<Integer> nodeRepository;

        @Bean
        public NodeService<Integer> mandateService() {
            return new NodeServiceImpl<>(nodeRepository);
        }
    }

    @Autowired
    private NodeService<Integer> nodeService;

    @Autowired
    private NodeRepository<Integer> nodeRepository;

    @Test(expected = IllegalArgumentException.class)
    public void addNodeWhenNodeIsNull() {
        Integer node = null;
        nodeService.addNode(node);
    }

    @Test
    public void addNodeWhenNodesEmpty() {
        Integer node = 1;
        Integer addedNode = 1;
        when(nodeRepository.add(node)).thenReturn(addedNode);
        Integer nodeResult = nodeService.addNode(node);
        assertNotNull(nodeResult);
        assertEquals(addedNode, nodeResult);
    }

    @Test
    public void getNodes() {
        List<Integer> nodes = new ArrayList<>();
        when(nodeRepository.getNodes()).thenReturn(nodes);
        List<Integer> nodesResult = nodeService.getNodes();
        assertNotNull(nodesResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void containsWhenNodeIsNull() {
        Integer id = null;
        nodeService.contains(id);
    }

    @Test
    public void contains() {
        Integer id = 78;
        when(nodeRepository.contains(id)).thenReturn(Boolean.FALSE);
        boolean containsById = nodeService.contains(id);
        assertFalse(containsById);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getWhenNodeIsNull() {
        Integer node = null;
        nodeService.get(node);
    }

    @Test(expected = EntityNotFoundException.class)
    public void getWhenIncorrectNode() {
        Integer node = 1;
        when(nodeRepository.get(node)).thenReturn(Optional.empty());
        nodeService.get(node);
    }

    @Test
    public void get() {
        Integer node = 1;
        when(nodeRepository.get(node)).thenReturn(Optional.of(node));
        Integer byId = nodeService.get(node);
        assertNotNull(byId);
        assertEquals(node, byId);
    }
}