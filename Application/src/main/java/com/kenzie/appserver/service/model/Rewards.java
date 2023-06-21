package com.kenzie.appserver.service.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Rewards {
    private List<String> rewards;
    private List<String> stickers;
    public Rewards() {
        rewards = new ArrayList<>();
        stickers = new ArrayList<>();
        childsRewards();
        childsStickers();

        // can use Random to call random reward or sticker returned if we want
    }
    private void childsRewards() {
        rewards.add("Pizza party");
        rewards.add("$10 in cash");
        rewards.add("Ice cream treat from any store");
        rewards.add("Small toy from toy store");
        rewards.add("Movie night with friends");
    }
    private void childsStickers() {
        stickers.add("Heart");
        stickers.add("smiley face");
        stickers.add("zebra");
        stickers.add("Thomas the Train");
        stickers.add("Superhero");
    }
    public String getRewardForCompletedTask() {
        Random random = new Random();
        int randomIndex = random.nextInt(rewards.size());
        return  rewards.get(randomIndex);
    }
    public String getStickerForCompletedTask(){
       Random random = new Random();
       int randomIndex = random.nextInt(stickers.size());
       return stickers.get(randomIndex);
        }
    private static Rewards getRewardForCompletedTask(Task task) {
        if (task.isCompleted()) {
            return new Rewards();
            }
            //if nothing
            return null;
        }
        private static Rewards getStickerForCompletedTask(Task task){
        if(task.isCompleted()){
            return new Rewards();
        }
        //if nothing
            return null;
        }
    }

