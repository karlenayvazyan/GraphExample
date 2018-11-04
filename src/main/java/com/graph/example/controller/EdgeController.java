package com.graph.example.controller;

import com.graph.example.entity.Edge;
import com.graph.example.facade.EdgeFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/edge")
@Validated
public class EdgeController<N> {

    private final EdgeFacade<N> edgeFacade;

    public EdgeController(EdgeFacade<N> edgeFacade) {
        this.edgeFacade = edgeFacade;
    }


    @PostMapping
    public ResponseEntity<Edge<N>> addEdges(@Valid @RequestBody Edge<N> edgeModel) {
        return ResponseEntity.ok()
                .body(edgeFacade.addEdge(edgeModel));
    }

    @GetMapping
    public ResponseEntity<List<Edge<N>>> getEdges() {
        return ResponseEntity.ok()
                .body(edgeFacade.getEdges());
    }
}
