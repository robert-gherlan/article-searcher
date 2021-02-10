import React, { Component } from 'react';
import { Link } from "react-router-dom";
import RecordItem from './Record/RecordItem';
import { connect } from "react-redux";
import { search } from "../actions/searchActions";
import PropTypes from "prop-types";
import classnames from "classnames";

class Search extends Component {

    constructor() {
        super();
        this.state = {
            query: "",
            pageNumber: 0,
            pageSize: 10,
            errors: {}
        }

        this.onSubmit = this.onSubmit.bind(this);
        this.onChange = this.onChange.bind(this);
    }

    onSubmit(e) {
        e.preventDefault();
        var { query, pageNumber, pageSize } = this.state;
        const searchRequest = {
            query: query,
            pageNumber: pageNumber,
            pageSize: pageSize
        }

        this.props.search(searchRequest);
    }

    pagination = pageNumber => e => {
        e.preventDefault();
        var { query, pageSize } = this.state;
        const searchRequest = {
            query: query,
            pageNumber: pageNumber,
            pageSize: pageSize
        }

        this.props.search(searchRequest);
    };

    onChange(e) {
        this.setState({ [e.target.name]: e.target.value });
    }

    render() {
        const { searchResult } = this.props;
        const { totalElements, number, first, last } = searchResult;
        const { query, pageSize } = this.state;
        console.log("number", number);
        let currentPage = number + 1;
        let nextPage = currentPage + 1;
        let previousPage = currentPage - 1;
        return (
            <div className="container">
                {
                    // Start search form
                }
                <form onSubmit={this.onSubmit}>
                    <div className="form-row">
                        <div className="form-group col-md-9">
                            <input type="text"
                                className="form-control"
                                placeholder="Query"
                                name="query"
                                value={query}
                                onChange={this.onChange}
                            />
                        </div>
                        <div className="form-group col-md-1">
                            <select
                                className="form-control"
                                name="pageSize"
                                value={pageSize}
                                onChange={this.onChange}
                            >
                                <option value="10">10</option>
                                <option value="25">25</option>
                                <option value="50">50</option>
                                <option value="100">100</option>
                            </select>
                        </div>

                        <div className="form-group col-md-2">
                            <button type="submit" className="btn btn-primary"><i className="fas fa-search mr-1" /> Search</button>
                        </div>
                    </div>
                </form>

                {
                    // End search form
                }

                <hr />

                {
                    totalElements && (<div className="alert alert-primary" role="alert">
                        <div className="d-flex justify-content-center">
                            <span>Showing results for <span className="font-weight-bold">{query}</span> (<span className="font-weight-bold">{totalElements}</span> articles found)</span>
                        </div>
                    </div>)
                }

                {
                    // Start pagination display
                }
                {
                    totalElements && (
                        <div className="d-flex justify-content-center">
                            <nav aria-label="pagination">
                                <ul className="pagination">
                                    <li className={classnames("page-item", {
                                        "disabled": first
                                    })}>
                                        <Link to="#" className="page-link" onClick={this.pagination(previousPage)}>Previous</Link>
                                    </li>
                                    <li className={classnames("page-item", {
                                        "disabled": last
                                    })}>
                                        <Link to="#" className="page-link" onClick={this.pagination(nextPage)}>Next</Link>
                                    </li>
                                </ul>
                            </nav>
                        </div>)
                }
                {
                    // End pagination display
                }

                {
                    // Start record display
                }
                {
                    searchResult.content.map(record => (
                        <RecordItem key={record.id} record={record} />)
                    )
                }
                {
                    // End record display
                }
            </div >

        );
    }
}

Search.propTypes = {
    searchResult: PropTypes.object.isRequired,
    search: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
    searchResult: state.search.searchResult
});

export default connect(
    mapStateToProps,
    { search }
)(Search);