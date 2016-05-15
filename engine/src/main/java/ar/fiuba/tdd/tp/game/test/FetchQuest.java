package ar.fiuba.tdd.tp.game.test;

import ar.fiuba.tdd.tp.game.Game;
import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.component.ComponentImpl;
import ar.fiuba.tdd.tp.game.component.attribute.Attribute;
import ar.fiuba.tdd.tp.game.component.attribute.impl.OpenableImpl;
import ar.fiuba.tdd.tp.game.component.attribute.impl.PickableImpl;
import ar.fiuba.tdd.tp.game.mission.Mission;
import ar.fiuba.tdd.tp.game.mission.MissionImpl;
import ar.fiuba.tdd.tp.game.mission.condition.Condition;
import ar.fiuba.tdd.tp.game.mission.condition.ConditionType;
import ar.fiuba.tdd.tp.game.mission.condition.PlayerHas;
import ar.fiuba.tdd.tp.game.player.Inventory;
import ar.fiuba.tdd.tp.game.player.Player;
import ar.fiuba.tdd.tp.game.player.PlayerImpl;
import ar.fiuba.tdd.tp.game.player.action.Action;
import ar.fiuba.tdd.tp.game.player.action.impl.LookAround;
import ar.fiuba.tdd.tp.game.player.action.impl.Pick;
import ar.fiuba.tdd.tp.game.player.action.impl.WhatToDoWith;
import ar.fiuba.tdd.tp.game.player.action.resolver.ActionResolver;
import ar.fiuba.tdd.tp.game.scenario.context.Context;
import ar.fiuba.tdd.tp.game.scenario.context.ContextImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FetchQuest implements Game {

    private final Player player;
    private final Mission mission;

    public FetchQuest() {
        final List<Component> roomComponents = createRoomComponents();
        final Context playerContext = new ContextImpl("room", roomComponents);

        this.player = getPlayer(playerContext);
        this.mission = createMission();
    }

    private Mission createMission() {
        List<Condition> winConditions = new ArrayList<>();
        List<Condition> loseConditions = new ArrayList<>();

        Condition winCondition = new PlayerHas(this.player, "stick1", ConditionType.HAVE);
        Condition loseCondition = new PlayerHas(this.player, "stick2", ConditionType.NOT_HAVE);

        winConditions.add(winCondition);
        loseConditions.add(loseCondition);

        return new MissionImpl(winConditions, loseConditions);
    }

    private static PlayerImpl getPlayer(Context context) {
        final Inventory inventory = new Inventory(new ArrayList<>(), 10);
        return new PlayerImpl(createActionResolver(inventory, context), inventory);
    }

    private static ActionResolver createActionResolver(Inventory inventory, Context context) {
        final Set<Action> actions = new HashSet<>();

        final LookAround lookAround = new LookAround(context);
        final WhatToDoWith whatToDoWith = new WhatToDoWith(context);
        final Pick pick = new Pick(inventory, context);

        actions.add(lookAround);
        actions.add(whatToDoWith);
        actions.add(pick);

        return new ActionResolver(actions);
    }

    private static Component createStick(String name) {
        List<Attribute> attributes = new ArrayList<>();
        Attribute pickable = new PickableImpl();
        attributes.add(pickable);

        return new ComponentImpl(name, attributes);
    }

    private static List<Component> createRoomComponents() {
        List<Component> roomComponents = new ArrayList<>();
        roomComponents.add(createStick("stick1"));
        roomComponents.add(createStick("stick2"));
        roomComponents.add(createAirplane());
        return roomComponents;
    }

    private static Component createAirplane() {

        List<Attribute> attributes = new ArrayList<>();
        attributes.add(new OpenableImpl());

        return new ComponentImpl("airplane", attributes);
    }

    @Override
    public String doCommand(String command) {
        return this.player.doCommand(command);
    }

    @Override
    public String getName() {
        return "Fetch Quest";
    }

    @Override
    public Boolean isWon() {
        return this.mission.isAccomplished();
    }

    @Override
    public Boolean isLost() {
        return this.mission.isFailed();
    }

    @Override
    public String help() {
        return "GAME COMMANDS:\n open X, close X, look around, consume X, store X in Y, what can i do with X, talk X, pick X";
    }
}
