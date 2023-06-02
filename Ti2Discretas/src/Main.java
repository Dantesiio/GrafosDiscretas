import model.Controller;
import model.Dijkstra;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to bad Trip!");

            System.out.println("""
                    Choose the means of transport for the trip:
                    1. Aerial.
                    2. Land.
                    3. Exit.
                    """
            );
            int option = lector.nextInt();

            switch (option) {
                case 1:
                    aerial();
                    break;

                case 2:
                    land();
                    break;

                case 3:
                    System.exit(0);

                default:
                    System.out.println("Option out of range");
                    break;
            }
        }
    }

    // Función para manejar la lógica de transporte aéreo
    public static void aerial() {
        Controller controller = new Controller();
        Dijkstra<String> graph = new Dijkstra<>();
        Scanner lector = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    What do you want to do?
                    1. Add scale.
                    2. Add connection between scales.
                    3. Consult added scales.
                    4. Find the most optimal trip from one point to any other.
                    5. Find the most optimal trip from one point to another.
                    6. Find the path from one point to another with the fewest number of scales (ignoring CO2 emissions and cost).
                    7. Delete a scale.
                    8. Exit.
                    """
            );

            int option = Integer.parseInt(lector.nextLine());

            switch (option) {
                case 1:
                    System.out.print("Type the name of the place: ");
                    String nameScale = lector.nextLine();
                    graph.addVertex(nameScale);
                    System.out.println("Scale added successfully");
                    break;

                case 2:
                    System.out.print("Type the name of the first place: ");
                    String firstPlace = lector.nextLine();
                    System.out.print("Type the name of the second place: ");
                    String secondPlace = lector.nextLine();

                    System.out.print("Type the distance between the places: ");
                    int distancePlaces = lector.nextInt();
                    lector.nextLine();

                    // Calcular el peso de la arista basado en la distancia y tipo de transporte (aéreo = 1)
                    double weight = controller.calculateWeight(distancePlaces, 1);

                    graph.addEdge(firstPlace, secondPlace, weight);
                    System.out.println("Connection between scales added successfully");
                    break;

                case 3:
                    System.out.println("----------");
                    System.out.println("The stops or possible places to go are:");
                    graph.printGraph();
                    break;

                case 4:
                    System.out.println("Type the name of the origin place:");
                    String origin = lector.nextLine();

                    // Ejecutar algoritmo de Dijkstra
                    Map<String, Double> distances = graph.dijkstra(origin);

                    // Imprimir las distancias más cortas desde el vértice "inicial"
                    for (Map.Entry<String, Double> entry : distances.entrySet()) {
                        String vertex = entry.getKey();
                        double distance = entry.getValue();
                        System.out.println("The shortest distance from " + origin + " to " + vertex + ", considering the lowest CO2 emissions and distance, is: " + distance + "gr of CO2");
                    }
                    break;

                case 5:
                    System.out.print("Type the name of the first place: ");
                    String start = lector.nextLine();
                    System.out.print("Type the name of the second place: ");
                    String destination = lector.nextLine();

                    List<String> shortestPathDj = graph.getShortestPathDj(start, destination);

                    if (shortestPathDj != null) {
                        System.out.println("The shortest path from " + start + " to " + destination + " is:");
                        for (String city : shortestPathDj) {
                            System.out.print(city + " -> ");
                        }
                        System.out.println("End");
                    } else {
                        System.out.println("There is no path from " + start + " to " + destination);
                    }
                    break;

                case 6:
                    System.out.print("Type the name of the first place: ");
                    String originPlace = lector.nextLine();
                    System.out.print("Type the name of the second place: ");
                    String destinationPlace = lector.nextLine();

                    List<String> shortestPath = graph.bfs(originPlace, destinationPlace);

                    // Imprimir el camino más corto
                    if (shortestPath != null) {
                        System.out.println("The shortest path from " + originPlace + " to " + destinationPlace + " is:");
                        for (String vertex : shortestPath) {
                            System.out.print(vertex + " -> ");
                        }
                        System.out.println("End");
                    } else {
                        System.out.println("There is no path from " + originPlace + " to " + destinationPlace);
                    }
                    break;

                case 7:
                    System.out.print("Type the name of the scale: ");
                    String scaleToDelete = lector.nextLine();
                    graph.removeVertex(scaleToDelete);
                    System.out.println("The scale has been removed.");
                    break;

                case 8:
                    System.exit(0);
                    break;
            }
        }
    }

    // Función para manejar la lógica de transporte terrestre
    public static void land() {
        Scanner lector = new Scanner(System.in);
        Controller controller = new Controller();
        Dijkstra<String> graphLand = new Dijkstra<>();

        while (true) {
            System.out.println("""
                    What do you want to do?
                    1. Add city/town.
                    2. Add connection between places.
                    3. Consult added places.
                    4. Find the most optimal trip from one point to any other.
                    5. Find the most optimal trip from one point to another.
                    6. Find the path from one point to another with the fewest number of stops (ignoring CO2 emissions and cost).
                    7. Delete a place.
                    8. Exit.
                    """
            );

            int option = Integer.parseInt(lector.nextLine());

            switch (option) {
                case 1:
                    System.out.print("Type the name of the place: ");
                    String namePlace = lector.nextLine();
                    graphLand.addVertex(namePlace);
                    System.out.println("Place added successfully");
                    break;

                case 2:
                    System.out.print("Type the name of the first place: ");
                    String firstPlace = lector.nextLine();
                    System.out.print("Type the name of the second place: ");
                    String secondPlace = lector.nextLine();

                    System.out.print("Type the distance between the places: ");
                    int distancePlaces = lector.nextInt();
                    lector.nextLine();

                    // Calcular el peso de la arista basado en la distancia y tipo de transporte (terrestre = 2)
                    double weight = controller.calculateWeight(distancePlaces, 2);

                    graphLand.addEdge(firstPlace, secondPlace, weight);
                    System.out.println("Connection between places added successfully");
                    break;

                case 3:
                    System.out.println("----------");
                    System.out.println("The added places are:");
                    graphLand.printGraph();
                    break;

                case 4:
                    System.out.print("Type the name of the origin place: ");
                    String origin = lector.nextLine();
                    graphLand.dijkstra(origin);

                    // Ejecutar algoritmo de Dijkstra
                    Map<String, Double> distances = graphLand.dijkstra(origin);

                    // Imprimir las distancias más cortas desde el vértice "inicial"
                    for (Map.Entry<String, Double> entry : distances.entrySet()) {
                        String vertex = entry.getKey();
                        double distance = entry.getValue();
                        System.out.println("The shortest distance from " + origin + " to " + vertex + " considering the least CO2 emissions and distance is: " + distance + "gr of CO2");
                    }
                    break;

                case 5:
                    System.out.print("Type the name of the first place: ");
                    String start = lector.nextLine();
                    System.out.print("Type the name of the second place: ");
                    String destination = lector.nextLine();

                    List<String> shortestPathDj = graphLand.getShortestPathDj(start, destination);

                    if (shortestPathDj != null) {
                        System.out.println("The shortest path from " + start + " to " + destination + " is:");
                        for (String city : shortestPathDj) {
                            System.out.print(city + " -> ");
                        }
                        System.out.println("End");
                    } else {
                        System.out.println("There is no path from " + start + " to " + destination);
                    }
                    break;

                case 6:
                    System.out.print("Type the name of the first place: ");
                    String originPlace = lector.nextLine();
                    System.out.print("Type the name of the second place: ");
                    String destinationPlace = lector.nextLine();

                    List<String> shortestPath = graphLand.bfs(originPlace, destinationPlace);

                    // Imprimir el camino más corto
                    if (shortestPath != null) {
                        System.out.println("The shortest path from " + originPlace + " to " + destinationPlace + " is:");
                        for (String vertex : shortestPath) {
                            System.out.print(vertex + " -> ");
                        }
                        System.out.println("End");
                    } else {
                        System.out.println("There is no path from " + originPlace + " to " + destinationPlace);
                    }
                    break;

                case 7:
                    System.out.print("Type the name of the city/town: ");
                    String place = lector.nextLine();
                    graphLand.removeVertex(place);
                    System.out.println("The city/town has been removed.");
                    break;

                case 8:
                    System.exit(0);
                    break;
            }
        }
    }
}
