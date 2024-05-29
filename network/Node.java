package network;

import java.util.Random;

import blockchain.Blockchain;
import blockchain.Coin;
import blockchain.Transaction;

public class Node {

    private int id;
    private double balance;
    private int computingPower;
    private static Blockchain blockchain = new Blockchain(); // Общий блокчейн

    public Node(int id, double balance, int computingPower) {
        this.id = id;
        this.balance = balance;
        this.computingPower = computingPower;
    }

    public void checkBlockchainIntegrity() {
        blockchain.checkIntegrity();
    }

    // Симуляция работы майнера
    public void startMining(double time, int intensity) {
        Random random = new Random();
        double coinsMined = 0; // Количество добытых монет
        double rewardMultiplier = blockchain.calculateRewardMultiplier(); // Мультипликатор награды
        double timeElapsed = 0;

        while (timeElapsed < time) {
            if (random.nextDouble() <= (intensity / 100.0) * (computingPower / 100.0)) {
                coinsMined += rewardMultiplier * Coin.BASE_REWARD;
            }
            timeElapsed += 1;
        }
        balance += coinsMined;
        System.out.println("Узел " + id + " добыл " + coinsMined + " монет.");
    }

    private static int getCoinsMined() {
        // Заглушка для получения количества добытых монет в сети
        // В реальном мире это бы получалось из блокчейна
        return 10000;
    }

    public void validateTransaction(Transaction transaction) {

        // Проверка баланса отправителя
        if (blockchain.getBalance(transaction.getSender()) < transaction.getAmount()) {
            System.out.println("Ошибка: У отправителя недостаточно средств.");
            return;
        }

        // Проверка подписи транзакции
        if (!transaction.verifySignature()) {
            System.out.println("Ошибка: Неверная подпись транзакции.");
            return;
        }

        blockchain.addTransaction(transaction);
        System.out.println("Транзакция подтверждена.");
    }

    public final double getBalance() {
        return this.balance;
    }

    public final int getId() {
        return this.id;
    }

    public final int getComputingPower() {
        return this.computingPower;
    }
    
}