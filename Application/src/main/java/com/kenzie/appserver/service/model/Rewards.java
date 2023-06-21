package com.kenzie.appserver.service.model;

public class Reward {
    private String name;
    private String description;
    public Reward(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public static void giveChildReward(Child child, Task task){
        Reward reward = getRewardForCompletedTask(task);
        if (reward != null) {
            System.out.println("Awesome job," + child.getUsername() + "You earned a reward"
                    + reward.getName());
        } else {
            System.out.println("No reward for this task");
        }
    }
    private static Reward getRewardForCompletedTask(Task task) {
        if (task.getTaskId().equals("task1")) {
            return new Reward("Puppy Sticker Pack", "A 10 pack of stickers from the Dollar Tree");
        } else if
        (task.getTaskId().equals("task2")) {
            return new Reward("Train Sticker", "Get 5 train stickers from Mom");
            }
            //if nothing
            return null;
        }
    }

