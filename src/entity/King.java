package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class King extends Piece {
    public King(String color) {
        super(color);
    }

    @Override
    public Move[] getValidMoves(ArrayList<Integer> position, HashMap<ArrayList<Integer>, Piece> boardState, Move lastMove) {
        Set<Move> validMoves = new HashSet<>();

        int[] xDisplacements = {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] yDisplacements = {-1, 0, 1, 1, 1, 0, -1, -1};

        for (int i = 0; i < xDisplacements.length; i++) {
            int x = position.get(0) + xDisplacements[i];
            int y = position.get(1) + yDisplacements[i];

            ArrayList<Integer> target = coordinateBuilder(x, y);

            String square = checkSquare(x, y, boardState);

            if (square.equals("empty")) {
                Move potentialMove = makeMove(position, target);

                // check that king is not in check
                HashMap<ArrayList<Integer>, Piece> tempBoardState = new HashMap<>(boardState);

                tempBoardState.put(target, this);
                tempBoardState.put(position, null);

                if (!isInCheck(target, tempBoardState)) {
                    validMoves.add(potentialMove);
                }
            } else if (square.equals("enemy")) {
                Move potentialMove = makeCapture(position, target);

                // check that king is not in check
                HashMap<ArrayList<Integer>, Piece> tempBoardState = new HashMap<>(boardState);

                tempBoardState.put(target, this);
                tempBoardState.put(position, null);

                if (!isInCheck(target, tempBoardState)) {
                    validMoves.add(potentialMove);
                }
            }
        }

        // castling special case
        if (!hasMoved) { // king has not moved yet
            int y = position.get(1);
            Piece potentialRightRook = boardState.get(coordinateBuilder(1, y));
            Piece potentialLeftRook = boardState.get(coordinateBuilder(8, y));
            if (potentialRightRook instanceof Rook && !potentialRightRook.hasMoved // rook at a1/a8 that has not moved
                    && boardState.get(coordinateBuilder(2, y)) == null // no pieces in between king and rook
                    && boardState.get(coordinateBuilder(3, y)) == null) {

                HashMap<ArrayList<Integer>, Piece> tempBoardState = new HashMap<>(boardState);

                tempBoardState.put(coordinateBuilder(3, y), this);
                tempBoardState.put(position, null);


            }

            if (potentialRightRook instanceof Rook && !potentialRightRook.hasMoved // rook at a1/a8 that has not moved
                    && ) {

            }
        }

        return validMoves.toArray(new Move[0]);
    }


    public boolean isInCheck(ArrayList<Integer> position, HashMap<ArrayList<Integer>, Piece> boardState) {
        return false;
        // TODO: IMPLEMENT THIS
    }

    private Move makeCapture(ArrayList<Integer> position, ArrayList<Integer> destination) {
        Move capture = new Move(this, position, destination);
        capture.setPieceCaptured();

        return capture;
    }

    private Move makeMove(ArrayList<Integer> position, ArrayList<Integer> destination) {
        Move move = new Move(this, position, destination);

        return move;
    }

    private ArrayList<Integer> coordinateBuilder(int x, int y) {
        ArrayList<Integer> coordinate = new ArrayList<>(2);
        coordinate.add(x);
        coordinate.add(y);

        return coordinate;
    }
}
