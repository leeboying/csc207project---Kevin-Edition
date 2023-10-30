package entity;

import java.util.ArrayList;

public class Move {
    private boolean isEnPassant;
    private boolean isCastle;
    private boolean isPromotion;
    private Piece piecePromotedTo;
    private boolean pieceCaptured;
    private ArrayList<Integer> pieceCaptureLocation;
    private Piece pieceMoving;
    private ArrayList<Integer> origin;
    private ArrayList<Integer> destination;

    Move(Piece pieceMoving, ArrayList<Integer> origin, ArrayList<Integer> destination) {
        this.pieceMoving = pieceMoving;
        this.origin = origin;
        this.destination = destination;

    }
    // getters and setters
    public void setIsEnPassant(){isEnPassant = true;}

    public void setIsCastle(){isCastle = true;}

    public void setIsPromotion(){isPromotion = true;}

    public void setPiecePromotedTo(Piece promotion) {piecePromotedTo = promotion;}

    public void setPieceCaptured(){pieceCaptured = true;}

    public void setPieceCaptureLocation(ArrayList<Integer> captureLocation){
        pieceCaptureLocation =  captureLocation;}

    public boolean getIsEnPassant() {return isEnPassant;}

    public boolean getIsCastle() {return isCastle;}

    public boolean getIsPromotion() {return isPromotion;}

    public Piece getPiecePromotedTo() {return piecePromotedTo;}

    public boolean getIsPieceCaptured() {return pieceCaptured;}

    public ArrayList<Integer> getPieceCaptureLocation() {return pieceCaptureLocation;}

    public Piece getPieceMoving() {return pieceMoving;}

    public ArrayList<Integer> getOrigin() {return origin;}

    public ArrayList<Integer> getDestination() {return destination;}
}