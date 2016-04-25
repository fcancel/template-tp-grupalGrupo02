package ar.fiuba.tdd.tp.motor.commands.zorktype;

import ar.fiuba.tdd.tp.motor.game.components.GameComponent;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ZorkCommandClose extends ZorkCommandActionable {

    public ZorkCommandClose(ZorkTypeGame game, String whatToClose) {
        super(game, whatToClose, "close");
    }

    @Override
    public Boolean componentAction(GameComponent component) {
        return component.close();
    }
}
