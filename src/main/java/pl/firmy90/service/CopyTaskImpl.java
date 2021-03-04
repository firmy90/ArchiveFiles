package pl.firmy90.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.firmy90.AppProperties;
import pl.firmy90.EmailProperties;
import pl.firmy90.exceptions.MissingFilesException;
import pl.firmy90.service.interfaces.CompressService;
import pl.firmy90.service.interfaces.CopyTask;
import pl.firmy90.service.interfaces.EmailSenderService;
import pl.firmy90.service.interfaces.MoveFileService;

import java.io.File;

@Slf4j
@Component
public class CopyTaskImpl implements CopyTask {
    private static final String OUTPUT_FILENAME = "compressed.zip";
    private final AppProperties appProperties;
    private final CompressService compressService;
    private final MoveFileService moveFileService;
    private final EmailSenderService emailSenderService;
    private final String userDir;
    private final EmailProperties emailProperties;

    @Autowired
    public CopyTaskImpl(AppProperties appProperties, CompressService compressService, MoveFileService moveFileService, EmailSenderService emailSenderService, EmailProperties emailProperties) {
        this.appProperties = appProperties;
        this.compressService = compressService;
        this.moveFileService = moveFileService;
        this.emailSenderService = emailSenderService;
        this.emailProperties = emailProperties;
        this.userDir = System.getProperty("user.dir");
    }


    @Override
    public void run() {
        try {
            File compress = compressService.compress(this.buildSourceDirectory());
            log.info("Skompresowano pliki do folderu: {} ", compress.toString());
        } catch (MissingFilesException e) {
            throw new RuntimeException(e);
        }


        File file = new File(userDir, OUTPUT_FILENAME);
        File move = moveFileService.move(file);
        log.info("Plik {} przeniesiono do: {}\n", file.getAbsolutePath(), move.getAbsolutePath());

        emailSenderService.sendEmail(
                emailProperties.getFrom(),
                emailProperties.getTo(),
                emailProperties.getSubject(),
                emailProperties.getContent() + " " + OUTPUT_FILENAME);
    }

    private File buildSourceDirectory(){
        return new File(System.getProperty("user.dir"), appProperties.getSource());
    }
}
