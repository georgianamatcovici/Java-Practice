package org.example;

public class TimeKeeper extends Thread {
    private final Game game;
    private final int timeLimitSeconds;

    public TimeKeeper(Game game, int timeLimitSeconds) {
        this.game = game;
        this.timeLimitSeconds = timeLimitSeconds;
        setDaemon(true);
    }
    public void run() {
        long startTime = System.currentTimeMillis();
        while (!game.isGameOver()) {
            long currentTime = System.currentTimeMillis();
            long diff = (currentTime - startTime)/1000;
            System.out.println(diff + " seconds.");
            if(diff>=timeLimitSeconds) {
                game.endGame();
                System.out.println("Game over!");
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }


        }

    }

}
