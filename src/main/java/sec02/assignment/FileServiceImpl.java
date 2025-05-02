package sec02.assignment;

import common.Util;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileServiceImpl implements FileService{
    @Override
    public Mono<String> read(String fileName) throws Exception{
        return Mono.fromCallable(() -> Files.readString(Path.of(fileName)));
    }

    @Override
    public Mono<Void> write(String fileName, String content) {
        return Mono.fromRunnable(() -> {
            try {
                Files.writeString(Path.of(fileName),content);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public Mono<Void> delete(String fileName) {
        return Mono.fromRunnable(() -> {
            try {
                Files.delete(Path.of(fileName));
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        });
    }
}
