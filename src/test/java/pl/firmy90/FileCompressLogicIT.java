package pl.firmy90;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.firmy90.exceptions.MissingFilesException;
import pl.firmy90.service.interfaces.CompressService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
public class FileCompressLogicIT {
    @Autowired
    private CompressService compressService;
    @TempDir
    File sourceTmpFolder;


    @Test
    void shouldCompressFilesInGivenFolder() throws IOException, MissingFilesException {
        //given
        assertTrue("Should be a directory ", this.sourceTmpFolder.isDirectory());
        File file = new File(sourceTmpFolder, "file1.txt");
        List<String> lines = Arrays.asList("This is first line", "That second line", "And finally third line");
        Files.write(file.toPath(), lines);

        //when
        File compress = compressService.compress(this.sourceTmpFolder);

        //then
        assertAll(
                () -> assertTrue("File should exist", Files.exists(compress.toPath())),
                () -> assertLinesMatch(lines, Files.readAllLines(file.toPath())));


    }
}
