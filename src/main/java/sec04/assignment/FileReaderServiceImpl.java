package sec04.assignment;

import reactor.core.publisher.Flux;

import java.io.*;
import java.nio.file.Path;

public class FileReaderServiceImpl implements FileReaderService{
    @Override
    public Flux<String> read(Path path) {
        return Flux.create(fluxSink -> {
            fluxSink.onRequest(request -> {
                try {
                    FileInputStream fs= new FileInputStream(path.toString());
                    BufferedReader br = new BufferedReader(new InputStreamReader(fs));
                    for (int i = 0; i < request; i++) {
                        fluxSink.next(br.readLine());
                    }
                } catch (FileNotFoundException e) {
                    fluxSink.error(e);
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    fluxSink.error(e);
                    throw new RuntimeException(e);
                }
                System.out.println("Testing git");
            });
        });
    }
}
