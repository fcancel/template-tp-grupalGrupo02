package ar.fiuba.tdd.tp.game.player.action;

import ar.fiuba.tdd.tp.game.commons.constraint.Constraint;
import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.player.Player;
import ar.fiuba.tdd.tp.game.player.action.resolver.ActionAbstract;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Many actions require an object to operate upon: TAKE THE KEY, READ THE BOOK.
 * These are called "transitive" actions, and the object that a transitive action
 * operates upon is called the action's 'direct object.'
 */
public abstract class OneObjectAction extends ActionAbstract {

    private final String pattern;

    protected OneObjectAction(Player player, String pattern) {
        super(player, pattern);
        this.pattern = pattern;
    }

    @Override
    public String execute(String action) {
        final String directObject = action.replaceFirst(pattern, "");
        final Optional<Component> component = this.getDirectObject(directObject);

        if (!component.isPresent()) {
            return "There is no " + directObject + " in the " + this.player.getCurrentContext().getName();
        }

        if (satisfiesActionConstraints() && satisfiesItemConstraints(component.get())) {
            return this.doExecute(component.get());
        }
        return "No se cumple un constraint de la accion o del objeto.";
    }

    private boolean satisfiesItemConstraints(Component component) {
        return component.satisfiesConstraints();
    }

    private Optional<Component> getDirectObject(String directObject) {
        return this.player.getCurrentContext().findComponentByName(directObject);
    }



    protected abstract String doExecute(Component component);


    public Boolean satisfiesActionConstraints() {
        for (Constraint constraint : constraints) {
            if (!constraint.isSatisfied()) {
                return false;
            }
        }
        return true;
    }

    public void setConstraints(List<Constraint> constraints) {
        this.constraints = constraints;
    }
}
