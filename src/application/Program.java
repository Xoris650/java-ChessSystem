package application;

import Chess.ChessException;
import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.ChessPosition;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ChessMatch match = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();

        while(!match.getCheckMate()){
            try {
                UI.clearScreen();
                UI.printMatch(match, captured);
                System.out.println();
                System.out.print("Posicao inicial: ");
                ChessPosition source = UI.readChessPosition(sc);
                boolean[][] possibleMoves = match.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(match.getPieces(),possibleMoves);


                System.out.println();
                System.out.print("Destino: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturePiece = match.performChessMove(source, target);
                if(capturePiece != null){
                    captured.add(capturePiece);
                }

                if(match.getPromoted() != null){
                    System.out.print("Digite a peca que deseja para a promocao (B/H/R/Q): ");
                    String type = sc.nextLine().toUpperCase();
                    while(!type.equals("B") && !type.equals("H") && !type.equals("R") && !type.equals("Q")){
                        System.out.print("Valor invalido, digite novamente (B/H/R/Q): ");
                        type = sc.nextLine().toUpperCase();
                    }
                    match.replacePromotedPiece(type);
                }
            }
            catch (ChessException | InputMismatchException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
        UI.clearScreen();
        UI.printMatch(match, captured);


    }
}