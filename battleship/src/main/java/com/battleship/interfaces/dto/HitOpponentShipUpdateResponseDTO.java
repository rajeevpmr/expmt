/**
 * 
 */
package com.battleship.interfaces.dto;

import java.util.List;

/**
 * @author amall3
 *
 */
public class HitOpponentShipUpdateResponseDTO extends ResponseDTO {

	private List<String> opponentHitCoordinates;

	private List<String> opponentMissCoordinates;

	private boolean isGameOver;

	/**
	 * @return the opponentHitCoordinates
	 */
	public List<String> getOpponentHitCoordinates() {
		return opponentHitCoordinates;
	}

	/**
	 * @param opponentHitCoordinates
	 *            the opponentHitCoordinates to set
	 */
	public void setOpponentHitCoordinates(List<String> opponentHitCoordinates) {
		this.opponentHitCoordinates = opponentHitCoordinates;
	}

	public List<String> getOpponentMissCoordinates() {
		return opponentMissCoordinates;
	}

	public void setOpponentMissCoordinates(List<String> opponentMissCoordinates) {
		this.opponentMissCoordinates = opponentMissCoordinates;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

}
