package hw12.src.main.java.apps.ucu.edu.ua.ChangeMaking;

interface CoinHandler {
    void setNextHandler(CoinHandler handler);
    CoinHandler getNextHandler();
    void handle(int amount);
}

class ConcreteCoinHandler implements CoinHandler {
    private int denomination;
    private CoinHandler nextHandler;

    public ConcreteCoinHandler(int denomination) {
        this.denomination = denomination;
    }

    public void setNextHandler(CoinHandler handler) {
        this.nextHandler = handler;
    }

    public CoinHandler getNextHandler(){
        return this.nextHandler;
    }

    public void handle(int amount) {
        if (amount <= 0) {
            return;
        }

        if (amount >= denomination) {
            int numCoins = amount / denomination;
            int remainingAmount = amount % denomination;
            System.out.println("Dispensing " + numCoins + " coins of " + denomination);
            if (remainingAmount != 0 && nextHandler != null) {
                nextHandler.handle(remainingAmount);
            }
        } else if (nextHandler != null) {
            nextHandler.handle(amount);
        }
    }
}

class ATM {
    private CoinHandler chainStart;

    public void addHandler(CoinHandler handler) {
        if (chainStart == null) {
            chainStart = handler;
        } else {
            CoinHandler currentHandler = chainStart;
            while (currentHandler != null && currentHandler.getNextHandler() != null) {
                currentHandler = currentHandler.getNextHandler();
            }
            currentHandler.setNextHandler(handler);
        }
    }

    public void makeChange(int amount) {
        if (chainStart != null) {
            chainStart.handle(amount);
        } else {
            System.out.println("No handlers available in the chain.");
        }
    }
}

public class Change {
    public static void main(String[] args) {
        int[] denominations = {25, 10, 5, 1}; 
        ATM atm = new ATM();

        for (int denomination : denominations) {
            CoinHandler handler = new ConcreteCoinHandler(denomination);
            atm.addHandler(handler);
        }

        atm.makeChange(47);
    }
}
