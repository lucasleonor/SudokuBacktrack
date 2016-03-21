/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

/**
 *
 * @author 31471730
 */
public class Sudoku {

    private final int[][] sudokuOrig;
    private final int[][] sudokuResp;
//    private Stack<String> backup;

    public Sudoku() {
        int[][] origSudoku = {
            //            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            //            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            //            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            //            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            //            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            //            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            //            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            //            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            //            {0, 0, 0, 0, 0, 0, 0, 0, 0}};
            {8, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 3, 6, 0, 0, 0, 0, 0},
            {0, 7, 0, 0, 9, 0, 2, 0, 0},
            {0, 5, 0, 0, 0, 7, 0, 0, 0},
            {0, 0, 0, 0, 4, 5, 7, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 3, 0},
            {0, 0, 1, 0, 0, 0, 0, 6, 8},
            {0, 0, 8, 5, 0, 0, 0, 1, 0},
            {0, 9, 0, 0, 0, 0, 4, 0, 0}};
//            {5, 3, 0, 0, 7, 0, 0, 0, 0},
//            {6, 0, 0, 1, 9, 5, 0, 0, 0},
//            {0, 9, 8, 0, 0, 0, 0, 6, 0},
//            {8, 0, 0, 0, 6, 0, 0, 0, 3},
//            {4, 0, 0, 8, 0, 3, 0, 0, 1},
//            {7, 0, 0, 0, 2, 0, 0, 0, 6},
//            {0, 6, 0, 0, 0, 0, 2, 8, 0},
//            {0, 0, 0, 4, 1, 9, 0, 0, 5},
//            {0, 0, 0, 0, 8, 0, 0, 7, 9},};
        int[][] respSudoku = {
            //            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            //            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            //            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            //            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            //            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            //            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            //            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            //            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            //            {0, 0, 0, 0, 8, 0, 0, 7, 9},};
            //            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            //            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            //            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            //            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            //            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            //            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            //            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            //            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            //            {0, 0, 0, 0, 0, 0, 0, 0, 0}};
            {8, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 3, 6, 0, 0, 0, 0, 0},
            {0, 7, 0, 0, 9, 0, 2, 0, 0},
            {0, 5, 0, 0, 0, 7, 0, 0, 0},
            {0, 0, 0, 0, 4, 5, 7, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 3, 0},
            {0, 0, 1, 0, 0, 0, 0, 6, 8},
            {0, 0, 8, 5, 0, 0, 0, 1, 0},
            {0, 9, 0, 0, 0, 0, 4, 0, 0}};
        this.sudokuOrig = origSudoku;
        this.sudokuResp = respSudoku;
//        backup = new Stack<>();
    }

    public boolean linhaContem(int l, int n) {
        for (int i = 0; i < sudokuResp.length; i++) {
            if (sudokuResp[l][i] == n) {
                return true;
            }
        }
        return false;
    }

    public boolean colunaContem(int c, int n) {
        for (int i = 0; i < sudokuResp.length; i++) {
            if (sudokuResp[i][c] == n) {
                return true;
            }
        }
        return false;
    }

    public boolean subMatrizContem(int subMatriz, int n) {
        int linhaInit;
        if (subMatriz < 4) {
            linhaInit = 0;
        } else if (subMatriz < 7) {
            linhaInit = 3;
            subMatriz -= 3;
        } else {
            linhaInit = 6;
            subMatriz -= 6;
        }
        int colInit = (subMatriz == 1) ? 0 : (subMatriz == 2) ? 3 : 6;
        for (int l = linhaInit; l < linhaInit + 3; l++) {
            for (int c = colInit; c < colInit + 3; c++) {
                if (sudokuResp[l][c] == n) {
                    return true;
                }
            }
        }
        return false;
    }

    public void print() {
        for (int l = 0; l < sudokuResp.length; l++) {
            for (int c = 0; c < sudokuResp[l].length; c++) {
                System.out.print(sudokuResp[l][c] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public int size() {
        return sudokuResp.length;
    }

    public int get(int l, int c) {
        return sudokuResp[l][c];
    }

    public void set(int l, int c, int n) {
//        backup.push(l + "," + c + "," + n);
        if (sudokuOrig[l][c] == 0)
            sudokuResp[l][c] = n;
    }

    public void setPermanente(int l, int c, int n) {
//        backup.push(l + "," + c + "," + n);
        if (sudokuOrig[l][c] == 0) {
            sudokuOrig[l][c] = n;
            sudokuResp[l][c] = n;
        }
    }

    public boolean isComplete() {
        for (int l = 0; l < sudokuResp.length; l++) {
            for (int c = 0; c < sudokuResp[l].length; c++) {
                if (sudokuResp[l][c] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isNotOriginal(int l, int c) {
        return sudokuOrig[l][c] == 0;
    }

//    public int[] restoreBackup() {
//        String lastMove = backup.pop();
//        String[] pos = lastMove.split(",");
//        int l = Integer.parseInt(pos[0]), c = Integer.parseInt(pos[1]), n = Integer.parseInt(pos[2]);
//        sudokuResp[l][c] = 0;
//        int r[] = {l, c, n};
//        return r;
//    }
//
//    public boolean hasBackup() {
//        return !backup.empty();
//    }
}
