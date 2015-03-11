/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.meeoo.boardgameserver;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import me.meeoo.otomaton.event.Event;
import me.meeoo.otomaton.game.Game;

public class GameHolder<G extends Game> {

    private final Map<String, G> games;
    private final LinkedList<TTL> ttls;

    public GameHolder() {
        this.games = new HashMap<>();
        this.ttls = new LinkedList<>();
    }

    public void add(G game) {
        final String visualHash = game.getVisualHash();
        games.put(visualHash, game);
        compactTTL();
        ttls.add(new TTL(visualHash));
    }

    public G get(String visualHash) {
        return games.get(visualHash);
    }

    public G get(Event<G> event) {
        return games.get(event.getGameVisualHash());
    }
    
    public TTL[] listAll() {
        return ttls.toArray(new TTL[ttls.size()]);
    }
    

    /**
     * Remove all games started more than 12 hours ago
     */
    private void compactTTL() {
        TTL ttl;
        do {
            ttl = ttls.peek();
            if (ttl == null || ttl.timeOfCreation < 12) {
                return;
            }
            ttls.poll();
            games.remove(ttl.key);
        } while (true);//ttl is 12 hours
    }

    /**
     * Time To Live
     */
    public static class TTL {

        private final String key;
        private final long timeOfCreation;

        public TTL(String key) {
            this.key = key;
            this.timeOfCreation = System.currentTimeMillis() / 1000 / 60 / 60;
        }

        public String getKey() {
            return key;
        }

        public long getTimeOfCreation() {
            return timeOfCreation;
        }
        
        
    }

}
