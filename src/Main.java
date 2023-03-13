import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.Random;

public class Main {

    //creating the lock array for object accessing
    static Semaphore[] lock;
    public static void lockMaker(int objects){
        lock = new Semaphore[objects];
        for(int i = 0; i < lock.length; i++){
            lock[i] = new Semaphore(1);
        }
    }
    public static void main(String[] args) {
        // Get number of domains N
        Random random = new Random();
        int N = 3+random.nextInt(6);
        // Get number of objects M
        int M = 3+random.nextInt(6);
        // Create Access Matrix of size N*(M+N)
        String[][] AM = new String[N][M+N];

        // calling the lockmaker function to create the lock
        // array and initialize all permits to 1.
        lockMaker(M);

        // Populate Access Matrix
        int RorW;
        int dSwitch;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M+N; j++) {
                if (j < M) { // Object R/W access
                    RorW = random.nextInt(4);
                    if (RorW == 0) AM[i][j] = " - ";
                    else if (RorW == 2) AM[i][j] = " R ";
                    else if (RorW == 3) AM[i][j] = " W ";
                    else AM[i][j] = "R/W";
                } else { // Domain switch access
                    dSwitch = random.nextInt(2);
                    if (dSwitch == 0 || j-M == i) AM[i][j] = "  -  "; // Don't allow switching to self
                    else AM[i][j] = "allow";
                }
            }
        }
        System.out.println(N + " domains");
        System.out.println(M + " objects");
        for (int i = 0; i < N; i++) {
            System.out.println("D" + (i+1) + ": " + Arrays.toString(AM[i]));
        }
        // Create object buffers

        // Create domain threads

    }
}