package kr.or.ddit.prod.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.Pagination;
import kr.or.ddit.vo.ProdVO;

@Repository
public class ProdDAOImpl extends SqlSessionDaoSupport implements ProdDAO {
	
	@Inject
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int insertProd(ProdVO prod) {
			ProdDAO mapper = getSqlSessionTemplate().getMapper(ProdDAO.class);
			return mapper.insertProd(prod);
	}

	@Override
	public int selectTotalRecord(Pagination<ProdVO> pagination) {
			ProdDAO mapper = getSqlSessionTemplate().getMapper(ProdDAO.class);
			return mapper.selectTotalRecord(pagination);
	}
	
	@Override
	public List<ProdVO> selectProdList(Pagination<ProdVO> pagination) {
			ProdDAO mapper = getSqlSessionTemplate().getMapper(ProdDAO.class);
			return mapper.selectProdList(pagination);
	}

	@Override
	public ProdVO selectProd(String prodId) {
			ProdDAO mapper = getSqlSessionTemplate().getMapper(ProdDAO.class);
			return mapper.selectProd(prodId);
	}

	@Override
	public int updateProd(ProdVO prod) {
			ProdDAO mapper = getSqlSessionTemplate().getMapper(ProdDAO.class);
			return mapper.updateProd(prod);
	}

}













