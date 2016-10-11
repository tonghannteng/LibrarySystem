package data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public abstract class DataAccessBase {

	public static final String OUTPUT_DIR = System.getProperty("user.dir")
			+ "/src/storage";
	public static final String DATE_PATTERN = "MM/dd/yyyy";

	protected <T> void save(List<T> list) {

		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR,
					this.getClass().getSimpleName());
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(list);
		} catch (NoSuchFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	protected <T> List<T> getAllItems() {
		ObjectInputStream in = null;
		ArrayList<T> allMembers = new ArrayList<T>();
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR,
					this.getClass().getSimpleName());
			if (path.toFile().isFile()) {
				in = new ObjectInputStream(Files.newInputStream(path));
				allMembers = (ArrayList<T>) in.readObject();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
		}
		return allMembers;
	}
}