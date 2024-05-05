package de.gdhokamp.easy.environment;

public class Environment {
    private int[][] trail = new int[33][33];

    public Environment() {
        // set up the trail
        trail[0][1] = 1; trail[0][2] = 1; trail[0][3] = 1; trail[0][4] = 1; trail[0][5] = 1;
        trail[0][6] = 1; trail[0][7] = 1; trail[0][8] = 1; trail[0][9] = 1; trail[0][10] = 1;
        trail[1][10] = 1; trail[2][10] = 1; trail[3][10] = 1; trail[4][10] = 1;
        trail[5][0] = 1; trail[5][1] = 1; trail[5][2] = 1; trail[5][3] = 1; trail[5][4] = 1;
        trail[5][10] = 1; trail[5][25] = 1; trail[5][26] = 1; trail[5][27] = 1; trail[5][28] = 1;
        trail[5][29] = 1; trail[5][30] = 1; trail[5][31] = 1;
        trail[6][3] = 1; trail[6][10] = 1; trail[6][24] = 1;
        trail[7][3] = 1; trail[7][10] = 1; trail[7][24] = 1;
        trail[8][3] = 1; trail[8][10] = 1; trail[8][24] = 1;
        trail[9][3] = 1; trail[9][10] = 1; trail[9][14] = 1; trail[9][24] = 1;
        trail[10][3] = 1; trail[10][4] = 1; trail[10][5] = 1; trail[10][6] = 1;  trail[10][7] = 1;
        trail[10][8] = 1; trail[10][9] = 1; trail[10][10] = 1; trail[10][12] = 1;  trail[10][24] = 1;
        trail[11][19] = 1; trail[11][20] = 1; trail[11][21] = 1; trail[11][22] = 1; trail[11][23] = 1;
        trail[12][18] = 1;
        trail[13][11] = 1; trail[13][18] = 1;
        trail[14][14] = 1; trail[14][18] = 1;
        trail[15][18] = 1;
        trail[16][15] = 1; trail[16][18] = 1;
        trail[17][12] = 1; trail[17][18] = 1;
        trail[19][12] = 1; trail[19][18] = 1;
        trail[20][8] = 1; trail[20][18] = 1;
        trail[21][18] = 1;
        trail[22][18] = 1;
        trail[23][7] = 1; trail[23][18] = 1;
        trail[24][5] = 1; trail[24][18] = 1;
        trail[25][18] = 1;
        trail[26][4] = 1;
        trail[27][4] = 1;
        trail[28][4] = 1; trail[28][7] = 1; trail[28][8] = 1; trail[28][9] = 1; trail[28][10] = 1; trail[28][12] = 1;
        trail[28][13] = 1; trail[28][14] = 1; trail[28][15] = 1; trail[28][16] = 1; trail[28][17] = 1;
    }
    public boolean hasFood(AntPosition antPosition) {
        return trail[antPosition.getX()][antPosition.getY()] == 1;
    }

    public void collectFood(AntPosition antPosition) {
        trail[antPosition.getX()][antPosition.getY()] = 0;
    }
}
