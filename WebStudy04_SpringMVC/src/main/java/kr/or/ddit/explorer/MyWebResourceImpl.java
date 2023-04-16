package kr.or.ddit.explorer;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MyWebResourceImpl implements MyWebResource{
	@JsonIgnore
	private transient File adaptee;
	private String url;
	public MyWebResourceImpl(File adaptee, String lp) {
		this.adaptee = adaptee;
		int length = lp.length();
		int lastIdx = lp.lastIndexOf("/");
		if(lastIdx==(length-1)) {
			this.url = lp.substring(0, lastIdx);
		}else {
			this.url = lp;
		}
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public boolean isFolder() {
		return adaptee.isDirectory();
	}

	@Override
	public String getName() {
		return adaptee.getName();
	}

	@Override
	public boolean isFile() {
		return adaptee.isFile();
	}
}
