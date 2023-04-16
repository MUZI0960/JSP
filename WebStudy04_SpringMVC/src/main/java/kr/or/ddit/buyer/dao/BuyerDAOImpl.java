package kr.or.ddit.buyer.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.Pagination;

@Repository
public class BuyerDAOImpl implements BuyerDAO {
	@Inject
	private SqlSessionFactory sqlSessionFactory;
	
	@Inject
	private SqlSession sqlSession;

	@Override
	public int insertBuyer(BuyerVO buyer) {
			BuyerDAO mapper = sqlSession.getMapper(BuyerDAO.class);
			return mapper.insertBuyer(buyer);
	}
	
	@Override
	public int selectTotalRecord(Pagination<BuyerVO> pagination) {
			BuyerDAO mapper = sqlSession.getMapper(BuyerDAO.class);
			return mapper.selectTotalRecord(pagination);
	}

	@Override
	public List<BuyerVO> selectBuyerList(Pagination<BuyerVO> pagination) {
			BuyerDAO mapper = sqlSession.getMapper(BuyerDAO.class);
			return mapper.selectBuyerList(pagination);
	}

	@Override
	public BuyerVO selectBuyer(String buyerId) {
			BuyerDAO mapper = sqlSession.getMapper(BuyerDAO.class);
			return mapper.selectBuyer(buyerId);
	}

	@Override
	public int updateBuyer(BuyerVO buyer) {
			BuyerDAO mapper = sqlSession.getMapper(BuyerDAO.class);
			return mapper.updateBuyer(buyer);
	}

}
