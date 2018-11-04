package com.graph.example.controller;

import com.graph.example.facade.NodeFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/node")
@Validated
public class NodeController<N> {

    private final NodeFacade<N> nodeFacade;

    public NodeController(NodeFacade<N> nodeFacade) {
        this.nodeFacade = nodeFacade;
    }

    @PostMapping
    public ResponseEntity<N> add(@Valid @RequestBody  N nodeModel) {
        return ResponseEntity.ok()
                .body(nodeFacade.addNode(nodeModel));
    }

    @GetMapping
    public ResponseEntity<List<N>> get() {
        return ResponseEntity.ok()
                .body(nodeFacade.getNodes());
    }
}
