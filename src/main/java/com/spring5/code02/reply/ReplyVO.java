package com.spring5.code02.reply;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class ReplyVO {

	private int level;
	private int articleNO;
	private int mainParentNO;
	private int parentNO;
	private String content;
	private Date writeDate;
	private String id;
	private String imageFileName;
	private String deleteFlag;
	
	public ReplyVO() {
		super();
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getArticleNO() {
		return articleNO;
	}

	public void setArticleNO(int articleNO) {
		this.articleNO = articleNO;
	}

	public int getMainParentNO() {
		return mainParentNO;
	}

	public void setMainParentNO(int mainParentNO) {
		this.mainParentNO = mainParentNO;
	}

	public int getParentNO() {
		return parentNO;
	}

	public void setParentNO(int parentNO) {
		this.parentNO = parentNO;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getdeleteFlag() {
		return deleteFlag;
	}

	public void setdeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + articleNO;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((deleteFlag == null) ? 0 : deleteFlag.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imageFileName == null) ? 0 : imageFileName.hashCode());
		result = prime * result + level;
		result = prime * result + mainParentNO;
		result = prime * result + parentNO;
		result = prime * result + ((writeDate == null) ? 0 : writeDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReplyVO other = (ReplyVO) obj;
		if (articleNO != other.articleNO)
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (deleteFlag == null) {
			if (other.deleteFlag != null)
				return false;
		} else if (!deleteFlag.equals(other.deleteFlag))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (imageFileName == null) {
			if (other.imageFileName != null)
				return false;
		} else if (!imageFileName.equals(other.imageFileName))
			return false;
		if (level != other.level)
			return false;
		if (mainParentNO != other.mainParentNO)
			return false;
		if (parentNO != other.parentNO)
			return false;
		if (writeDate == null) {
			if (other.writeDate != null)
				return false;
		} else if (!writeDate.equals(other.writeDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReplyVO [level=" + level + ", articleNO=" + articleNO + ", mainParentNO=" + mainParentNO + ", parentNO="
				+ parentNO + ", content=" + content + ", writeDate=" + writeDate + ", id=" + id + ", imageFileName="
				+ imageFileName + ", deleteFlag=" + deleteFlag + "]";
	}
	
	
	
}
