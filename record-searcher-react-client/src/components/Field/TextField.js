import React from 'react';
import { connect } from "react-redux";

class TextField extends React.Component {

    render() {
        const { name, value } = this.props;
        if (value) {
            return (
                <React.Fragment>
                    <dt className="col-sm-2">{name}</dt>
                    <dd className="col-sm-10">{value}</dd>
                </React.Fragment>
            );
        }

        return (<React.Fragment />);
    }
}

TextField.propTypes = {

}

export default connect(null, {})(TextField);