package sample;

import java.util.List;
import java.util.ArrayList;

public class Scientists {
    private List<Scientist> scientists;

    public Scientists() {
        scientists = new ArrayList<>();
    }

    public void add(Scientist s) {
        scientists.add(s);
    }
    public void remove(int i) {
        scientists.remove(i);
    }

    public void set(int i, Scientist s) {
        scientists.set(i, s);
    }

    public Scientist get(int i) {
        return scientists.get(i);
    }

    public int getNumCoins() {
        return scientists.size();
    }
}
