import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
public class FilePipeline {
    public static void main(String[] args) throws IOException {
        int[] a={1,2,3,4};
        Arrays.stream(a).forEach(g->System.out.println(g));
        
        Path p=Paths.get("text.txt");
        Files.lines(p).forEach(l->System.out.println(l));
    }
}