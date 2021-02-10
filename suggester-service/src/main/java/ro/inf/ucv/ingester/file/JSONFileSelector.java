package ro.inf.ucv.ingester.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.IOUtils;
import org.jooq.lambda.Unchecked;
import org.springframework.stereotype.Component;
import org.tukaani.xz.XZInputStream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JSONFileSelector {

	private static final String SOURCE_DIRECTORY_PATH = "E:\\Core DataSets\\Core Data Sets";

	private static final String DESTINATION_DIRECTORY_PATH = "D:\\Microservices Dizertation\\search-service\\src\\main\\resources\\records";

	private static final int MIN_SIZE_OF_INGESTED_FILE = 1_048_576;

	private static final int MAX_SIZE_OF_INGESTED_FILE = 2_097_152;

	public Stream<File> selectFiles() {
		File destinationDirectory = new File(DESTINATION_DIRECTORY_PATH);
		if (Files.isDirectory(destinationDirectory.toPath()) && !containsAnyFile(destinationDirectory.toPath())) {
			getFilesBasedOnSize().parallelStream()
					.forEach(Unchecked.consumer(file -> unzipAndCopyFile(destinationDirectory, file)));
		}

		return Arrays.stream(destinationDirectory.listFiles());
	}

	private int unzipAndCopyFile(File destinationDirectory, File file) throws IOException {
		File destinationFile = new File(destinationDirectory, file.getName());
		try (InputStream source = new BufferedInputStream(new XZInputStream(new FileInputStream(file)));
				OutputStream destination = new FileOutputStream(destinationFile)) {
			return IOUtils.copy(source, destination);
		} catch (Exception e) {
			log.error("Failed to copy {} file.", file, e);
		}

		return -1;
	}

	private List<File> getFilesBasedOnSize() {
		File sourceSirectory = new File(SOURCE_DIRECTORY_PATH);
		File[] files = sourceSirectory
				.listFiles(f -> f.length() > MIN_SIZE_OF_INGESTED_FILE && f.length() < MAX_SIZE_OF_INGESTED_FILE);
		return Arrays.stream(files).collect(Collectors.toList());
	}

	private static boolean containsAnyFile(Path directory) {
		try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(directory)) {
			return dirStream.iterator().hasNext();
		} catch (IOException e) {
			log.error("Failed to check if {} directory contains any files.", directory, e);
		}

		return false;
	}
}
