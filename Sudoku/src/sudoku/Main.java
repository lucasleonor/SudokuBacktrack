package sudoku;

/**
 *
 * @author 31471730
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static Sudoku sudoku;

    public static void main(String[] args) {
        sudoku = new Sudoku();
        preenche();
        sudoku.print();
    }

    private static void preenche() {
        permanentes();
        if (!sudoku.isComplete()) {
            backtrack();
        }
    }

    public static void permanentes() {
        boolean mudou = false;
        int possivel = 0, subMatriz, lastSubMatriz = 0;
        for (int l = 0; l < sudoku.size(); l++) {
            for (int c = 0; c < sudoku.size(); c++) {
                    subMatriz = getSubMatriz(l, c);
                if (lastSubMatriz <subMatriz) {
                    boolean mudouSM = permanentesSubMatriz(subMatriz);
                    lastSubMatriz = subMatriz;
                    if (!mudou) {
                        mudou = mudouSM;
                    }
                }
                for (int n = 1; n < 10; n++) {
                    if (sudoku.get(l, c) == 0) {
                        if (!(sudoku.linhaContem(l, n) || sudoku.colunaContem(c, n))) {
                            if (!sudoku.subMatrizContem(subMatriz, n)) {
                                if (possivel == 0) {
                                    possivel = n;
                                } else {
                                    possivel = 0;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (possivel != 0) {
                    mudou = true;
                    sudoku.set(l, c, possivel);
                    possivel = 0;
                }
            }
        }
        if (mudou) {
            permanentes();
        }
    }

    private static boolean permanentesSubMatriz(int subMatriz) {
        int possivel = 0, col = 10, linhaInit, subMatrizAux = subMatriz;
        if (subMatrizAux < 4) {
            linhaInit = 0;
        } else if (subMatrizAux < 7) {
            linhaInit = 3;
            subMatrizAux -= 3;
        } else {
            linhaInit = 6;
            subMatrizAux -= 6;
        }
        int colInit = (subMatrizAux == 1) ? 0 : (subMatrizAux == 2) ? 3 : 6;
        boolean mudou = false;
        numero:
        for (int n = 1; n < 10; n++) {
            linha:
            for (int l = linhaInit; l < linhaInit + 3; l++) {
                for (int c = colInit; c < colInit + 3; c++) {
                    if (sudoku.subMatrizContem(subMatriz, n)) {
                        continue numero;
                    }
                    if (sudoku.linhaContem(l, n)) {
                        continue linha;
                    }
                    if (sudoku.colunaContem(c, n)) {
                        continue;
                    }
                    possivel++;
                    col = c;
                    if (possivel == 2) {
                        col = 10;
                        possivel = 0;
                        continue numero;
                    }
                }
                if (possivel == 1 && col < 10 && sudoku.get(l, col) == 0) {
                    mudou = true;
                    sudoku.set(l, col, n);
                }
                possivel = 0;
            }
        }
        sudoku.print();
        System.out.println("");
        return mudou;
    }

    private static void backtrack() {
        boolean achou = true;
        int i = 1;
        for (int l = 0; l < sudoku.size(); l++) {
            for (int c = 0; c < sudoku.size(); c++) {
                if (sudoku.get(l, c) == 0) {
                    if (!achou && sudoku.hasBackup()) {
                        int r[];
                        do {
                            r = sudoku.restoreBackup();
                            i = r[2] + 1;
                        } while (i > 9 && sudoku.hasBackup());
                        l = r[0];
                        c = r[1];
                    } else {
                        achou = false;
                    }
                    for (int n = i; n < 10; n++) {
                        if (!(sudoku.linhaContem(l, n) || sudoku.colunaContem(c, n) || sudoku.subMatrizContem(getSubMatriz(l, c), n))) {
                            achou = true;
                            sudoku.set(l, c, n);
                            sudoku.print();
                            System.out.println("");
                            break;
                        }
                    }
                    i = 1;
                }
            }
        }
    }

    private static int getSubMatriz(int l, int c) {
        int subMatriz;
        if (c < 3) {
            subMatriz = 1;
        } else if (c < 6) {
            subMatriz = 2;
        } else {
            subMatriz = 3;
        }
        if (l > 5) {
            subMatriz += 6;
        } else if (l > 2) {
            subMatriz += 3;
        }
        return subMatriz;
    }
}
