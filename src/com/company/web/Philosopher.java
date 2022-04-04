package com.company.web;

public class Philosopher extends Thread {
    Fork left;
    Fork right;
    Table main;
    Integer numberOfPhilosopher;

    public Philosopher() {
    }

    public Philosopher(Fork left, Fork right, Table main, Integer numberOfPhilosopher) {
        this.left = left;
        this.right = right;
        this.main = main;
        this.numberOfPhilosopher = numberOfPhilosopher;
    }

    @Override
    public void run() {
        System.out.println("philosopher №" + numberOfPhilosopher + " starts");
        while (true) {
            try {
                Thread.sleep(100);
                Boolean leftBoo = main.getFork(left);
                Boolean rightBoo = main.getFork(right);
                if (leftBoo && rightBoo) {
                    System.out.println("nyam-nyam philosopher" + numberOfPhilosopher);
                    main.putForkIntoTable(left);
                    main.putForkIntoTable(right);
                } else {
                    if (leftBoo) {
                        main.putForkIntoTable(left);
                    }
                    if (rightBoo) {
                        main.putForkIntoTable(right);
                    }
                    System.out.println("Philosopher №" + numberOfPhilosopher + " can't take the forks right=" + right + "left=" + left + "he puts it");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
