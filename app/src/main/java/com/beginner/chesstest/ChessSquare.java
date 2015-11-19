package com.beginner.chesstest;

import java.util.ArrayList;
import java.util.List;

/**
 * A representation of a square on a chessboard.
 * Never use default constructor.
 * @author Oscar Hong
 */
public class ChessSquare {

    //Whether or not the square color is black, white otherwise.
    private final boolean SQUARECOLORBLACK;
    //Whether or not the piece on the square is black, white otherwise. If no piece defaults to squares color value.
    private boolean pieceColorBlack;
    //Whether or not the pieces first move has been made (defaults to false if no piece).
    private boolean firstMove;
    //Piece on board, null if no piece.
    private Piece piece;
    //Whether or not king is checked. KING ONLY!
    private boolean checked;
    //List of possible spaces that the current piece can move to.
    private List<int[]> moves;
    //X Y COORDINATES of square (0 based)
    private final int[] COORDINATES;

    /**
     * Constructor for empty square.
     * @param squareColor Whether or not square is black.
     */
    public ChessSquare(boolean squareColor, int x, int y){

        SQUARECOLORBLACK = squareColor;
        pieceColorBlack = SQUARECOLORBLACK;
        firstMove = false;
        checked = false;
        moves = new ArrayList<>();
        COORDINATES = new  int[2];
        COORDINATES[0] = x;
        COORDINATES[1] = y;

    }

    /**
     * Constructor for square with piece in it.
     * @param squareColor Whether or not square is black.
     * @param pieceColor Whether or not piece is black.
     * @param newPiece The piece on the square.
     */
    public ChessSquare(boolean squareColor, int x, int y, boolean pieceColor, Piece newPiece){

        SQUARECOLORBLACK = squareColor;
        pieceColorBlack = pieceColor;
        firstMove = true;
        piece = newPiece;
        checked = false;
        moves = new ArrayList<>();
        COORDINATES = new  int[2];
        COORDINATES[0] = x;
        COORDINATES[1] = y;

    }

    /**
     * Accessor for boolean value indicating whether or not the square is black.
     * @return Whether or not the square is black.
     */
    public boolean getSquareColorBlack(){
        return SQUARECOLORBLACK;
    }

    /**
     * Accessor for boolean value indicating whether or not the piece is black.
     * @return Whether or not piece is black.
     */
    public boolean getPieceColorBlack() {
        return pieceColorBlack;
    }

    /**
     * Accessor for boolean value indicating whether or not the piece on the square is on its first move.
     * @return Whether or not the piece on the square is on its first move.
     */
    public boolean getFirstMove() {
        return firstMove;
    }

    /**
     * Accessor for the piece on the square.
     * @return The piece on the square.
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Accessor for boolean value indicating whether or not the piece on the square is checked.
     * KING ONLY!
     * @return Whether or not the piece is checked.
     */
    public boolean getChecked() {
        return checked;
    }

    /**
     * Accessor for list of possible moves.
     * Allows for alterations
     * @return List of possible moves.
     */
    public List<int[]> getMoves() {
        return moves;
    }

    /**
     * Accessor for COORDINATES.
     * @return Array containing COORDINATES.
     */
    public int[] getCoordinates() {
        return COORDINATES;
    }

    /**
     * Mutator for the boolean value indicating whether or not the piece on the square is black.
     * @param color New boolean value indicating whether not the piece is black.
     * @return New boolean value.
     */
    public boolean setPieceColorBlack(boolean color) {
        pieceColorBlack = color;
        return pieceColorBlack;
    }

    /**
     * Sets boolean value indicating whether or not the piece on the square is on its first move.
     * Cannot be reactivated.
     * @return False value indicating that the piece on the square is no longer on its first move.
     */
    public boolean deactivateFirstMove() {
        firstMove = false;
        return firstMove;
    }

    /**
     * Mutator for the piece on the square.
     * @param newPiece The new piece on the square.
     * @return The new piece placed on the square.
     */
    public Piece setPiece(Piece newPiece) {
        piece = newPiece;
        return piece;
    }

    /**
     * Mutator for boolean value indicating whether or not the piece on the square is checked.
     * KING ONLY!
     * @param isChecked Boolean value indicating the new checked state of the piece.
     * @return The new checked state of the piece.
     */
    public boolean setChecked(boolean isChecked) {
        checked = isChecked;
        return checked;
    }

    /**
     * Sets a new set of moves.
     * @param newMoves The new list of moves.
     * @return The new list.
     */
    public List<int[]> setMoves(List<int[]> newMoves) {
        moves = newMoves;
        return moves;
    }
}
