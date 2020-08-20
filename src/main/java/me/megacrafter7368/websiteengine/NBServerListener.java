package me.megacrafter7368.websiteengine;

public class NBServerListener implements Runnable {

    private Thread thread;
    private boolean running = false;

    private ServerListener handler;

    public NBServerListener(int port) {
        this.handler = new ServerListener(port);
    }

    public synchronized void start() {
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        handler.start();
    }

}