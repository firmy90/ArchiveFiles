package pl.firmy90.service;

import org.springframework.stereotype.Service;
import pl.firmy90.exceptions.MissingFilesException;
import pl.firmy90.service.interfaces.CompressService;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class CompressServiceImpl implements CompressService {
    private static final String OUTPUT_FILENAME = "compressed.zip";

    @Override
    public File compress(File directory) throws MissingFilesException {
        if (!directory.isDirectory()) {
            throw new MissingFilesException("Directory " + directory.getName() + " not found");
        }

        File[] files = directory.listFiles();
        if (files.length == 0) {
            throw new MissingFilesException("Directory " + directory.getName() + " is empty");
        }
        List<File> fileList = Arrays.asList(directory.listFiles());

        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(OUTPUT_FILENAME))) {
            fileList
                    .stream()
                    .filter(File::isFile)
                    .forEach(inputFile ->{
                                try {
                                    zipOut.putNextEntry(new ZipEntry(inputFile.getName()));
                                    try(FileInputStream fis = new FileInputStream(inputFile)){
                                        byte[] bytes = new byte[1024];
                                        int length;
                                        while ((length = fis.read(bytes)) >= 0) {
                                            zipOut.write(bytes, 0, length);
                                        }
                                    }
                                }
                                catch (IOException e) {
                                    throw new UncheckedIOException(e);
                                }
                            }
                    );
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return new File(OUTPUT_FILENAME);
    }
}

