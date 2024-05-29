package blockchain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private List<Block> chain;
    private int minedCoins;

    public Blockchain() {
        chain = new ArrayList<>();
        chain.add(new Block(null, 0)); // Genesis block
        minedCoins = 0;
    }

    public void addBlock(Block block) {
        chain.add(block);
        minedCoins += Coin.BASE_REWARD; // Увеличиваем количество добытых монет
    }

    public Block getLastBlock() {
        return chain.get(chain.size() - 1);
    }

    public double calculateRewardMultiplier() {
        // Процент монет, оставшихся в сети
        double remainingCoinsPercentage = (double) (Coin.TOTAL_COINS - minedCoins) / Coin.TOTAL_COINS; 
        // Используем логарифмическую функцию для снижения награды с уменьшением количества монет
        return Math.pow(remainingCoinsPercentage, 0.5); 
    }

    public void checkIntegrity() {
        for (int i = 1; i < chain.size(); i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);

            // проверка хэша
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                System.out.println("Ошибка целостности: Несоответствие хэша предыдущего блока.");
                return;
            }

            // провервка валидности блока
        }
        System.out.println("Цепочка блоков проверена. Все в порядке.");
    }

    public double getBalance(int nodeId) {
        // Простой способ подсчета баланса
        // В реальном мире баланс нужно хранить в транзакциях
        double balance = 0;
        for (Block block : chain) {
            if (block.getMinerId() == nodeId) {
                balance += Coin.BASE_REWARD * calculateRewardMultiplier();
            }
        }
        return balance;
    }

    public void addTransaction(Transaction transaction) {
        //добавление транзакции в блок
    }
}