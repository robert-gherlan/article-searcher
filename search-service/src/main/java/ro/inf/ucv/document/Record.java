package ro.inf.ucv.document;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;
import ro.inf.ucv.dto.RecordDto;

@Data
@Document(indexName = "search_service_records")
public class Record implements Serializable {

	private static final long serialVersionUID = 3215527028271949012L;

	@Id
	private String id;

	@Field(name = "doi")
	private String doi;

	@Field(name = "core_id")
	private String coreId;

	@Field(name = "title", type = FieldType.Keyword)
	private String title;

	@Field(name = "abstract", type = FieldType.Keyword)
	private String metadata;

	@Field(name = "full_text", type = FieldType.Text)
	private String fullText;

	@Field(name = "publisher", type = FieldType.Keyword)
	private String publisher;

	@Field(name = "download_url", type = FieldType.Keyword)
	private String downloadUrl;

	@Field(name = "full_text_identifier", type = FieldType.Keyword)
	private String fullTextIdentifier;

	@Field(name = "year", type = FieldType.Integer)
	private Integer year;

	@Field(name = "published_date", type = FieldType.Keyword)
	private String publishedDate;

	@Field(name = "language", type = FieldType.Keyword)
	private String language;

	@Field(type = FieldType.Nested, includeInParent = true)
	private List<String> authors;

	@Field(type = FieldType.Nested, includeInParent = true)
	private List<String> subjects;

	@Field(type = FieldType.Nested, includeInParent = true)
	private List<String> topics;

	@Field(type = FieldType.Nested, includeInParent = true)
	private List<String> contributors;

	protected Record() {
	}

	public Record(RecordDto recordDto) {
		this.id = recordDto.getId();
		this.doi = recordDto.getDoi();
		this.coreId = recordDto.getCoreId();
		this.title = recordDto.getTitle();
		this.metadata = recordDto.getMetadata();
		this.fullText = recordDto.getFullText();
		this.publisher = recordDto.getPublisher();
		this.downloadUrl = recordDto.getDownloadUrl();
		this.fullTextIdentifier = recordDto.getFullTextIdentifier();
		this.year = recordDto.getYear();
		this.publishedDate = recordDto.getPublishedDate();
		this.language = recordDto.getLanguage();
		this.authors = recordDto.getAuthors();
		this.subjects = recordDto.getSubjects();
		this.topics = recordDto.getTopics();
		this.contributors = recordDto.getContributors();
	}
}
