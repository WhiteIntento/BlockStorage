package me.nome.BlockStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ChunkFileStorage {
	public  static ChunkContent readChunk(ChunkFile file) throws ClassNotFoundException, FileNotFoundException, IOException {
		File f = new File(file.getPath());
		FileInputStream input = new FileInputStream(f);
		byte[] bytes=new byte[(int) f.length()];
		input.read(bytes);
		input.close();
		return ChunkContent.deserialize(bytes);
	}
	
	public static boolean existsChunkFile(ChunkFile file) {
		return new File(file.getPath()).exists();
	}
	
	public static void writeChunk(ChunkFile file,ChunkContent chunk) throws FileNotFoundException, IOException {
		byte[] bytes=chunk.serialize();
		FileOutputStream out = new FileOutputStream(new File(file.getPath()));
		out.write(bytes);
	}
}
