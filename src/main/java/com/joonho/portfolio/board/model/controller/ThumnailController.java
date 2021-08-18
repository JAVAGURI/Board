package com.joonho.portfolio.board.model.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.joonho.portfolio.board.model.dto.AttachmentDTO;
import com.joonho.portfolio.board.model.dto.BoardDTO;
import com.joonho.portfolio.board.model.service.BoardService;
import com.joonho.portfolio.member.model.dto.MemberDTO;

@Controller
@RequestMapping("/thumbnail/*")
public class ThumnailController {

	private BoardService service;
	
	public ThumnailController(BoardService service) {
		this.service = service;
	}
	@GetMapping("list")
	public String ThumbnailSelectList(Model model) {
		
	List<BoardDTO> thumbnailList = service.selectThumbnailList();
	
	for(BoardDTO board : thumbnailList) {
		System.out.println(board);
	}
	
	String path = "";
	if(thumbnailList != null) {
		path = "/WEB-INF/views/thumbnail/thumbnailList.jsp";
		model.addAttribute("thumbnailList", thumbnailList);
	} else {
		path = "/WEB-INF/views/common/failed.jsp";
		model.addAttribute("message", "썸네일 게시판 조회 실패!");
	}
	
	return "forward:"+path;
	}
	
	@GetMapping("detail")
	public String ThumbnailDetail(Model model, @RequestParam int no) {
		
		BoardDTO thumbnail = service.selectOneThumbnailBoard(no);
		System.out.println(thumbnail);
		
		String path = "";
		if(thumbnail != null) {
			path = "/WEB-INF/views/thumbnail/thumbnailDetail.jsp";
			model.addAttribute("thumbnail", thumbnail);
		} else {
			path = "/WEB-INF/views/common/failed.jsp";
			model.addAttribute("message", "썸네일 게시판 상세 조회 실패!");
		}
		
		return "forward:"+path;
	}
	
	@GetMapping("insert")
	public String ThumbnailInsert(Model model) {
		
		return "thumbnail/thumbnailInsertForm";
	}
	
	@PostMapping("insert")
	public String ThumbnailInsert(Model model, @RequestParam List<MultipartFile> multiFiles,
			@ModelAttribute BoardDTO boardDTO, HttpSession session, HttpServletRequest request) {
		
		/*
		 * multipart로 전송된 request에 대한 인코딩 처리를 해주어야 하는데 일반 인코딩 필터보다 구현하기 힘들다.
		 * 스프링에서 인코딩 필터를 제공한다. --> web.xml에 필터를 등록
		 */
		System.out.println("multiFile : " + multiFiles);
		
		/* 파일을 저장할 경로 설정 */
		/* RootContext : request.getSession().getServletContext() + getRealPath("이 부분을 찾는다.") */
		String root =  request.getSession().getServletContext().getRealPath("resources");
		
		String filePath = root + "\\uploadFiles";
		
		System.out.println("filePath :" + filePath);
		
		 // 폴더경로만들어주기 자동생성
		File mkdir = new File(filePath);
		if(!mkdir.exists()) {
			mkdir.mkdirs();
		}
		
		List<Map<String,String>> files = new ArrayList<>();
		for(int i = 0 ; i<multiFiles.size();i++) {
			
			/* 파일명 변경 처리 */
			if(multiFiles.get(i).getOriginalFilename().equals("")) {continue;}
			String originFileName = multiFiles.get(i).getOriginalFilename();
			String ext = originFileName.substring(originFileName.lastIndexOf("."));
			String saveName = UUID.randomUUID().toString().replace("-", "") + ext;
			
			/* 파일에 관한 정보 추출 후 보관*/
			Map<String, String> file = new HashMap<String, String>();
			file.put("originFileName", originFileName);
			file.put("saveName", saveName);
			file.put("filePath", filePath);
			
			files.add(file);
		
			}
		
		/* BoardDTO 넣기 */
		boardDTO.setWriter((MemberDTO) session.getAttribute("loginMember"));
		
		int result = service.thumbnailInsert(boardDTO);
		
		/* AttachmentDTO 넣기 */
		int result2 = 0;
		
		if(result>0) {
			for(int i = 0; i<files.size(); i++) {
			AttachmentDTO imgdto = new AttachmentDTO();
			imgdto.setOriginalName(files.get(i).get("originFileName"));
			imgdto.setSavedName(files.get(i).get("saveName"));
			imgdto.setSavePath(files.get(i).get("filePath"));
			imgdto.setThumbnailPath("/resources/uploadFiles/" + files.get(i).get("saveName"));
			
			result2 += service.insertAttachment(imgdto);
			}
		}
		System.out.println("====================");
		System.out.println(result2);
		System.out.println(files.size());
		
		/* 파일을 저장한다. */
		String path = "";
		try {
			if(result2 == files.size()) {
			for(int i = 0; i<files.size(); i++) {
				
				Map<String, String> file = files.get(i);
				
				multiFiles.get(i).transferTo(new File(filePath + "\\" + file.get("saveName")));
				
			}
			}
			 path = "/WEB-INF/views/common/success.jsp";
			model.addAttribute("successCode", "insertThumbnail");
		} catch (Exception e) {
			e.printStackTrace();
			
			/* 실패시 파일 삭제 */
			for(int i = 0; i<files.size(); i++) {
			
			Map<String, String> file = files.get(i);
			
			new File(filePath + "\\" + file.get("saveName")).delete();
			}
			 path = "/WEB-INF/views/common/failed.jsp";
			model.addAttribute("message", "썸네일 게시판 등록 실패!");
		}
		
		return "forward:" + path;
	}


}
