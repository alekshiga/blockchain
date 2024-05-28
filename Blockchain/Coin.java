package Blockchain;
import java.util.Random;

public class CoinPrice {

    private static final double AVERAGE_PRICE = 7500;
    private static final int MAX_PRICE_CHANGE = 100; // Максимальное изменение цены за секунду
    private static final int MIN_GROWTH_DURATION = 3; // Минимальная длительность роста
    private static final int MAX_GROWTH_DURATION = 8; // Максимальная длительность роста
    private static final int MIN_FALL_DURATION = 2; // Минимальная длительность падения
    private static final int MAX_FALL_DURATION = 5; // Максимальная длительность падения

    private double currentPrice = AVERAGE_PRICE;
    private Random random = new Random();

    public void simulatePriceChange() {
        int growthDuration = random.nextInt(MAX_GROWTH_DURATION - MIN_GROWTH_DURATION + 1) + MIN_GROWTH_DURATION;
        int fallDuration = random.nextInt(MAX_FALL_DURATION - MIN_FALL_DURATION + 1) + MIN_FALL_DURATION;

        // Симулируем рост цены
        for (int i = 0; i < growthDuration; i++) {
            double priceChange = random.nextDouble() * MAX_PRICE_CHANGE;
            currentPrice += priceChange;
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Симулируем падение цены
        for (int i = 0; i < fallDuration; i++) {
            double priceChange = random.nextDouble() * MAX_PRICE_CHANGE;
            currentPrice -= priceChange;
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // коррекция цены по отклонению от среднего
        if (currentPrice > AVERAGE_PRICE) {
            currentPrice -= (currentPrice - AVERAGE_PRICE) * 0.5;
        } else {
            currentPrice += (AVERAGE_PRICE - currentPrice) * 0.5;
        }
    }

    public final double getCurrentPrice() {
        return this.currentPrice;
    }
}