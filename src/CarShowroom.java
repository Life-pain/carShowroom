public class CarShowroom {
    final Manufacturer manufacturer = new Manufacturer(this);
    public int countSales = 0;

    public void incCountSales() {
        this.countSales++;
    }

    public int getCountSales() {
        return countSales;
    }

    public void sellCar() {
        manufacturer.sellCar();
    }

    public void produceCar() {
        while (countSales < manufacturer.getMAX_SALES_CAR())
            manufacturer.produceCar();
    }
}
