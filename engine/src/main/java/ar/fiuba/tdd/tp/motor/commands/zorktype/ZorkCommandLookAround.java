package ar.fiuba.tdd.tp.motor.commands.zorktype;

import ar.fiuba.tdd.tp.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.motor.game.components.GameComponent;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ZorkCommandLookAround implements GameCommand {
    private ZorkTypeGame game;

    public ZorkCommandLookAround(ZorkTypeGame game) {
        this.game = game;
    }

    @Override
    public String execute() {
        StringBuffer message = new StringBuffer();
        message.append(this.game.getCurrentRoom().getDescription() + " has:");
        for (GameComponent component : this.game.getCurrentRoom().getListOfComponents()) {
            message.append(" A " + component.getDescription() + ".");
        }
        return message.toString();
    }
}
