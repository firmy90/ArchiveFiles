package pl.firmy90.service.interfaces;

import pl.firmy90.exceptions.MissingFilesException;

import java.io.File;

public interface CompressService {
    File compress(File directory) throws MissingFilesException;
}