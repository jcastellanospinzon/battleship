package edu.udistrital.battleship.client.mvc;

/**
 * @author Julián Yezid Castellanos Pinzón <i>&lt;jcastellanospinzon@gmail.com&gt;</i>
 */
public abstract class Controller<M extends Model, V extends View> {

    protected M model;

    protected V view;

    public Controller() {
    }

    public void init() {
        model.init();
    }

    public void setModel(M model) {
        this.model = model;
    }

    public void setView(V view) {
        this.view = view;
    }

}
