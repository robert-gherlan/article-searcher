import React, { Component } from 'react';
import { connect } from "react-redux";

class Dashboard extends Component {

    render() {
        return (
            <main role="main" class="container">
                <div className="jumbotron">
                    <h1 className="display-5">We brings the best articles to you!</h1>
                    <p className="lead">This is an application based on newest technologies, which help the users to explore a wide range of articles with a powerful content.</p>
                    <hr className="my-4" />
                    <p>It provide access to high quality, open access, peer-reviewed journals.</p>
                </div>
            </main>
        );
    }
}

Dashboard.propTypes = {

}

export default connect(null)(Dashboard);