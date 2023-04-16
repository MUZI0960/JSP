package kr.or.ddit.explorer.fancytree;

import java.io.File;

public class FileWrapper implements FancytreeNode<File> {
	private File adaptee;
	public FileWrapper(File adaptee) {
		super();
		this.adaptee = adaptee;
	}

	@Override
	public String getTitle() {
		return adaptee.getName();
	}

	@Override
	public String getKey() {
		return getTitle();
	}

	@Override
	public boolean isFolder() {
		return adaptee.isDirectory();
	}

	@Override
	public boolean isLazy() {
		return isFolder();
	}

	@Override
	public File getData() {
		return adaptee;
	}

	@Override
	public int compareTo(FancytreeNode o) {
		if(isFolder()^o.isFolder()) {
			return isFolder()?-1:1;
		}else {
			return getTitle().toLowerCase().compareTo(o.getTitle().toLowerCase());
		}
	}
}










