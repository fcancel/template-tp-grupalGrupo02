package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

/**
 * Created by kevin on 27/04/16.
 */
public class ComponentPoison extends GameComponent {
    @Override
    public String getBasicName() {
        return "Poison";
    }

    @Override
    public String whatCanIDo() {
        return "I pollute people.";
    }

    @Override
    public void addedToRoom(ZorkTypeGame game) {
        game.getGamePlayer().setPoisson();
    }
}