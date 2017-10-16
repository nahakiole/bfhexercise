package ch.robinglauser.bfhexercise.tictactoe;

public class Field {

    public static final int FREE = 0;
    public static final int O = 10;
    public static final int X = 100;

    private int[][] field = new int[3][3];

    public void setField(int cell, int x, int y) throws WrongMoveException {
        if (field[x][y] == 0) {
            field[x][y] = cell;
        }
        else {
            throw new WrongMoveException();
        }
    }

    public boolean hasWon(int sign) {
        for (int i = 0; i < 3; i++) {
            if (field[0][i] + field[1][i] + field[2][i] == sign * 3 || field[i][0] + field[i][1] + field[i][2] == sign * 3) {
                return true;
            }
        }
        return field[0][0] + field[1][1] + field[2][2] == sign * 3 || field[0][2] + field[1][1] + field[2][0] == sign * 3;
    }

    public static int[] parseSet(String set) throws WrongMoveException {
        int[] point = new int[2];
        if (set == null){
            throw new WrongMoveException();
        }
        String[] parsed = (set.split(" "));
        if (parsed.length != 3){
            throw new WrongMoveException();
        }
        point[0] = Integer.parseInt(parsed[1]);
        point[1] = Integer.parseInt(parsed[2]);
        return point;
    }

    public void printField() {
        System.out.println("  0|1|2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i);
            System.out.print("|");
            for (int j = 0; j < 3; j++) {
                switch (field[i][j]) {
                    case Field.X:
                        System.out.print("X");
                        break;
                    case Field.O:
                        System.out.print("O");
                        break;
                    case Field.FREE:
                        System.out.print(" ");
                        break;
                }
                System.out.print("|");
            }
            System.out.print("\n");
        }
    }

    public boolean fieldFull(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }

}

