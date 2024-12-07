package main.java.com.adventofcode.snake;

import java.awt.*;
import java.util.Random;

public class Cibo {
    private int x, y;
    private final int SIZE = 10; // Dimensione del cibo
    private final int GRID_SIZE = 40; // Numero di celle per la griglia (puoi cambiarlo a seconda della finestra)

    public Cibo(int maxWidth, int maxHeight) {
        // Genera una posizione casuale per il cibo dentro i limiti della finestra
        Random rand = new Random();
        this.x = rand.nextInt(maxWidth / SIZE) * SIZE; // Calcola posizione x
        this.y = rand.nextInt(maxHeight / SIZE) * SIZE; // Calcola posizione y
    }

    public void reset(int maxWidth, int maxHeight) {
        // Rende il cibo in una nuova posizione casuale
        Random rand = new Random();
        this.x = rand.nextInt(maxWidth / SIZE) * SIZE;
        this.y = rand.nextInt(maxHeight / SIZE) * SIZE;
    }

    public void draw(Graphics g) {
        // Disegna il cibo come un quadrato di colore verde
        g.setColor(Color.GREEN);
        g.fillRect(x, y, SIZE, SIZE);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, SIZE, SIZE);
    }
}
