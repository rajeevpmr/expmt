/**
 * 
 */
package com.battleship.interfaces.dto;

import com.battleship.domain.model.player.Player;

/**
 * @author amall3
 *
 */
public class RegisterNewPlayerResponseDTO {

	private Player player;

	private int boardSize;

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player
	 *            the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @return the boardSize
	 */
	public int getBoardSize() {
		return boardSize;
	}

	/**
	 * @param boardSize
	 *            the boardSize to set
	 */
	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}

}
