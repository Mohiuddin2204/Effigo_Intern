public class Object {
    public static void main(String[] args) {
        Car car1 = new Car("Red", 2020, 50000);
        Car car2 = new Car("Blue", 2019, 45000);
        Car car3 = new Car("Black", 2021, 60000);

        System.out.println("Car 1: Color: " + car1.color + ", Year: " + car1.year + ", Price: " + car1.price);
        System.out.println("Car 2: Color: " + car2.color + ", Year: " + car2.year + ", Price: " + car2.price);
        System.out.println("Car 3: Color: " + car3.color + ", Year: " + car3.year + ", Price: " + car3.price);
    }
}