import java.util.ArrayDeque;

public class Manufacturer {
    private CarShowroom carShowroom;
    private ArrayDeque<Car> carList = new ArrayDeque<>();
    private final int TIME_PRODUCE_CAR = 3000;
    private final int DELAY_SALES_CAR = 1000;
    private final int MAX_SALES_CAR = 10;

    public Manufacturer(CarShowroom carShowroom) {
        this.carShowroom = carShowroom;
    }

    public synchronized void produceCar() {
        try {
            Thread.sleep(TIME_PRODUCE_CAR);
            carList.add(new Car("Toyota"));
            System.out.println(Thread.currentThread().getName() + " Toyota выпустил 1 автомобиль");
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sellCar() {
        try {
            System.out.printf("Покупатель %s зашел в автосалон\n", Thread.currentThread().getName());
            Thread.sleep(DELAY_SALES_CAR);
            while (carList.isEmpty()) {
                System.out.println("Машин нет для " + Thread.currentThread().getName());
                wait();
            }
            System.out.println(Thread.currentThread().getName() + " уехал на новом авто " + carList.poll().getModel());
            carShowroom.incCountSales();
            if (carShowroom.getCountSales() < MAX_SALES_CAR) sellCar();
            else {
                System.out.printf("Продано %d автомобилей!\n", carShowroom.getCountSales());
                Main.byuers.interrupt();
            }
        } catch (InterruptedException e) {
        }
    }

    public int getMAX_SALES_CAR() {
        return MAX_SALES_CAR;
    }
}
