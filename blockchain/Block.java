package blockchain;

public class Block {

    private String previousHash;
    private String hash;
    private int minerId;

    public Block(String previousHash, int minerId) {
        this.previousHash = previousHash;
        this.minerId = minerId;
        this.hash = calculateHash();
    }

    private String calculateHash() {
        // Простая симуляция хэширования
        // В реальном мире для хэширования используется криптографическая функция
        return previousHash + minerId;
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public int getMinerId() {
        return minerId;
    }
}
