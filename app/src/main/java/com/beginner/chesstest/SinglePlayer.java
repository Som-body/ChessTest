package com.beginner.chesstest;

import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;


public class SinglePlayer extends AppCompatActivity {

    //Array of arrays of ChessSquares representing board.
    final private ChessSquare[][] board = new ChessSquare[8][8];
    //Indicates whether or not it's currently Blacks turn.
    private boolean blackTurn;
    //List of white pieces. King is first element for efficiency reasons.
    final private LinkedList<ChessSquare> whitePieces = new LinkedList<>();
    //List of black pieces. King is first element for efficiency reasons.
    final private LinkedList<ChessSquare> blackPieces = new LinkedList<>();
    //Currently selected ChessSquare
    private ChessSquare selected;
    //ImageView of selected square
    private ImageView selectedImage;
    //Represents the possible moves that a knight can make. An element in the first subarray is paired with an element in the second
    private final int[][] KNIGHTMOVEMENT = {{1, 2}, {-1, 2}, {1, -2}, {-1, -2}, {2, 1}, {-2, 1}, {2, -1}, {-2, -1}};
    //Represents the possible moves that a bishop can make. Multiply a subarray to move further.
    private final int[][] BISHOPMOVEMENT = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    //Represents the possible moves that a rook can make. Multiply a subarray to move further.
    private final int[][] ROOKMOVEMENT = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
    //Represents the possible moves that a king or queen can make. Multiply a subarray to move further. King can only move one space.
    private final int[][] ROYALMOVEMENT = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}, {1, 0}, {0, 1}, {0, -1}, {-1, 0}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);

        blackTurn = false;
        populateBoard();

    }

    /**
     * Determines whether or not square is black based on whether sum of coordinates is even.
     * @param x X coordinate
     * @param y Y coordinate
     * @return Boolean value indicating whether or not square is black.
     */
    private boolean isBlack(int x, int y){

        return (((x + y) & 1) == 0);

    }

    /**
     * Populates chess board (array representation) with ChessSquares
     */
    private void populateBoard() {

        //Populate first row with white pieces.
        board[0][0] = new ChessSquare(true, 0, 0, false, Piece.ROOK);
        whitePieces.addLast(board[0][0]);
        board[1][0] = new ChessSquare(false, 1, 0, false, Piece.KNIGHT);
        whitePieces.addLast(board[1][0]);
        board[2][0] = new ChessSquare(true, 2, 0, false, Piece.BISHOP);
        whitePieces.addLast(board[2][0]);
        board[3][0] = new ChessSquare(false, 3, 0, false, Piece.QUEEN);
        whitePieces.addLast(board[3][0]);
        board[4][0] = new ChessSquare(true, 4, 0, false, Piece.KING);
        whitePieces.addFirst(board[4][0]);
        board[5][0] = new ChessSquare(false, 5, 0, false, Piece.BISHOP);
        whitePieces.addLast(board[5][0]);
        board[6][0] = new ChessSquare(true, 6, 0, false, Piece.KNIGHT);
        whitePieces.addLast(board[6][0]);
        board[7][0] = new ChessSquare(false, 7, 0, false, Piece.ROOK);
        whitePieces.addLast(board[7][0]);

        //Populate second row with white pawns.
        for(int i = 0; i < board.length; i++) {

            ChessSquare pawn = new ChessSquare(isBlack(i, 1), i, 1, false, Piece.PAWN);
            board[i][1] = pawn;
            whitePieces.addLast(board[i][1]);

        }

        //Populate middle squares.
        for(int i = 0; i < board.length; i++) {

            for(int i2= 2; i2 < 6; i2++) { //Rows 3 to 6 are empty at start of game.

                ChessSquare emptySquare = new ChessSquare(isBlack(i, i2), i, i2);
                board[i][i2] = emptySquare;

            }

        }

        //Populate seventh row with black pawns.
        for(int i = 0; i < board.length; i++) {

            ChessSquare pawn = new ChessSquare(isBlack(i, 6), i, 6, true, Piece.PAWN);
            board[i][6] = pawn;
            blackPieces.addLast(board[i][6]);

        }

        //Populate last row with black pieces.
        board[0][7] = new ChessSquare(false, 0, 7, true, Piece.ROOK);
        blackPieces.addLast(board[0][7]);
        board[1][7] = new ChessSquare(true, 1, 7, true, Piece.KNIGHT);
        blackPieces.addLast(board[1][7]);
        board[2][7] = new ChessSquare(false, 2, 7, true, Piece.BISHOP);
        blackPieces.addLast(board[2][7]);
        board[3][7] = new ChessSquare(true, 3, 7, true, Piece.QUEEN);
        blackPieces.addLast(board[3][7]);
        board[4][7] = new ChessSquare(false, 4, 7, true, Piece.KING);
        blackPieces.addFirst(board[4][7]);
        board[5][7] = new ChessSquare(true, 5, 7, true, Piece.BISHOP);
        blackPieces.addLast(board[5][7]);
        board[6][7] = new ChessSquare(false, 6, 7, true, Piece.KNIGHT);
        blackPieces.addLast(board[6][7]);
        board[7][7] = new ChessSquare(true, 7, 7, true, Piece.ROOK);
        blackPieces.addLast(board[7][7]);

    }

    /**
     * Retrieves the ChessSquare associated with the id.
     * @param id The id of the image clicked.
     * @return The ChessSquare associated with the image clicked.
     */
    private ChessSquare findSquare(int id) {

        switch(id) {

            case R.id.tile0_0: return board[0][0];
            case R.id.tile0_1: return board[0][1];
            case R.id.tile0_2: return board[0][2];
            case R.id.tile0_3: return board[0][3];
            case R.id.tile0_4: return board[0][4];
            case R.id.tile0_5: return board[0][5];
            case R.id.tile0_6: return board[0][6];
            case R.id.tile0_7: return board[0][7];
            case R.id.tile1_0: return board[1][0];
            case R.id.tile1_1: return board[1][1];
            case R.id.tile1_2: return board[1][2];
            case R.id.tile1_3: return board[1][3];
            case R.id.tile1_4: return board[1][4];
            case R.id.tile1_5: return board[1][5];
            case R.id.tile1_6: return board[1][6];
            case R.id.tile1_7: return board[1][7];
            case R.id.tile2_0: return board[2][0];
            case R.id.tile2_1: return board[2][1];
            case R.id.tile2_2: return board[2][2];
            case R.id.tile2_3: return board[2][3];
            case R.id.tile2_4: return board[2][4];
            case R.id.tile2_5: return board[2][5];
            case R.id.tile2_6: return board[2][6];
            case R.id.tile2_7: return board[2][7];
            case R.id.tile3_0: return board[3][0];
            case R.id.tile3_1: return board[3][1];
            case R.id.tile3_2: return board[3][2];
            case R.id.tile3_3: return board[3][3];
            case R.id.tile3_4: return board[3][4];
            case R.id.tile3_5: return board[3][5];
            case R.id.tile3_6: return board[3][6];
            case R.id.tile3_7: return board[3][7];
            case R.id.tile4_0: return board[4][0];
            case R.id.tile4_1: return board[4][1];
            case R.id.tile4_2: return board[4][2];
            case R.id.tile4_3: return board[4][3];
            case R.id.tile4_4: return board[4][4];
            case R.id.tile4_5: return board[4][5];
            case R.id.tile4_6: return board[4][6];
            case R.id.tile4_7: return board[4][7];
            case R.id.tile5_0: return board[5][0];
            case R.id.tile5_1: return board[5][1];
            case R.id.tile5_2: return board[5][2];
            case R.id.tile5_3: return board[5][3];
            case R.id.tile5_4: return board[5][4];
            case R.id.tile5_5: return board[5][5];
            case R.id.tile5_6: return board[5][6];
            case R.id.tile5_7: return board[5][7];
            case R.id.tile6_0: return board[6][0];
            case R.id.tile6_1: return board[6][1];
            case R.id.tile6_2: return board[6][2];
            case R.id.tile6_3: return board[6][3];
            case R.id.tile6_4: return board[6][4];
            case R.id.tile6_5: return board[6][5];
            case R.id.tile6_6: return board[6][6];
            case R.id.tile6_7: return board[6][7];
            case R.id.tile7_0: return board[7][0];
            case R.id.tile7_1: return board[7][1];
            case R.id.tile7_2: return board[7][2];
            case R.id.tile7_3: return board[7][3];
            case R.id.tile7_4: return board[7][4];
            case R.id.tile7_5: return board[7][5];
            case R.id.tile7_6: return board[7][6];
            case R.id.tile7_7: return board[7][7];
            default: return board[0][0];

        }

    }

    /**
     * Calculates moveset for selected square.
     * Stores moves in ChessSquares moves variable if any are found.
     * @param cs The ChessSquare whose moveset is to be calculated.
     */
    private void calculateMoves(ChessSquare cs) {
        if(cs.getPiece() == null) {
            return;
        }

        switch(cs.getPiece()) {
            case PAWN: calculatePawnMoves(cs);
                break;

            case KNIGHT: calculateKnightMoves(cs);
                break;

            case BISHOP: calculateBishopMoves(cs);
                break;

            case ROOK: calculateRookMoves(cs);
                break;

            case QUEEN: calculateQueenMoves(cs);
                break;

            case KING: calculateKingMoves(cs);
                break;
        }
    }

    /**
     * Simple method to determine if x y coordinates are legal on a 8 by 8 zero based board.
     * @param x X coordinate of new position.
     * @param y Y coordinate of new position.
     * @return Boolean value indicating whether or not position is legal.
     */
    private boolean isLegalPosition(int x, int y) {

        return ((x >= 0) && (x < 8) && (y >= 0) && (y < 8));

    }

    /**
     * Simple method to determine if placing current piece on square is legal assuming the square is within its movement range.
     * @param x X coordinate of new position.
     * @param y Y coordinate of new position.
     * @param isBlack Boolean value indicating whether or not the selected piece is black.
     * @return Boolean value indicating whether or not the move is permissible assuming the square is within the pieces movement range.
     */
    private boolean isLegalMove(int x, int y, boolean isBlack) {

        return (isLegalPosition(x, y) && ((board[x][y].getPiece() == null) || (board[x][y].getPieceColorBlack() != isBlack)));

    }

    /**
     * Calculates possible movements for a piece. Can be used for pieces that can move infinitely in one direction on a single turn.
     * @param movement The set of movements allowed for the piece assuming the piece can only move a single space.
     * @param x The x coordinate of the piece whose movements will be calculated.
     * @param y The y coordinate of the piece whose movements will be calculated.
     * @param isBlack Whether or not the piece is black.
     * @param canContinue Whether or not the piece can move more than one space. False for king.
     * @return An array list of the possible moves that a piece can make.
     */
    private ArrayList<int[]> calMultiMoves(int[][] movement,int x, int y, boolean isBlack, boolean canContinue){

        int[] move;
        int newX;
        int newY;
        boolean stopped;
        int multiplier = 0;
        final ArrayList<int[]> moves = new ArrayList<>();

        for(int i = 0; i < movement.length; i++){

            stopped = !canContinue;

            do {

                multiplier++;
                newX = x + (movement[i][0] * multiplier);
                newY = y + (movement[i][1] * multiplier);

                if(isLegalMove(newX, newY, isBlack)){

                    move = new int[2];
                    move[0] = newX;
                    move[1] = newY;
                    moves.add(move);

                    if(board[newX][newY].getPiece() != null){

                        stopped = true;

                    }


                } else {

                    stopped = true;

                }

            }while(!stopped);

            multiplier = 0;

        }

        return moves;

    }

    /**
     * Calculates possible moves that can be taken by the current pawn.
     * En passant move can only be added when another pawn moves //TODO
     * Promotion is a special case represented by {8, 8}
     * @param cs The square containing a pawn whose possible moves will be calculated.
     */
    private void calculatePawnMoves(ChessSquare cs) {

        final int x = cs.getCoordinates()[0];
        final int y = cs.getCoordinates()[1];
        final int movement;
        final ArrayList<int[]> moves = new ArrayList<>();
        int[] move;

        if(cs.getPieceColorBlack()) {
            movement = -1;
        } else {
            movement = 1;
        }

        //Promotion represented by coordinates 8, 8
        if(cs.getPieceColorBlack() && y == 0){

            move = new int[2];
            move[0] = 8;
            move[1] = 8;
            moves.add(move);

        }
        else if(cs.getPieceColorBlack() && y == 7){

            move = new int[2];
            move[0] = 8;
            move[1] = 8;
            moves.add(move);

        } else {

            //Space directly in front.
            if (board[x][(y + movement)].getPiece() == null) {

                move = new int[2];
                move[0] = x;
                move[1] = (y + movement);
                moves.add(move);

                //Two spaces forward on first move.
                if (cs.getFirstMove() && board[x][(y + (movement * 2))].getPiece() == null) {

                    move = new int[2];
                    move[0] = x;
                    move[1] = (y + (movement * 2));
                    moves.add(move);

                }
            }

            //Can take piece to the (whites) right. Checks if that area is off board first
            if (isLegalPosition((x + 1), (y + movement)) && board[(x + 1)][y + movement].getPiece() != null && board[(x + 1)][y + movement].getPieceColorBlack() != cs.getPieceColorBlack()) {

                move = new int[2];
                move[0] = (x + 1);
                move[1] = (y + movement);
                moves.add(move);

            }

            //Can take piece to the (whites) left. Checks if that area is off board first
            if (isLegalPosition((x - 1), (y + movement)) && board[(x - 1)][y + movement].getPiece() != null && board[(x - 1)][y + movement].getPieceColorBlack() != cs.getPieceColorBlack()) {

                move = new int[2];
                move[0] = (x - 1);
                move[1] = (y + movement);
                moves.add(move);

            }

        }

        cs.setMoves(moves);

    }

    /**
     * Calculates the possible moves that can be taken by the current knight.
     * @param cs The square containing the knight whose moves will be calculated.
     */
    private void calculateKnightMoves(ChessSquare cs) {

        cs.setMoves(calMultiMoves(KNIGHTMOVEMENT, cs.getCoordinates()[0], cs.getCoordinates()[1], cs.getPieceColorBlack(), false));

    }

    /**
     * Calculates the possible movements that the specified bishop can make.
     * @param cs The square containing the bishop.
     */
    private void calculateBishopMoves(ChessSquare cs) {

        cs.setMoves(calMultiMoves(BISHOPMOVEMENT, cs.getCoordinates()[0], cs.getCoordinates()[1], cs.getPieceColorBlack(), true));

    }

    /**
     * Calculates the moves that the specified rook can make.
     * @param cs The square containing the rook.
     */
    private void calculateRookMoves(ChessSquare cs) {

        cs.setMoves(calMultiMoves(ROOKMOVEMENT, cs.getCoordinates()[0], cs.getCoordinates()[1], cs.getPieceColorBlack(), true));

    }

    /**
     * Calculates the moves that the specified queen can make.
     * @param cs The square containing the queen.
     */
    private void calculateQueenMoves(ChessSquare cs) {

        cs.setMoves(calMultiMoves(ROYALMOVEMENT, cs.getCoordinates()[0], cs.getCoordinates()[1], cs.getPieceColorBlack(), true));

    }

    /**
     * Calculates the moves that the specified king can make.
     * @param cs The square containing the king.
     */
    private void calculateKingMoves(ChessSquare cs) {

        cs.setMoves(calMultiMoves(ROYALMOVEMENT, cs.getCoordinates()[0], cs.getCoordinates()[1], cs.getPieceColorBlack(), false));

    }

    /**
     * Determines whether a set of coordinates resides in a list of coordinates.
     * @param coordinates The coordinates of the space the selected piece is going to move to.
     * @param moveList The list of valid moves that the piece can make.
     * @return Whether or not the piece can legally move to that space.
     */
    private boolean inMoveList(int[] coordinates, ArrayList<int[]> moveList){

        int x = coordinates[0];
        int y = coordinates[1];

        for(int i = 0; i < moveList.size(); i++){

            if(moveList.get(i)[0] == x && moveList.get(i)[1] == y){

                return true;

            }

        }
        return false;

    }

    /**
     * Gives the ImageView of a ChessSquare a new image based on the specified parameters.
     * @param imageView The ImageView whose source image will be updated.
     * @param isBlack Whether or not the new piece will be black. Does not matter if square is empty.
     * @param squareIsBlack Whether or not the square is black.
     * @param piece The piece on the square.
     */
    private void setSquareImage(ImageView imageView, boolean isBlack, boolean squareIsBlack, Piece piece){

        if(piece == null){

            if(squareIsBlack){

                imageView.setImageResource(R.drawable.empty_black);

            } else{

                imageView.setImageResource(R.drawable.empty_white);

            }

        } else {

            if (isBlack) {

                if (squareIsBlack) {

                    switch (piece) {

                        case PAWN:
                            imageView.setImageResource(R.drawable.black_on_black_pawn);
                            break;
                        case KNIGHT:
                            imageView.setImageResource(R.drawable.black_on_black_knight);
                            break;
                        case BISHOP:
                            imageView.setImageResource(R.drawable.black_on_black_bishop);
                            break;
                        case ROOK:
                            imageView.setImageResource(R.drawable.black_on_black_rook);
                            break;
                        case QUEEN:
                            imageView.setImageResource(R.drawable.black_on_black_queen);
                            break;
                        case KING:
                            imageView.setImageResource(R.drawable.black_on_black_king);
                            break;

                    }

                } else {

                    switch (piece) {

                        case PAWN:
                            imageView.setImageResource(R.drawable.black_on_white_pawn);
                            break;
                        case KNIGHT:
                            imageView.setImageResource(R.drawable.black_on_white_knight);
                            break;
                        case BISHOP:
                            imageView.setImageResource(R.drawable.black_on_white_bishop);
                            break;
                        case ROOK:
                            imageView.setImageResource(R.drawable.black_on_white_rook);
                            break;
                        case QUEEN:
                            imageView.setImageResource(R.drawable.black_on_white_queen);
                            break;
                        case KING:
                            imageView.setImageResource(R.drawable.black_on_white_king);
                            break;

                    }

                }

            } else {

                if (squareIsBlack) {

                    switch (piece) {

                        case PAWN:
                            imageView.setImageResource(R.drawable.white_on_black_pawn);
                            break;
                        case KNIGHT:
                            imageView.setImageResource(R.drawable.white_on_black_knight);
                            break;
                        case BISHOP:
                            imageView.setImageResource(R.drawable.white_on_black_bishop);
                            break;
                        case ROOK:
                            imageView.setImageResource(R.drawable.white_on_black_rook);
                            break;
                        case QUEEN:
                            imageView.setImageResource(R.drawable.white_on_black_queen);
                            break;
                        case KING:
                            imageView.setImageResource(R.drawable.white_on_black_king);
                            break;

                    }

                } else {

                    switch (piece) {

                        case PAWN:
                            imageView.setImageResource(R.drawable.white_on_white_pawn);
                            break;
                        case KNIGHT:
                            imageView.setImageResource(R.drawable.white_on_white_knight);
                            break;
                        case BISHOP:
                            imageView.setImageResource(R.drawable.white_on_white_bishop);
                            break;
                        case ROOK:
                            imageView.setImageResource(R.drawable.white_on_white_rook);
                            break;
                        case QUEEN:
                            imageView.setImageResource(R.drawable.white_on_white_queen);
                            break;
                        case KING:
                            imageView.setImageResource(R.drawable.white_on_white_king);
                            break;

                    }

                }

            }

        }

    }

    public void move(View view) {

        final int id = view.getId();
        final ChessSquare cs = findSquare(id);
        final ImageView imageView = (ImageView) findViewById(id);
        ChessSquare[][] temp = board;

        //If no piece is selected then select piece if valid
        if(selected == null) {
            if(cs.getPiece() == null) {
                Toast.makeText(this, "You cannot select an empty square", Toast.LENGTH_SHORT).show();
            }
            else if(blackTurn) {
                if(!cs.getPieceColorBlack()) {
                    Toast.makeText(this, "It is currently blacks turn", Toast.LENGTH_SHORT).show();
                } else{
                    selected = cs;
                    selectedImage = imageView;
                    imageView.getDrawable().setColorFilter(getResources().getColor(R.color.select), PorterDuff.Mode.MULTIPLY );
                }
            } else {
                if(cs.getPieceColorBlack()) {
                    Toast.makeText(this, "It is currently whites turn", Toast.LENGTH_SHORT).show();
                } else {
                    selected = cs;
                    selectedImage = imageView;
                    imageView.getDrawable().setColorFilter(getResources().getColor(R.color.select), PorterDuff.Mode.MULTIPLY );
                }
            }
        } else {

            if(selected == cs) {

                imageView.getDrawable().clearColorFilter();
                selected = null;

            } else {

                calculateMoves(selected);

                if (inMoveList(cs.getCoordinates(), (ArrayList<int[]>) selected.getMoves())) {

                    ChessSquare selectedPiece = board[selected.getCoordinates()[0]][selected.getCoordinates()[1]];
                    ChessSquare currentSquare = board[cs.getCoordinates()[0]][cs.getCoordinates()[1]];
                    currentSquare.setPiece(selectedPiece.getPiece());
                    currentSquare.setPieceColorBlack(selectedPiece.getPieceColorBlack());
                    selectedPiece.deactivateFirstMove();
                    setSquareImage(selectedImage, true, selected.getSquareColorBlack(), null);
                    setSquareImage(imageView, selected.getPieceColorBlack(), cs.getSquareColorBlack(), cs.getPiece());
                    selected.setPiece(null);

                    selectedImage.getDrawable().clearColorFilter();
                    selected = null;
                    selectedImage = null;
                    blackTurn = !blackTurn;


                } else {

                    Toast.makeText(this, "That is not a valid move", Toast.LENGTH_SHORT).show();

                }

            }

        }


    }
}
