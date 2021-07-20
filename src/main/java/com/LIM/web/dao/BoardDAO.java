package com.LIM.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.LIM.web.vo.BoardVO;

@Repository
public class BoardDAO {

	@Autowired
	SqlSession sqlSession;

	public List<BoardVO> listArticles() {
		return sqlSession.selectList("mapper.board.selectAllBoard");
	}

	public void boardWrite(Map<String, Object> articleMap) {
		sqlSession.insert("mapper.board.insert", articleMap);
	}

	public BoardVO viewArticle(int articleNO) {
		return sqlSession.selectOne("mapper.board.selectArticle", articleNO);
	}

	public void insertNewArticle(BoardVO vo) {
		sqlSession.insert("mapper.board.insert", vo);
	}

	public int maxArticleNo() {
		return sqlSession.selectOne("mapper.board.maxArticleNo");
	}

	public void modArticle(BoardVO vo) {
		sqlSession.insert("mapper.board.modArticle", vo);
	}

	public void delArticle(int articleNO) {
		sqlSession.insert("mapper.board.delArticle", articleNO);
	}

}
