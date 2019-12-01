package com.paschoalini.error;

public class ResourceNotFoundDetails extends ErrorDetails {
	private ResourceNotFoundDetails() {
	}

	public static final class Builder {
		private String title;
		private int status;
		private String detail;
		private Long timestamp;
		private String developerMessage;

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

		public ResourceNotFoundDetails build() {
			ResourceNotFoundDetails rnfDetails = new ResourceNotFoundDetails();
			rnfDetails.setDetail(this.detail);
			rnfDetails.setDeveloperMessage(this.developerMessage);
			rnfDetails.setStatus(this.status);
			rnfDetails.setTimestamp(this.timestamp);
			rnfDetails.setTitle(this.title);
			return rnfDetails;
		}
	}
}
