package model;

public class Controller {


    public  double calculateWeight(double distance, int tipoVehiculo) {
        double factorPonderacion = 0.5; // Factor de ponderación para las emisiones (ajustable según tus necesidades)

        double weight = distance;

        if (tipoVehiculo == 1) { // Avión
            double emsionsAirplane = calculateEmisionsAirplane(distance);
            weight += emsionsAirplane * factorPonderacion;
        } else if (tipoVehiculo == 2) { // Carro
            double emisionesAuto = calculateEmisionsCar(distance);
            weight += emisionesAuto * factorPonderacion;
        }

        return weight;
    }




    public double calculateEmisionsAirplane(double distance) {
        // Supongamos que un avión emite aproximadamente 250 gramos de CO2 por cada kilómetro recorrido
        double emisionsforKm = 0.25; // 0.25 kg (250 gramos)

        return emisionsforKm * distance; // Emisiones totales en kg
    }

    public  double calculateEmisionsCar(double distance) {
        // Supongamos que un automóvil emite aproximadamente 120 gramos de CO2 por cada kilómetro recorrido
        double emisionsforKm = 0.12; // 0.12 kg (120 gramos)

        return emisionsforKm * distance; // Emisiones totales en kg
    }




}




