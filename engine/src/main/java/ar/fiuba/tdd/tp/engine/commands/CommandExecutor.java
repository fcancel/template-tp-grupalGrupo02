package ar.fiuba.tdd.tp.engine.commands;

/**
 * Created by manuelcruz on 18/05/2016.
 */
public interface CommandExecutor {
    String execute(String[] params);
}