package kr.or.ddit.explorer;

public interface MyWebResource extends Comparable<MyWebResource>{
	public String getName();
	public String getUrl();
	public boolean isFolder();
	public boolean isFile();
	
	// default 모든 구현체에 실행됨
	@Override
	default int compareTo(MyWebResource o) {
		return getName().compareTo(o.getName());
	}
	
}
