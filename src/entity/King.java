package entity;

import java.util.ArrayList;
import java.util.HashMap;

public class King extends Piece {
    public King(String color) {
        super(color);
    }

    @Override
    public Move[] getValidMoves(ArrayList<Integer> position, HashMap<ArrayList<Integer>, Piece> boardState, Move lastMove) {
        return new Move[0];
    }

    public boolean isInCheck(ArrayList<Integer> position, HashMap<ArrayList<Integer>, Piece> boardState) {
        return false;
        // TODO: IMPLEMENT THIS
    }
}
