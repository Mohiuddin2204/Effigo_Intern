public class GetterandSetter {
    public static void main(String[] args) {
        Car2 car1 = new Car2("Red", 2020, 50000);
        Car2 car2 = new Car2(car1);

        car2.setColor("Blue");
        car2.setYear(2021);
        car2.setPrice(55000);

        System.out.println("Car 1: Color: " + car1.getColor() + ", Year: " + car1.getYear() + ", Price: " + car1.getPrice());
        System.out.println("Car 2: Color: " + car2.getColor() + ", Year: " + car2.getYear() + ", Price: " + car2.getPrice());
    }
}