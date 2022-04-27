package sm.sandy.lambda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Lambda2 {

    public static String processFile(CustomBufferedReader bufferedReader) throws IOException{
        try (BufferedReader bufferedReader1 = new BufferedReader(new FileReader("sample.txt"))) {
            return bufferedReader.process(bufferedReader1);
        }
    }
    public static void main(String[] args) throws IOException {
        String fileContents = processFile((bf) -> bf.readLine() + bf.readLine());
        System.out.println(fileContents);
    }

}

@FunctionalInterface
interface CustomBufferedReader {
    String process(BufferedReader bf) throws IOException;
}
