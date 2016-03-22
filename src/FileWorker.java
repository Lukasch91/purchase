import java.io.*;
import java.util.ArrayList;


public class FileWorker {

    public void addToFile(String printText) {

        BufferedWriter bufferWriter = null;
        FileWriter fileWriter = null;

        try {
            File file = new File("history.txt");
            fileWriter = new FileWriter(file.getName(), true);
            bufferWriter = new BufferedWriter(fileWriter);

            bufferWriter.write(printText);
            bufferWriter.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeWriteResources(bufferWriter, fileWriter);
        }
    }

    public ArrayList<Double> getFromFile() {
        BufferedReader br = null;
        FileInputStream fstream = null;
        ArrayList<Double> cashBalanceArray = new ArrayList<Double>();

        try {
            fstream = new FileInputStream("history.txt");
            br = new BufferedReader(new InputStreamReader(fstream));

            String strLine;
            while ((strLine = br.readLine()) != null) {
                String[] parts = strLine.split(";");
                String stringName = parts[0];
                String amount = parts[1];
                if (stringName.equals("Withdrawal")) {
                    Double amountDouble = Double.parseDouble(amount);
                    cashBalanceArray.add(amountDouble);
                } else if (stringName.equals("Sum")) {
                    Double amountDouble = Double.parseDouble(amount);
                    cashBalanceArray.add(amountDouble);
                }
            }
        } catch (FileNotFoundException exception) {
            System.out.println("File Not Found");
        } catch (java.io.IOException exception) {
            System.out.println("Error");
        } finally {
            closeReadResources(br, fstream);
        }
        return cashBalanceArray;
    }

    private void closeReadResources(BufferedReader br, FileInputStream fstream) {
        try {
            if (br != null) {
                br.close();
            }
            if (fstream != null) {
                fstream.close();
            }
        } catch (java.io.IOException exception) {
            System.out.println("Error");
        }
    }


    private void closeWriteResources(BufferedWriter bw, FileWriter fw) {
        try {
            if (bw != null) {
                bw.close();
            }
            if (fw != null) {
                fw.close();
            }
        } catch (java.io.IOException exception) {
            System.out.println("Error");
        }
    }
}
