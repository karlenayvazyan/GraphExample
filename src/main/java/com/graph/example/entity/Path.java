package com.graph.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Path<N> {

    @NotNull(message = "source node should not be null")
    private N sourceNodeId;

    @NotNull(message = "destinationNode node should not be null")
    private N destinationNodeId;
}
