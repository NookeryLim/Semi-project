package com.LIM.web.vo;

import java.util.Date;

public class BoardVO {
	private int level, articleNO, parentNO;
	private String title, content, imageFileName, id;
	private Date writeDate;

	public BoardVO(int level, int articleNO, int parentNO, String title, String content, Date writeDate,
			String imageFileName, String id) {
		this(articleNO, parentNO, title, content,writeDate, imageFileName, id);
		this.level = level;
	}

	public BoardVO(int articleNO, int parentNO, String title, String content,Date writeDate, String imageFileName, 
			String id) {
		this(articleNO,title,content,id);
		setParentNO(parentNO);
		setImageFileName(imageFileName);
		setWriteDate(writeDate);
	}

	public BoardVO(String title, String content, String id) {
		setTitle(title);
		setContent(content);;
		setId(id);
	}

	public BoardVO(int articleNO, String title, String content, String id) {
		this(title,content,id);
		setArticleNO(articleNO);
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		if(level != 0) {
			this.level =level;
		} else {
			this.level = 0;
		}
	}

	public int getArticleNO() {
		return articleNO;
	}

	public void setArticleNO(int articleNO) {
		if(articleNO != 0) {
			this.articleNO =articleNO;
		} else {
			this.articleNO = 0;
		}
	}

	public int getParentNO() {
		return parentNO;
	}

	public void setParentNO(int parentNO) {
		if(parentNO != 0) {
			this.parentNO =parentNO;
		} else {
			this.parentNO = 0;
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title){
		if(title != null) {
			this.title = title;
		} else {
			this.title = null;
		}
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		if(content != null) {
			this.content = content;
		} else {
			this.content = null;
		}
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		if(imageFileName != null) {
			this.imageFileName = imageFileName;
		} else {
			this.imageFileName = null;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if(id != null) {
			this.id = id;
		} else {
			this.id = null;
		}
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate){
		if(writeDate != null) {
			this.writeDate = writeDate;
		} else {
			this.writeDate = null;
		}

	}

	@Override
	public String toString() {
		return "ArticleVO [level=" + level + ", articleNO=" + articleNO + ", parentNO=" + parentNO + ", title=" + title
				+ ", content=" + content + ", imageFileName=" + imageFileName + ", id=" + id + ", writeDate="
				+ writeDate + "]";
	}

}
