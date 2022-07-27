public class Main {
    public static ThreadGroup byuers = new ThreadGroup("Покупатели");
    private static final int COUNT_BUYERS = 3;

    public static void main(String[] args) {
        final CarShowroom carShowroom = new CarShowroom();

        for (int i = 1; i <= COUNT_BUYERS; i++) {
            new Thread(byuers, carShowroom::sellCar, "Покупатель " + i).start();
        }
        new Thread(null, carShowroom::produceCar, "Производитель").start();
    }
}
