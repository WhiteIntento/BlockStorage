package me.nome.BlockStorage;

import java.io.File;

public class ChunkFile {
	protected String directory;
	protected int x;
	protected int z;
	public String extension="bin";
	
	public ChunkFile(String directory, int x, int z) {
		this.directory=directory;
		this.x=x;
		this.z=z;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}
	
	public String getFileName() {
		return  x+ "-"+z+"."+this.extension;
	}
	
	public String getPath() {
		return this.getDirectory() + File.separator + this.getFileName();
	}
	
	public boolean createDirectoryIfNotExits() {
		File dir = new File(this.getDirectory());
		if(!dir.exists()) {
			dir.mkdirs();
		}
		return dir.canRead() && dir.canWrite();
		
	}
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}
}
