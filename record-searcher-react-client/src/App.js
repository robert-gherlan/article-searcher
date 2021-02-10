import React from "react";
import "./App.css";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import { Provider } from "react-redux";
import store from "./store";
import jwt_decode from "jwt-decode";
import setJWTToken from "./securityUtils/setJWTToken";
import SecuredRoute from "./securityUtils/SecuredRoute";

// Import Bootstrap.
import "bootstrap/dist/css/bootstrap.min.css";
import "jquery/dist/jquery";
import "bootstrap/dist/js/bootstrap";

// Import Components.

import Header from "./components/Layout/Header";
import Landing from "./components/Layout/Landing";
import Login from "./components/UserManagement/Login";
import Register from "./components/UserManagement/Register";
import { SET_CURRENT_USER } from "./actions/types";
import { logout } from "./actions/securityActions"
import Search from "./components/Search";
import Footer from "./components/Layout/Footer";
import Dashboard from "./components/Dashboard";

const jwtToken = localStorage.jwtToken;
if (jwtToken) {
  setJWTToken(jwtToken);
  const decoded_jwtToken = jwt_decode(jwtToken);
  store.dispatch({
    type: SET_CURRENT_USER,
    payload: decoded_jwtToken
  })

  const currentTime = Date.now() / 1000;
  if (decoded_jwtToken.exp < currentTime) {
    store.dispatch(logout());
  }
}

function App() {
  return (
    <Provider store={store}>
      <Router>
        <div className="App">
          <Header />
          {
            // Public routes
          }
          <Route exact path="/" component={Landing} />
          <Route exact path="/dashboard" component={Dashboard} />
          <Route exact path="/login" component={Login} />
          <Route exact path="/register" component={Register} />
          {
            // Private routes(secured)
          }
          <Switch>
            {
              <SecuredRoute exact path="/search" component={Search} />
            }
          </Switch>
          <Footer />
        </div>
      </Router>
    </Provider>
  );
}

export default App;