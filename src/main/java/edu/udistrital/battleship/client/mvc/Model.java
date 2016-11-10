package edu.udistrital.battleship.client.mvc;

/**
 * @author Julián Yezid Castellanos Pinzón <i>&lt;jcastellanospinzon@gmail.com&gt;</i>
 */
public abstract class Model <V extends View> {

    protected V view;

    public void setView(V view) {
        this.view = view;
    }
}
