package com.risesun;

public class Test {
    public static void main(String[] args) {
        Game game = new Game();
        game.hurl(1);
        game.hurl(4);

        game.hurl(4);
        game.hurl(5);

        game.hurl(6);
        game.hurl(4);

        game.hurl(5);
        game.hurl(5);


        game.hurl(10);

        game.hurl(0);
        game.hurl(1);

        System.out.println(game.getScore());
    }
}
