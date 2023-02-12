package me.nome.BlockStorage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BlockContent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2421023885325915918L;
	
	
	protected transient int x;
	protected transient int y;
	
	
	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public Map<String, Object> getContent() {
		return content;
	}


	public void setContent(Map<String, Object> content) {
		this.content = content;
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
	
	public String getName() {
		return "" + this.x + this.z + this.y;
	}
	
	public static String getName(int x, int z, int y) {
		
		return ""+x+z+y;
	}

	protected int z;
	protected Map<String,Object> content;
	
	public BlockContent(int x, int z,int y) {
		this.x=x;
		this.z=z;
		this.y=y;
		this.content=new HashMap<String,Object>();
	}
	
	
	public BlockContent() {
		this.content=new HashMap<String,Object>();
	}
	
	
	public Object get(String key) {
		if(this.content.containsKey(key)) {
			return this.content.get(key);
		}
		return null;
	}
	
	public void set(String key, Object value) {
		this.content.put(key, value);
	}
	
	public byte[] serialize() throws IOException {
		ByteArrayOutputStream  os= new ByteArrayOutputStream();
		ObjectOutputStream o = new ObjectOutputStream(os);
		o.writeObject(this);
		o.close();
		os.close();
		return os.toByteArray();
	}
	
	public static BlockContent deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
		ByteArrayInputStream ais= new ByteArrayInputStream(bytes);
		ObjectInputStream ois = new ObjectInputStream(ais);
		BlockContent bsd = (BlockContent) ois.readObject();
		return bsd;
	}
}
