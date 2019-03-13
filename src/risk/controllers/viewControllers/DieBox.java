package risk.controllers.viewControllers;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DieBox {

	public static void display(int[] attackOutcomes, int[] defenceOutcomes, int attackersDead, int defendersDead) {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initStyle(StageStyle.UTILITY);
		stage.setTitle("Attack Outcome");
		stage.setMinWidth(250);
		stage.setMinHeight(375);
		Button confirmButton = new Button("OK");
		//makes a labda action to close the stage
		confirmButton.setOnAction(EventHandler -> {
			stage.close();
		});
		Label attackersDied = new Label("Attackers Lost: "+attackersDead);
		Label defendersDied = new Label("Defenders Lost: "+defendersDead);
		attackersDied.getStyleClass().add("contentText");
		defendersDied.getStyleClass().add("contentText");
		attackersDied.setWrapText(true);
		defendersDied.setWrapText(true);
		attackersDied.setAlignment(Pos.CENTER);
		defendersDied.setAlignment(Pos.CENTER);
		ImageView[] dice = new ImageView[6];
		for (int i = 0; i < dice.length; i++) {
			dice[i] = new ImageView(); 
			dice[i].setFitWidth(75);
			dice[i].setFitHeight(75);
			dice[i].setPreserveRatio(true);
		}
		for (int i = 0; i < attackOutcomes.length; i++) {
			dice[i].setImage(new Image("/risk/views/Images/AttackDieFace"+(attackOutcomes[i])+".png"));
		}
		for (int i = 0; i < defenceOutcomes.length; i++) {
			dice[i+3].setImage(new Image("/risk/views/Images/DefenceDieFace"+(defenceOutcomes[i])+".png"));
		}
		VBox attackDiceBox = new VBox(10);
		attackDiceBox.setAlignment(Pos.CENTER);
		attackDiceBox.setMinHeight(200);
		attackDiceBox.setMinWidth(100);
		attackDiceBox.getChildren().addAll(dice[0],dice[1],dice[2],attackersDied);
		VBox defenceDiceBox = new VBox(10);
		defenceDiceBox.setAlignment(Pos.CENTER);
		defenceDiceBox.setMinHeight(200);
		defenceDiceBox.setMinWidth(100);
		defenceDiceBox.getChildren().addAll(dice[3],dice[4],dice[5],defendersDied);
		HBox diceBox = new HBox();
		diceBox.setAlignment(Pos.CENTER);
		diceBox.setMinWidth(240);
		diceBox.setMinHeight(240);
		diceBox.getChildren().addAll(attackDiceBox, defenceDiceBox);
		VBox layout = new VBox(20);
		layout.setAlignment(Pos.CENTER);
		layout.setMinHeight(270);
		layout.setMinWidth(250);
		layout.getChildren().addAll(diceBox, confirmButton);
		Scene scene = new Scene(layout);
		scene.getStylesheets().add("/risk/views/FXML/style1.css");
		stage.setScene(scene);
		stage.showAndWait();
	}
}
