package com.battleship.application.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.battleship.application.PlayGameService;
import com.battleship.application.dto.HitOpponentShipDTO;
import com.battleship.application.dto.HitOpponentShipUpdateDTO;
import com.battleship.application.dto.TurnStatusDTO;
import com.battleship.application.util.GenericUtil;
import com.battleship.domain.model.board.Board;
import com.battleship.domain.model.game.Game;
import com.battleship.domain.model.handling.NoBoardAvailableException;
import com.battleship.domain.model.handling.NoGameAvailableException;
import com.battleship.domain.model.player.Player;
import com.battleship.infrastructure.BattleShipBoardRepository;
import com.battleship.infrastructure.BattleShipGameRepository;

/**
 * This class is an implementation of <code>PlayGameService</code> and has
 * method that checks the game and player's turn status.
 * 
 * @author amall3
 *
 */
@Service
@ComponentScan("com.battleship.repository")
public class PlayGameServiceImpl implements PlayGameService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BattleShipGameRepository gameRepository;
	
	@Autowired
	private BattleShipBoardRepository battleShipBoardRepository;
	
	@Autowired
	private GenericUtil genericUtil;

	
	/**
	 * This implements check turn status service. 
	 *  
	 * 
	 * @param checkTurnStatusRequest
	 * @param response
	 * @return GAME_OVER_STATUS if Game is Over else TURN_STATUS_FALSE (false) or TURN_STATUS_TRUE (true)
	 * @throws NoGameAvailableException 
	 * @throws NumberFormatException 
	 */
	public TurnStatusDTO checkPlayersTurnOrGameOverStatus(int gameId, int playerId) throws NoGameAvailableException {

		logger.info("Inside PlayGameServiceImpl.checkPlayersTurnOrGameOverStatus()");
		TurnStatusDTO turnStatusDTO = new TurnStatusDTO();
		Game game = gameRepository.getGameByID(gameId);
		boolean isGameOver = Boolean.FALSE;
		
		for (Player player : game.getGamePlayers()) {
			if (player.getPlayerID() != playerId) {
				//turnStatusDTO.setCoordinates(player.getShip().getHitOrMissCoordinates());
			}
			
			if(player.getShip().isDestroyed()){
				isGameOver = true;
			}
		}
		
		if (isGameOver) {
			turnStatusDTO.setGameOver(isGameOver);
			turnStatusDTO.setTurnStatus(Boolean.FALSE);
			return turnStatusDTO;
			
		} else if(game.getWhoseTurnPlayerId() == playerId) {
			turnStatusDTO.setGameOver(isGameOver);
			turnStatusDTO.setTurnStatus(Boolean.TRUE);
			return turnStatusDTO;
		}		
		turnStatusDTO.setGameOver(isGameOver);
		turnStatusDTO.setTurnStatus(Boolean.FALSE);
		return turnStatusDTO;
	}



	/**
	 * This implements hit ship event update service. 
	 * 
	 * @param <code>HitOpponentShipUpdateDTO</code> hit ship event update details.
	 * @param response
	 * @return List of hit coordinates   
	 * @throws NoGameAvailableException 
	 * @throws NoBoardAvailableException 
	 * @throws NumberFormatException 
	 */

	public HitOpponentShipDTO hitOpponentShipUpdateEvent(HitOpponentShipUpdateDTO hitOpponentShipUpdateDTO) throws NoGameAvailableException, NoBoardAvailableException{

		logger.info("Inside PlayGameServiceImpl.processGame()");
		HitOpponentShipDTO hitOpponentShipDTO = new HitOpponentShipDTO();
		Game game = gameRepository.getGameByID(Integer.valueOf(hitOpponentShipUpdateDTO.getGameId()));
		for (Player player : game.getGamePlayers()) {

			if (Integer.parseInt(hitOpponentShipUpdateDTO.getPlayerId()) != player.getPlayerID()) {
				
				Board board = battleShipBoardRepository.getBoardByID(player.getBoardId());
				if(board.getShipPositionCoordinateList().contains(genericUtil.getCoordinateArray(hitOpponentShipUpdateDTO.getHitCoordinate()))){
					board.getHitCoordinateList().add(genericUtil.getCoordinateArray(hitOpponentShipUpdateDTO.getHitCoordinate()));
				}else{
					board.getMissCoordinateList().add(genericUtil.getCoordinateArray(hitOpponentShipUpdateDTO.getHitCoordinate()));
				}
				
				if(board.getHitCoordinateList().size() == 3){
					player.getShip().setDestroyed(Boolean.TRUE);
				}else{
					game.setWhoseTurnPlayerId(player.getPlayerID());
				}
				
				//hitOpponentShipDTO.setOpponentHitCoordinates(board.getHitCoordinateList());
				//hitOpponentShipDTO.setOpponentMissCoordinates(board.getMissCoordinateList());
		}
		}
		return hitOpponentShipDTO; 
	}
}
