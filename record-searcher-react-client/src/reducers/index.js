import { combineReducers } from "redux";
import errorReducer from "./errorReducer";
import securityReducer from "./securityReducer";
import searchReducer from "./searchReducer";

export default combineReducers({
    search: searchReducer,
    errors: errorReducer,
    security: securityReducer
});