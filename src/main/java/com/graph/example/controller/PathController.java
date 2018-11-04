package com.graph.example.controller;

import com.graph.example.entity.Path;
import com.graph.example.facade.PathFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/path")
@Validated
public class PathController<N> {

    private final PathFacade<N> pathFacade;

    public PathController(PathFacade<N> pathFacade) {
        this.pathFacade = pathFacade;
    }

    @PostMapping
    public ResponseEntity<List<N>> getPath(@Valid @RequestBody Path<N> path) {
        return ResponseEntity.ok()
                .body(pathFacade.getPath(path));
    }
}
