import java.util.Random;

public class ScannerWorker implements Scanner {
    int randomNumber;
    public int scannerWorker() {
        Random rand = new Random();
        randomNumber = rand.nextInt(12) + 1;

        return randomNumber;
    }
}
