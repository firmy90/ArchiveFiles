package pl.firmy90.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.firmy90.AppProperties;
import pl.firmy90.service.interfaces.MoveFileService;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@Slf4j
public class MoveFileServiceImpl implements MoveFileService {
    private final Path target;
    private final AppProperties appProperties;


    @Autowired
    public MoveFileServiceImpl(AppProperties appProperties) {
        this.appProperties = appProperties;
        this.target = Paths.get(System.getProperty("user.dir"), appProperties.getDestination());
        log.info("Destination folder: {}",this.target);
    }

    @Override
    public File move(File file) {
        Path source = Paths.get(file.getAbsolutePath());
        try {
            Files.move(source, Paths.get(target.toString(),file.getName()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return new File(String.valueOf(target));
    }
}
