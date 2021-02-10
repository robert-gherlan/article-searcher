package ro.inf.ucv.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

@Data
@Document(indexName = "auth_service_roles")
public class Role implements Serializable {

	private static final long serialVersionUID = -8481925883688607728L;

	@Id
	private String id;

	@Field(type = FieldType.Keyword)
	private String name;

	@Field(type = FieldType.Text)
	private String description;

	protected Role() {
	}

	public Role(String name) {
		this.name = name;
	}
}
