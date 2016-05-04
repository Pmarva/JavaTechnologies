package practice2.tetris;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import practice2.tetris.parts.Block;
import practice2.tetris.parts.Figure;

public class TetrisStart extends Application {
	
	public static void main(String[] args) {
		TetrisStart.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		Pane layout = new Pane();
		
		ObservableList<Node> contents = layout.getChildren();

		Figure figure = new Figure();
		figure.getChildren().add(new Block(1, 0));
		figure.getChildren().add(new Block(0, 0));
		figure.getChildren().add(new Block(-1, 0));
		figure.getChildren().add(new Block(0, 1));
		figure.move(9, 10);
		contents.add(figure);

		Scene scene = new Scene(layout, Block.SIZE * 20, Block.SIZE * 40);

		scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			if (KeyCode.UP.equals(event.getCode())) {
				figure.rotateClockwise();
				//figure.move(0, -1);
				System.out.format("block absolute y: %.0f\n",
						figure.getChildren().get(0).getLocalToSceneTransform().getTy()+Block.SIZE);
				System.out.format("block absolute x: %.0f\n",
						figure.getChildren().get(0).getLocalToSceneTransform().getTx());
			}

            if (KeyCode.LEFT.equals(event.getCode())) {
                figure.move(-1, 0);
            }

            if (KeyCode.RIGHT.equals(event.getCode())) {
                figure.move(1, 0);
            }

            if (KeyCode.DOWN.equals(event.getCode())) {
                figure.move(0, 1);
            }
		});

        Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				figure.move(0,1);
				System.out.println(figure.getLocalToParentTransform().getTy());
				System.out.format("block absolute y: %.0f\n",
                        figure.getChildren().get(0).getLocalToSceneTransform().getTy());
                System.out.format("block absolute x: %.0f\n",
                        figure.getChildren().get(0).getLocalToSceneTransform().getTx());
			}
		}, 1000, 1000);
		
		stage.setOnCloseRequest(e -> System.exit(0));
		stage.setScene(scene);
		stage.show();
	}

}
