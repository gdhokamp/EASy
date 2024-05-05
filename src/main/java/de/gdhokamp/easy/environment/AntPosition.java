package de.gdhokamp.easy.environment;

import de.gdhokamp.easy.organism.phenotype.Perspective;

public class AntPosition {

    private int x = 0;
    private int y = 0;

    private int maxX = 31;
    private int maxY = 31;
    private int minX = 0;
    private int minY = 0;


    public AntPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public AntPosition add(Perspective perspective) {
        AntPosition antPosition = new AntPosition(this.x, this.y);
        if (perspective.equals(Perspective.NORTH)) {
            if (antPosition.getY() > minY ) {
                antPosition.setY(antPosition.getY() - 1);
            } else {
                antPosition.setY(maxY);
            }
        }
        if (perspective.equals(Perspective.SOUTH)) {
            if (antPosition.getY() < maxY ) {
                antPosition.setY(antPosition.getY() + 1);
            } else {
                antPosition.setY(minY);
            }
        }
        if (perspective.equals(Perspective.EAST)) {
            if (antPosition.getX() < maxX ) {
                antPosition.setX(antPosition.getX() + 1);
            } else {
                antPosition.setX(minX);
            }
        }
        if (perspective.equals(Perspective.WEST)) {
            if (antPosition.getX() > minX ) {
                antPosition.setX(antPosition.getX() - 1);
            } else {
                antPosition.setX(maxX);
            }
        }
        return antPosition;
    }

}
