package ro.inf.ucv.dto;

import java.util.List;

import lombok.Data;
import ro.inf.ucv.document.Record;

@Data
public class RecordDto {

	private String id;

	private String doi;

	private String coreId;

	private String title;

	private String metadata;

	private String fullText;

	private String publisher;

	private String downloadUrl;

	private String fullTextIdentifier;

	private Integer year;

	private String publishedDate;

	private String language;

	private List<String> authors;

	private List<String> subjects;

	private List<String> topics;

	private List<String> contributors;

	public RecordDto() {
	}

	public RecordDto(Record record) {
		this.id = record.getId();
		this.doi = record.getDoi();
		this.coreId = record.getCoreId();
		this.title = record.getTitle();
		this.metadata = record.getMetadata();
		this.fullText = record.getFullText();
		this.publisher = record.getPublisher();
		this.downloadUrl = record.getDownloadUrl();
		this.fullTextIdentifier = record.getFullTextIdentifier();
		this.year = record.getYear();
		this.publishedDate = record.getPublishedDate();
		this.language = record.getLanguage();
		this.authors = record.getAuthors();
		this.subjects = record.getSubjects();
		this.topics = record.getTopics();
		this.contributors = record.getContributors();
	}
}
