import axios from "axios";
import { SEARCH_RESULT } from "./types";


/**
 * Get all the records found.
 */
export const search = (searchRequest) => async dispatch => {
    const result = await axios.post("/search-service/api/v1/search", searchRequest);
    dispatch({
        type: SEARCH_RESULT,
        payload: result.data
    })
}