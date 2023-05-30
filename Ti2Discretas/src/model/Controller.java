package model;

public class Controller {


    //1== airplane, 2== cars
    // para los aviones: cada hora recorren 900 km de distancia lo que  equivale a 2 toneladas de c02
    // para los carros: cada hora recorren 80 km de distancia, lo que equivale a 2 kg de c02
    public int calculateWeight(int distance, int option){

        int emision=0;


        if(option==1) {
            int distanceForhour = distance / 900;

            emision = distanceForhour * 2;
        }else if (option==2){
            int distanceForHour= distance/80;
            emision=distanceForHour*2;
        }

        return emision;

    }


}
