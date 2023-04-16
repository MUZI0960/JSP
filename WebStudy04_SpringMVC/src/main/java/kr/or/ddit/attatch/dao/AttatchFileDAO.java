package kr.or.ddit.attatch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.AttatchFileVO;

/**
 * 첨부된 형태의 파일 데이터를 관리하기 위한 Persistence Layer
 *
 */
@Mapper
public interface AttatchFileDAO {
	public int insertAttatchList(Map<String, Object> paramMap);
	public AttatchFileVO selectAttatch(AttatchFileVO condition);
	public List<AttatchFileVO> selectAttatchList(int atchId);
	public int deleteAttatch(AttatchFileVO condition);
	public int deleteAttatchList(int atchId);
}
