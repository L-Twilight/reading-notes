package com.risesun;

import java.util.ArrayList;
import java.util.List;

/**
 * 轮
 */
public class Round {
    /**
     * 投掷数据
     */
    private final List<Hurl> hurls = new ArrayList<>();

    private boolean isFinal = false;

    private Round nextRound;
    private Round preRound;

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    /**
     * 获取本轮分数
     */
    public int getScore() {
        if (hurls.isEmpty()) {
            return 0;
        }
        if (isFinal) {
            if (hurls.get(0).getBottles() == 10) {
                return 10 + preRound.getScore() + hurls.get(1).getBottles() + hurls.get(2).getBottles();
            } else if (hurls.get(0).getBottles() + hurls.get(1).getBottles() == 10) {
                return 10 + preRound.getScore() + hurls.get(2).getBottles();
            } else {
                return hurls.get(0).getBottles() + hurls.get(1).getBottles();
            }
        } else {
            if (hurls.get(0).getBottles() == 10) {
                return 10 + preRound.getScore() + getNextBottles(1) + getNextBottles(2);
            } else if (hurls.get(0).getBottles()+ hurls.get(1).getBottles() == 10) {
                return 10 + preRound.getScore() + getNextBottles(1);
            } else {
                return hurls.get(0).getBottles() + hurls.get(1).getBottles() + (preRound == null ? 0 : preRound.getScore());
            }
        }
    }

    private int getNextBottles(int nextHurls) {
        if (nextRound == null) {
            return 0;
        }
        List<Hurl> hurls = nextRound.getHurls();
        if (hurls.size() >= nextHurls) {
            return hurls.get(nextHurls - 1).getBottles();
        } else if (nextRound.isFinished()) {
            return nextRound.nextRound.getNextBottles(nextHurls - 2);
        } else {
            return 0;
        }
    }

    public void addHurl(int bottles) {
        hurls.add(new Hurl(bottles));
    }

    public boolean isFinished() {
        if (hurls.isEmpty()) {
            return false;
        }
        if (isFinal) {
            if (hurls.get(0).getBottles() == 10) { // 全中
                return hurls.size() == 3;
            } else if ((hurls.get(0).getBottles() + hurls.get(1).getBottles()) == 10) { //补中
                return hurls.size() == 3;
            } else {
                return hurls.size() == 2;
            }
        } else {
            if (hurls.get(0).getBottles() == 10) { // 全中
                return true;
            } else return hurls.size() > 1;
        }
    }

    public void setNextRound(Round nextRound) {
        this.nextRound = nextRound;
    }

    public void setPreRound(Round preRound) {
        this.preRound = preRound;
    }

    public List<Hurl> getHurls() {
        return hurls;
    }
}
