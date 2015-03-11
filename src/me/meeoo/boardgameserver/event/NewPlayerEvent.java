/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.meeoo.boardgameserver.event;

import me.meeoo.otomaton.game.Game;
import me.meeoo.otomaton.game.Player;

/**
 *
 * @author duncan.berenguier
 */
public abstract class NewPlayerEvent<G extends Game<?, P>, P extends Player> extends ServerEvent<G>{

}