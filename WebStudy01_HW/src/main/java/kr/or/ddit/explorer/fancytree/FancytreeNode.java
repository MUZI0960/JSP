package kr.or.ddit.explorer.fancytree;

public interface FancytreeNode<T> extends Comparable<FancytreeNode>{
	public String getTitle();
	public String getKey();
	public boolean isFolder();
	public boolean isLazy();
	public T getData();
	
	@Override
	default int compareTo(FancytreeNode o) {
		if(isFolder()^o.isFolder()) {
			return isFolder()?-1:1;
		}else {
			return getTitle().compareTo(o.getTitle());
		}
	}
	
}
