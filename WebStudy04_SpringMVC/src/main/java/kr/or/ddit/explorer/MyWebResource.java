package kr.or.ddit.explorer;

public interface MyWebResource extends Comparable<MyWebResource>{
	public String getName();
	public String getUrl();
	public boolean isFolder();
	public boolean isFile();
	
	@Override
	default int compareTo(MyWebResource o) {
		return getName().compareTo(o.getName());
	}
}
