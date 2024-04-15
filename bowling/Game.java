package com.risesun;

import java.util.Arrays;

/**
 * 游戏
 */
public class Game {
    /**
     * 游戏共10轮
     */
    private final Round[] frames = new Round[10];
    private int curHurl = 0;

    public Game() {
        int frameCount = 10;
        Round pre = null;
        for (int i = 0; i < frameCount; i++) {
            frames[i] = new Round();
            frames[i].setNextRound(pre);
            pre = frames[i];
        }
        Round after = null;
        for (int i = 9; i >= 0; i--) {
            frames[i].setNextRound(after);
            after = frames[i];
        }
        frames[9].setFinal(true);
    }

    /**
     * 投递行为
     */
    public void hurl(int bottles) {
        Round cur = frames[curHurl];
        cur.addHurl(bottles);
        if (cur.isFinished()) {
            int nextIndex = ++curHurl;
            cur.setNextRound(frames[nextIndex]);
            frames[nextIndex].setPreRound(cur);
        }
    }

    public int getScore() {
        int i =0;
        for (Round frame : frames) {
            System.out.print("第" + (++i) + "轮得分：" + frame.getScore() );
            System.out.print(",");
            frame.getHurls().stream().forEach(item -> System.out.print(item.getBottles() + ","));
            System.out.println();
        }
        return 0;
    }
}
