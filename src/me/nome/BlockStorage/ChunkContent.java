package me.nome.BlockStorage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ChunkContent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int x;
	protected int z;
	protected Map<String, BlockContent> blocks;
	protected Map<String,Object> content;
	
	public static String getName(int x, int z) {
		return ""+ x + z;
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
	public Map<String, BlockContent> getBlocks() {
		return blocks;
	}
	public void setBlocks(Map<String, BlockContent> blocks) {
		this.blocks = blocks;
	}
	public Map<String, Object> getContent() {
		return content;
	}
	public void setContent(Map<String, Object> content) {
		this.content = content;
	}
	public ChunkContent(int x, int z) {
		this.x=x;
		this.z=z;
		this.blocks=new HashMap<String,BlockContent>();
	}
	
	
	public static String getBlockName(int x, int z, int y) {
		
		return ""+x+z+y;
	}

	
	public void addBlock(int x, int z,  int y,BlockContent block) {
		this.blocks.put(ChunkContent.getBlockName(x,z,y), block);
	}
	
	public BlockContent getOrCreateBlock(int x, int z, int y) {
		if(this.getBlock(x, z, y) == null) {
			this.addBlock(x, z, y, new BlockContent());
		}
		return this.getBlock(x, z, y);
	}
	

	public void removeBlock(int x, int z,  int y) {
		this.blocks.remove(ChunkContent.getBlockName(x, z, y));
	}
	
	public BlockContent getBlock(String name) {
		return this.blocks.get(name);
	}
	
	public BlockContent getBlock(int x, int z,  int y) {
		return this.blocks.get(ChunkContent.getBlockName(x, z, y));
	}
	
	public byte[] serialize() throws IOException {
		ByteArrayOutputStream  os= new ByteArrayOutputStream();
		ObjectOutputStream o = new ObjectOutputStream(os);
		o.writeObject(this);
		o.close();
		os.close();
		return os.toByteArray();
	}
	

	
	public static ChunkContent deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
		ByteArrayInputStream ais= new ByteArrayInputStream(bytes);
		ObjectInputStream ois = new ObjectInputStream(ais);
		ChunkContent bsd = (ChunkContent) ois.readObject();
		ais.close();
		ois.close();
		return bsd;
	}
	
}
