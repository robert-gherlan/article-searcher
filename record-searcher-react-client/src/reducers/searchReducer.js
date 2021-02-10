import { SEARCH_RESULT, } from "../actions/types";

const initialState = {
    searchResult: {
        content: []
    }
};

export default function (state = initialState, action) {
    switch (action.type) {
        case SEARCH_RESULT:
            return {
                ...state,
                searchResult: action.payload
            }

        default:
            return state;
    }
}