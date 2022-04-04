package com.company.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Table {
    private static Integer NUM_PHILOSOPHERS = 2;
    Map<Fork, Boolean> forks = new TreeMap<>();
    //fixme make singleton
    List<Philosopher> philosophers = new ArrayList<>();
    Lock lock = new ReentrantLock();

    //input philosophers into list, put forks on the table
    {
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks.put(new Fork(i + 1), false);
        }
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            if (i != NUM_PHILOSOPHERS - 1) {
                philosophers.add(new Philosopher(getForkNum(i + 1), getForkNum(i + 2), this, i + 1));
            } else {
                philosophers.add(new Philosopher(getForkNum(i + 1), getForkNum(1), this, i + 1));
            }
        }
    }

    public Table() {
    }

    public Boolean getFork(Fork fork) {
        synchronized (lock) {
            if (forks.containsKey(fork)) {
                if (forks.get(fork)) {
                    return false;
                }
                forks.put(fork, true);
                return true;
            }
            throw new IllegalArgumentException("Map forks doen't contain fork num=" + fork.getNum());
        }
    }

    public void putForkIntoTable(Fork fork) {
        synchronized (lock) {
            if (forks.containsKey(fork)) {
                if (forks.get(fork)) {
                    forks.put(fork, false);
                } else {
                    throw new IllegalStateException("fork must be taken, but it is not taken");
                }
            } else {
                throw new IllegalArgumentException("Map forks doen't contain fork num=" + fork);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Table table = new Table();
        for (Philosopher p : table.philosophers) {
            p.start();
        }
    }

    private Fork getForkNum(Integer num) {
        for (Map.Entry<Fork, Boolean> forkEntry : forks.entrySet()) {
            if (forkEntry.getKey().getNum().equals(num)) {
                return forkEntry.getKey();
            }
        }
        throw new IllegalArgumentException("This fork does not exist");
    }
}
