package com.beginner.chesstest;

/**
 * Enum for chess pieces.
 * Each piece is assigned a certain number of points.
 * @author Oscar Hong
 */
public enum Piece {

    //Infinity because game is lost when king is taken.
    KING (Double.POSITIVE_INFINITY),
    QUEEN (9),
    ROOK (5),
    KNIGHT (3),
    BISHOP (3),
    PAWN (1);

    //Value of each piece based on common standards.
    public final double POINTS;

    /**
     * Assigns point value to pieces.
     * @param points Value of piece.
     */
    Piece(double points){
        this.POINTS = points;
    }
}
