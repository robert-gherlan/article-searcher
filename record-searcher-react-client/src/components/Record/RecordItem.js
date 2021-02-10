import React, { PureComponent } from 'react';
import { connect } from "react-redux";
import TextField from '../Field/TextField';
import URLField from '../Field/URLField';
import MultipleValuesField from '../Field/MultipleValuesField';

class RecordItem extends PureComponent {

    render() {
        const { id, doi, coreId, title, metadata, fullText, publisher, downloadUrl, fullTextIdentifier, year, publishedDate, language, authors, subjects, topics, contribuitors } = this.props.record;
        return (
            <div className="card card-body bg-light mb-3">
                <div className="row">
                    <TextField key="title" name="Title" value={title} />
                    <TextField key="metadata" name="Metadata" value={metadata} />
                    {
                        // <TextField key="fullText" name="Full Text" value={fullText} />
                    }
                    <TextField key="id" name="ID" value={id} />
                    <TextField key="doi" name="DOI" value={doi} />
                    <TextField key="coreID" name="CORE_ID" value={coreId} />
                    <TextField key="publisher" name="Publisher" value={publisher} />
                    <URLField key="downloadURL" name="Download URL" value={downloadUrl} />
                    <URLField key="fullTextIdentifier" name="Full Text Identifier" value={fullTextIdentifier} />
                    <TextField key="year" name="Year" value={year} />
                    <TextField key="publishedDate" name="Published Date" value={publishedDate} />
                    <TextField key="language" name="Language" value={language} />
                    <MultipleValuesField key="authors" name="Authors" value={authors} />
                    <MultipleValuesField key="subjects" name="Subjects" value={subjects} />
                    <MultipleValuesField key="topics" name="Topics" value={topics} />
                    <MultipleValuesField key="contribuitors" name="Contribuitors" value={contribuitors} />
                </div>
            </div>
        );
    }
}

RecordItem.propTypes = {

}

export default connect(null, {})(RecordItem);
