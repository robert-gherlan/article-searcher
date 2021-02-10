import axios from "axios";
import { GET_ERRORS, SET_CURRENT_USER } from "./types";
import setJWTToken from "../securityUtils/setJWTToken";
import jwt_decode from "jwt-decode";

export const createNewUser = (newUser, history) => async dispatch => {
    try {
        await axios.post("/auth-service/register", newUser);
        history.push("/login");
        dispatch({
            type: GET_ERRORS,
            payload: {}
        })
    } catch (error) {
        dispatch({
            type: GET_ERRORS,
            payload: error.response.data
        })
    }
};

export const login = (loginRequest) => async dispatch => {
    try {
        // Login request
        const result = await axios.post("/auth-service/login", loginRequest);
        // Extract token
        const { token } = result.data;
        // save the token in the local storage
        localStorage.setItem("jwtToken", token);
        // set token in the axios Authorization header
        setJWTToken(token);
        // decode the JWT token
        const decoded = jwt_decode(token);
        // set the current user
        dispatch({
            type: SET_CURRENT_USER,
            payload: decoded
        })
        // Clear all errors
        dispatch({
            type: GET_ERRORS,
            payload: {}
        })
    } catch (error) {
        dispatch({
            type: GET_ERRORS,
            payload: error.response.data
        })
    }
};


export const logout = () => dispatch => {
    localStorage.removeItem("jwtToken");
    setJWTToken(false);
    dispatch({
        type: SET_CURRENT_USER,
        payload: {}
    })
    // Redirect to landing page.
    window.location.href = "/";
}