public class Car2 {
    private String color;
    private int year;
    private double price;

    public Car2(String color, int year, double price) {
        this.color = color;
        this.year = year;
        this.price = price;
    }

    public Car2(Car2 other) {
        this.color = other.color;
        this.year = other.year;
        this.price = other.price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
