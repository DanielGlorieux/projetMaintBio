package animations;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Fader {
    private FadeTransition fadeTransition;

    public Fader(Node node) {
        fadeTransition = new FadeTransition(Duration.millis(1000));
        fadeTransition.setFromValue(0f);
        fadeTransition.setToValue(1f);
        fadeTransition.setCycleCount(1);
        fadeTransition.setAutoReverse(false);
    }
    public void fade(){
        fadeTransition.playFromStart();
    }
}
