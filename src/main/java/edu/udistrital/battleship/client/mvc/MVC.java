package edu.udistrital.battleship.client.mvc;

import edu.udistrital.battleship.client.BattleshipFrame;

public class MVC<M extends Model<V>, V extends View<C>, C extends Controller<M, V>> {

    public MVC(M model, V view, C controller, BattleshipFrame battleshipFrame, String viewId) {
        model.setView(view);
        view.setController(controller);
        controller.setModel(model);
        controller.setView(view);
        battleshipFrame.subscribeView(viewId, view);
    }

}
