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
                if (!wouldBeCheck(position, boardState, target)) {
                    validMoves.add(potentialMove);
                }

            } else if (square.equals("enemy")) {
                Move potentialMove = makeCapture(position, target);

                // check that king is not in check
                if (!wouldBeCheck(position, boardState, target)) {
                    validMoves.add(potentialMove);
                }
            }
        }

        // castling special case
        if (!hasMoved && !isInCheck(position, boardState)) { // king has not moved yet and is not in check
            int y = position.get(1);

            Piece potentialRightRook = boardState.get(coordinateBuilder(1, y));
            Piece potentialLeftRook = boardState.get(coordinateBuilder(8, y));

            if (potentialRightRook instanceof Rook && !potentialRightRook.hasMoved // rook at a1/a8 that has not moved
                    && boardState.get(coordinateBuilder(6, y)) == null // no pieces in between king and rook
                    && boardState.get(coordinateBuilder(7, y)) == null) {

                if (!wouldBeCheck(position, boardState, coordinateBuilder(6, y)) // does not castle through or into check
                        && !wouldBeCheck(position, boardState, coordinateBuilder(7, y))) {

                    Move castleRight = new Move(this, position, coordinateBuilder(7, y));
                    castleRight.setIsCastle();

                    validMoves.add(castleRight);
                }
            }

            if (potentialLeftRook instanceof Rook && !potentialLeftRook.hasMoved // rook at h1/h8 that has not moved
                    && boardState.get(coordinateBuilder(2, y)) == null // no pieces in between
                    && boardState.get(coordinateBuilder(3, y)) == null
                    && boardState.get(coordinateBuilder(4, y)) == null) {

                if (!wouldBeCheck(position, boardState, coordinateBuilder(3, y)) // does not castle through or into check
                        && !wouldBeCheck(position, boardState, coordinateBuilder(4, y))) {

                    Move castleLeft = new Move(this, position, coordinateBuilder(3, y));
                    castleLeft.setIsCastle();

                    validMoves.add(castleLeft);
                }
            }
        }
        return validMoves.toArray(new Move[0]);
    }

    private boolean wouldBeCheck(ArrayList<Integer> position, HashMap<ArrayList<Integer>, Piece> boardState,
                                ArrayList<Integer> newPos) {
        HashMap<ArrayList<Integer>, Piece> tempBoardState = new HashMap<>(boardState);

        tempBoardState.put(newPos, this);
        tempBoardState.put(position, null);

        return isInCheck(newPos, tempBoardState);
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
