package sample;

import java.util.ArrayList;
import java.util.List;

public class CoinGroup {

    private List<Coin> coins;

    public CoinGroup() {
        coins = new ArrayList<>();
    }

    public void add(Coin coin) {
        coins.add(coin);
    }

    public void remove(int i) {
        coins.remove(i);
    }

    public void set(int i, Coin coin) {
        coins.set(i, coin);
    }

    public Coin get(int i) {
        return coins.get(i);
    }

    public int getNumCoins() {
        return coins.size();
    }


}
