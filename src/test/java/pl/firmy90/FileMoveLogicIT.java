package pl.firmy90;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.firmy90.exceptions.MissingFilesException;
import pl.firmy90.service.interfaces.MoveFileService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
public class FileMoveLogicIT {
    @Autowired
    private MoveFileService moveFileService;
    @TempDir
    File sourceTmpFolder;

    @TempDir
    File destinationTmpFolder;



    @Test
    public void shouldMoveFile() throws MissingFilesException, IOException {
        //given
        assertTrue("Should be a directory ", this.sourceTmpFolder.isDirectory());
        File fileCompressed = new File(sourceTmpFolder,"compressed.zip");

        //when
        File move = moveFileService.move(fileCompressed);

        //then
        assertTrue("File shouldn't exist", Files.notExists(fileCompressed.toPath()));
    }
}
