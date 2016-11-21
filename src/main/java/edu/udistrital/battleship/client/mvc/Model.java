package edu.udistrital.battleship.client.mvc;

import edu.udistrital.battleship.business.Business;

/**
 * @author Julián Yezid Castellanos Pinzón <i>&lt;jcastellanospinzon@gmail.com&gt;</i>
 */
public abstract class Model<V extends View> {

    protected Business business;

    protected V view;

    public Model() {
    }

    public void init() {

    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public void setView(V view) {
        this.view = view;
    }

}
