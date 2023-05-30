import model.Controller;
import model.Dijkstra;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);


        while (true) {
            System.out.println("Welcome to bad Trip>!");

            System.out.println(
                    """                         
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
                    System.out.println("Option out range");
                    break;

            }

        }
    }








    public static void aerial() {

        Controller controller=new Controller();

        Dijkstra<String> graph= new Dijkstra<>();

        Scanner lector = new Scanner(System.in);
        while (true) {

            System.out.println(
                    """
                            
                            What do you want to do?
                                                         1. Add scale.
                                                         2. Add connection between scales.
                                                         3. Consult: added scales.  
                                                         4. Know the most optimal trip from one point to any other.
                                                         5. Know the most optimal trip from one point to another.
                                                         6. path from one point to another with the fewest number of scales. (does not take into account c02 emissions or cost between edges, just the fewest number of scales between one point and another)
                                                         7. Exit.
                            """
            );


            int option = Integer.parseInt(lector.nextLine());

            switch (option) {


                case 1:
                    System.out.print("Type the name of the place: ");
                    String nameScale=lector.nextLine();
                    graph.addVertex(nameScale);
                    System.out.println("scale added successfully");


                    break;

                case 2:
                    System.out.print("Type the name of the first place:");
                         String firstPlace=lector.nextLine();
                    System.out.print("Type the name of the second place:");
                        String secondPlace=lector.nextLine();

                    System.out.print("Type the distance between the places:");
                        int distancePlaces= lector.nextInt();
                        lector.nextLine();




                    graph.addEdge(firstPlace,secondPlace,controller.calculateWeight(distancePlaces,1));
                    System.out.println("connection between scales added successfully");


                    break;

                case 3:
                    System.out.println("----------");
                    System.out.println("The stops or possible places to go are:");
                    graph.printGraph();
                    break;
                case 4:
                    System.out.println("Type the name of the origin place");
                    String origin=lector.nextLine();
                    graph.dijkstra(origin);

                    // Ejecutar algoritmo de Dijkstra
                    Map<String, Integer> distances = graph.dijkstra(origin);

                    // Imprimir las distancias más cortas desde el vértice "A"
                    for (Map.Entry<String, Integer> entry : distances.entrySet()) {
                        String vertex = entry.getKey();
                        int distance = entry.getValue();
                        System.out.println("Distancia más corta desde "+origin+" hasta " + vertex + ", teniendo en cuenta las menores emisiones de c02 y la distancia, es de : " + distance+" toneladas de c02");
                    }

                    break;
                case 5:

                    System.out.print("Type the name of the first place:");
                    String start=lector.nextLine();
                    System.out.print("Type the name of the second place:");
                    String destination=lector.nextLine();


                    List<String> shortestPathDj = graph.getShortestPathDj(start, destination);

                    if (shortestPathDj != null) {
                        System.out.println("El camino más corto desde " + start + " hasta " + destination + " es:");
                        for (String city : shortestPathDj) {
                            System.out.print(city + " -> ");
                        }
                        System.out.println("Fin");
                    } else {
                        System.out.println("No hay un camino desde " + start + " hasta " + destination);
                    }

                    break;

                case 6:


                    System.out.print("Type the name of the first place:");
                            String originPlace=lector.nextLine();
                        System.out.print("Type the name of the second place:");
                            String destinationPlace=lector.nextLine();

                    List<String> shortestPath = graph.bfs(originPlace, destinationPlace);

                    // Imprimir el camino más corto
                    if (shortestPath != null) {
                        System.out.println("El camino más corto desde " + originPlace + " hasta " + destinationPlace + " es:");
                        for (String vertex : shortestPath) {
                            System.out.print(vertex + " -> ");
                        }
                        System.out.println("Fin");
                    }else {
                        System.out.println("No se encontró un camino desde " + originPlace + " hasta " + destinationPlace);
                    }


                    break;

                case 7:
                    System.exit(0);
                    break;
            }


        }
    }







    public static void land() {

        Scanner lector = new Scanner(System.in);
        Controller controller=new Controller();
        Dijkstra<String> graphLand= new Dijkstra<>();

        while (true) {

            System.out.println(
                    """
                            What do you want to do?
                                                         1. Add city/town.
                                                         2. Add connection between places.
                                                         3. Know the most optimal trip from one point to any other.
                                                         4. Know the most optimal trip from one point to another.
                                                         5. Exit.
                            """
            );


            int option = Integer.parseInt(lector.nextLine());

            switch (option) {


                case 1:
                    System.out.println("");
                    break;

                case 2:
                    System.out.println("");
                    break;
                case 3:
                    System.out.println("");
                    break;
                case 4:
                    System.out.println("");
                    break;

                case 5:
                    System.exit(0);
                    break;
            }


        }


    }
}