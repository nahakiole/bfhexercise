package ch.robinglauser.bfhexercise.tictactoe;

import org.junit.Test;

public class FieldTest {
    @Test
    public void hasWon() throws Exception, WrongMoveException {
        Field field = new Field();
        field.setField(Field.X, 0,0);
        field.setField(Field.X, 0,1);
        field.setField(Field.X, 0,2);
        field.setField(Field.O, 1,2);
        field.setField(Field.O, 2,2);
        System.out.println(field.hasWon(Field.X));
        System.out.println(field.hasWon(Field.O));
    }

}