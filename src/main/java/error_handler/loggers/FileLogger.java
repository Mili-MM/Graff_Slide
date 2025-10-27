package error_handler.loggers;

import error_handler.ErrorMessage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger extends Logger {


    public FileLogger() {

    }


    @Override
    public void update(ErrorMessage errorMessage) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter("/Users/milenko/Desktop/RAF/Semestar 3/DS/PROJEKAT DS/src/main/resources/log.txt", true);
            bw = new BufferedWriter(fw);
            bw.write(super.formatErrorMessage(errorMessage));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) bw.close();
                if (fw != null) fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
