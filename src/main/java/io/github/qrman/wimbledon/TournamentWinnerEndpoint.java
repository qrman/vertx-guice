/*
 * The MIT License
 *
 * Copyright 2015 Krzysztof Urman.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.github.qrman.wimbledon;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.ext.web.RoutingContext;
import java.util.Random;

public class TournamentWinnerEndpoint implements Handler<RoutingContext> {

    private final String player1;
    private final String player2;
    private final EventBus eventBus;

    @Inject
    public TournamentWinnerEndpoint(EventBus eventBus, @Named("player1") String player1, @Named("player2") String player2) {
        this.eventBus = eventBus;
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void handle(RoutingContext request) {
        Random tournamentKarma = new Random();
        boolean firstPlayerIsAWinner = tournamentKarma.nextBoolean();

        String winner = firstPlayerIsAWinner ? player1 : player2;
        eventBus.send(winner, "Congratulation Wimbledon Winner!");
        request.response().end(winner);
    }
}
