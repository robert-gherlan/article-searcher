import React from 'react';
import { connect } from "react-redux";

class MultipleValuesField extends React.Component {

    render() {
        const { name, value } = this.props;
        if (value) {
            return (
                <React.Fragment>
                    <dt className="col-sm-2">{name}</dt>
                    <dd className="col-sm-10"><ul className="list-inline">{value.map(e => <li key={e + window.performance.now()} className="list-inline-item"><span className="badge badge-primary">{e}</span></li>)}</ul></dd>
                </React.Fragment>
            );
        }

        return (<React.Fragment />);
    }
}

MultipleValuesField.propTypes = {

}

export default connect(null, {})(MultipleValuesField);