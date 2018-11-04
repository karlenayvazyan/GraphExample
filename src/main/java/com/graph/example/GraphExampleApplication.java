package com.graph.example;

import com.graph.example.entity.Edge;
import com.graph.example.entity.Path;
import com.graph.example.facade.EdgeFacade;
import com.graph.example.facade.NodeFacade;
import com.graph.example.facade.PathFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class GraphExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphExampleApplication.class, args);
	}

	@Autowired
	private NodeFacade<Integer> nodeFacade;

	@Autowired
	private EdgeFacade<Integer> edgeFacade;

	@Bean
	public CommandLineRunner commandLineRunner(PathFacade<Integer> pathFacade) {
		return args -> {
			for (int i = 1; i < 12; i++) {
				nodeFacade.addNode(i);
			}

			addLane(1, 2, 85);
			addLane(1, 3, 217);
			addLane(1, 5, 173);

			addLane(3, 7, 186);
			addLane(3, 8, 103);
			addLane(4, 8, 183);

			addLane(6, 9, 250);
			addLane(9, 10, 84);
			addLane(8, 10, 167);

			addLane(5, 10, 502);
			addLane( 10, 11, 40);
			addLane( 2, 11, 600);

			edgeFacade.getEdges().forEach(System.out::println);

			List<Integer> path = pathFacade.getPath(new Path<>(1, 11));
			System.out.println("-------------");

			path.forEach(System.out::println);
		};
	}

	private void addLane(Integer sourceLocNo, Integer destLocNo, int duration) {
		Edge<Integer> lane = new Edge<>(sourceLocNo, destLocNo, duration);
		edgeFacade.addEdge(lane);
	}
}