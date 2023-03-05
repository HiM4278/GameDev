package extra;

public class GameTimer {
    private long startTime;
    private long elapsedTime;
    private boolean isPaused;
    private long pauseStartTime;

    public GameTimer(long time) {
        startTime = 0;
        elapsedTime = time;
        isPaused = false;
        pauseStartTime = 0;
    }
    public GameTimer(){
        this(0);
    }

    public void start() {
        if (!isPaused) {
            startTime = System.currentTimeMillis();
        } else {
            elapsedTime += System.currentTimeMillis() - pauseStartTime;
            isPaused = false;
        }
    }

    public void stop() {
        if (!isPaused) {
            elapsedTime = System.currentTimeMillis() - startTime;
        }
    }

    public long getElapsedTime() {
        if (isPaused) {
            return pauseStartTime - startTime;
        } else {
            return elapsedTime;
        }
    }

    public void reset() {
        startTime = 0;
        elapsedTime = 0;
        isPaused = false;
        pauseStartTime = 0;
    }

    public void pauseTime() {
        if (!isPaused) {
            pauseStartTime = System.currentTimeMillis();
            isPaused = true;
        }
    }

    public void resumeTime() {
        if (isPaused) {
            startTime += System.currentTimeMillis() - pauseStartTime;
            isPaused = false;
        }
    }
}

