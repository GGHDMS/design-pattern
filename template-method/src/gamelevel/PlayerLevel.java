package gamelevel;

public abstract class PlayerLevel {

    public abstract void run();
    public abstract void jump();
    public abstract void turn();

    public abstract void showLevelMessage();

    public void fly() {} //훅 메서드 -> 추가적인

    final public void go(int count) {

        run();

        for (int i = 0; i < count; i++) {
            jump();
        }

        turn();

        fly();
    }
}
