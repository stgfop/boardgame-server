/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.meeoo.boardgameserver;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.meeoo.boardgameserver.event.NewGameEvent;
import me.meeoo.boardgameserver.event.ServerEvent;
import me.meeoo.otomaton.automata.Otomaton;
import me.meeoo.otomaton.event.Event;
import me.meeoo.otomaton.game.Game;
import me.meeoo.otomaton.game.Player;

public abstract class BoardGameServer<G extends Game<O, P>, O extends Otomaton<G>, P extends Player> {

    protected final GameHolder<G> games;

    public BoardGameServer() {
        games = new GameHolder<>();
    }

    public boolean recieveEvent(Event<G> event) {
        if (event instanceof NewGameEvent) {
            G newGame = ((NewGameEvent<G>) event).newGame();
            games.add(newGame);
            return true;
        } else if (event instanceof ServerEvent) {
            return recieveServerEvent((ServerEvent)event);
        } else {
            return games.get(event).recieveEvent(event);
        }
    }

    public Event<G> recieve(DataInput in) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String className = in.readUTF(); //read the class name of the event

        Event<G> event = (Event<G>) Class.forName(className).newInstance(); //instanciates a new event
        event.read(in); //fill this event from the stream
        recieveEvent(event); //execute the event 
        return event;
    }

    public boolean send(DataOutput out, Event<G> event) throws IOException {
        out.writeUTF(event.getClass().getName());
        event.send(out);
        return true;
    }

    protected abstract boolean recieveServerEvent(ServerEvent serverEvent);

}
