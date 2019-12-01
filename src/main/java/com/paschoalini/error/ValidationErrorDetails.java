package com.paschoalini.error;

public class ValidationErrorDetails extends ErrorDetails {
	private String field;
	private String fieldMessage;
	
	public ValidationErrorDetails() {
	}
	
	public String getField() {
		return field;
	}

	public String getFieldMessage() {
		return fieldMessage;
	}
	
	public static final class Builder {
		private String title;
		private int status;
		private String detail;
		private Long timestamp;
		private String developerMessage;
		private String field;
		private String fieldMessage;

		public Builder() {
		}

		public static Builder newBuilder() {
			return new Builder();
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder status(int status) {
			this.status = status;
			return this;
		}

		public Builder detail(String detail) {
			this.detail = detail;
			return this;
		}

		public Builder timestamp(Long timestamp) {
			this.timestamp = timestamp;
			return this;
		}
		
		public Builder developerMessage(String developerMessage) {
			this.developerMessage = developerMessage;
			return this;
		}

		public Builder field(String field) {
			this.field = field;
			return this;
		}

		public Builder fieldMessage(String fieldMessage) {
			this.fieldMessage = fieldMessage;
			return this;
		}
		
		public ValidationErrorDetails build() {
			ValidationErrorDetails veDetails = new ValidationErrorDetails();
			veDetails.setDetail(this.detail);
			veDetails.setDeveloperMessage(this.developerMessage);
			veDetails.setStatus(this.status);
			veDetails.setTimestamp(this.timestamp);
			veDetails.setTitle(this.title);
			veDetails.field = this.field;
			veDetails.fieldMessage = this.fieldMessage;
			return veDetails;
		}
	}
}
