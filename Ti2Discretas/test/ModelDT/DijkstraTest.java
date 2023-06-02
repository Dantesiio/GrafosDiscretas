package ModelDT;
import model.Dijkstra;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class DijkstraTest {



    //Setup 1  grafo de Double
    public Dijkstra<Double> setup1(){


        Dijkstra<Double> graph= new Dijkstra<>();


        return  graph;
    }


    //Setup 2  grafo de String
    public Dijkstra<String> setup2(){


        Dijkstra<String> graph= new Dijkstra<>();


        return  graph;
    }


    //Setup 3 grafo de Character
    public Dijkstra<Character> setup3(){


        Dijkstra<Character> graph= new Dijkstra<>();


        return  graph;
    }

    //---------------------------------------------------

    //Test para comprobar que se ha añadido un vértice
    @Test
    public void testAddVertex(){


        boolean pass=false;

        //Arrange
        Dijkstra<Double> graph=setup1();

        //Act
        graph.addVertex(4.0);
        double numberTest=graph.searchVertex(4.0);


        if(numberTest==4.0){

            pass=true;

        }

        assertTrue(pass);

    }


    //Prueba que se agregue un vertice y ahora usa contains para verificarlo
    @Test
    public void testAddVertexWithContains(){

        //Arrange
        Dijkstra<Double> graph=setup1();
        graph.addVertex(4.0);


        //act
        // Verificar que el vértice "4" se haya agregado correctamente
        boolean vertexExists = graph.getAdjacencyMap().containsKey(4.0);

        //assert
        assertEquals(true, vertexExists);



    }


    //Busca un vertice que no existe
    @Test
    public void testSearchANonExistentVertex(){


        boolean pass=true;
        //arrange
        Dijkstra<Double> graph=setup1();


        if(graph.searchVertex(1.0)==null){
            pass=false;
        }

        assertFalse(pass);

    }


   // -------------------------------------


    //test para añadir una arista
    @Test
    public void testAddEdge(){

        //Arrange
        Dijkstra<String> graph=setup2();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B", 5);

        //Act
        // Verificar que la arista se haya agregado correctamente desde "A" hacia "B"
        boolean edgeExistsAB = graph.adjacencyMap.get("A").containsKey("B");


        //Assert
        assertEquals(true, edgeExistsAB);



    }



    //Test para verificar que la arista sea bidireccional
    @Test
    public void testAddEdgeBiDerection(){

        //Arrange
        Dijkstra<String> graph=setup2();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B", 5);



        //Act
        // Verificar que la arista se haya agregado correctamente desde "B" hacia "A"
        boolean edgeExistsBA = graph.adjacencyMap.get("B").containsKey("A");


        //Assert
        assertEquals(true, edgeExistsBA);




    }


    //Test para comprobar que el peso agregado es el mismo
    @Test
    public void testAddEdgeVerifyWeight(){


        //Arrange
        Dijkstra<String> graph=setup2();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B", 5);



        //Act

        double weightBA = graph.adjacencyMap.get("B").get("A");


        assertEquals(5, weightBA);

    }





    //Test para verificar dijkstra
    @Test
    public void testDijkstra(){


        //Arrange
        Dijkstra<String> graph=setup2();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");

        //act
        graph.addEdge("A", "B", 5);
        graph.addEdge("A", "C", 2);
        graph.addEdge("B", "D", 4);
        graph.addEdge("C", "D", 1);
        graph.addEdge("C", "E", 6);
        graph.addEdge("D", "E", 3);

        Map<String, Double> distances = graph.dijkstra("A");

        //Asserts
        // Verificar las distancias más cortas desde el vértice "A" a los demás vértices
        assertEquals(0, (double) distances.get("A"));
        assertEquals(5, (double) distances.get("B"));
        assertEquals(2, (double) distances.get("C"));
        assertEquals(3, (double) distances.get("D"));
        assertEquals(6, (double) distances.get("E"));



    }


    @Test
    public void testDijkstra2() {

        //Arrange
        Dijkstra<String> graph=setup2();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");


        //act
        graph.addEdge("A", "B", 2);
        graph.addEdge("B", "C", 2);
        graph.addEdge("A", "c", 100000000);

        Map<String, Double> distances = graph.dijkstra("A");

        //Asserts
        // Verificar las distancias más cortas desde el vértice "A" a los demás vértices
        assertEquals(0, (double) distances.get("A"));
        assertEquals(2, (double) distances.get("B"));
        assertEquals(4, (double) distances.get("C"));





    }


    //Test para bfs
    @Test
    public void testBfs(){



        //Arrange
        Dijkstra<String> graph=setup2();


        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");

        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 1);
        graph.addEdge("B", "D", 1);
        graph.addEdge("C", "D", 1);
        graph.addEdge("C", "E", 1);
        graph.addEdge("D", "E", 1);

        //Act
        List<String> path = graph.bfs("A", "E");

        //Asserts
        // Verificar el camino desde "A" hasta "E"
        List<String> expectedPath = Arrays.asList("A", "C", "E");
        assertEquals(expectedPath, path);


    }


    @Test
    public void testBfs2(){



        //Arrange
        Dijkstra<String> graph=setup2();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        graph.addEdge("A","B",2);
        graph.addEdge("B","C",2);
        graph.addEdge("A","C",1000000000);

        List<String> path = graph.bfs("A", "C");


        List<String> expectedPath = Arrays.asList("A","C");
        assertEquals(expectedPath, path);


    }






    //Test para verificar el camino mas corto (teniendo en cuenta el peso) aqui se retorna un camino a seguir
    @Test
    public void testGetShortestPathDj_Double() {


        // Arrange
        Dijkstra<Double> graph = setup1();

        // Act
        graph.addVertex(1.0);
        graph.addVertex(2.0);
        graph.addVertex(3.0);
        graph.addVertex(4.0);
        graph.addVertex(5.0);

        graph.addEdge(1.0, 2.0, 10);
        graph.addEdge(1.0, 3.0, 5);
        graph.addEdge(2.0, 4.0, 3);
        graph.addEdge(3.0, 2.0, 2);
        graph.addEdge(3.0, 4.0, 9);
        graph.addEdge(3.0, 5.0, 2);
        graph.addEdge(4.0, 5.0, 7);

        List<Double> shortestPath = graph.getShortestPathDj(1.0, 5.0);

        // Assert
        List<Double> expectedPath = Arrays.asList(1.0, 3.0, 5.0);
        assertEquals(expectedPath, shortestPath);
    }



    @Test
    public void testGetShortestPathDj_Character() {
        // Arrange
        Dijkstra<Character> graph = setup3();

        // Act
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');

        graph.addEdge('A', 'B', 400);
        graph.addEdge('A', 'C', 200);
        graph.addEdge('B', 'C', 100);
        graph.addEdge('B', 'D', 500);
        graph.addEdge('C', 'D', 800);
        graph.addEdge('C', 'E', 1000);
        graph.addEdge('D', 'E', 200);

        List<Character> shortestPath = graph.getShortestPathDj('A', 'E');

        // Assert
        List<Character> expectedPath = Arrays.asList('A', 'C', 'D', 'E');
        assertTrue(shortestPath.containsAll(expectedPath));
    }




    //Test para remover
    @Test
    public void testRemove(){

        //Arrange
        boolean pass=false;

        Dijkstra<Double> graph= setup1();

        //Act
        graph.addVertex(4.0);



        if(graph.removeVertex(4.0)){
            pass=true;
        }

        assertTrue(pass);



    }



    @Test
    public void testIncorrectRemove(){

        //Arrange
        boolean pass=true;

        Dijkstra<Double> graph= setup1();

        //Act


        if(!graph.removeVertex(4.0)){
            pass=false;
        }

        assertFalse(pass);

    }








}
