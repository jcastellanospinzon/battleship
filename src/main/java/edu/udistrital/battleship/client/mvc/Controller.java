package edu.udistrital.battleship.client.mvc;

/**
 * @author Julián Yezid Castellanos Pinzón <i>&lt;jcastellanospinzon@gmail.com&gt;</i>
 */
public abstract class Controller<M extends Model> {

    protected M model;

    public void setModel(M model) {
        this.model = model;
    }

}
