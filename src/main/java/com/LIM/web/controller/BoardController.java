package com.LIM.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.LIM.web.service.BoardService;
import com.LIM.web.vo.BoardVO;

@Controller
public class BoardController {

	@Autowired
	BoardService boardService;

	/** 모든 글 보기 */
	@ResponseBody
	@RequestMapping(value = "boardList", method = { RequestMethod.GET }, produces = "application/text; charset=utf8")
	public String boardList(HttpServletRequest request, HttpServletResponse response )  {
		ModelAndView mav = new ModelAndView("board");
		List<BoardVO> articlesList = boardService.listArticles();
		
		JSONArray jsonArray = new JSONArray();
		for(BoardVO vo : articlesList) {
			JSONObject o = new JSONObject();
			o.put("level", vo.getLevel());
			o.put("articleNO", vo.getArticleNO());
			o.put("parentNO", vo.getParentNO());
			o.put("title", vo.getTitle());
			o.put("content", vo.getContent());
			o.put("id", vo.getId());
			o.put("writeDate", vo.getWriteDate().toString());
			jsonArray.add(o);
		}
		return jsonArray.toJSONString();
	}

	/** 글 쓰기 화면 얻기 */
	@ResponseBody
	@RequestMapping(value = "boardWriteForm", method = { RequestMethod.GET }, produces = "application/text; charset=utf8")
	public String boardWriteForm(HttpServletRequest request) {
		return "boardWriteForm";
	}
	
	/** 글 쓰기 처리 */
	@ResponseBody
    @RequestMapping(value = "boardWrite", method = { RequestMethod.POST }, produces = "application/text; charset=utf8")
	public String boardWrite(HttpServletRequest request, HttpServletResponse response ) {
		System.out.println("글쓰기 호출");
		String id=request.getParameter("id");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		System.out.println(id+title+content);	
		try {
			int articleNo = boardService.maxArticleNo() + 1;
			
			BoardVO vo=new BoardVO(0,articleNo,0,title, content,null,null, id);
			//(int level, int articleNO, int parentNO, String title, String content,Date writeDate, String imageFileName,String id )
			
			System.out.println(vo.getArticleNO());
			System.out.println(vo.getParentNO());
			System.out.println(vo.getTitle());
			System.out.println(vo.getContent());
			System.out.println(vo.getId());
			boardService.addArticle(vo);
			return "Write Success";
		} catch (Exception e) {
			return "Write failed";
		}
	}

	/**  글 내용 보기 */
	@ResponseBody
	@RequestMapping(value = "viewArticle", method = RequestMethod.GET)
	public String viewArticle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int articleNO= Integer.parseInt(request.getParameter("articleNO")); 
		
		BoardVO boardVO = boardService.viewArticle(articleNO);
		
		JSONObject json = new JSONObject();
		json.put("level", boardVO.getLevel());
		json.put("articleNO", boardVO.getArticleNO());
		json.put("parentNO", boardVO.getParentNO());
		json.put("title", boardVO.getTitle());
		json.put("content", boardVO.getContent());
		json.put("id", boardVO.getId());
		json.put("writeDate", boardVO.getWriteDate().toString());
		
		//System.out.println(json.toString());
		
		return json.toJSONString();
	}
	
	/**  글 수정 */	
	@ResponseBody
    @RequestMapping(value = "modArticle", method = { RequestMethod.POST }, produces = "application/text; charset=utf8")
	public String modArticle(HttpServletRequest request, HttpServletResponse response ) {
		//System.out.println("modArticle 호출");
		int articleNO=Integer.parseInt(request.getParameter("articleNO"));
		String title=request.getParameter("title");
		String content=request.getParameter("content");
			
		try {
			BoardVO vo=new BoardVO(0,articleNO,0,title, content,null,null, null);
			//(int level, int articleNO, int parentNO, String title, String content,Date writeDate, String imageFileName,String id )
			
			boardService.modArticle(vo);
			return "modArticle Success";
		} catch (Exception e) {
			return "modArticle failed";
		}
	}
	
	/**  글 삭제 */
	@ResponseBody
    @RequestMapping(value = "delArticle", method = { RequestMethod.POST }, produces = "application/text; charset=utf8")
	public String delArticle(HttpServletRequest request, HttpServletResponse response ) {
		int articleNO=Integer.parseInt(request.getParameter("articleNO"));
			
		try {
			boardService.delArticle(articleNO);
			return "delArticle Success";
		} catch (Exception e) {
			return "delArticle failed";
		}
	}
	
	/**  댓글 달기 */
	@ResponseBody
    @RequestMapping(value = "replyInsert", method = { RequestMethod.POST }, produces = "application/text; charset=utf8")
	public String replyInsert(HttpServletRequest request, HttpServletResponse response ) {
		//System.out.println("replyInsert 호출");
		String id=request.getParameter("id");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		int parentNO=Integer.parseInt(request.getParameter("parentNO"));
		//System.out.println(id +" : "+ title +" : "+ content +" : "+ parentNO);
		try {
			int articleNo = boardService.maxArticleNo() + 1;
			
			BoardVO vo=new BoardVO(0,articleNo,parentNO,title, content,null,null, id);
			//(int level, int articleNO, int parentNO, String title, String content,Date writeDate, String imageFileName,String id )
			
			boardService.addArticle(vo);
			return "replyInsert Success";
		} catch (Exception e) {
			return "replyInsert failed";
		}
	}
	
	/**  답글 작성 화면 얻기 */
	@RequestMapping(value = "replyForm", method =  RequestMethod.POST)
	private ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		ModelAndView mav = new ModelAndView();
		mav.addObject("parentNO", request.getParameter("parentNO"));
		mav.setViewName("replyForm");
		return mav;
	}

}

