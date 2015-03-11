/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.meeoo.boardgameserver.event;

import me.meeoo.otomaton.event.Event;
import me.meeoo.otomaton.game.Game;

/**
 *
 * @author duncan.berenguier
 */
public abstract class ServerEvent<G extends Game> extends Event<G> {

}
