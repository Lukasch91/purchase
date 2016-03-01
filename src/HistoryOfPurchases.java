import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class HistoryOfPurchases {
    String printText;
    public void historyOfJava (String printText){

        try {
        File file = new File("history.txt");

        FileWriter fileWriter = new FileWriter(file.getName(), true);
        BufferedWriter bufferWriter = new BufferedWriter(fileWriter);

        bufferWriter.write(printText);
        bufferWriter.newLine();
        bufferWriter.close();

    } catch (IOException e) {
        e.printStackTrace();
    }
    }

}
