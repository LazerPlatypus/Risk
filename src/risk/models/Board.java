package risk.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Board implements Serializable{
	
	//class variables
	private int[] gameState;
	private Map map;
	private ArrayList<Card> undrawnCards;
	private ArrayList<Card> drawnCards;
	private Die[] attackDice;
	private Die[] defenceDice;
	private Player[] players;
	private Player[] playerOrder;
	private Player activePlayer;
	
	//constructors
	public Board(Map map, ArrayList<Card> undrawnCards, ArrayList<Card> drawnCards, Die[] attackDice, Die[] defenceDice,
			Player[] players, Player[] playerOrder, Player activePlayer, int[] gameState) {
		this.map = map;
		this.undrawnCards = undrawnCards;
		this.drawnCards = drawnCards;
		this.attackDice = attackDice;
		this.defenceDice = defenceDice;
		this.players = players;
		this.playerOrder = playerOrder;
		this.activePlayer = activePlayer;
		this.gameState = gameState;
	}
	
	
	//getters
	public Map getMap() {
		return map;
	}
	
	public ArrayList<Card> getUndrawnCards() {
		return undrawnCards;
	}
	
	public ArrayList<Card> getDrawnCards() {
		return drawnCards;
	}
	
	public Die[] getAttackDice() {
		return attackDice;
	}
	
	public Die[] getDefenceDice() {
		return defenceDice;
	}
	
	public Player[] getPlayers() {
		return players;
	}
	
	public Player[] getPlayerOrder() {
		return playerOrder;
	}
	
	public Player getActivePlayer() {
		return activePlayer;
	}
	public int[] getGameState() {
		return gameState;
	}
	
	//setters
	public void setMap(Map map) {
		this.map = map;
	}
	
	public void setUndrawnCards(ArrayList<Card> undrawnCards) {
		this.undrawnCards = undrawnCards;
	}
	
	public void setDrawnCards(ArrayList<Card> drawnCards) {
		this.drawnCards = drawnCards;
	}
	
	public void setAttackDice(Die[] attackDice) {
		this.attackDice = attackDice;
	}
	
	public void setDefenceDice(Die[] defenceDice) {
		this.defenceDice = defenceDice;
	}
	
	public void setPlayers(Player[] players) {
		this.players = players;
	}
	
	public void setPlayerOrder(Player[] playerOrder) {
		this.playerOrder = playerOrder;
	}
	
	public void setActivePlayer(Player activePlayer) {
		this.activePlayer = activePlayer;
	}
	public void setGameState(int[] gameState) {
		this.gameState = gameState;
	}
	
}
