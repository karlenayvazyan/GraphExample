package com.graph.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Edge<N> {

    @NotNull(message = "source node should not be null")
    private N sourceNode;

    @NotNull(message = "destinationNode node should not be null")
    private N destinationNode;

    @NotNull(message = "weight should not be null")
    @Min(value = 1, message = "weight should not be less then 1")
    private Integer weight;
}