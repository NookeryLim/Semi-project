package com.LIM.web.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.LIM.web.dao.BoardDAO;
import com.LIM.web.vo.BoardVO;

@Service
public class BoardService {
	@Autowired
	BoardDAO boardDAO;

	public List<BoardVO> listArticles() {
		return boardDAO.listArticles();
	}

	public void boardWrite(Map<String, Object> articleMap) {
		boardDAO.boardWrite(articleMap);

	}

	public BoardVO viewArticle(int articleNO) {
		return boardDAO.viewArticle(articleNO);
	}

	public void addArticle(BoardVO vo) {
		boardDAO.insertNewArticle(vo);
	}

	public int maxArticleNo() {
		// TODO Auto-generated method stub
		return boardDAO.maxArticleNo();
	}

	public void modArticle(BoardVO vo) {
		boardDAO.modArticle(vo);

	}

	public void delArticle(int articleNO) {
		boardDAO.delArticle(articleNO);
	}

}
