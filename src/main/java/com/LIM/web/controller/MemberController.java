package com.LIM.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.LIM.web.service.MemberService;
import com.LIM.web.vo.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@ResponseBody
	@RequestMapping("/memberInsert")
	public String memberInsert(HttpServletRequest request, HttpServletResponse response) {
		//System.out.println("memberInsert 호출됨");
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		String name=request.getParameter("name");
		
		MemberVO vo = null;
		try {
			vo = new MemberVO(id, pw, name);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//System.out.println(vo);
		
		try {
			memberService.insertMember(vo);
			//System.out.println("회원 가입 완료");
			return "Register success";
		} catch (DataAccessException e) {
			return "Register failed";
		}
	}
	
	@ResponseBody
	@RequestMapping("/loginById")
	public String loginById(HttpServletRequest request, HttpServletResponse response){
		//System.out.println("loginById 호출됨");
		JSONObject json=new JSONObject();
		try {
			String id=request.getParameter("id");
			String pw=request.getParameter("pw");
			MemberVO memberVO = null;
			try {
				memberVO = new MemberVO(id, pw, null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			memberVO=memberService.loginById(memberVO);
			
			if(memberVO == null) {
				json.put("login", "failed");
				return json.toJSONString();
			}
			
			//System.out.println(memberVO);
			
			String name=memberVO.getName();
			json.put("name", name);
			json.put("id", id);
			json.put("pw", pw);
			//System.out.println(name);
			
			
			HttpSession session=request.getSession();
			session.setAttribute("id", id);
			return json.toJSONString();
			
		}catch (DataAccessException e) {
			e.printStackTrace();
			json.put("login", "failed");
			return json.toJSONString();
		}
	}
	
	@ResponseBody
	@RequestMapping("/getById")
	public String getById(HttpServletRequest request, HttpServletResponse response){
		System.out.println("getById 호출됨");
		JSONObject json=new JSONObject();
		try {
			String id=request.getParameter("id");
			
			MemberVO memberVO;
			try {
				memberVO = new MemberVO(id, "", null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			memberVO=memberService.getById(id);
			String name=memberVO.getName();
			String pw=memberVO.getPw();
			String address=memberVO.getAddress();
			json.put("name", name);
			json.put("id", id);
			json.put("address", address);
			
			return json.toJSONString();
			
		}catch (DataAccessException e) {
			e.printStackTrace();
			json.put("login", "failed");
			return json.toJSONString();
		}
	}
	
	@ResponseBody
	@RequestMapping("/deleteMember")
	public String deleteMember(HttpServletRequest request, HttpServletResponse response){
		//System.out.println("deleteMember 호출됨");
		try {
			String id=request.getParameter("id");
			memberService.deleteMember(id);
			//System.out.println();
			return "Delete success";
		}catch (DataAccessException e) {
			e.printStackTrace();
			return "Delete failed";
		}
	}
	
	@ResponseBody
	@RequestMapping("/updateMember")
	public String updateMember(HttpServletRequest request, HttpServletResponse response){
		try {
			String id=request.getParameter("id");
			String pw=request.getParameter("pw");
			String name=request.getParameter("name");
			String address=request.getParameter("address");
			
			MemberVO vo = null;
			try {
				vo = new MemberVO(id, pw, name, address, 0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			memberService.updateMember(vo);
			return "Modify success";
		}catch (DataAccessException e) {
			e.printStackTrace();
			return "Modify failed";
		}
	}
	
	@RequestMapping("/selectAllMemberList")
	public String selectAllMemberList(HttpServletRequest request, HttpServletResponse response){
		try {
			List<MemberVO> list= memberService.selectAllMemberList();
			return "ok.jsp";
		}catch (DataAccessException e) {
			e.printStackTrace();
			return "fail.jsp";
		}
	}
	
}//end class
