import java.util.Random;

public class Conway {
    
    int colonnes = 10;
    int lignes = 5;
    int populationInitiale = 10;
    Random generateur = new Random();
    boolean[][] cellules = new boolean[colonnes][lignes];
    boolean debugVoisins = false;
    
    public Conway() {
        for (int i = 0; i < 10; i++) {
            int x = 0;
            int y = 0;
            do {
                x = generateur.nextInt(colonnes);
                y = generateur.nextInt(lignes);
            } while (cellules[x][y] == true);
            cellules[x][y] = true;
        }
    }
    
    public Conway(boolean test) {
        cellules[0][0] = true;
        cellules[7][0] = true;
        cellules[3][1] = true;
        cellules[5][1] = true;
        cellules[4][2] = true;
        cellules[5][2] = true;
        cellules[9][2] = true;
        cellules[3][3] = true;
        cellules[9][3] = true;
        cellules[4][4] = true;  
    }
    
    private boolean estInclus(int x, int y) {
        return x > -1 && x < colonnes && y > -1 && y < lignes;
    }
    
    private boolean vaSurvivre(int x, int y) {
        if (!cellules[x][y])
            return false;
        int nbv = getNbVoisins(x, y);
        if (nbv > 1 && nbv < 4)
            return true;
        else
            return false;
    }
    
    private boolean vaNaitre(int x, int y) {
        return cellules[x][y] == false && getNbVoisins(x, y) == 3;
    }
    
    public int getNbCellules() {
        int result = 0;
        for (int x = 0; x < colonnes; x++)
            for (int y = 0; y < lignes; y++)
                if (cellules[x][y])
                    result++;
        return result;
    }
    
    public void generationSuivante() {
        boolean[][] cellulesNext = new boolean[colonnes][lignes];
        for (int x = 0; x < colonnes; x++)
            for (int y = 0; y < lignes; y++)
                cellulesNext[x][y] = vaNaitre(x, y) || vaSurvivre(x, y);
        cellules = cellulesNext;
    }
    
    private int getNbVoisins(int x, int y) {
        int result = 0;
        for (int col = x - 1; col < x + 2; col++)
            for (int lig = y - 1; lig < y + 2; lig++)
                if (estInclus(col, lig) && (!(x == col && y == lig))
                        && cellules[col][lig])
                    result++;
        return result;
    }
    
    public String toString() {
        String result = "";
        for (int lig = 0; lig < lignes; lig++) {
            for (int col = 0; col < colonnes; col++) {
                if (cellules[col][lig])
                    result += "X";
                else
                    result += " ";
                if (debugVoisins)
                    result += getNbVoisins(col, lig);
                result += "|";
            }
            result += "\n";
        }
        return result;
    }
    
    public static void main(String[] args) {
        Conway c = new Conway();
        for (int i = 0; i < 10; i++) {
            System.out.println(c);
            c.generationSuivante();
        }
    }
}