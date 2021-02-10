package ro.inf.ucv.ingester.file;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import lombok.extern.slf4j.Slf4j;
import ro.inf.ucv.dto.RecordDto;

@Slf4j
public class JSONObjectMapper {

	private JSONObjectMapper() {
	}

	protected static Optional<RecordDto> toRecordDto(JSONObject jsonObject) {
		RecordDto returnedValue = null;
		if (Objects.nonNull(jsonObject)) {
			try {
				String doi = getDoi(jsonObject);
				String title = getTitle(jsonObject);
				String metadata = getMetadata(jsonObject);
				String publisher = getPublisher(jsonObject);
				String language = getLanguage(jsonObject);
				String fullText = getFullText(jsonObject);
				Integer year = getYear(jsonObject);
				String downloadUrl = getDownloadUrl(jsonObject);
				String fullTextIdentifier = getFullTextIdentifier(jsonObject);
				String publishedDate = getPublishedDate(jsonObject);

				JSONArray authorsJsonArray = jsonObject.getJSONArray("authors");
				List<String> authors = convertToStringList(authorsJsonArray);

				JSONArray contributorsJsonArray = jsonObject.getJSONArray("contributors");
				List<String> contributors = convertToStringList(contributorsJsonArray);

				JSONArray topicsJsonArray = jsonObject.getJSONArray("topics");
				List<String> topics = convertToStringList(topicsJsonArray);

				JSONArray subjectsJsonArray = jsonObject.getJSONArray("subjects");
				List<String> subjects = convertToStringList(subjectsJsonArray);

				returnedValue = new RecordDto();
				returnedValue.setDoi(doi);
				returnedValue.setTitle(title);
				returnedValue.setMetadata(metadata);
				returnedValue.setPublisher(publisher);
				returnedValue.setLanguage(language);
				returnedValue.setFullText(fullText);
				returnedValue.setYear(year);
				returnedValue.setDownloadUrl(downloadUrl);
				returnedValue.setFullTextIdentifier(fullTextIdentifier);
				returnedValue.setPublishedDate(publishedDate);
				returnedValue.setAuthors(authors);
				returnedValue.setContributors(contributors);
				returnedValue.setTopics(topics);
				returnedValue.setSubjects(subjects);
			} catch (Exception e) {
				log.warn("Failed to map the record: {}", jsonObject, e);
			}
		}

		return Optional.ofNullable(returnedValue);
	}

	private static Integer getYear(JSONObject jsonObject) {
		try {
			return jsonObject.has("year") && isJSONObjectNonNull(jsonObject, "year") ? jsonObject.getInt("year") : 0;
		} catch (JSONException e) {
			log.warn("Failed to extract year field from {}", jsonObject, e);
		}

		return null;
	}

	private static String getFullTextIdentifier(JSONObject jsonObject) {
		try {
			return getStringValue(jsonObject, "fullTextIdentifier");
		} catch (JSONException e) {
			log.warn("Failed to extract full text identifier from {}", jsonObject, e);
		}
		return null;
	}

	private static String getDownloadUrl(JSONObject jsonObject) {
		try {
			return getStringValue(jsonObject, "downloadUrl");
		} catch (JSONException e) {
			log.warn("Failed to extract download URL field from {}", jsonObject, e);
		}

		return null;
	}

	private static String getFullText(JSONObject jsonObject) {
		try {
			return getStringValue(jsonObject, "rawRecordXml");
		} catch (JSONException e) {
			log.warn("Failed to extract full text field from {}", jsonObject, e);
		}

		return null;
	}

	private static String getPublisher(JSONObject jsonObject) {
		try {
			return getStringValue(jsonObject, "publisher");
		} catch (JSONException e) {
			log.warn("Failed to extract publisher field from {}", jsonObject, e);
		}

		return null;
	}

	private static String getTitle(JSONObject jsonObject) {
		try {
			return getStringValue(jsonObject, "title");
		} catch (JSONException e) {
			log.warn("Failed to extract title field from {}", jsonObject, e);
		}

		return null;
	}

	private static String getMetadata(JSONObject jsonObject) {
		try {
			return getStringValue(jsonObject, "abstract");
		} catch (JSONException e) {
			log.warn("Failed to extract abstract field from {}", jsonObject, e);
		}

		return null;
	}

	private static String getDoi(JSONObject jsonObject) {
		try {
			return getStringValue(jsonObject, "doi");
		} catch (JSONException e) {
			log.warn("Failed to extract doi field from {}", jsonObject, e);
		}

		return null;
	}

	private static String getPublishedDate(JSONObject jsonObject) {
		try {
			return getStringValue(jsonObject, "datePublished");
		} catch (JSONException e) {
			log.warn("Failed to extract date published field from {}", jsonObject, e);
		}

		return null;
	}

	private static boolean isJSONObjectNonNull(JSONObject jsonObject, String key) {
		return !JSONObject.NULL.equals(jsonObject.get(key));
	}

	private static String getStringValue(JSONObject jsonObject, String key) {
		String result = null;
		if (jsonObject.has(key) && isJSONObjectNonNull(jsonObject, key)) {
			result = jsonObject.getString(key);
		}

		return result;
	}

	private static String getLanguage(JSONObject jsonObject) {
		String language = null;
		if (jsonObject.has("language") && isJSONObjectNonNull(jsonObject, "language")) {
			JSONObject languageJsonObject = jsonObject.getJSONObject("language");
			language = languageJsonObject.getString("name");
		}

		return language;
	}

	private static List<String> convertToStringList(JSONArray jsonArray) {
		List<String> result = null;
		if (Objects.nonNull(jsonArray) && !jsonArray.isEmpty()) {
			result = jsonArray.toList().stream().filter(String.class::isInstance).map(String.class::cast)
					.collect(Collectors.toList());
		}

		return result;
	}
}
