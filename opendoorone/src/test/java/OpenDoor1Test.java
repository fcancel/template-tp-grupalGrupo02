import ar.fiuba.tdd.tp.engine.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("CPD-START")
public class OpenDoor1Test {
    private final OpenDoor1Builder builder = new OpenDoor1Builder();
    private Game game;
    private static final int PLAYERID = 1;
    private static final String WON_MESSAGE = "Won";

    @Before
    public void init() {
        game = builder.build();
        game.joinPlayer(PLAYERID);
    }

    @Test
    public void winGame() {
        game.command(PLAYERID, "pick key");
        assertEquals(WON_MESSAGE, game.command(PLAYERID, "open door"));
    }

    @Test
    public void alreadyWonGameOpenDoor() {
        game.command(PLAYERID, "pick key");
        game.command(PLAYERID, "open door");
        assertEquals(WON_MESSAGE, game.command(PLAYERID, "open door"));
    }

    @Test
    public void alreadyWonGamePickKey() {
        game.command(PLAYERID, "pick key");
        game.command(PLAYERID, "open door");
        assertEquals(WON_MESSAGE, game.command(PLAYERID, "pick key"));
    }

    @Test
    public void alreadyWonGameLookAround() {
        game.command(PLAYERID, "pick key");
        game.command(PLAYERID, "open door");
        assertEquals(WON_MESSAGE, game.command(PLAYERID, "look around"));
    }

    @Test
    public void alreadyWonGameInvalidCommand() {
        game.command(PLAYERID, "pick key");
        game.command(PLAYERID, "open door");
        assertEquals(WON_MESSAGE, game.command(PLAYERID, "something"));
    }

    @Test
    public void invalidPick() {
        assertEquals("Can't do pick on something", game.command(PLAYERID, "pick something"));
    }

    @Test
    public void invalidCommand() {
        assertEquals("invalid command", game.command(PLAYERID, "something"));
    }

    @Test
    public void cantOpenDoorWithoutKey() {
        assertEquals("Can't do open on door", game.command(PLAYERID, "open door"));
    }

    @Test
    public void lookAround() {
        assertEquals("room1 has door-player1-key", game.command(PLAYERID, "look around"));
    }

    @Test
    public void lookAroundAfterPickAKey() {
        game.command(PLAYERID, "pick key");
        assertEquals("room1 has door-player" + PLAYERID, game.command(PLAYERID, "look around"));
    }

    @Test
    public void openKey() {
        assertEquals("Can't do open on key", game.command(PLAYERID, "open key"));
    }

    @Test
    public void pickDoor() {
        assertEquals("Can't do pick on door", game.command(PLAYERID, "pick door"));
    }

}