package shi.qiu.com.org.qiushi.bean;

import java.util.List;

/**
 * @author azhao
 * @date 2018/1/2
 * $desc
 */
public class DemoBean {
	
	private int          error_code;
	private String       reason;
	private List<Result> result;
	
	public class Result {
		private String content;
		private String title;
		private String type;
		private String updatetime;
		
		public String getContent() {
			return content;
		}
		
		public void setContent(String content) {
			this.content = content;
		}
		
		public String getTitle() {
			return title;
		}
		
		public void setTitle(String title) {
			this.title = title;
		}
		
		public String getType() {
			return type;
		}
		
		public void setType(String type) {
			this.type = type;
		}
		
		public String getUpdatetime() {
			return updatetime;
		}
		
		public void setUpdatetime(String updatetime) {
			this.updatetime = updatetime;
		}
	}
	
	public int getError_code() {
		return error_code;
	}
	
	public void setError_code(int error_code) {
		this.error_code = error_code;
	}
	
	public String getReason() {
		return reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public List<Result> getResult() {
		return result;
	}
	
	public void setResult(List<Result> result) {
		this.result = result;
	}
}
