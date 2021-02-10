import React from 'react';
import { connect } from "react-redux";

class URLField extends React.Component {

    render() {
        const { name, value } = this.props;
        if (value) {
            return (
                <React.Fragment>
                    <dt className="col-sm-2">{name}</dt>
                    <dd className="col-sm-10"><a href={value} target="_blank" rel="noopener noreferrer">{value}</a></dd>
                </React.Fragment>
            );
        }

        return (<React.Fragment />)
    }
}

URLField.propTypes = {

}

export default connect(null, {})(URLField);