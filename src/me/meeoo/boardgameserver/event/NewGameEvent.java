/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.meeoo.boardgameserver.event;

import me.meeoo.otomaton.game.Game;


public abstract class NewGameEvent<G extends Game> extends ServerEvent<G>{

    @Override
    public boolean execute(Game game) {
        throw new UnsupportedOperationException("New game event should not be executed.");
    }
    
    /**
     * Create a new Game
     * @return 
     */
    public abstract G newGame();

}
