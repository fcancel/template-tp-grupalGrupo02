package ar.fiuba.tdd.tp.motor.game.components.gamestatus;

public class GameStatusWon implements GameStatus {
    @Override
    public String statusMessage() {
        return "You won the game.";
    }
}