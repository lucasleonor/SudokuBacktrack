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
    }

    private static void preenche() {
        sudoku.print();
        permanentes();
        if (!sudoku.isComplete()) {
            sudoku.print();
            System.out.println("\nBackTrack\n");
            backtrack();
        }
        sudoku.print();
    }

    public static void permanentes() {
        boolean mudou = false;
        int possivel = 0, subMatriz, lastSubMatriz = 0;
        for (int l = 0; l < 9; l++) {
            for (int c = 0; c < 9; c++) {
                subMatriz = getSubMatriz(l, c);
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
                    sudoku.setPermanente(l, c, possivel);
                    possivel = 0;
                } else if (lastSubMatriz < subMatriz) {
                    boolean mudouSM = permanentesSubMatriz(subMatriz);
                    lastSubMatriz = subMatriz;
                    mudou = mudouSM;
                }
            }
        }
        if (mudou) {
            permanentes();
        }
    }

    private static boolean permanentesSubMatriz(int subMatriz) {
        int possivel = 0, col = 10, lin = 10, linhaInit, subMatrizAux = subMatriz;
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
                    lin = l;
                    if (possivel == 2) {
                        col = 10;
                        lin = 10;
                        possivel = 0;
                        continue numero;
                    }
                }
            }
            if (possivel == 1 && lin < 10 && col < 10 && sudoku.get(lin, col) == 0) {
                mudou = true;
                sudoku.setPermanente(lin, col, n);
            }
            possivel = 0;
        }
        return mudou;
    }

    private static void backtrack() {
        boolean achou = false;
        int i = 1;
        for (int l = 0; l < 9; l++) {
            for (int c = 0; c < 9; c++) {
                if (sudoku.isNotOriginal(l, c)) {
                    for (int n = i; n < 10; n++) {
                        if (!(sudoku.linhaContem(l, n) || sudoku.colunaContem(c, n))) {
                            if (!sudoku.subMatrizContem(getSubMatriz(l, c), n)) {
                                achou = true;
                                sudoku.set(l, c, n);
                                sudoku.print();
                                break;
                            } else if (getSubMatriz(l, c) == 3 || getSubMatriz(l, c) == 6 || getSubMatriz(l, c) == 9) {
                                achou = false;
                                break;
                            }
                        }
                    }
                    if (!achou) {
                        do {
                            if (c == 0) {
                                c = 8;
                                if (l != 0) {
                                    l--;
                                }
                            } else {
                                c--;
                            }
                        } while (!sudoku.isNotOriginal(l, c) && c >= 0 && l >= 0);
                        i = sudoku.get(l, c) + 1;
                        sudoku.set(l, c, 0);
                        if (c == 0) {
                            c = 8;
                            if (l != 0) {
                                l--;
                            }
                        } else {
                            c--;
                        }
                    } else {
                        i = 1;
                        achou = false;
                    }
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
