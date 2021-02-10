import React, { Component } from 'react';
import { connect } from "react-redux";

class Footer extends Component {

    render() {
        return (
            <footer className="footer">
                <div className="container">
                    <span className="text-muted"><span className="text-muted">&copy; 2020 Article Searcher</span></span>
                </div>
            </footer>
        );
    }
}

Footer.propTypes = {

}

export default connect(null)(Footer);